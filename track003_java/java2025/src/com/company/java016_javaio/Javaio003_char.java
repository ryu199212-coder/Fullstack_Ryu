package com.company.java016_javaio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Javaio003_char {
	public static void main(String[] args) {
		// #1. 경로
		String folder_rel = "src/com/company/java016_javaio_ex/";
		String file_path = "io003.txt";

		File folder = new File(folder_rel);
		File file = new File(folder_rel + file_path);

		// #2. 폴더 + 파일만들기
		if (!folder.exists()) {
			folder.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
				System.out.println("1) 폴더/파일 만들기");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// #3. char 쓰기 Reader > [프로그램] > Writer #
		try {
			Writer writer = new FileWriter(file);
			writer.write("hello\n"); // 버퍼에 저장
			writer.write("java\n"); // 버퍼에 저장
			writer.flush(); // 버퍼(임시저장공간)에 저장된 데이터를 강제로 출력스트림에 밀어내기
							// 버퍼에 있는 내용을 즉시 파일에 쓰기.
			writer.close(); // 쓰기스트림 버퍼 닫기
			System.out.println("writer 쓰기완료");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// #4. char 읽기 Reader # > [프로그램] > Writer
		try {
			Reader reader = new FileReader(file);
			int cnt = 0;
			
				while ((cnt = reader.read()) != -1) {
					System.out.println((char) cnt);
				}
				reader.close();
			}catch (FileNotFoundException e) { e.printStackTrace(); 
			}catch (IOException e) { e.printStackTrace(); }
		  

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