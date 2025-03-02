package com.builder.services.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IMsBuilderServiceApplication {
    public ResponseEntity<Void> msibuileder(MultipartFile file);
}
