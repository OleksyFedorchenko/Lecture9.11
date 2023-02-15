package com.example.Lecture91.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class UploadFileServiceImpl implements UploadFileService{

    private final String PATH = System.getProperty("user.dir");
    private final String SEPARATOR = System.getProperty("file.separator");
 private  final Path uploadFileLocation;
    public UploadFileServiceImpl(){
        String uploadDir = PATH+SEPARATOR+"uploads";

        this.uploadFileLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(uploadFileLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path targetLocation;

        try {
            targetLocation = this.uploadFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        unzipFile(fileName);

        return fileName;
    }

    @Override
    public void unzipFile(String zipFileName) throws IOException {
        String uploadDir = PATH+SEPARATOR+"uploads";
        final ZipInputStream zis = new ZipInputStream(new FileInputStream(uploadDir+SEPARATOR+zipFileName));
        ZipEntry ze=zis.getNextEntry();
        String nextFileName;
        byte[] buffer = new byte[1024];
        while (ze!=null) {
            nextFileName = ze.getName();
            File nextFile = new File(uploadDir+SEPARATOR+nextFileName);
            FileOutputStream fos = new FileOutputStream(nextFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            ze=zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

}
