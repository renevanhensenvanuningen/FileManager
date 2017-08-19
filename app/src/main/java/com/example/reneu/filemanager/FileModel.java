package com.example.reneu.filemanager;

/**
 * Created by rene on 19-8-17.
 */

public class FileModel {
    public String name;
    public String fullName;
    public String size;

    public FileModel(String name, String FullName, long size) {
        this.name = name;
        this.size = friendlyFileSize(size);
        this.fullName = FullName;
    }

    private String friendlyFileSize(long size) {
        String unit = "bytes";
        if (size > 1024) {
            size = size / 1024;
            unit = "kb";
        }
        if (size > 1024) {
            size = size / 1024;
            unit = "mb";
        }
        if (size > 1024) {
            size = size / 1024;
            unit = "gb";
        }
        return " (" + size + ")" + unit;
    }
}

