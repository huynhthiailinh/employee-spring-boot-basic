package com.example.employee.controller;

import com.example.employee.constant.DefaultParam;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping(
            value = "getImage/{type}/{id}/{imageName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable(name = "type") String type,
                                 @PathVariable(name = "id") int id,
                                 @PathVariable(name = "imageName") String fileName) throws IOException {
        return this.imageService.getImageWithMediaType(fileName, id, type);
    }

    @DeleteMapping("{type}/{id}")
    public ResponseEntity deleteEventImageById(@PathVariable String type, @PathVariable int id) {
        switch (type) {
            case DefaultParam.AVARTAR:
                employeeService.updateAvatarById(id, "");
                break;
            default:
                break;
        }
        return new ResponseEntity<>("Delete " + type + " image succeed!", HttpStatus.OK);
    }

}
