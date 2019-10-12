package com.eeit109team6.servletmember;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;

@WebServlet("/forgetPWDInsertNewPassowrd")
public class forgetPWDInsertNewPassowrd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private Session hbSession;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String token = request.getParameter("token");
		String newpassword = request.getParameter("newpassword");
		String oldpassword = request.getParameter("oldpassword");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("account=" + account);
		System.out.println("token=" + token);
		System.out.println("newpassword=" + newpassword);

		String key = "MickeyKittyLouis";
		String password_AES = CipherUtils.encryptString(key, newpassword).replaceAll("[\\pP\\p{Punct}]", "").replace(" ",
				"");

		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Member mem = context.getBean(Member.class);
		IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
		
		
		
		
		mem.setAccount(account);
		mem.setToken(token);
		mem.setPassword(password_AES);
		mem.setType("General");
		

		try {
			

			MemDao.changePwd(mem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		   request.setAttribute("msg", "請使用新的密碼登入帳號");
		   RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
		   rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
