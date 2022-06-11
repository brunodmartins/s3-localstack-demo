package com.github.brunodmartins.s3localstackdemo.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import java.net.URI;

@Configuration
public class S3Factory {

    @Bean
    public S3Client buildS3Client(@Value("${aws.s3.endpoint}") String endpoint) {
        S3ClientBuilder s3ClientBuilder = S3Client.builder()
                .region(Region.US_EAST_1);
        if(endpoint != null && !"".equalsIgnoreCase(endpoint)) {
            s3ClientBuilder = s3ClientBuilder.endpointOverride(URI.create(endpoint));
        }
        return s3ClientBuilder.build();
    }
}
