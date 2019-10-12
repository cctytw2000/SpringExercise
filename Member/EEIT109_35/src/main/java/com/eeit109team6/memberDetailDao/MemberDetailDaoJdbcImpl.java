package com.eeit109team6.memberDetailDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eeit109team6.memberDao.Member;
@Transactional
@Repository
public class MemberDetailDaoJdbcImpl implements IMemberDetailDao {

	private Connection conn;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MemberDetailDaoJdbcImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(MemberDetail md)  {
		sessionFactory.getCurrentSession().save(md);
		
	}

	@Override
	public void update(MemberDetail md)  {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(MemberDetail md)  {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<MemberDetail> fintAll()  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDetail fintById(Member m)  {
		List<MemberDetail> memList = null;
		Query query = sessionFactory.getCurrentSession()
				.createQuery("from MemberDetail where memberID = ?1 ");
		System.out.println(" m.getMember_id()="+ m.getMember_id());
		query.setParameter(1, m.getMember_id());
		memList = (List<MemberDetail>) query.getResultList();

		if (memList.size() != 0) {
			memList.get(0);
			return memList.get(0);
		} else {
			return null;
		}
	}

}
