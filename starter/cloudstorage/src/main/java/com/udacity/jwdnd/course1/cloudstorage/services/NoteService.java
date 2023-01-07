package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteService {

	private final NoteMapper noteMapper;

	public int createNote(Note note) {
		log.info("createNote: {}", note);
		return noteMapper.insert(note);
	}

	public List<Note> getNotesList() {
		List<Note> notes = noteMapper.getNotesList();
		log.info("getNotesList: {}" + notes);
		return notes;
	}

}
