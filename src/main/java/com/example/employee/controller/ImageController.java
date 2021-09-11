package com.example.employee.controller;

import com.example.employee.constant.DefaultParam;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/images")
public class ImageController {

    private final EmployeeService employeeService;

    private final ImageService imageService;

    @PostMapping
    public String uploadImage(@RequestParam String type, @RequestParam int id, @RequestParam MultipartFile multipartFile) {
        String imageUrl = "";
        switch (type) {
            case DefaultParam.AVARTAR:
                employeeService.updateAvatarById(id, imageService.uploadToLocalFileSystem(multipartFile, type, id));
                imageUrl = employeeService.getEmployeeById(id).getAvatar();
                break;
            default:
                break;
        }
        return imageUrl;
    }

}
