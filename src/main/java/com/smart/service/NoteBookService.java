package com.smart.service;

import java.util.List;
import java.util.Map;

import com.smart.entity.NoteBook;

public interface NoteBookService {
	/**
	 * 查询全部的笔记本列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 这个用户的笔记本列表信息
	 * @throws UserNotFoundException
	 *             对应userId不存在情况
	 */
	List<Map<String, Object>> listNoteBooks(String userId) throws UserNotFoundException;

	NoteBook createNoteBook(String userId, String name);
	
	void deleteNoteBook(String id);
}
