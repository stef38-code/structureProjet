package org.model.projet.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
@AnalyzeClasses(
        packages = "org.model.projet",
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
}
