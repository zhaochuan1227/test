package com.smart.test;



import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.dao.NoteBookDao;
import com.smart.entity.NoteBook;
import com.smart.service.NoteBookService;

public class NoteBookTestCase {
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
	public void testfindNoteBooksByUserId(){
		NoteBookDao nbd = ac.getBean("noteBookDao",NoteBookDao.class);
		List<Map<String,Object>> ls = nbd.findNoteBooksByUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Map<String,Object> m : ls){
			System.out.println(m);
		}
	}
	@Test
	public void testlistNoteBooks(){
		NoteBookService nbs = ac.getBean("noteBookService",NoteBookService.class);
		List<Map<String,Object>> ls = nbs.listNoteBooks("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		for(Map<String,Object> m : ls){
			System.out.println(m);
		}
	}
	@Test
	public void testfindNoteBooksById(){
		NoteBookDao nbd = ac.getBean("noteBookDao",NoteBookDao.class);
		NoteBook n = nbd.findNoteBooksById("0b11444a-a6d6-45ff-8d46-282afaa6a655");
		System.out.println(n);
	}
	@Test
	public void testSaveNoteBook(){
		NoteBookDao nbd = ac.getBean("noteBookDao",NoteBookDao.class);
		NoteBook n = nbd.findNoteBooksById("0b11444a-a6d6-45ff-8d46-282afaa6a655");
		String id = UUID.randomUUID().toString();
		NoteBook n1 = nbd.findNoteBooksById(id);
		if(n1==null){
			n.setId(id);
			n.setName("你是我的小呀小苹果");
			System.out.println(n);
			nbd.saveNoteBook(n);
		}
		
	}
	
	@Test
	public void testDeleteNoteBook(){
		NoteBookService nbs = ac.getBean("noteBookService",NoteBookService.class);
		nbs.deleteNoteBook("1d46f5db-f569-4c05-bdba-75106108fcba");
	}
}
