package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;

@Mapper
public interface FileMapper {

	@Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
	File getFile(Integer fileId);

	@Select("SELECT * FROM FILES WHERE filename = #{fileName}")
	File getFileByName(String fileName);

	@Select("SELECT * FROM FILES WHERE userid = #{userId}")
	List<File> getUserFilesList(Integer userId);

	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	int insert(File file);
}
