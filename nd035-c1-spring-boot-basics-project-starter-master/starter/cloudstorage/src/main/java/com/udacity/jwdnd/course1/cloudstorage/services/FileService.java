package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper mapper;

    public FileService(FileMapper mapper) {
        this.mapper = mapper;
    }

    public File get(int fileId) {
        return mapper.get(fileId);
    }

    public List<File> getAll() {
        return mapper.getAll();
    }

    public int delete(int fileId) {
        return mapper.delete(fileId);
    }

    public int edit(FileForm file) {
        return mapper.edit(new File(file.getFileId(), file.getFileName(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getFileData()));
    }

    public int insert(FileForm file) {
        return mapper.insert(new File(file.getFileId(), file.getFileName(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getFileData()));
    }

}
