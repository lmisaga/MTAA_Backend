package com.sclad.scladapp.service;

import com.sclad.scladapp.entity.UploadedFile;

public interface UploadedFileService extends AbstractService<UploadedFile> {

    Long create(UploadedFile fileToUpload);

    void remove(Long id);
}
