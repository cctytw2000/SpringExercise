package com.eeit109team6.servletmember;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.annotation.Resource;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eeit109team6.memberDao.IMemberDao;
import com.eeit109team6.memberDao.Member;

@WebServlet("/RegisteredMember")
public class RegisteredMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/team6project")
	private DataSource ds;
	Connection conn;
	private SessionFactory sessionFactory;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// ==============================取值=======================
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String username = request.getParameter("username");

		// ==============================/取值=======================


		// ===============================處理資料====================
		if (account != "" && password != "") {

			// ==============設定創建帳號時間=======================
			Calendar rightNow = Calendar.getInstance();
			String registeredtime = rightNow.get(Calendar.YEAR) + "-" + (rightNow.get(Calendar.MONTH) + 1) + "-"
					+ rightNow.get(Calendar.DATE) + " " + rightNow.get(Calendar.HOUR) + ":"
					+ rightNow.get(Calendar.MINUTE) + ":" + rightNow.get(Calendar.SECOND);
			// ==============/設定創建帳號時間=======================

			// ==============密碼加密=======================
			int isactive = 0;
			String key = "MickeyKittyLouis";
			String password_AES = CipherUtils.encryptString(key, password).replaceAll("[\\pP\\p{Punct}]", "")
					.replace(" ", "");
			// ==============/密碼加密=======================

			// ===============設定member物件============
//			Member mem = new Member();

			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			Member mem = context.getBean(Member.class);

			mem.setAccount(account);
			mem.setPassword(password_AES);
			mem.setUsername(username);

			mem.setRegisteredtime(registeredtime);
			mem.setIsactive(isactive);
			// ===============/設定member物件============

			// ==============設定token====================
			KeyGenerator keyGen;
			try {
				keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(256, new SecureRandom());
				SecretKey secretKey = keyGen.generateKey();
				byte[] iv = new byte[16];
				SecureRandom prng = new SecureRandom();
				prng.nextBytes(iv);
				Long math = Long.valueOf((long) (Math.random() * 999999999));
				String token_notformat = AES_CBC_PKCS5PADDING.Encrypt(secretKey, iv, math.toString());
				String token = token_notformat.replaceAll("[\\pP\\p{Punct}]", "").replace(" ", "");
				mem.setToken(token);
			} catch (Exception e) {

				e.printStackTrace();
			}
			// ==============/設定token====================

			// ==============寫進資料庫===================

			IMemberDao MemDao = (IMemberDao) context.getBean("memberDaoJdbcImpl");
			
			
		
			ArrayList<Member> members = null ;
			Integer memberId = null;
			try {
				memberId = MemDao.add(mem);
				members =MemDao.fintAll();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (Member member:members) {
				System.out.println("帳號="+member.getAccount());
			}
			
			
			
			String email = null;
			String pwd = null;

			BufferedReader bfr = new BufferedReader(new FileReader("C:\\sqldata\\sqldata.txt"));
			String data;

			while ((data = bfr.readLine()) != null) {
				System.out.println(data);
				email = data.split(",")[0];
				pwd = data.split(",")[1];
			}

			bfr.close();

			final String Email = email;// your Gmail
			final String EmailPwd = pwd;// your password
			String host = "smtp.gmail.com";
			int port = 587;

			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", port);
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(Email, EmailPwd);
				}
			});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(Email));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(account));
				message.setSubject("驗證信");
				message.setText("Wellcome To FootBook \n http://localhost:8080/Member/member/insertMemberDetail.jsp?id=" + memberId
						+ "&token=" + mem.getToken());

				Transport transport = session.getTransport("smtp");
				transport.connect(host, port, Email, EmailPwd);

				Transport.send(message);

				System.out.println("寄送email結束.");
				request.setAttribute("msg", "請至您輸入的信箱收取驗證信輸入完整資料開通帳號");
				RequestDispatcher rd = request.getRequestDispatcher("member/jump.jsp");
				rd.forward(request, response);
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		} else {
			System.out.println("註冊  else");
			PrintWriter out = response.getWriter();
			out.write("<script>alert('你是不是想找麻煩!');history.go(-1);</script>");
			out.close();
		} 
	
		
		// ===============================/處理資料====================

	}

}
