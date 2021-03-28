package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.form.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper mapper;

    public FileService(FileMapper mapper) {
        this.mapper = mapper;
    }

    public File get(String filename) {
        return mapper.get(filename);
    }

    public List<File> getAll(int userId) {
        return mapper.getAll(userId);
    }

    public int delete(String filename) {
        return mapper.delete(filename);
    }

    public int insert(FileForm file) throws IOException {

        return mapper.insert(new File(null, file.getFileName(), file.getContentType(), file.getFileSize(), file.getUserId(), file.getMultipartFile().getBytes()));
    }

}
