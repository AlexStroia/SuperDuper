package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES where fileId = #{fileId}")
    File get(int noteId);

    @Select("SELECT * FROM FILES")
    List<File> getAll();

    @Delete("DELETE FROM FILES where fileId = #{fileId}")
    int delete(int fileId);

    @Update("UPDATE FILES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE #noteId = #{noteId}")
    int edit(File file);

    @Insert("INSERT INTO NOTES(fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

}