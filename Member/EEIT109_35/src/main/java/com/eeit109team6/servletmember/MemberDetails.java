package com.eeit109team6.servletmember;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;
import com.eeit109team6.memberDetailDao.IMemberDetailDao;
import com.eeit109team6.memberDetailDao.MemberDetail;

@WebServlet("/MemberDetails")
public class MemberDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println("session=" + session);
		if (session == null) { // 使用逾時
			System.out.println("session == null");
			response.sendRedirect(getServletContext().getContextPath() + "/home.jsp");
			return;
		}
		if (session.getAttribute("account") == null) {
			System.out.println("session.getAttribute(\"account\").toString() == null");

			response.sendRedirect(getServletContext().getContextPath() + "/home.jsp");
			return;
		}
//		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("account"));

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Member mem = context.getBean(Member.class);
		mem.setAccount(session.getAttribute("account").toString());
		mem.setToken(session.getAttribute("token").toString());
		mem.setMember_id(Integer.parseInt(session.getAttribute("member_id").toString()));
		mem.setUsername(session.getAttribute("username").toString());

		IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");

		Member member = MemDao.fintById(mem);

		request.setAttribute("memberDetail", member.getMemberdetail());
		request.setAttribute("member", member);
		RequestDispatcher rd = request.getRequestDispatcher("member/memberDetails.jsp");

		rd.forward(request, response);

	}

}
