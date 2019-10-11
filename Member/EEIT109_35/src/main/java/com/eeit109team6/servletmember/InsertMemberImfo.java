package com.eeit109team6.servletmember;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Calendar;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;
import com.eeit109team6.memberDetailDao.IMemberDetailDao;
import com.eeit109team6.memberDetailDao.MemberDetail;

@WebServlet("/InsertMemberImfo")
public class InsertMemberImfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String memberID = request.getParameter("memberID");
		String token = request.getParameter("token");

		String account = request.getParameter("account");
		String username = request.getParameter("username");
		String idnumber = request.getParameter("idnumber");
		String alladdress = request.getParameter("alladdress");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");

		if (memberID != null && token != null) {

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			Member mem = context.getBean(Member.class);
			IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");

			IMemberDetailDao MDDao = (IMemberDetailDao) context.getBean("memberDetailDaoJdbcImpl");

			MemberDetail md = context.getBean(MemberDetail.class);
			mem.setMember_id(Integer.valueOf(memberID));
			mem.setToken(token);
			md.setAddress(alladdress);
			md.setBirth(birth);
			md.setIdnumber(idnumber);
			md.setSex(sex);

			try {
				Member member = MemDao.fintById(mem);
				md.setMember(member);
				MDDao.add(md);
				System.out.println("資料新增完成");
				MemDao.openActive(member);
				request.setAttribute("msg", "已開通帳號請至登入頁面登入");
				RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
				rd.forward(request, response);
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

			// ==============設定創建帳號時間=======================
			Calendar rightNow = Calendar.getInstance();
			String registeredtime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
					+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":"
					+ rightNow.get(Calendar.MINUTE) + ":" + rightNow.get(Calendar.SECOND);
			// ==============/設定創建帳號時間=======================

			// ==============密碼加密=======================
			int isactive = 0;
			String key = "MickeyKittyLouis";
			String password_AES = CipherUtils.encryptString(key, account).replaceAll("[\\pP\\p{Punct}]", "")
					.replace(" ", "");
			// ==============/密碼加密=======================

			// ==============設定token====================
			KeyGenerator keyGen;
			String tokenFormat = null;
			try {
				keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(256, new SecureRandom());
				SecretKey secretKey = keyGen.generateKey();
				byte[] iv = new byte[16];
				SecureRandom prng = new SecureRandom();
				prng.nextBytes(iv);
				Long math = Long.valueOf((long) (Math.random() * 999999999));
				String token_notformat = AES_CBC_PKCS5PADDING.Encrypt(secretKey, iv, math.toString());
				tokenFormat = token_notformat.replaceAll("[\\pP\\p{Punct}]", "").replace(" ", "");

			} catch (Exception e) {

				e.printStackTrace();
			}
			// ==============/設定token====================

			IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
			IMemberDetailDao MDDao = (IMemberDetailDao) context.getBean("memberDetailDaoJdbcImpl");

			MemberDetail md = context.getBean(MemberDetail.class);
			Member mem = context.getBean(Member.class);

			mem.setAccount(account);
			mem.setUsername(username);
			mem.setIsactive(0);
			mem.setPassword(password_AES);
			mem.setRegisteredtime(registeredtime);
			mem.setToken(tokenFormat);

			md.setAddress(alladdress);
			md.setBirth(birth);
			md.setIdnumber(idnumber);
			md.setSex(sex);

			System.out.println("account:" + mem.getAccount());
			System.out.println("password:" + mem.getPassword());
			System.out.println("username:" + mem.getUsername());
			System.out.println("sex:" + md.getSex());
			System.out.println("address:" + md.getAddress());

			mem.setMemberdetail(md);
			md.setMember(mem);

			try {
				int insertMemberId = MemDao.add(mem);
				mem.setMember_id(insertMemberId);
				MemDao.openActive(mem);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("msg", "已完成輸入會員資料及開通帳號");
			RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
			rd.forward(request, response);

		}
	}

}
