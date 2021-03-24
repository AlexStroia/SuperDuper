package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES where noteId = #{noteId}")
    Note get(int noteId);

    @Select("SELECT * FROM NOTES")
    List<Note> getAll();

    @Delete("DELETE FROM NOTES where noteId = #{noteId}")
    int delete(int noteId);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE #noteId = #{noteId}")
    int edit(Note note);

    @Insert("INSERT INTO NOTES(noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

}
