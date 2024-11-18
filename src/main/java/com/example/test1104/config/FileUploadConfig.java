package com.example.test1104.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileUploadConfig {

    @Value("${uploadPath}")
    private String uploadPath;

    //init 메서드가 애플리케이션 초기화 시 자동으로 실행되도록 설정
    @PostConstruct
    public void init() {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();  // 폴더 생성
            System.out.println("업로드 폴더 생성: " + uploadPath);
        }
    }
}
