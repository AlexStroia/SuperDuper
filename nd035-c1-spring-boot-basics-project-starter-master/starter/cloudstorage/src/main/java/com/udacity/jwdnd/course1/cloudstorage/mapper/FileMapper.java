package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES where fileName = #{filename}")
    File get(String filename);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getAll(int userId);

    @Delete("DELETE FROM FILES where fileId = #{fileId}")
    int delete(int fileId);

    //    @Update("UPDATE FILES SET noteTitleN = #{noteTitle}, noteDescription = #{noteDescription} WHERE #noteId = #{noteId}")
    int edit(File file);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

}