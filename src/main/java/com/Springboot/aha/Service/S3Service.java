package com.Springboot.aha.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface S3Service {

    String saveFile(MultipartFile file);
    byte[] downloadFile(String filename);
    String deleteFile(String filename);
    List<String> listAllFile();
}
