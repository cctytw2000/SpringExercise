package com.eeit109team6.memberDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public class MemberDaoJdbcImpl implements IMemberDao {

	private Connection conn;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MemberDaoJdbcImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int add(Member m) throws SQLException {
		System.out.println("sessionFactory" + sessionFactory);
		sessionFactory.getCurrentSession().save(m);
		return m.getMember_id();

	}

	@Override
	public void update(Member m) throws SQLException {
//		String sqlcode = "update member set  account = ?,password = ?,username = ?,idnumber = ?,sex = ?,birth = ? where member_id = ? ";
//		PreparedStatement state = conn.prepareStatement(sqlcode);
//		state.setString(1, m.getAccount());
//		state.setString(2, m.getPassword());
//		state.setString(3, m.getUsername());
//		state.setString(4, m.getIdnumber());
//		state.setString(5, m.getSex());
//		state.setString(6, m.getBirth());
//		state.setInt(7, m.getMember_id());
//		state.executeUpdate();
//		state.close();
	}

	@Override
	public void delete(Member m) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Member> fintAll() throws SQLException {
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member");
		ArrayList<Member> member=(ArrayList<Member>) query.getResultList();
		return member;
	}

	@Override
	public Member fintById(Member m) throws SQLException {

		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where member_id = ?1 ");
		query.setParameter(1, m.getMember_id());
		memList = (List<Member>) query.getResultList();

		if (memList.size() != 0) {
			memList.get(0);
			return memList.get(0);
		} else {
			return null;
		}

	}

	@Override
	public Member login(Member m) throws SQLException {
		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where account = ?1 and password = ?2 and isactive = 1");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getPassword());
		memList = (List<Member>) query.getResultList();
		if (memList.size() != 0) {

			return memList.get(0);
		} else {
			return null;
		}

	}

	@Override
	public boolean openActive(Member m) throws SQLException {

		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where member_id = ?1 and token = ?2");
		query.setParameter(1, m.getMember_id());
		query.setParameter(2, m.getToken());
		memList = (List<Member>) query.getResultList();

		if (memList.size() != 0) {
			memList.get(0).setIsactive(1);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void forgetPwd(Member m) throws SQLException {

		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession().createQuery("from Member where account = ?1");
		query.setParameter(1, m.getAccount());

		memList = (List<Member>) query.getResultList();

		if (memList.size() != 0) {
			memList.get(0).setToken(m.getToken());
		}

	}

	@Override
	public void changePwd(Member m) throws SQLException {
		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from Member where account =?1  and token = ?2");
		query.setParameter(1, m.getAccount());
		query.setParameter(2, m.getToken());

		memList = (List<Member>) query.getResultList();

		if (memList.size() != 0) {
			memList.get(0).setPassword(m.getPassword());
		}

	}

	@Override
	public boolean checkAccount(Member m) throws SQLException {
		List<Member> memList = null;
		Query query = sessionFactory.getCurrentSession().createQuery("from Member where account =?1");
		query.setParameter(1, m.getAccount());
		memList = (List<Member>) query.getResultList();

		if (memList.size() != 0) {
			return false;
		} else {
			return true;
		}

	}

}
