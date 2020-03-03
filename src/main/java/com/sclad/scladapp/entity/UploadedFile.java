package com.sclad.scladapp.entity;


import javax.persistence.*;

@Table
@Entity
public class UploadedFile extends AbstractEntity {

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private byte[] data;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
