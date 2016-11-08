package com.smart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.dao.NoteBookDao;
import com.smart.dao.NoteDao;
import com.smart.dao.UserDao;
import com.smart.entity.Note;
import com.smart.entity.NoteBook;
import com.smart.entity.User;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource(name = "noteDao")
	private NoteDao noteDao;
	@Resource(name = "noteBookDao")
	private NoteBookDao noteBookDao;
	@Resource(name = "userDao")
	private UserDao userDao;
	@Transactional
	public List<Map<String, Object>> listNotes(String noteBookId) {
		if (noteBookId == null || noteBookId.trim().isEmpty()) {
			throw new IllegalArgumentException("noteBookId 不能为空");
		}
		NoteBook noteBook = noteBookDao.findNoteBooksById(noteBookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("noteBookId 不能为空");
		}
		List<Map<String, Object>> list = noteDao.findNotesByNoteBookId(noteBookId);
		return list;
	}
	@Transactional
	public Note loadNote(String noteId) throws NoteNotFoundException {
		if (noteId == null || noteId.trim().isEmpty()) {
			throw new IllegalArgumentException("noteId 不能为空");
		}
		Note note = noteDao.findNoteBodyById(noteId);
		return note;
	}
	@Transactional
	public Note createNote(String noteBookId, String userId, String title) {
		if (noteBookId == null || noteBookId.trim().isEmpty()) {
			throw new IllegalArgumentException("笔记本ID信息不能为空！");
		}
		if (userId == null || userId.trim().isEmpty()) {
			throw new IllegalArgumentException("用户ID信息不能为空！");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new NoteNotFoundException("用户ID信息不存在！");
		}
		NoteBook noteBook = noteBookDao.findNoteBooksById(noteBookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("笔记本不存在！");
		}
		if (title == null || title.trim().isEmpty()) {
			title = "新建笔记";
		}
		title = title.trim();
		String id = UUID.randomUUID().toString();
		long time = System.currentTimeMillis();
		Note note = new Note(id, noteBookId, userId, "1", "1", title, "", time, time);
		noteDao.saveNote(note);
		return note;
	}
	//修改笔记信息
	@Transactional
	public void updateNote(String id, String noteBookId, String title, String body) {

		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("笔记ID信息不能为空！");
		}
		Note note = noteDao.findNoteBodyById(id);
		if (noteBookId == null || noteBookId.trim().isEmpty()) {
			throw new IllegalArgumentException("用户ID信息不能为空！");
		}
		NoteBook noteBook = noteBookDao.findNoteBooksById(noteBookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("笔记本不存在！");
		}
		if (title == null || title.trim().isEmpty()) {
			title = "新建笔记";
		}
		title = title.trim();
		long time = System.currentTimeMillis();
		if(!note.getNotebookId().equals(noteBookId)){
			note.setNotebookId(noteBookId);
		}
		note.setTitle(title);
		note.setBody(body);
		note.setLastModifyTime(time);
		noteDao.updateNote(note);
	}
	//更改笔记所属笔记本
	@Transactional
	public boolean moveNote(String id, String noteBookId) {
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("笔记ID信息不能为空！");
		}
		Note note = noteDao.findNoteBodyById(id);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在！");
		}
		if (noteBookId == null || noteBookId.trim().isEmpty()) {
			throw new IllegalArgumentException("用户ID信息不能为空！");
		}
		NoteBook noteBook = noteBookDao.findNoteBooksById(noteBookId);
		if (noteBook == null) {
			throw new NoteBookNotFoundException("笔记本不存在！");
		}
		if(noteBookId.equals(note.getNotebookId())){
			//笔记本没有更换
			return false;
		}
		Note n = new Note();
		n.setId(id);
		n.setNotebookId(noteBookId);
		noteDao.updateNote(n);
		//System.out.println("NoteServiceImpl.moveNote执行完成");
		return true;
	}
	@Transactional
	public void deleteNote(String id){
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("笔记ID信息不能为空！");
		}
		Note note = noteDao.findNoteBodyById(id);
		if (note == null) {
			throw new NoteBookNotFoundException("笔记不存在！");
		}
		noteDao.deleteNote(id);
	}
	//(批量)删除笔记
	@Transactional
	public void deleteNotes(String... ids) {
		int count = noteDao.countNotes(Arrays.asList(ids));
		if(count != ids.length){
			throw new NoteNotFoundException("笔记ID不存在");
		}
		noteDao.deleteNotes(ids);
	}
	
}
