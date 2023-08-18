package org.example.structure.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class CheckArchitectureBusinessTest {

    JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .importPath(Paths.get(".."))
            ;

    @Test
    @DisplayName("Le module 'business' indépendance")
    void businessLayerDependenciesAreRespected() {
        ArchRule rule = layeredArchitecture().consideringOnlyDependenciesInLayers()
                .withOptionalLayers(true)
                .layer("Business").definedBy("..business..")
                .layer("Application").definedBy("..application..")
                .layer("Infrastructure").definedBy("..infrastructure..")
                .whereLayer("Business").mayNotAccessAnyLayer()
                .because("Le module 'business' ne doit pas avoir de dependence avec les autres modules");
        rule.check(importedClasses);
    }

    @Test
    @DisplayName("..adapters.* doit uniquement contenir des interfaces")
    void shouldBeInProperPackage_AndBeInterfaces() {
        ArchRule rule = classes().that()
                .resideInAPackage("..adapters..")
                .should().beInterfaces().allowEmptyShould(true)
                .because("Ce package doit uniquement contenir des interfaces !!");
        rule.check(importedClasses);
    }

    @Test
    @DisplayName("business.services doit implémente une interface contenue dans le package adapters.in")
    void shouldBeImplementTheInterfaceInPackageAdaptersIn() {
        ArchRule rule = classes().that()
                .resideInAPackage("..business.services")
                .should().implement(resideInAPackage("..adapters.in"))
                .allowEmptyShould(true)
                .because("Cette classe n'implémente pas une interface contenue dans le package adapters.in");
        rule.check(importedClasses);
    }

    @Test
    @DisplayName("les regles de gestion ne peut être utilisées que dans les classes contenues dans ..business.services ou ..business.rules")
    void shouldBeUseTheRuleInPackageBusinessServices_orBusinessRules(){

        ArchRule rule = classes().that().resideInAPackage("..business.rules").should()
                .onlyHaveDependentClassesThat()
                .resideInAnyPackage("..business.services","..business.rules")
                .because("les règles de gestion ne peut être utilisées que dans les classes contenues dans ..business.services");

        rule.check(importedClasses);
    }

}
