package com.sport.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sport.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

    private final ResourceLoader resourceLoader;

    public UploadServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
        String fileNameUpload = "";

        try {
            String rootPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();

            byte[] bytes = file.getBytes();

            // Tạo thư mục target nếu chưa tồn tại
            Path targetPath = Paths.get(rootPath, targetFolder);
            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath);
            }

            fileNameUpload = System.currentTimeMillis() + "-" + file.getOriginalFilename();

            // Lưu file vào thư mục target
            File serverFile = new File(targetPath.toFile(), fileNameUpload);
            FileCopyUtils.copy(bytes, serverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(fileNameUpload);
        return fileNameUpload;
    }
}
