package com.company.java016_javaio;


public class Javaio006_stringbuffer {
	public static void main(String[] args) {
		// 1. String		문자열 누적시 - 새로운주소 (주소값 변형x)
		String str = "ABC";	// 1번지 = 1번지["abc"] heap
		System.out.println("1.Str주소 > " + str + System.identityHashCode(str));
		
		str += "D";			// 2번지 = 2번지["abcd"] heap
		System.out.println("2.Str주소 > " + str + System.identityHashCode(str));
		

		
		// 2. StringBuffer	문자열 누적시 - 기존주소 (주소값 변형o)
		StringBuffer sb = new StringBuffer();
		sb.append("ABC");
		System.out.println("3.sb주소 > " + sb + System.identityHashCode(sb));
		
		sb.append("D");
		System.out.println("4.sb주소 > " + sb + System.identityHashCode(sb));
		
		// 3. 
		
		// 4. 

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
