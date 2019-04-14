package org.com.esti.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadImages(MultipartFile multipartFile) throws IOException;
}
