package com.example.Lecture91.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface UploadFileService {
    String storeFile(MultipartFile file) throws IOException;

    void unzipFile(String zipFileName) throws IOException;
}
