package com.eeit109team6.memberDetailDao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.eeit109team6.memberDao.Member;

public interface IMemberDetailDao {
	public void add(MemberDetail md);

	public void update(MemberDetail md);

	public void delete(MemberDetail md);

	public ArrayList<MemberDetail> fintAll();

	public MemberDetail fintById(Member m);

}
