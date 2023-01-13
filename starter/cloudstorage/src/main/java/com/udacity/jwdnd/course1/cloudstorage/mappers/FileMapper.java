package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;

@Mapper
public interface FileMapper {

	@Select("SELECT * FROM FILES WHERE filename = #{fileName}")
	File getFile(String fileName);

	@Select("SELECT * FROM FILES WHERE userid = #{userId}")
	List<File> getUserFilesList(Integer userId);

	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	int insert(File file);

	@Delete("DELETE FROM FILES WHERE filename = #{fileName}")
	int deleteFile(String fileName);
}
