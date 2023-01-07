package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;

@Mapper
public interface NoteMapper {

	@Select("SELECT * FROM NOTES")
	List<Note> getNotesList();

	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insert(Note note);

	@Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userId} WHERE noteid = #{noteId}")
	int update(Note note);

	@Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
	int delete(Integer noteId);
}
