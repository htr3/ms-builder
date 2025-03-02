package com.builder.api.resources;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import org.builder.api.UploadYamlFileApi;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UploadYamlFileController implements UploadYamlFileApi, ApplicationContextAware {

    @Inject
    protected HttpServletRequest httpServletRequest;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;  // Properly setting the application context
    }

    public interface IMsBuilderDelegate {
        ResponseEntity<Void> excute(MultipartFile file);
    }

    @Override
    public ResponseEntity<Void> uploadYamlFile(MultipartFile file) {
        // Ensure applicationContext is not null before accessing the bean
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext is not initialized.");
        }

        // Fetch the delegate bean
        IMsBuilderDelegate delegate = applicationContext.getBean(IMsBuilderDelegate.class);
        return delegate.excute(file);
    }
}