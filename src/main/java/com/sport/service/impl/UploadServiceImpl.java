package com.sport.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sport.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService{


    @Override
    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
        String fileNameUpload = "";

        try {
            String rootPath = "D:/Workspace_STS4/Sport-Ecommerce/ecommerce-images";

            byte[] bytes = file.getBytes();

            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists())
                dir.mkdirs();

            fileNameUpload = System.currentTimeMillis() + "-" + file.getOriginalFilename();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileNameUpload);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileNameUpload;
    }

}
