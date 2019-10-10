package com.eeit109team6.memberDao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IMemberDao {

	public int add(Member m) throws SQLException;

	public void update(Member m) throws SQLException;

	public void delete(Member m) throws SQLException;

	public ArrayList<Member> fintAll() throws SQLException;

	public Member fintById(Member m) throws SQLException;

	public Member login(Member m) throws SQLException;

	public boolean openActive(Member m) throws SQLException;

	public void forgetPwd(Member m) throws SQLException;

	public void changePwd(Member m) throws SQLException;

	public boolean checkAccount(Member m) throws SQLException;

}
