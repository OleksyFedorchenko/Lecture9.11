package com.example.Lecture91.controller;

import com.example.Lecture91.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fileUpload")
public class FileController {
    UploadFileService uploadFileService;

    @Autowired
    public FileController(UploadFileService uploadFileService){
        this.uploadFileService=uploadFileService;
    }

@PostMapping
public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    uploadFileService.storeFile(file);
    return new ResponseEntity<Void>(HttpStatus.OK);
}

}
