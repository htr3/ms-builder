package com.builder.services.implementations;

//import com.builder.services.interfaces.IMsBuilderServiceApplication;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class MsBuilderServiceApplication implements IMsBuilderServiceApplication {
//    @Override
//    public ResponseEntity<Void> msibuileder(MultipartFile file) {
//        return ResponseEntity.ok().build();
//    }
//}



import com.builder.services.helper.MsBuilder;
import com.builder.services.interfaces.IMsBuilderServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class MsBuilderServiceApplication implements IMsBuilderServiceApplication {

    @Override
    public ResponseEntity<Void> msibuileder(MultipartFile file) {
        try {
            // Save the uploaded file to a temporary location
            Path tempFile = Files.createTempFile("swagger", ".yaml");
            file.transferTo(tempFile);

            // Call the Microservice Builder Tool
            MsBuilder.buildMicroservice(tempFile.toString());

            // Delete the temporary file
            Files.delete(tempFile);

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}