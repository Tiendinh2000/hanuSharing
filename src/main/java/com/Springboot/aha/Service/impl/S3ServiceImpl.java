package com.Springboot.aha.Service.impl;

import com.Springboot.aha.Service.S3Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3ServiceImpl implements S3Service {

    @Value("${s3.bucketname}")
    private String bucketName;

    private final AmazonS3 s3;

    public S3ServiceImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String saveFile(MultipartFile multipartFile) {

        String originalFileName = multipartFile.getOriginalFilename();
        try {
            File file = converMultipartToFile(multipartFile);
            PutObjectResult putObjectResult = s3.putObject(bucketName, originalFileName, file);
            return putObjectResult.getContentMd5();
        } catch (Exception e) {
            throw new RuntimeException("error file");
        }

    }

    @Override
    public byte[] downloadFile(String filename) {
        S3Object object = s3.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException("error download");
        }
    }

    @Override
    public String deleteFile(String filename) {
        s3.deleteObject(bucketName, filename);

        return "file deleted";
    }

    @Override
    public List<String> listAllFile() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    private File converMultipartToFile(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

}
