package com.thejoa703.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID; 
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class FileStorageService {
    
    private final Path root = Paths.get("uploads"); 

    public String storeFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            if (!Files.exists(root)) Files.createDirectories(root);
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return "uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }
}