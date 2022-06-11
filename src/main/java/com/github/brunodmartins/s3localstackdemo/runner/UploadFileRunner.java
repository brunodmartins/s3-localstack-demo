package com.github.brunodmartins.s3localstackdemo.runner;

import com.github.brunodmartins.s3localstackdemo.service.Storage;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UploadFileRunner implements CommandLineRunner {

    private static final String KEY = "my-test-key";

    private Storage storage;

    @Override
    public void run(String... args) {
        storage.saveObject(KEY, "This is a test file".getBytes());
        System.out.println("File uploaded successfully");

        byte[] object = storage.getObject(KEY);
        System.out.println("Object found: " + new String(object));

    }
}
