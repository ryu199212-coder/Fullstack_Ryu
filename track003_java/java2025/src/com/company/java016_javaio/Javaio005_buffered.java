package com.company.java016_javaio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Javaio005_buffered {
	public static void main(String[] args) throws IOException {

		// 1. 경로
		Calendar today = Calendar.getInstance();
		String folder_rel = "src/com/company/java016_javaio_ex/"; // 상대경로(현재 작업 폴더기준)
		String file_path ="io005_"+ today.get(1) + today.get(3) + today.get(5) + ".txt";
		// 						년				 월 			일
		File folder = new File(folder_rel);
		File file = new File(folder_rel + file_path);

		// 2. 폴더+파일 만들기 [##]
		if (!folder.exists()) {
			folder.mkdir();
		}
		if (!file.exists()) {
					file.createNewFile();
					System.out.println(">1. 폴더+파일 준비완료!");
		}

		// 3. 파일쓰기		InputStream		>	[프로그램]		>	OutputStream #
		// BufferedWriter(속도향상) - OutputStreamWriter(단어) - FileOutputStream(byte)
		// a(입력버퍼) -> a(버퍼비우기), abc(버퍼) -> abc(한번에 모았다가 비우기)
		BufferedWriter bw =
				new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		bw.write("1. write,1200\n");
		bw.write("2. choco,1500\n");
		bw.write("3. banana,1800\n");
		bw.close();
		System.out.println(">2. 쓰기완료");
		
		
		// 4. 파일읽기		InputStream	#	>	[프로그램]		>	OutputStream 
		// BufferedReader(속도향상) - InputStreamReader(단어) - FileInputStream(byte)
		BufferedReader br =
				new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = "";
		StringBuffer sb = new StringBuffer(); //Q.수정되지 않는 버퍼...? ##
		
		while( (line = br.readLine()) != null  ) { sb.append(line+"\n"); }
		System.out.println(sb.toString());
		br.close();
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
