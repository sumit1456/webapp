package com.rasthrabhasha.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String uploadFile(MultipartFile file);
    String getFileUrl(String objectName);
    void deleteFile(String objectName);
}
