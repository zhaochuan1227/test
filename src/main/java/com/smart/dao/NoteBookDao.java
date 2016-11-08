package com.smart.dao;

import java.util.List;
import java.util.Map;

import com.smart.entity.NoteBook;

/**
 * 笔记本的数据访问接口
 *
 */
public interface NoteBookDao {
	/**
	 * 根据用户ID信息查询笔记列表信息
	 * 
	 * @param userId
	 * @return 笔记列表信息
	 */
	List<Map<String, Object>> findNoteBooksByUserId(String userId);

	NoteBook findNoteBooksById(String noteBookId);

	void saveNoteBook(NoteBook noteBook);
	
	void deleteNoteBook(String id);
}
