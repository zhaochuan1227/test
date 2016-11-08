package com.smart.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.entity.NoteBook;
import com.smart.service.NoteBookService;
import com.smart.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NoteBookController {
	
	@Autowired
	private NoteBookService noteBookService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> listNoteBooks(String userId){
		try {
			List<Map<String,Object>> list = noteBookService.listNoteBooks(userId);
			return new JsonResult<List<Map<String,Object>>>(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<Map<String,Object>>>(e);
		}	
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult<NoteBook> saveNoteBook(String userId, String name){
		try {
			return new JsonResult<NoteBook>(noteBookService.createNoteBook(userId, name));
		} catch (Exception e) {
			return new JsonResult<NoteBook>(e.getMessage());
		}
	}
	
	
}
