package com.Springboot.aha;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${s3.accessKey}")
    private String accesskey;

    @Value("${s3.secret}")
    private String secret;

    @Value("${s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials credentials = new BasicAWSCredentials(accesskey, secret);
        return AmazonS3ClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

}
