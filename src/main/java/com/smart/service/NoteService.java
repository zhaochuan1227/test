package com.smart.service;

import java.util.List;
import java.util.Map;

import com.smart.entity.Note;

public interface NoteService {
	List<Map<String, Object>> listNotes(String noteBookId) throws NoteBookNotFoundException;

	Note loadNote(String noteId) throws NoteNotFoundException;

	void updateNote(String id, String noteBookId, String title, String body);

	Note createNote(String noteBookId, String userId, String title);
	
	boolean moveNote(String id,String noteBookId);
	
	void deleteNote(String id);
	
	void deleteNotes(String... ids);
	
}
