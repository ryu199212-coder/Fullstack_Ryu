package com.company.java016_javaio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class JavaIo004_img {
	public static void main(String[] args) throws IOException {
		//#1.경로            com.company.java016_javaio_ex
		String origin="src/com/company/java016_javaio_ex/bird.jpg";
		String target1="src/com/company/java016_javaio_ex/bird1.jpg";
		String target2="src/com/company/java016_javaio_ex/bird2.jpg";
		
		//#2. byte 이미지파일 원본 읽어들여서 쓰기
		// bird.jpg → bird1.jpg
		// InputStream > [프로그램] > OutputStream
		InputStream bis = new FileInputStream(origin);     //원본 읽어들이기
		OutputStream bos = new FileOutputStream(target1);  //bird1.jpg 쓰기
		
		int cnt1 = 0;
		while((cnt1=bis.read())!=-1) {
			bos.write((byte)cnt1);
		}
		bos.flush(); bos.close(); bis.close();
		System.out.println(">> byte 이미지 복사완료!");
		
		//#3. byte 이미지파일 원본 읽어들여서 쓰기
		// bird.jpg → bird2.jpg
		// Reader > [프로그램] > Writer
		Reader cr = new FileReader(origin);
		Writer cw = new FileWriter(target2);
		
		int cnt2=0;
		while((cnt2=cr.read())!=-1) {//원본 읽어들이기
			cw.write((char)cnt2);    //bird2.jpg 쓰기
		}
		cw.flush(); cw.close(); cr.close();
		System.out.println(">> char 이미지 복사완료!"); //char단위는 복사 불가
	}
}
/*
1. Java Io 
- 입력(input)과 출력(output) 
- 두 대상간의 데이터를 주고 받는것 
- 스트림이란? 사용연결통로
  
    입력스트림     →     [프로그램]     →     [출력스트림]
    	   InputStream        OutStream
    	   Reader             Writer
    	   
2. Java Io 분류 
- byte / char 단위
- byte단위(InputStream / OutStream) - 모든종류(그림, 멀티미디어, 문자)
- char단위(Reader / Writer) - 문자

3. 보조스트림
-----------------------------------------------------------------------
- new BufferedReader(new InputStreamReader(new FileInputStream(file)))
-----------------------------------------------------------------------
- 1) new FileInputStream(네트워크 or 사용자가 넣어준 값)    - byte[#]/char 
- 2) new InputStreamReader       - 바이트를 [문자]스트림으로 - 텍스트처리 가능
- 3) new BufferedReader        - 속도향상

 */