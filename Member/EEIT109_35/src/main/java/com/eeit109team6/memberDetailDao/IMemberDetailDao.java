package com.eeit109team6.memberDetailDao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.eeit109team6.memberDao.Member;

public interface IMemberDetailDao {
	public void add(MemberDetail md) throws SQLException;

	public void update(MemberDetail md) throws SQLException;

	public void delete(MemberDetail md) throws SQLException;

	public ArrayList<MemberDetail> fintAll() throws SQLException;

	public MemberDetail fintById(Member m) throws SQLException;

}
