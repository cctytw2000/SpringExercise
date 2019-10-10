package com.eeit109team6.servletmember;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutMember
 */
@WebServlet("/LogOutMember")
public class LogOutMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		
		if (session != null) {
		    session.invalidate();
			response.getWriter().write("<script>alert('已登出');</script>");
			response.sendRedirect(getServletContext().getContextPath()+"/home.jsp");
		}else {
			response.getWriter().write("<script>alert('您尚未登入');");
			response.sendRedirect(getServletContext().getContextPath()+"/home.jsp");
		}
	}



}
