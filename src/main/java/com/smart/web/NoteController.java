package com.smart.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.entity.Note;
import com.smart.service.NoteService;
import com.smart.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource(name = "noteService")
	private NoteService noteService;

	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> listNote(String noteBookId) {
		return new JsonResult<List<Map<String, Object>>>(noteService.listNotes(noteBookId));
	}

	@RequestMapping("/body.do")
	@ResponseBody
	public JsonResult<Note> loadNote(String noteId) {
		return new JsonResult<Note>(noteService.loadNote(noteId));
	}

	@RequestMapping("/update.do")
	@ResponseBody
	public JsonResult<Boolean> updateNote(String id, String noteBookId, String userId, String title, String body) {
		noteService.updateNote(id, noteBookId, title, body);
		return new JsonResult<Boolean>(JsonResult.SUCCESS);
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult<Note> addNote(String noteBookId, String userId, String title) {
		return new JsonResult<Note>(noteService.createNote(noteBookId, userId, title));
	}

	@RequestMapping("/move.do")
	@ResponseBody
	public JsonResult<Boolean> moveNote(String id, String noteBookId) {
		// System.out.println(id+":"+noteBookId);
		return new JsonResult<Boolean>(noteService.moveNote(id, noteBookId));
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult<Boolean> deleteNote(String... ids) {
		noteService.deleteNotes(ids);
		return new JsonResult<Boolean>(JsonResult.SUCCESS);
	}

}
