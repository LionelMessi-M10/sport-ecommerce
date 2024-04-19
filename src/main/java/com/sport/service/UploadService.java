package com.sport.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploadService {
    String handleSaveUploadFile(MultipartFile file, String targetFolder);
}
