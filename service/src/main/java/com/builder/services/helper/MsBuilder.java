package com.builder.services.helper;
import com.builder.services.helper.implementations.*;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.oas.models.OpenAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MsBuilder.class);


    private final CreateFolderStructureService createFolderStructureService;
    private final ApiModuleGeneratorService apiModuleGeneratorService;
    private final OtherModulesGeneratorService otherModulesGeneratorService;
    private final CreateBasePomService createBasePomService;
    private final CreateMvnDirService createMvnDirService;


    public MsBuilder(CreateFolderStructureService createFolderStructureService, ApiModuleGeneratorService apiModuleGeneratorService, OtherModulesGeneratorService otherModulesGeneratorService, CreateBasePomService createBasePomService, CreateMvnDirService createMvnDirService) {
        this.createFolderStructureService = createFolderStructureService;
        this.apiModuleGeneratorService = apiModuleGeneratorService;
        this.otherModulesGeneratorService = otherModulesGeneratorService;
        this.createBasePomService = createBasePomService;
        this.createMvnDirService = createMvnDirService;
    }


    public static void buildMicroservice(String swaggerFilePath) {
        String outputDir = "C:\\Users\\visha\\Documents\\ms"; // Output directory for the generated microservice

        if (swaggerFilePath == null || swaggerFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Swagger file path cannot be null or empty");
        }



        try {
            // Parse the swagger.yaml file
            OpenAPI openAPI = new OpenAPIV3Parser().read(swaggerFilePath);

            if (openAPI == null) {
                throw new IllegalArgumentException("Invalid Swagger file");
            }

            CreateBasePomService.createBasePom(outputDir);

            CreateMvnDirService.createMvnDirectory(outputDir);

            CreateFolderStructureService.createFolderStructure(outputDir);

            ApiModuleGeneratorService.generateApiModule(openAPI, outputDir);

            OtherModulesGeneratorService.generateOtherModules(outputDir);

            logger.info("Microservice structure generated successfully at: {}", outputDir);
        } catch (Exception e) {
            logger.error("Error generating microservice structure: ", e);
        }
    }
}