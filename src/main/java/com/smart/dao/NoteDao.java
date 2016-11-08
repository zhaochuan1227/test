package com.smart.dao;

import java.util.List;
import java.util.Map;

import com.smart.entity.Note;

public interface NoteDao {

	/**
	 * 根据笔记本ID查询笔记列表
	 * 
	 * @param noteBookId
	 *            笔记本ID
	 * @return 笔记列表
	 */
	public List<Map<String, Object>> findNotesByNoteBookId(String noteBookId);

	public Note findNoteBodyById(String noteId);

	public void saveNote(Note note);

	public void updateNote(Note note);

	public void deleteNote(String id);

	public void deleteNotes(String... ids);

	public int countNotes(List<String> idList);
	
	public int countNotesByParams(Map<String,Object> param);
}
