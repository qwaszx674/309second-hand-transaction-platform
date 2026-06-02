package com.daowen.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SingleFileUpload {

    private FileItem fileItem;

    public void parseRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    this.fileItem = item;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upload(File directory) throws Exception {
        if (fileItem != null && directory != null) {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String fileName = fileItem.getName();
            File file = new File(directory, fileName);
            fileItem.write(file);
        }
    }

    public FileItem getFileItem() {
        return fileItem;
    }

    public void setFileItem(FileItem fileItem) {
        this.fileItem = fileItem;
    }
}