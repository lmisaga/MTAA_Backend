package com.sclad.scladapp.service;


import com.sclad.scladapp.entity.UploadedFile;
import com.sclad.scladapp.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadedFileServiceImpl implements UploadedFileService {


    private final UploadedFileRepository uploadedFileRepository;

    @Autowired
    public UploadedFileServiceImpl(UploadedFileRepository uploadedFileRepository) {
        this.uploadedFileRepository = uploadedFileRepository;
    }

    @Override
    public Long create(UploadedFile fileToUpload) {
        //todo
        return null;
    }

    @Override
    public void remove(Long id) {
        UploadedFile uploadedFile = getById(id);
        uploadedFileRepository.delete(uploadedFile);
    }

    @Override
    public UploadedFile getById(Long id) {
        UploadedFile uploadedFile = uploadedFileRepository.findById(id).orElse(null);
        //TODO throw exception when not found
        return uploadedFile;
    }
}
