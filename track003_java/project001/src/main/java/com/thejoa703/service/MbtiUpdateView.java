package com.thejoa703.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thejoa703.dto.PostDao;

public class MbtiUpdateView implements MbtiService {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
	
		 int id = Integer.parseInt(request.getParameter("id"));
		 
		 PostDao dao = new PostDao();
	
	     request.setAttribute("dto", dao.select(id));
	}

}
