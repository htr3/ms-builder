package com.builder.services.helper.implementations;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CreateFolderStructureService {

    private static final Logger logger = LoggerFactory.getLogger(CreateFolderStructureService.class);

    public static void createFolderStructure(String outputDir) throws IOException {
        if (outputDir == null || outputDir.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty");
        }

        // Create the base directory
        FileUtils.forceMkdir(new File(outputDir));
        logger.info("Base directory created at: {}", outputDir);

        // Create subdirectories for each module
        String[] modules = {"api", "services", "persistence", "gateway", "asyncmessages", "resources", "test", "exe", "parent"};
        for (String module : modules) {
            // Create module directory
            File moduleDir = new File(outputDir + "/" + module);
            FileUtils.forceMkdir(moduleDir);
            logger.info("Module directory created: {}", moduleDir.getAbsolutePath());

            // Create src/main/java base package structure
            String mainJavaPackagePath = moduleDir + "/src/main/java/com/msname/" + module;
            FileUtils.forceMkdir(new File(mainJavaPackagePath));
            logger.info("Main Java package structure created: {}", mainJavaPackagePath);

            // Create src/main/resources directory
            String mainResourcesPath = moduleDir + "/src/main/resources";
            FileUtils.forceMkdir(new File(mainResourcesPath));
            logger.info("Main resources directory created: {}", mainResourcesPath);

            // For the test module, create src/test/java and src/test/resources
            if (module.equals("test")) {
                // Create src/test/java base package structure
                String testJavaPackagePath = moduleDir + "/src/test/java/com/msname/" + module;
                FileUtils.forceMkdir(new File(testJavaPackagePath));
                logger.info("Test Java package structure created: {}", testJavaPackagePath);

                // Create src/test/resources directory
                String testResourcesPath = moduleDir + "/src/test/resources";
                FileUtils.forceMkdir(new File(testResourcesPath));
                logger.info("Test resources directory created: {}", testResourcesPath);
            }
        }

        CreateMainClass.createMainClassForExeModule(outputDir);

        logger.info("Folder structure created successfully at: {}", outputDir);
    }
}