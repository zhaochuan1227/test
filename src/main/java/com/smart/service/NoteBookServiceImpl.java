package com.smart.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smart.dao.NoteBookDao;
import com.smart.dao.UserDao;
import com.smart.entity.NoteBook;
import com.smart.entity.User;

@Service("noteBookService")
public class NoteBookServiceImpl implements NoteBookService {

	@Resource(name = "noteBookDao")
	private NoteBookDao noteBookDao;

	@Autowired
	private UserDao userDao;
	@Autowired
	private NoteService noteService;
	
	@Transactional
	public List<Map<String, Object>> listNoteBooks(String userId) throws UserNotFoundException {
		if (userId == null || userId.trim().isEmpty()) {
			throw new IllegalArgumentException("用户ID为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户ID不存在");
		}
		return noteBookDao.findNoteBooksByUserId(userId);
	}

	@Transactional
	public NoteBook createNoteBook(String userId, String name) {
		if (userId == null || userId.trim().isEmpty()) {
			throw new IllegalArgumentException("用户ID为空");
		}
		User user = userDao.findUserById(userId);
		if (user == null) {
			throw new UserNotFoundException("用户不存在,请重新登录！");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("笔记本名不能为空");
		}
		String id = UUID.randomUUID().toString();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		NoteBook noteBook = new NoteBook(id,userId,name,"5",null,ts);
		
		noteBookDao.saveNoteBook(noteBook);
		return noteBook;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteNoteBook(String id) {
		//找到这个笔记本的全部笔记
		//逐一删除笔记
		//调用其他的业务方法实现这个功能，目的是为了研究事务传播
		List<Map<String,Object>> notes = noteService.listNotes(id);
		String[] ary = new String[notes.size()]; 
		//获得全部笔记的ID
		for(int i=0;i<ary.length;i++){
			ary[i] = (String)notes.get(i).get("id");
		}
		noteService.deleteNotes(ary);
		//删除笔记本
		noteBookDao.deleteNoteBook(id);
	}
}
