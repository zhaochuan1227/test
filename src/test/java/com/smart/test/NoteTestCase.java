package com.smart.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.dao.NoteDao;
import com.smart.entity.Note;
import com.smart.service.NoteService;

public class NoteTestCase {
	ApplicationContext ac;
	@Before
	public void init(){
		System.out.println("spring start");
		ac = new ClassPathXmlApplicationContext(
				"spring-mybatis.xml",
				"spring-web.xml",
				"spring-service.xml"			
				);
		System.out.println("spring start success");
	}
	@Test
	public void testFindNotesByNoteBookId(){
		NoteDao noteDao = ac.getBean("noteDao",NoteDao.class);
		List<Map<String,Object>> list = noteDao.findNotesByNoteBookId("1d46f5db-f569-4c05-bdba-75106108fcba"); 
		for(Map m : list){
			System.out.println(m);
		}
	}
	@Test
	public void testlistNotes(){
		NoteService ns = ac.getBean("noteService",NoteService.class);
		List<Map<String,Object>> list = ns.listNotes("1d46f5db-f569-4c05-bdba-75106108fcba");
		for(Map m : list){
			System.out.println(m);
		}
	}
	@Test
	public void testfindNoteBodyById(){
		NoteDao noteDao = ac.getBean("noteDao",NoteDao.class);
		Note m = noteDao.findNoteBodyById("7e87de1e-0963-4fe0-b996-d0b13481786e");
		System.out.println(m);
	}
	
	@Test
	public void testUpdateNote(){
		NoteDao noteDao = ac.getBean("noteDao",NoteDao.class);
		Note m = new Note();//noteDao.findNoteBodyById("7e87de1e-0963-4fe0-b996-d0b13481786e");
		String str = "<p>一二三四再来一次！1111111111</p>";
		m.setId("7e87de1e-0963-4fe0-b996-d0b13481786e");
		m.setBody(str);
		m.setLastModifyTime(System.currentTimeMillis());
		noteDao.updateNote(m);
	}
	
	@Test
	public void testSaveNote(){
		String id = UUID.randomUUID().toString();
		String userID = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String notebookId = "6d763ac9-dca3-42d7-a2a7-a08053095c08";
		NoteDao noteDao = ac.getBean("noteDao",NoteDao.class);
		long l = System.currentTimeMillis();
		Note m = new Note(id,notebookId,userID,"1",null,"JAVA","java stutsadubgisafihp",l,l);
		noteDao.saveNote(m);
	}
	@Test
	public void testCreateNote(){
		String noteBookId = "b8080f1b-bb1e-4929-853c-b1cd1e2ff7a5";
		String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String title = "";
		NoteService ns = ac.getBean("noteService",NoteService.class);
		ns.createNote(noteBookId, userId, title);
	}
	@Test
	public void testDeleteNotes(){
		String[] id = {"fsaf-as-df-asdf-as-df-dsa","22222222222222222222222222222222222"};
		NoteService noteDao = ac.getBean("noteService",NoteService.class);
		noteDao.deleteNotes(id);
		/*Note note  = noteDao.findNoteBodyById("89732d3a-a85a-4a9c-8618-2b3a9b3c2d65");
		System.out.println(note);*/
	}
	@Test
	public void testCountNotes(){
		List<String> idList = new ArrayList<String>();
		idList.add("ebd65da6-3f90-45f9-b045-782928a5e2c0");
		idList.add("584043b2-5156-44b0-bf7c-5943abd83f5d");
		idList.add("1ec185d6-554a-481b-b322-b562485bb8e8");
		idList.add("054449b4-93d4-4f97-91cb-e0043fc4497f");
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		int c = dao.countNotes(idList);
		System.out.println(c);
	}
	
	@Test
	public void testCountNotesByParams(){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", "39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		param.put("statusId", 1);
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		int count = dao.countNotesByParams(param);
		System.out.println(count);
		
		
		
	}
	
	
	
}
