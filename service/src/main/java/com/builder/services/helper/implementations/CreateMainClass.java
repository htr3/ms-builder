package com.builder.services.helper.implementations;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateMainClass {

    private static final Logger logger = LoggerFactory.getLogger(CreateMainClass.class);

    public static void createMainClass(String outputDir, String moduleName) throws IOException {
        if (outputDir == null || outputDir.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty");
        }
        if (moduleName == null || moduleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Module name cannot be null or empty");
        }

        // Define the path for the Main class
        String mainClassPath = outputDir + "/" + moduleName + "/src/main/java/com/msname/" + moduleName + "/Main.java";
        File mainClassFile = new File(mainClassPath);

        // Create the directory structure if it doesn't exist
        FileUtils.forceMkdir(mainClassFile.getParentFile());

        // Define the content of the Main class
        String mainClassContent = "package com.msname." + moduleName + ";\n\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "import org.springframework.boot.context.properties.EnableConfigurationProperties;\n\n" +
                "@SpringBootApplication(scanBasePackages = {\"com.vishal.ms.api\"})\n" +
                "@EnableConfigurationProperties\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.setProperty(\"spring.main.web-application-type\", \"reactive\");\n" +
                "        SpringApplication.run(Main.class, args);\n" +
                "    }\n" +
                "}";

        // Write the Main class content to the file
        FileUtils.writeStringToFile(mainClassFile, mainClassContent, String.valueOf(StandardCharsets.UTF_8));
        logger.info("Main class created successfully at: {}", mainClassPath);

        // Create application.properties file to enforce reactive web application type
        String propertiesFilePath = outputDir + "/" + moduleName + "/src/main/resources/application.properties";
        String propertiesContent = "spring.main.web-application-type=reactive\n";
        FileUtils.writeStringToFile(new File(propertiesFilePath), propertiesContent, String.valueOf(StandardCharsets.UTF_8));
        logger.info("application.properties file created successfully at: {}", propertiesFilePath);
    }

    public static void createMainClassForExeModule(String outputDir) throws IOException {
        if (outputDir == null || outputDir.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty");
        }

        // Create Main class only for the exe module
        createMainClass(outputDir, "exe");
    }
}