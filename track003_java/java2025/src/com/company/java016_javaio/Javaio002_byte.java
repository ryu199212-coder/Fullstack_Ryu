package com.company.java016_javaio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Javaio002_byte {
	public static void main(String[] args) {
		// 1. 경로
		String folder_rel="src/com/company/java016_javaio_ex/";	// 상대경로(현재 작업 폴더기준)
		String file_path="io002.txt";
		
		File folder = new File(folder_rel);
		File file = new File(folder_rel+file_path);
		
		// 2. 폴더+파일 만들기	(exists, mkdir, createNewFile)
		try {
		if(!folder.exists()) {folder.mkdir();}
		if(!file.exists()) {file.createNewFile();}
		}
		catch(Exception e){e.printStackTrace();}
		
		// 3. byte 파일쓰기	InputStream > [프로그램] > OutputStream #
		try {
			OutputStream output = new FileOutputStream(file);
			output.write('j');
			output.write('a');
			output.write('v');
			output.write('a');
			output.close();
			System.out.println("OutputStream 쓰기완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// 4. byte 파일읽기	InputStream # > [프로그램] > OutputStream #
		try {
			InputStream input = new FileInputStream(file);
			int cnt=0;
			while(( cnt=input.read() ) != -1) {		//read : 못찾으면(마지막 끝) -1 / 끝이 아니라면
			System.out.print((char)cnt);		//저장은 숫자 그래서 타입캐스팅(char)
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}

/*
1. Java IO
- 입력(input)과 출력(output)
- 두 대상간 데이터를 주고 받는것
- 스트림이란? 사용연결통로

			입력스트림		→	[프로그램]		→	출력스트림
				 InputStream		OutputStream
				 Reader				Writer
			
2. Java IO 분류
- byte / char 단위
- byte단위 (InputStream / OutputStream) - 모든종류(그림, 멀티미디어, 문자)
- char단위 (Reader / Writer) - 문자

3. 보조스트림 
--------------------------------------------------------------------------
- new BufferedReader(new InputStreamReader(new FileInputStream(file)))
--------------------------------------------------------------------------
- 1) new FileInputStream(네트워크 or 사용자가 넣어준 값)	 - byte[#]/char 
- 2) new InputStreamReader		 - 바이트를 [문자]스트림으로 - 텍스트처리 가능
- 3) new BufferedReader 		 - 속도향상

*/