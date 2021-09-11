package com.example.employee.service;

import org.apache.commons.io.IOUtils;
import com.example.employee.constant.DefaultParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageService {

    @Value("/source/repos/iTechGenOne/employee")
    private String storagePath;

    public String uploadToLocalFileSystem(MultipartFile multipartFile, String type, int id) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Path storageDirectory = Paths.get(System.getProperty("user.home") + storagePath + "/images/");
        String imageDir = "";
        Path imageDirPath = null;

        if(!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            switch (type) {
                case DefaultParam.AVARTAR:
                    imageDir = storageDirectory + File.separator + "avatars" + File.separator + id;
                    break;
                default:
                    break;
            }

            imageDirPath = Paths.get(imageDir);

            if(!Files.exists(imageDirPath)) {
                try {
                    Files.createDirectories(imageDirPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Path destination = Paths.get(Objects.requireNonNull(imageDirPath).toString() + File.separator + fileName);

        try {
            Files.copy(multipartFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return type + "/" + id + "/" + fileName;
    }

    public byte[] getImageWithMediaType(String imageName, int id, String type) throws IOException {
        Path destination = null;

        String imageDir = "";

        Path storageDirectory = Paths.get(System.getProperty("user.home") + storagePath + "/images/");

        switch (type) {
            case DefaultParam.AVARTAR:
                imageDir = storageDirectory + File.separator + "avatars" + File.separator + id;
                break;
            default:
                break;
        }

        destination = Paths.get(imageDir + File.separator + imageName);

        return IOUtils.toByteArray(destination.toUri());
    }

}
