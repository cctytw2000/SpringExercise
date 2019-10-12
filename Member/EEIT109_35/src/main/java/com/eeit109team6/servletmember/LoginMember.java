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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;

@WebServlet("/LoginMember")
public class LoginMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private Session hbSession;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		System.out.println("login" + request.getParameter("login"));
		System.out.println("fergetpwd" + request.getParameter("fergetpwd"));
		if (request.getParameter("fergetpwd") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/forgetPWD.html");
			rd.forward(request, response);
			return;
		}

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String type = "General";

		
		if (account == null || account.trim().length() == 0) {
			response.getWriter().write("<script>alert('帳號必須填入');history.go(-1);</script>");
			return;
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (password == null || password.trim().length() == 0) {
			response.getWriter().write("<script>alert('密碼必須填入');history.go(-1);</script>");
			return;
		}

		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, password).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Member mem = context.getBean(Member.class);

		IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
		mem.setAccount(account);
		mem.setPassword(password_AES);
		mem.setType(type);

		try {

			Member member = MemDao.login(mem);

			System.out.println("member != null  =  " + (member != null));
			if (member != null) {
				session.setAttribute("username", member.getUsername());
				session.setAttribute("token", member.getToken());
				session.setAttribute("account", member.getAccount());
				session.setAttribute("member_id", member.getMember_id());
				response.getWriter().write("<script>alert('歡迎光臨');</script>");

				request.setAttribute("msg", "歡迎光臨");
				RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
				rd.forward(request, response);
			} else {

				response.getWriter().write("<script>alert('帳號或密碼錯誤，或者未開通');history.go(-1);</script>");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
