package com.builder.services.helper.implementations;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GeneratePomService {
    private static final Logger logger = LoggerFactory.getLogger(GeneratePomService.class);


   public static String generateModulePom(String module, String outputDir) throws IOException {
        String pomContent;

        switch (module) {
            case "api":
                pomContent = generateApiPom();
                break;
            case "services":
                pomContent = generateServicesPom();
                break;
            case "persistence":
                pomContent = generatePersistencePom();
                break;
            case "gateway":
                pomContent = generateGatewayPom();
                break;
            case "asyncmessages":
                pomContent = generateAsyncMessagesPom();
                break;
            case "resources":
                pomContent = generateResourcesPom();
                break;
            case "test":
                pomContent = generateTestPom();
                break;
            case "exe":
                pomContent = generateExePom();
                break;
            case "parent":
                pomContent = generateParentPom();
                break;
            default:
                throw new IllegalArgumentException("Unknown module: " + module);
        }

        // Write the pom.xml file for the module
        FileUtils.writeStringToFile(new File(outputDir + "/" + module + "/pom.xml"), pomContent, String.valueOf(StandardCharsets.UTF_8));
        logger.info("Generated {}/pom.xml successfully.", module);
       return pomContent;
   }

    // api pom complete
    private static String generateApiPom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "        <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "     <groupId>com.builder.api</groupId>\n" +
                "    <artifactId>api</artifactId>\n\n" +
                "     <packaging>jar</packaging>\n" +
                "    <dependencies>\n" +
                "        <!-- Jakarta Dependencies -->\n" +
                "        <dependency>\n" +
                "            <groupId>jakarta.validation</groupId>\n" +
                "            <artifactId>jakarta.validation-api</artifactId>\n" +
                "            <version>3.0.2</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>jakarta.annotation</groupId>\n" +
                "            <artifactId>jakarta.annotation-api</artifactId>\n" +
                "            <version>2.1.1</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>jakarta.inject</groupId>\n" +
                "            <artifactId>jakarta.inject-api</artifactId>\n" +
                "            <version>2.0.1</version>\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>jakarta.servlet</groupId>\n" +
                "            <artifactId>jakarta.servlet-api</artifactId>\n" +
                "            <version>5.0.0</version>\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>jakarta.ws.rs</groupId>\n" +
                "            <artifactId>jakarta.ws.rs-api</artifactId>\n" +
                "            <version>3.1.0</version>\n" +
                "            <scope>provided</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- Spring Dependencies -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-web</artifactId>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-context</artifactId>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-autoconfigure</artifactId>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.apache.tomcat.embed</groupId>\n" +
                "            <artifactId>tomcat-embed-core</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- Sisu Guice -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.sonatype.sisu</groupId>\n" +
                "            <artifactId>sisu-guice</artifactId>\n" +
                "            <version>3.2.3</version>\n" +
                "            <scope>compile</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- OpenFeign Dependencies -->\n" +
                "        <dependency>\n" +
                "            <groupId>io.github.openfeign</groupId>\n" +
                "            <artifactId>feign-core</artifactId>\n" +
                "            <version>12.4</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>io.github.openfeign</groupId>\n" +
                "            <artifactId>feign-jackson</artifactId>\n" +
                "            <version>12.4</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.cloud</groupId>\n" +
                "            <artifactId>spring-cloud-starter-openfeign</artifactId>\n" +
                "            <version>4.0.4</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Spring Retry -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.retry</groupId>\n" +
                "            <artifactId>spring-retry</artifactId>\n" +
                "            <version>2.0.3</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Testing Dependencies -->\n" +
                "        <dependency>\n" +
                "            <groupId>junit</groupId>\n" +
                "            <artifactId>junit</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.junit.jupiter</groupId>\n" +
                "            <artifactId>junit-jupiter</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-test</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- OpenAPI/Swagger Dependencies -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springdoc</groupId>\n" +
                "            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>\n" +
                "            <version>2.3.0</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.openapitools</groupId>\n" +
                "            <artifactId>jackson-databind-nullable</artifactId>\n" +
                "            <version>0.2.6</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.yaml</groupId>\n" +
                "            <artifactId>snakeyaml</artifactId>\n" +
                "            <version>2.0</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- SLF4J for Logging -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-api</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-simple</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "            <!-- Maven Compiler Plugin -->\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-compiler-plugin</artifactId>\n" +
                "                <version>3.11.0</version>\n" +
                "                <configuration>\n" +
                "                    <source>17</source>\n" +
                "                    <target>17</target>\n" +
                "                </configuration>\n" +
                "            </plugin>\n\n" +
                "            <!-- Maven Resources Plugin -->\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-resources-plugin</artifactId>\n" +
                "                <version>3.3.1</version>\n" +
                "                <executions>\n" +
                "                    <execution>\n" +
                "                        <id>copy-generated-sources</id>\n" +
                "                        <phase>process-resources</phase>\n" +
                "                        <goals>\n" +
                "                            <goal>copy-resources</goal>\n" +
                "                        </goals>\n" +
                "                        <configuration>\n" +
                "                            <outputDirectory>${project.build.directory}/generated-sources</outputDirectory>\n" +
                "                            <resources>\n" +
                "                                <resource>\n" +
                "                                    <directory>${project.basedir}/target/generated-sources</directory>\n" +
                "                                    <includes>\n" +
                "                                        <include>**/*</include>\n" +
                "                                    </includes>\n" +
                "                                </resource>\n" +
                "                            </resources>\n" +
                "                        </configuration>\n" +
                "                    </execution>\n" +
                "                </executions>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "</project>";
    }

    // generate service pom complete
    private static String generateServicesPom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "         <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "        <groupId>com.builder.services</groupId>\n" +
                "    <artifactId>services</artifactId>\n\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Boot Starter -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- Spring Context -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework</groupId>\n" +
                "            <artifactId>spring-context</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- SLF4J for Logging -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-api</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-simple</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "</project>";
    }

    // persistance pom complete
    private static String generatePersistencePom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
              "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "         <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "    <groupId>com.builder.persistence</groupId>\n" +
                "    <artifactId>persistence</artifactId>\n\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Data JPA -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-data-jpa</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- H2 Database (for testing) -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.h2database</groupId>\n" +
                "            <artifactId>h2</artifactId>\n" +
                "            <scope>runtime</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- SLF4J for Logging -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-api</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-simple</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <!-- Services Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.services</groupId>\n" +
                "            <artifactId>services</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "    </dependencies>\n" +
                "</project>";
    }

    //  gateway complete
    private static String generateGatewayPom() {
    return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
            "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
            "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
            "    <modelVersion>4.0.0</modelVersion>\n" +
            "    <parent>\n" +
            "        <groupId>com.msname.ms</groupId>\n" +
            "        <artifactId>parent</artifactId>\n" +
            "        <version>0.0.1-SNAPSHOT</version>\n" +
            "         <relativePath>../parent</relativePath>\n" +
            "    </parent>\n" +
            "     <groupId>com.builder.gateway</groupId>\n" +
            "    <artifactId>gateway</artifactId>\n\n" +
            "    <dependencies>\n" +
            "        <!-- Spring Cloud Gateway -->\n" +
            "        <dependency>\n" +
            "            <groupId>org.springframework.cloud</groupId>\n" +
            "            <artifactId>spring-cloud-starter-gateway</artifactId>\n" +
            "        </dependency>\n\n" +
            "        <!-- Spring Boot Starter Web -->\n" +
            "        <dependency>\n" +
            "            <groupId>org.springframework.boot</groupId>\n" +
            "            <artifactId>spring-boot-starter-web</artifactId>\n" +
            "        </dependency>\n\n" +
            "        <!-- Spring Boot Actuator -->\n" +
            "        <dependency>\n" +
            "            <groupId>org.springframework.boot</groupId>\n" +
            "            <artifactId>spring-boot-starter-actuator</artifactId>\n" +
            "        </dependency>\n\n" +
            "        <!-- SLF4J for Logging -->\n" +
            "        <dependency>\n" +
            "            <groupId>org.slf4j</groupId>\n" +
            "            <artifactId>slf4j-api</artifactId>\n" +
            "            <version>2.0.7</version>\n" +
            "        </dependency>\n" +
            "        <dependency>\n" +
            "            <groupId>org.slf4j</groupId>\n" +
            "            <artifactId>slf4j-simple</artifactId>\n" +
            "            <version>2.0.7</version>\n" +
            "        </dependency>\n" +
            "        <!-- Services Module -->\n" +
            "        <dependency>\n" +
            "            <groupId>com.builder.services</groupId>\n" +
            "            <artifactId>services</artifactId>\n" +
            "            <version>${project.version}</version>\n" +
            "        </dependency>\n\n" +
            "        <!-- Services Module -->\n" +
            "        <dependency>\n" +
            "            <groupId>com.builder.api</groupId>\n" +
            "            <artifactId>api</artifactId>\n" +
            "            <version>${project.version}</version>\n" +
            "        </dependency>\n\n" +
            "        <!-- Services Module -->\n" +
            "        <dependency>\n" +
            "            <groupId>com.builder.asyncMessages</groupId>\n" +
            "            <artifactId>asyncMessages</artifactId>\n" +
            "            <version>${project.version}</version>\n" +
            "        </dependency>\n\n" +
            "    </dependencies>\n" +
            "</project>";
}

    //  generateAsyncMessagesPom complete
    private static String generateAsyncMessagesPom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "         <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "    <groupId>com.builder.asyncmessages</groupId>\n" +
                "    <artifactId>asyncmessages</artifactId>\n\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Boot Starter for AMQP (RabbitMQ) -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-amqp</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- Spring Kafka -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.kafka</groupId>\n" +
                "            <artifactId>spring-kafka</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- SLF4J for Logging -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-api</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-simple</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n" +
                "</project>";
    }

    //  resources complete
    private static String generateResourcesPom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
              "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "         <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "    <groupId>com.builder.resources</groupId>\n" +
                "    <artifactId>resources</artifactId>\n\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Boot Starter -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- SLF4J for Logging -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-api</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-simple</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <!-- Services Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.services</groupId>\n" +
                "            <artifactId>services</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- api Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.api</groupId>\n" +
                "            <artifactId>api</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- gateway Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.gateway</groupId>\n" +
                "            <artifactId>gateway</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "    </dependencies>\n" +
                "</project>";
    }

    // test complete
    private static String generateTestPom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
              "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "         <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "    <groupId>com.builder.test</groupId>\n" +
                "    <artifactId>test</artifactId>\n\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Boot Starter Test -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-test</artifactId>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- JUnit 5 -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.junit.jupiter</groupId>\n" +
                "            <artifactId>junit-jupiter-api</artifactId>\n" +
                "            <version>5.10.0</version>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.junit.jupiter</groupId>\n" +
                "            <artifactId>junit-jupiter-engine</artifactId>\n" +
                "            <version>5.10.0</version>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- Mockito for Testing -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.mockito</groupId>\n" +
                "            <artifactId>mockito-core</artifactId>\n" +
                "            <version>5.5.0</version>\n" +
                "            <scope>test</scope>\n" +
                "        </dependency>\n\n" +
                "        <!-- API Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.api</groupId>\n" +
                "            <artifactId>api</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Services Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.services</groupId>\n" +
                "            <artifactId>services</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Persistence Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.persistence</groupId>\n" +
                "            <artifactId>persistence</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Gateway Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.gateway</groupId>\n" +
                "            <artifactId>gateway</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Async Messages Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.asyncmessages</groupId>\n" +
                "            <artifactId>asyncmessages</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Resources Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.resources</groupId>\n" +
                "            <artifactId>resources</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "            <!-- Maven Surefire Plugin for Testing -->\n" +
                "            <plugin>\n" +
                "                <groupId>org.apache.maven.plugins</groupId>\n" +
                "                <artifactId>maven-surefire-plugin</artifactId>\n" +
                "                <version>3.1.2</version>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "</project>";
    }

    //    generateExePom exe complete
    private static String generateExePom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
              "    <parent>\n" +
                "        <groupId>com.msname.ms</groupId>\n" +
                "        <artifactId>parent</artifactId>\n" +
                "        <version>0.0.1-SNAPSHOT</version>\n" +
                "         <relativePath>../parent</relativePath>\n" +
                "    </parent>\n" +
                "    <groupId>com.builder.exe</groupId>\n" +
                "    <artifactId>exe</artifactId>\n\n" +
                "    <dependencies>\n" +
                "        <!-- Spring Boot Starter Web -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-web</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- Spring Boot Actuator -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-actuator</artifactId>\n" +
                "        </dependency>\n\n" +
                "        <!-- SLF4J for Logging -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-api</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n" +
                "        <dependency>\n" +
                "            <groupId>org.slf4j</groupId>\n" +
                "            <artifactId>slf4j-simple</artifactId>\n" +
                "            <version>2.0.7</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Resources Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.resources</groupId>\n" +
                "            <artifactId>resources</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Services Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.services</groupId>\n" +
                "            <artifactId>services</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Persistence Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.persistence</groupId>\n" +
                "            <artifactId>persistence</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n\n" +
                "        <!-- Gateway Module -->\n" +
                "        <dependency>\n" +
                "            <groupId>com.builder.gateway</groupId>\n" +
                "            <artifactId>gateway</artifactId>\n" +
                "            <version>${project.version}</version>\n" +
                "        </dependency>\n" +
                "    </dependencies>\n\n" +
                "    <build>\n" +
                "        <plugins>\n" +
                "            <!-- Spring Boot Maven Plugin -->\n" +
                "            <plugin>\n" +
                "                <groupId>org.springframework.boot</groupId>\n" +
                "                <artifactId>spring-boot-maven-plugin</artifactId>\n" +
                "            </plugin>\n" +
                "        </plugins>\n" +
                "    </build>\n" +
                "</project>";
    }

    //  parent pom complete
    private static String generateParentPom() {
        return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "    <groupId>com.msname.ms</groupId>\n" +
                "    <artifactId>parent</artifactId>\n" +
                "    <version>0.0.1-SNAPSHOT</version>\n" +
                "   <name>${project.groupId}:${project.artifactId}</name>\n" +
                "    <packaging>pom</packaging>\n\n" +
                "    <description>Parent POM for MS Builder Microservices</description>\n\n" +
                "    <properties>\n" +
                "        <java.version>17</java.version>\n" +
                "        <spring-boot.version>3.1.0</spring-boot.version>\n" +
                "        <spring-cloud.version>2022.0.3</spring-cloud.version>\n" +
                "        <slf4j.version>2.0.7</slf4j.version>\n" +
                "        <jakarta.servlet.version>4.0.4</jakarta.servlet.version>\n" +
                "        <maven.compiler.source>${java.version}</maven.compiler.source>\n" +
                "        <maven.compiler.target>${java.version}</maven.compiler.target>\n" +
                "    </properties>\n\n" +
                "    <dependencyManagement>\n" +
                "        <dependencies>\n" +
                "            <!-- Spring Boot Dependencies -->\n" +
                "            <dependency>\n" +
                "                <groupId>org.springframework.boot</groupId>\n" +
                "                <artifactId>spring-boot-dependencies</artifactId>\n" +
                "                <version>${spring-boot.version}</version>\n" +
                "                <type>pom</type>\n" +
                "                <scope>import</scope>\n" +
                "            </dependency>\n\n" +
                "            <!-- Spring Cloud Dependencies -->\n" +
                "            <dependency>\n" +
                "                <groupId>org.springframework.cloud</groupId>\n" +
                "                <artifactId>spring-cloud-dependencies</artifactId>\n" +
                "                <version>${spring-cloud.version}</version>\n" +
                "                <type>pom</type>\n" +
                "                <scope>import</scope>\n" +
                "            </dependency>\n\n" +
                "            <!-- SLF4J for Logging -->\n" +
                "            <dependency>\n" +
                "                <groupId>org.slf4j</groupId>\n" +
                "                <artifactId>slf4j-api</artifactId>\n" +
                "                <version>${slf4j.version}</version>\n" +
                "            </dependency>\n" +
                "            <dependency>\n" +
                "                <groupId>org.slf4j</groupId>\n" +
                "                <artifactId>slf4j-simple</artifactId>\n" +
                "                <version>${slf4j.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Jakarta Servlet API -->\n" +
                "            <dependency>\n" +
                "                <groupId>jakarta.servlet</groupId>\n" +
                "                <artifactId>jakarta.servlet-api</artifactId>\n" +
                "                <version>${jakarta.servlet.version}</version>\n" +
                "                <scope>provided</scope>\n" +
                "            </dependency>\n\n" +
                "            <!-- API Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.api</groupId>\n" +
                "                <artifactId>api</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Services Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.services</groupId>\n" +
                "                <artifactId>services</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Persistence Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.persistence</groupId>\n" +
                "                <artifactId>persistence</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Gateway Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.gateway</groupId>\n" +
                "                <artifactId>gateway</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Async Messages Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.asyncmessages</groupId>\n" +
                "                <artifactId>asyncmessages</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Resources Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.resources</groupId>\n" +
                "                <artifactId>resources</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Test Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.test</groupId>\n" +
                "                <artifactId>test</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n\n" +
                "            <!-- Executable Module -->\n" +
                "            <dependency>\n" +
                "                <groupId>com.builder.exe</groupId>\n" +
                "                <artifactId>exe</artifactId>\n" +
                "                <version>${project.version}</version>\n" +
                "            </dependency>\n" +
                "        </dependencies>\n" +
                "    </dependencyManagement>\n\n" +
                "    <build>\n" +
                "        <pluginManagement>\n" +
                "            <plugins>\n" +
                "                <!-- Maven Compiler Plugin -->\n" +
                "                <plugin>\n" +
                "                    <groupId>org.apache.maven.plugins</groupId>\n" +
                "                    <artifactId>maven-compiler-plugin</artifactId>\n" +
                "                    <version>3.11.0</version>\n" +
                "                    <configuration>\n" +
                "                        <source>${java.version}</source>\n" +
                "                        <target>${java.version}</target>\n" +
                "                    </configuration>\n" +
                "                </plugin>\n\n" +
                "                <!-- Spring Boot Maven Plugin -->\n" +
                "                <plugin>\n" +
                "                    <groupId>org.springframework.boot</groupId>\n" +
                "                    <artifactId>spring-boot-maven-plugin</artifactId>\n" +
                "                    <version>${spring-boot.version}</version>\n" +
                "                </plugin>\n" +
                "            </plugins>\n" +
                "        </pluginManagement>\n" +
                "    </build>\n\n" +
                "</project>";
    }

}
