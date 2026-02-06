package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thejoa703.dto.PostDao;
import com.thejoa703.dto.PostDto;

public class MbtiDelete implements MbtiService {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 	request.setCharacterEncoding("UTF-8");
	        int app_user = (Integer)session.getAttribute("APP_USER_ID");
	        String postId = request.getParameter("id");
	        String pass = request.getParameter("pass");

	        PostDto dto = new PostDto();
	        dto.setId(Integer.parseInt(postId));
	        dto.setAppUserId(app_user);
	        dto.setPass(pass);

	        PostDao dao = new PostDao();
	        int result = dao.delete(dto);
	        
	        request.setAttribute("result", result);
	}

}
