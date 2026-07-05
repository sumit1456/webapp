package com.rasthrabhasha.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.UPLOAD_FILES);
        if (err != null) return err;
        Map<String, String> response = files.stream().collect(Collectors.toMap(
                MultipartFile::getOriginalFilename,
                file -> storageService.uploadFile(file)
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/url")
    public ResponseEntity<?> getFileUrl(@RequestParam("objectName") String objectName) {
        String url = storageService.getFileUrl(objectName);
        return ResponseEntity.ok(Map.of("url", url));
    }

    @DeleteMapping("/upload")
    public ResponseEntity<?> deleteFile(@RequestParam("objectName") String objectName) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_STUDENTS);
        if (err != null) return err;
        storageService.deleteFile(objectName);
        Map<String, String> response = Map.of("message", "File deleted successfully");
        return ResponseEntity.ok(response);
    }
}
