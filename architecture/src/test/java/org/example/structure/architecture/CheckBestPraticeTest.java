package org.example.structure.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;

import java.nio.file.Paths;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
class CheckBestPraticeTest {
    JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .importPath(Paths.get(".."))
            ;
    @Test
    @DisplayName("Logger doit être final")
    void loggerShouldBeFinal() {
        ArchRule rule = fields()
                .that().haveRawType(Logger.class)
                .should().bePrivate().andShould().beFinal()
                .andShould().notBeStatic()
                .allowEmptyShould(true)
                .because("Le logger doit être private final\n Exemple: private final Logger log = LoggerFactory.getLogger(XXXX.class);");
        rule.check(importedClasses);
    }
}
