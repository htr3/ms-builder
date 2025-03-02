package com.builder.resource.implemantations;


import com.builder.api.resources.UploadYamlFileController;
import com.builder.services.interfaces.IMsBuilderServiceApplication;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class MsBuilderDelegate implements UploadYamlFileController.IMsBuilderDelegate {

    @Inject
    private IMsBuilderServiceApplication msBuilderServiceApplication;

    @Override
    public ResponseEntity<Void> excute(MultipartFile file) {
        try {
            // Read the file content as a string
            String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);

            // Print the file content to the console
            System.out.println("Swagger File Content:");
            System.out.println(fileContent);

            // Alternatively, you can log the content
            // logger.info("Swagger File Content: {}", fileContent);
            msBuilderServiceApplication.msibuileder(file);
            // Return a successful response
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            // Handle the exception
            System.err.println("Failed to read the file: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}