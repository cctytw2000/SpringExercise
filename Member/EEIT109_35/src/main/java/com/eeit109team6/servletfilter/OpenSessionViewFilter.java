package com.eeit109team6.servletfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@WebFilter("/OpenSessionViewFactory")
public class OpenSessionViewFilter implements Filter {

	private SessionFactory sessionFactory;
	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		SessionFactory Sessionfactory = HibernateUtil.getSessionfactory();
//		try {
//			Sessionfactory.getCurrentSession().beginTransaction();
//			System.out.println("Transaction Begin");
			chain.doFilter(request, response);
//			Sessionfactory.getCurrentSession().getTransaction().commit();
//			System.out.println("Transaction Commit");
//		} catch (Exception e) {
//			Sessionfactory.getCurrentSession().getTransaction().rollback();
//			System.out.println("Transaction Rollback");
//			e.printStackTrace();
//		} finally {
//			Sessionfactory.getCurrentSession().close();
//			System.out.println("Transaction Closed");
//		}
		

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		this.config = filterConfig;
//		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
//		sessionFactory = (SessionFactory)context.getBean("sessionFactory", SessionFactory.class);

		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
