package org.example.structure.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class CheckArchitectureApplicationTest {
    JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .importPath(Paths.get(".."));

    @Test
    void applicationLayerDependenciesAreRespected() {
        ArchRule rule = layeredArchitecture().consideringOnlyDependenciesInLayers()
                .withOptionalLayers(true)
                .layer("Business").definedBy("..business..")
                .layer("Application").definedBy("..application..")
                .layer("Infrastructure").definedBy("..infrastructure..")
                .whereLayer("Application").mayOnlyBeAccessedByLayers("Business")
                .whereLayer("Application").mayOnlyBeAccessedByLayers("Infrastructure")
                .because("Le module 'Application' peut utiliser uniquement des objets des modules 'Business' et 'infrastructure'");
        rule.check(importedClasses);
    }
}
