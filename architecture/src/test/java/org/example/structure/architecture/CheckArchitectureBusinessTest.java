package org.example.structure.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class CheckArchitectureBusinessTest {
    /*JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.example.structure");*/
    JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .importPath(Paths.get(".."));

    @Test
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
    void shouldBeInProperPackage_AndBeInterfaces() {
        ArchRule rule = classes().that()
                .resideInAPackage("..adapters..")
                .should().beInterfaces().allowEmptyShould(true)
                .because("Ce package doit uniquement contenir des interfaces !!");
        rule.check(importedClasses);
    }

    @Test
    void shouldBeImplementTheInterfaceInPackageAdaptersIn() {
        ArchRule rule = classes().that()
                .resideInAPackage("..business.services")
                .should().implement(resideInAPackage("..adapters.in"))
                .allowEmptyShould(true)
                .because("Cette classe n'implémente pas une interface contenue dans le package adapters.in");
        rule.check(importedClasses);
    }
    //todo ajout la régles : les regles de gestion ne peut être utilisées que dans les classes contenues dans ..business.services
    @Test
    void test(){
        ArchRule rule = classes().that().resideInAPackage("..business.rules").should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAPackage("..business.services");

        rule.check(importedClasses);
    }
}
