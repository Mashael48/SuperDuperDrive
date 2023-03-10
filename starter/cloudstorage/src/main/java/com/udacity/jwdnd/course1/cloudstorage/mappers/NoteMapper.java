package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;

@Mapper
public interface NoteMapper {

	@Select("SELECT * FROM NOTES WHERE userid = #{userId}")
	List<Note> getUserNotesList(Integer userId);

	@Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insert(Note note);

	@Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userId} WHERE noteid = #{noteId}")
	int update(Note note);

	@Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
	int delete(Integer noteId);
}
