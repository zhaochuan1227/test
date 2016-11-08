package com.smart.test;

import java.io.FileInputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class MD5TestCase {
	@Test
	public void testMD5() throws Exception {
		String str = "123";
		/**
		 * 参数是算法名
		 * 参数算法名：MD5 sha1 sha256 等,如果算法名错误，则抛出异常
		 */
		MessageDigest md5 = MessageDigest.getInstance("md5");
		//将数据提交到MD5
		byte[] data = str.getBytes("utf-8");
		md5.update(data);
		//md5.update(data);
		//md5.update(data);
		//....
		//获取计算出的摘要
		byte[] md = md5.digest();
		String hex1 = hex(md);
		System.out.println(hex1);
	}
	//将2进制转换为16进制
	public String hex(byte[] ary){
		System.out.println("ary:"+ary);
		char[] chs = new char[ary.length*2]; 
		int i = 0;//代表chs数组的下标
		String digi="0123456789abcdef";
		for(int b:ary){
			b &= 0xff;
			System.out.println("b:"+b);
			int b1 = (b>>>4) & 0xf;
			System.out.println("b1:"+b1);
			int b2 = b & 0xf;
			System.out.println("b2:"+b2);
			char c1 = digi.charAt(b1);
			char c2 = digi.charAt(b2);
			chs[i++] = c1;
			chs[i++] = c2;
		}
		
		return new String(chs);
	}
	
	@Test
	public void testFileMD5() throws Exception{
		FileInputStream in = new FileInputStream("qita.txt");
		byte[] buf = new byte[1024];
		MessageDigest md = MessageDigest.getInstance("md5");
		int n;
		while((n=in.read(buf))!=-1){
			md.update(buf, 0, n);
		}
		in.close();
		byte[] md1 = md.digest();
		//org.apache.commons.codec.binary.Hex;
		String hex = Hex.encodeHexString(md1);
		System.out.println(hex);
	}
	@Test
	public void testMd52(){
		//org.apache.commons.codec.digest.DigestUtils;
		String md = DigestUtils.md5Hex("123456"+"再来一盘哇!");
		System.out.println(md);
	}
	
	@Test
	public void testFileMd52() throws Exception{
		FileInputStream in = new FileInputStream("qita.txt");
		String md = DigestUtils.md5Hex(in);
		System.out.println(md);
	}
}
