package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM Credentials where credentialId = #{credentialId}")
    Credential get(int credentialId);

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAll();

    @Delete("DELETE FROM CREDENTIALS where credentialId = #{credentialId}")
    int delete(int credentialId);

    @Update("UPDATE CREDENTIALS SET credentialId = #{credentialId}, url = #{url}, username = #{url}, key= #{key} WHERE #userId = #{userId}")
    int edit(Credential credential);

    @Insert("INSERT INTO CREDENTIALS(url, username, key, userId) VALUES(#{credentialsId}, #{url}, #{username}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);
}
