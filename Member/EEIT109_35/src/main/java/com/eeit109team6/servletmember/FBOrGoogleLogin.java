package com.eeit109team6.servletmember;

import java.io.IOException;

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

@WebServlet("/FBOrGoogleLogin")
public class FBOrGoogleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String type = request.getParameter("type");
		System.out.println("account=" + account);
		System.out.println("username=" + username);

		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, account).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Member mem = context.getBean(Member.class);

		IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
		mem.setAccount(account);
		mem.setPassword(password_AES);
		mem.setType(type);

		System.out.println("account=" + account);
		System.out.println("password_AES=" + password_AES);

		Member member = MemDao.login(mem);

		System.out.println("member != null  =  " + (member != null));
		if (member != null) {
			session.setAttribute("username", member.getUsername());
			session.setAttribute("token", member.getToken());
			session.setAttribute("account", member.getAccount());
			session.setAttribute("member_id", member.getMember_id());

			response.getWriter().write("true");
			return;
//				response.getWriter().write("<script>alert('歡迎光臨');</script>");
//				request.setAttribute("msg", "歡迎光臨");
//				RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
//				rd.forward(request, response);
		} else {

			response.getWriter().write("false");
		}

	}

}
