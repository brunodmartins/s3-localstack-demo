package com.github.brunodmartins.s3localstackdemo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

@Component
public class UploadFileRunner implements CommandLineRunner {

    private static final String BUCKET = "my-test-bucket";

    private static final String KEY = "my-test-key";

    @Override
    public void run(String... args) throws Exception {
        S3Client s3Client = S3Client.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .region(Region.US_EAST_1).build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(KEY)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromString("This is a test file"));
        System.out.println("File uploaded successfully");

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(BUCKET)
                .key(KEY)
                .build();

        byte[] object = s3Client.getObject(getObjectRequest).readAllBytes();
        System.out.println("Object found: " + new String(object));

    }
}
