package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejoa703.dto.PostDao;
import com.thejoa703.dto.PostDto;

public class MbtiInsert implements MbtiService {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 데이터 넘겨받기 X
		//int app_user = Integer.parseInt(request.getParameter("APP_USER_ID"));
		HttpSession session = request.getSession();
		int app_user = (Integer)session.getAttribute("APP_USER_ID");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pass = request.getParameter("pass");
		//2. 드커프리(PostDao)
		PostDao dao = new PostDao();
		PostDto dto = new PostDto();
		
		dto.setAppUserId(app_user); 
		dto.setTitle(title); dto.setContent(content); dto.setPass(pass); 
		String result = String.valueOf(dao.insert(dto)); 
		//3. 데이터 넘겨주기
		request.setAttribute("result", result);
		
	}

}
