package com.github.brunodmartins.s3localstackdemo.service;

public interface Storage {

    byte[] getObject(String key);

    void saveObject(String key, byte[] data);
}
