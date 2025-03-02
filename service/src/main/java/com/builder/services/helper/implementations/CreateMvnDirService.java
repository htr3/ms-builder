package com.builder.services.helper.implementations;



import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateMvnDirService {

    private static final Logger logger = LoggerFactory.getLogger(CreateMvnDirService.class);


    public static void createMvnDirectory(String outputDir) throws IOException {
        String mvnDir = outputDir + "/.mvn";
        String wrapperDir = mvnDir + "/wrapper";
        FileUtils.forceMkdir(new File(wrapperDir));

        // Create maven-wrapper.properties
        String wrapperPropertiesContent =
                "wrapperVersion=3.3.2\n" +
                        "distributionType=only-script\n" +
                        "distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.9/apache-maven-3.9.9-bin.zip";
        FileUtils.writeStringToFile(new File(wrapperDir + "/maven-wrapper.properties"), wrapperPropertiesContent, String.valueOf(StandardCharsets.UTF_8));

        // Create maven-wrapper.jar (dummy file, actual jar will be downloaded by the wrapper script)
        FileUtils.touch(new File(wrapperDir + "/maven-wrapper.jar"));

        // Create mvnw (Unix script)
        String mvnwContent = "#!/bin/sh\n" +
                "export MAVEN_PROJECTBASEDIR=${MAVEN_BASEDIR:-\"$PWD\"}\n" +
                "export MAVEN_OPTS=\"-Xmx1024m\"\n" +
                "exec \"$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar\" \"$@\"";
        FileUtils.writeStringToFile(new File(outputDir + "/mvnw"), mvnwContent, String.valueOf(StandardCharsets.UTF_8));
        new File(outputDir + "/mvnw").setExecutable(true);

        // Create mvnw.cmd (Windows script)
        String mvnwCmdContent = "@echo off\n" +
                "set MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%\n" +
                "if \"%MAVEN_BASEDIR%\"==\"\" set MAVEN_PROJECTBASEDIR=%CD%\n" +
                "set MAVEN_OPTS=-Xmx1024m\n" +
                "java -jar \"%MAVEN_PROJECTBASEDIR%\\.mvn\\wrapper\\maven-wrapper.jar\" %*";
        FileUtils.writeStringToFile(new File(outputDir + "/mvnw.cmd"), mvnwCmdContent, String.valueOf(StandardCharsets.UTF_8));

        logger.info("Maven Wrapper created successfully at: {}", mvnDir);
    }
}
