package com.rasthrabhasha.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        Map<String, String> response = files.stream().collect(Collectors.toMap(
                MultipartFile::getOriginalFilename,
                file -> storageService.uploadFile(file)
        ));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/upload")
    public ResponseEntity<Map<String, String>> deleteFile(@RequestParam("objectName") String objectName) {
        storageService.deleteFile(objectName);
        Map<String, String> response = Map.of("message", "File deleted successfully");
        return ResponseEntity.ok(response);
    }
}
