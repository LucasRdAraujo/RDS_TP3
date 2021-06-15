package br.edu.infnet.rdsdemo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonService {
    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    public String uploadFile(MultipartFile mpf) {
        StringBuilder fileUrl = new StringBuilder();
        try {
            File file = multipartToFile(mpf);
            String fileName = generateFileName(mpf);
            fileUrl.append(endpointUrl).append("/").append(bucketName).append("/").append(fileName);
            uploadFileToBucket(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl.toString();
    }

    private void uploadFileToBucket(String fileName, File file) {
        s3client.putObject(
                new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private String generateFileName(MultipartFile mpf) {
        return Long.toString(Instant.now().toEpochMilli());
    }

    private File multipartToFile(MultipartFile mpf) throws IOException {
        File file = new File(mpf.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mpf.getBytes());
        fos.close();
        return file;
    }

}
