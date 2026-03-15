package com.rasthrabhasha.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String uploadFile(MultipartFile file);
    void deleteFile(String objectName);
}
