package org.example.structure.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideOutsideOfPackages;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
@AnalyzeClasses(
        packages = "org.example.structure",
        importOptions = { ImportOption.DoNotIncludeTests.class})
public class ControleArchitectureBusinessTest {
    @ArchTest
    public static final ArchRule business_layer_dependencies_are_respected = layeredArchitecture().consideringOnlyDependenciesInLayers()
            .withOptionalLayers(true)
            .layer("Business").definedBy("..business..")
            .layer("Application").definedBy("..application..")
            .layer("Infrastructure").definedBy("..infrastructure..")
            .whereLayer("Business").mayNotAccessAnyLayer()
            .because("Le module 'business' ne doit pas avoir de dependence avec les autres modules");

    @ArchTest()
    static final ArchRule shouldBeInProperPackage_AndBeInterfaces =
            classes().that()
                    .resideInAPackage("..adapters..")
                    .should().beInterfaces().allowEmptyShould(true).because("Ce package doit uniquement contenir des interfaces !!");

    @ArchTest()
    static final ArchRule test =
            classes().that()
                    .resideInAPackage("..business.services")
                    .should().onlyHaveDependentClassesThat(resideInAPackage("..adapters.in")).allowEmptyShould(true).because("----- !!");
}
