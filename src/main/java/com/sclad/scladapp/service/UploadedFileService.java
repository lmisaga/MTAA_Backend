package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

public interface UploadedFileService extends AbstractService<UploadedFile> {

    Long create(MultipartFile multipartFile);

    UploadedFile getByFilename(String fileName);

    void remove(Long id);
}
