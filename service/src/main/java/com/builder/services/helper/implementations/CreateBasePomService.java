package com.builder.services.helper.implementations;

import org.apache.catalina.Container;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateBasePomService {

    private static final Logger logger = LoggerFactory.getLogger(CreateBasePomService.class);



    public static void createBasePom(String outputDir) throws IOException {

        String rootPomContent =
                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                        "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                        "    <modelVersion>4.0.0</modelVersion>\n" +
                        "    <groupId>com.msname.ms</groupId>\n" +
                        "    <artifactId>msbuilder</artifactId>\n" +
                        "     <version>0.0.1-SNAPSHOT</version>\n" +
                        "    <packaging>pom</packaging>\n" +
                        "    <name>${project.groupId}:${project.artifactId}</name>\n" +
                        "    <modules>\n" +
                        "        <!-- List all modules here -->\n" +
                        "        <module>api</module>\n" +
                        "        <module>services</module>\n" +
                        "        <module>persistence</module>\n" +
                        "        <module>gateway</module>\n" +
                        "        <module>asyncmessages</module>\n" +
                        "        <module>resources</module>\n" +
                        "        <module>test</module>\n" +
                        "        <module>exe</module>\n" +
                        "        <module>parent</module>\n" +
                        "    </modules>\n" +
                        "    <properties>\n" +
                        "        <maven.compiler.source>17</maven.compiler.source>\n" +
                        "        <maven.compiler.target>17</maven.compiler.target>\n" +
                        "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                        "    </properties>\n" +
                        "    <dependencyManagement>\n" +
                        "        <dependencies>\n" +
                        "            <!-- Add common dependencies here -->\n" +
                        "        </dependencies>\n" +
                        "    </dependencyManagement>\n" +
                        "</project>";

        FileUtils.writeStringToFile(new File(outputDir + "/pom.xml"), rootPomContent, String.valueOf(StandardCharsets.UTF_8));
        logger.info("Root pom.xml created successfully at: {}", outputDir);


    }

}
