package com.builder.services.helper.implementations;

import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ApiModuleGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(ApiModuleGeneratorService.class);

    private static final String BASE_PACKAGE = "com.vishal.ms";

    public static void generateApiModule(OpenAPI openAPI, String outputDir) throws IOException {
        if (openAPI == null) {
            throw new IllegalArgumentException("OpenAPI specification cannot be null");
        }
        if (outputDir == null || outputDir.trim().isEmpty()) {
            throw new IllegalArgumentException("Output directory cannot be null or empty");
        }

        // Save the Swagger file in the api module's resources directory
        saveSwaggerFile(openAPI, outputDir);

        // Generate files in the target directory
        String apiDir = outputDir + "/api/target/generated-sources/api/src/main/java/" + BASE_PACKAGE.replace(".", "/") + "/api";
        FileUtils.forceMkdir(new File(apiDir + "/interfaces"));
        FileUtils.forceMkdir(new File(apiDir + "/models"));
        FileUtils.forceMkdir(new File(apiDir + "/implementations"));

        for (Map.Entry<String, PathItem> entry : openAPI.getPaths().entrySet()) {
            String path = entry.getKey();
            PathItem pathItem = entry.getValue();

            for (Map.Entry<PathItem.HttpMethod, Operation> operationEntry : pathItem.readOperationsMap().entrySet()) {
                PathItem.HttpMethod httpMethod = operationEntry.getKey();
                Operation operation = operationEntry.getValue();

                // Generate interface
                String interfaceName = "I" + capitalize(operation.getOperationId()) + "API";
                String interfaceContent = generateInterface(operation, interfaceName, httpMethod.toString(), path);
                FileUtils.writeStringToFile(new File(apiDir + "/interfaces/" + interfaceName + ".java"), interfaceContent, String.valueOf(StandardCharsets.UTF_8));

                // Generate controller class
                String controllerName =  capitalize(operation.getOperationId()) + "Controller";
                String controllerContent = generateControllerClass(interfaceName, controllerName, operation,httpMethod,path);
                FileUtils.writeStringToFile(new File(apiDir + "/implementations/" + controllerName + ".java"), controllerContent, String.valueOf(StandardCharsets.UTF_8));

                // Generate models for parameters
                for (Parameter parameter : operation.getParameters()) {
                    String modelName = capitalize(parameter.getName()) + "Model";
                    String modelContent = generateModel(parameter.getSchema(), modelName);
                    FileUtils.writeStringToFile(new File(apiDir + "/models/" + modelName + ".java"), modelContent, String.valueOf(StandardCharsets.UTF_8));
                }
            }
        }

        logger.info("API module generated successfully at: {}", apiDir);
    }

    private static void saveSwaggerFile(OpenAPI openAPI, String outputDir) throws IOException {
        String swaggerFilePath = outputDir + "/api/src/main/resources/swagger.yaml";

        // Serialize OpenAPI object to YAML
        String swaggerContent = Yaml.pretty(openAPI);

        // Write the YAML content to the file
        FileUtils.writeStringToFile(new File(swaggerFilePath), swaggerContent, String.valueOf(StandardCharsets.UTF_8));

        logger.info("Swagger file saved successfully at: {}", swaggerFilePath);
    }

    private static String generateModel(Schema<?> schema, String modelName) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(BASE_PACKAGE).append(".api.models;\n\n");
        sb.append("public class ").append(modelName).append(" {\n");

        if ("object".equals(schema.getType()) && schema.getProperties() != null) {
            for (Map.Entry<String, Schema> entry : schema.getProperties().entrySet()) {
                String fieldName = entry.getKey();
                String fieldType = getJavaType(entry.getValue());
                sb.append("    private ").append(fieldType).append(" ").append(fieldName).append(";\n\n");
                sb.append("    public ").append(fieldType).append(" get").append(capitalize(fieldName)).append("() {\n");
                sb.append("        return this.").append(fieldName).append(";\n");
                sb.append("    }\n\n");
                sb.append("    public void set").append(capitalize(fieldName)).append("(").append(fieldType).append(" ").append(fieldName).append(") {\n");
                sb.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
                sb.append("    }\n");
            }
        } else {
            sb.append("    private ").append(getJavaType(schema)).append(" value;\n\n");
            sb.append("    public ").append(getJavaType(schema)).append(" getValue() {\n");
            sb.append("        return this.value;\n");
            sb.append("    }\n\n");
            sb.append("    public void setValue(").append(getJavaType(schema)).append(" value) {\n");
            sb.append("        this.value = value;\n");
            sb.append("    }\n");
        }

        sb.append("}\n");
        return sb.toString();
    }

    private static String getJavaType(Schema<?> schema) {
        String type = schema.getType();
        String format = schema.getFormat();

        switch (type) {
            case "integer":
                return "int64".equals(format) ? "long" : "int";
            case "number":
                return "double".equals(format) ? "double" : "float";
            case "string":
                return "date".equals(format) || "date-time".equals(format) ? "java.time.LocalDate" : "String";
            case "boolean":
                return "boolean";
            default:
                return "Object";
        }
    }

    private static String generateInterface(Operation operation, String interfaceName, String httpMethod, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(BASE_PACKAGE).append(".api.interfaces;\n\n");
        sb.append("import org.springframework.web.bind.annotation.*;\n\n");
        sb.append("public interface ").append(interfaceName).append(" {\n");

        // Generate method signature
        String formattedMethod = httpMethod.substring(0, 1).toUpperCase() + httpMethod.substring(1).toLowerCase();
        sb.append("    @").append(formattedMethod).append("Mapping(\"").append(path).append("\")\n");

//        sb.append("    @").append(httpMethod.toUpperCase()).append("Mapping(\"").append(path).append("\")\n");
        sb.append("    String ").append(operation.getOperationId()).append("(");

        // Add parameters
        if (operation.getParameters() != null && !operation.getParameters().isEmpty()) {
            for (Parameter parameter : operation.getParameters()) {
                sb.append("@PathVariable(\"").append(parameter.getName()).append("\") String ").append(parameter.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }

        sb.append(");\n");
        sb.append("}\n");
        return sb.toString();
    }

    private static String generateControllerClass(String interfaceName, String controllerName, Operation operation, PathItem.HttpMethod httpMethod, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(BASE_PACKAGE).append(".api.implementations;\n\n");
        sb.append("import ").append(BASE_PACKAGE).append(".api.interfaces.").append(interfaceName).append(";\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        sb.append("import org.springframework.context.ApplicationContext;\n");
        sb.append("import org.springframework.context.ApplicationContextAware;\n");
        sb.append("import org.springframework.context.annotation.Bean;\n");
        sb.append("import org.springframework.context.annotation.Configuration;\n");
        sb.append("import org.springframework.context.annotation.Scope;\n");
        sb.append("import org.springframework.stereotype.Controller;\n");
        sb.append("import org.springframework.web.bind.annotation.*;\n\n");
        sb.append("import jakarta.servlet.http.HttpServletRequest;\n\n");
        sb.append("@Controller\n");
        sb.append("@Scope(value = \"prototype\")\n");
        sb.append("public class ").append(controllerName).append(" implements ").append(interfaceName).append(", ApplicationContextAware {\n\n");
        sb.append("    @Autowired\n");
        sb.append("    protected HttpServletRequest httpServletRequest;\n\n");
        sb.append("    private ApplicationContext applicationContext;\n\n");
        sb.append("    @Autowired\n");
        sb.append("    private I").append(capitalize(operation.getOperationId())).append("Delegate delegate;\n\n");
        sb.append("    @Override\n");
        sb.append("    public void setApplicationContext(ApplicationContext applicationContext) {\n");
        sb.append("        this.applicationContext = applicationContext;\n");
        sb.append("    }\n\n");

        // Generate parameters class
        sb.append("    public static class ").append(capitalize(operation.getOperationId())).append("Parameters {\n");
        if (operation.getParameters() != null && !operation.getParameters().isEmpty()) {
            for (Parameter parameter : operation.getParameters()) {
                sb.append("        private String ").append(parameter.getName()).append(";\n\n");
                sb.append("        public String get").append(capitalize(parameter.getName())).append("() {\n");
                sb.append("            return this.").append(parameter.getName()).append(";\n");
                sb.append("        }\n\n");
                sb.append("        public void set").append(capitalize(parameter.getName())).append("(String ").append(parameter.getName()).append(") {\n");
                sb.append("            this.").append(parameter.getName()).append(" = ").append(parameter.getName()).append(";\n");
                sb.append("        }\n");
            }
        }
        sb.append("    }\n\n");

        // Generate parameters configuration
        sb.append("    @Configuration\n");
        sb.append("    public class ").append(capitalize(operation.getOperationId())).append("ParametersConfig {\n");
        sb.append("        @Bean\n");
        sb.append("        @Scope(value = \"prototype\")\n");
        sb.append("        public ").append(capitalize(operation.getOperationId())).append("Parameters getBean").append(capitalize(operation.getOperationId())).append("Parameters() {\n");
        sb.append("            return new ").append(capitalize(operation.getOperationId())).append("Parameters();\n");
        sb.append("        }\n");
        sb.append("    }\n\n");

        // Generate delegate interface
        sb.append("    public interface I").append(capitalize(operation.getOperationId())).append("Delegate {\n");
        sb.append("        String execute(").append(capitalize(operation.getOperationId())).append("Parameters parameters);\n");
        sb.append("    }\n\n");

        // Generate controller method
        sb.append("    @Override\n");
        sb.append("    @")
                .append(httpMethod.name().substring(0, 1).toUpperCase())  // First letter uppercase
                .append(httpMethod.name().substring(1).toLowerCase())     // Remaining letters lowercase
                .append("Mapping(\"")
                .append(path)
                .append("\")\n");

//        sb.append("    @").append(httpMethod.toString().toUpperCase()).append("Mapping(\"").append(path).append("\")\n");
        sb.append("    public String ").append(operation.getOperationId()).append("(");
        if (operation.getParameters() != null && !operation.getParameters().isEmpty()) {
            for (Parameter parameter : operation.getParameters()) {
                sb.append("@PathVariable(\"").append(parameter.getName()).append("\") String ").append(parameter.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append(") {\n");
        sb.append("        ").append(capitalize(operation.getOperationId())).append("Parameters parameters = this.applicationContext.getBean(").append(capitalize(operation.getOperationId())).append("Parameters.class);\n");
        if (operation.getParameters() != null && !operation.getParameters().isEmpty()) {
            for (Parameter parameter : operation.getParameters()) {
                sb.append("        parameters.set").append(capitalize(parameter.getName())).append("(").append(parameter.getName()).append(");\n");
            }
        }
        sb.append("        return delegate.execute(parameters);\n");
        sb.append("    }\n");
        sb.append("}\n");
        return sb.toString();
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
