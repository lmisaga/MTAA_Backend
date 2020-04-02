package com.sclad.scladapp.controller;


import com.sclad.scladapp.entity.UploadedFile;
import com.sclad.scladapp.service.UploadedFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/uploadedFile")
public class UploadedFileController {

    private final UploadedFileService uploadedFileService;

    public UploadedFileController(UploadedFileService uploadedFileService) {
        this.uploadedFileService = uploadedFileService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody @Valid MultipartFile fileToUpload) {
        UploadedFile uploadedFile = getById(this.uploadedFileService.create(fileToUpload));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploadedFile/download/")
                .path(uploadedFile.getFileName()).path("/db")
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity download(@PathVariable String fileName) {
        UploadedFile uploadedFile = uploadedFileService.getByFilename(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(uploadedFile.getData());
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable  Long id) {
        uploadedFileService.remove(id);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public UploadedFile getById(@PathVariable Long id) {
        return uploadedFileService.getById(id);
    }
}
