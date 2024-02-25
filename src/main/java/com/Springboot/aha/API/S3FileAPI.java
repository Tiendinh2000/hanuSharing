//package com.Springboot.aha.API;
//
//import com.Springboot.aha.Service.S3Service;
//import com.Springboot.aha.Service.impl.S3ServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/file")
//public class S3FileAPI {
//
//    @Autowired
//    private S3ServiceImpl s3Service;
//
//    @PostMapping("/upload")
//    public String upload(@RequestParam("file")MultipartFile file){
//      return   s3Service.saveFile(file);
//    }
//}
