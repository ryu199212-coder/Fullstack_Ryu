package com.thejoa703.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class PagingDto {
	private int listTotal; 	 //1. 전체글 512개
	private int onepagelist; //2. 한페이지에 보여줄 게시물 수
	private int pagetotal;   //3. 총 페이지 512-10 = 50(51페이지+2글) 
	private int bottomlist;  //4. 하단의 페이지 나누기 (1 2 3 ... 11 12 13 ...)
	private int pstartno;    //5. 페이지 시작번호 - 스타트 번호
	
	private int current;	 //6. 현재 페이지 번호
	private int start;		 //7. 시작 페이지 번호
	private int end;	 	 //8. 끝 페이지 번호
	public PagingDto(int listtotal, int pstartno) { // 전체 페이지 수, 시작번호
		super();
		this.listTotal = listtotal; 	// 전체 페이지 수
		this.onepagelist = 10;			// 한페이지에 보여줄 게시물 수 10 
		if(listtotal <= 0) {listtotal=1;}
		this.pagetotal = (int)Math.ceil(listtotal/(double)onepagelist);
						// 무조건 올림처리
		this.bottomlist = 10;
		this.pstartno 	= (pstartno-1) * onepagelist +1;       // (1)1,10    (2)11,20   (3)21,30   (4)31,40   (5)41,60
		this.current 	= pstartno;       
		this.start 		= ((current-1)/bottomlist) * bottomlist + 1;
		//		1   2   3   4   5   6   7   8   9  10
		//이전  11  12  13  14  15  16  17  18  19  20  다음
		this.end 		= start + bottomlist -1;
		if(end > pagetotal) {end=pagetotal;}
	}	
} 
 
	 