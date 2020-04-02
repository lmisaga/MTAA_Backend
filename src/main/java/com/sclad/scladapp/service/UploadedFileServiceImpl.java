package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.UploadedFile;
import com.sclad.scladapp.exceptions.FileStorageException;
import com.sclad.scladapp.exceptions.UploadedFileNotFoundException;
import com.sclad.scladapp.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class UploadedFileServiceImpl implements UploadedFileService {


    private final UploadedFileRepository uploadedFileRepository;

    @Autowired
    public UploadedFileServiceImpl(UploadedFileRepository uploadedFileRepository) {
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public Long create(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Provided file has invalid filename!");
            }
            UploadedFile file = new UploadedFile(fileName, multipartFile.getContentType(), multipartFile.getBytes());
            return uploadedFileRepository.save(file).getId();
        } catch (IOException exception) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again.");
        }
    }

    @Override
    public void remove(Long id) {
        UploadedFile uploadedFile = getById(id);
        if (uploadedFile != null ) {
            uploadedFileRepository.delete(uploadedFile);
        }
    }

    @Override
    public UploadedFile getByFilename(String fileName) {
        UploadedFile uploadedFile = uploadedFileRepository.findByFileNameLike(fileName).orElse(null);
        if (uploadedFile != null) {
            return uploadedFile;
        } else {
            throw new UploadedFileNotFoundException();
        }
    }

    @Override
    public UploadedFile getById(Long id) {
        UploadedFile uploadedFile = uploadedFileRepository.findById(id).orElse(null);
        if (uploadedFile != null) {
            return uploadedFile;
        } else {
            throw new UploadedFileNotFoundException();
        }

    }
}
