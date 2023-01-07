package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;

@Mapper
public interface CredentialMapper {

	@Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
	List<Credential> getUserCredentialsList(Integer userId);

	@Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "credentialId")
	int insert(Credential credential);

	@Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username},key = #{key}, password = #{password}, userid = #{userId} WHERE credentialid = #{credentialId}")
	int update(Credential credential);

	@Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
	int delete(Integer credentialId);
}
