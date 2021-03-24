package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES where noted = #{noteid}")
    Note get(int noteId);

    @Select("SELECT * FROM NOTES where userid = #{userid}")
    List<Note> getAll(int userid);

    @Delete("DELETE FROM NOTES where noteid = #{noteid}")
    int delete(int noteId);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE #noteid = #{noteid}")
    int edit(Note note);

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

}
