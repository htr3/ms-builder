package com.builder.services.helper.implementations;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import static com.builder.services.helper.implementations.GeneratePomService.generateModulePom;

public class OtherModulesGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(OtherModulesGeneratorService.class);

    private static final String[] MODULES = {"api", "services", "persistence", "gateway", "asyncmessages", "resources", "test", "exe", "parent"};

    private static final String BASE_PACKAGE = "com.vishal.ms";


    public static void generateOtherModules(String outputDir) throws IOException {
        // List of modules to generate
//        String[] modules = {"api", "services", "persistence", "gateway", "asyncmessages", "resources", "test", "exe", "parent"};
        if (outputDir == null || outputDir.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty");
        }

        for (String module : MODULES) {
            // Create module directory
            String moduleDir = outputDir + "/" + module;
            FileUtils.forceMkdir(new File(moduleDir));
            System.out.println("Module directory created: " + moduleDir);

            // Generate pom.xml for the module
            String pomContent = generateModulePom(module, outputDir);
            File pomFile = new File(moduleDir + "/pom.xml");
            FileUtils.writeStringToFile(pomFile, pomContent, "UTF-8");
            System.out.println("pom.xml generated: " + pomFile.getAbsolutePath());

            // Create src/main/java and src/test/java directories
            FileUtils.forceMkdir(new File(moduleDir + "/src/main/java"));
            FileUtils.forceMkdir(new File(moduleDir + "/src/test/java"));
            System.out.println("Source directories created for module: " + module);

            // Create src/main/resources and src/test/resources directories (if needed)
            if (module.equals("resources")) {
                FileUtils.forceMkdir(new File(moduleDir + "/src/main/resources"));
                FileUtils.forceMkdir(new File(moduleDir + "/src/test/resources"));
                System.out.println("Resource directories created for module: " + module);
            }
        }

        logger.info("Other modules generated successfully at: {}", outputDir);
    }


//    private static String generatePom(String module) {
//        return String.format(
//                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
//                        "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
//                        "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
//                        "    <modelVersion>4.0.0</modelVersion>\n" +
//                        "    <parent>\n" +
//                        "        <groupId>%s</groupId>\n" +
//                        "        <artifactId>msbuilder-parent</artifactId>\n" +
//                        "        <version>1.0.0</version>\n" +
//                        "    </parent>\n" +
//                        "    <artifactId>%s</artifactId>\n" +
//                        "</project>",
//                BASE_PACKAGE, module
//        );
//    }
}


