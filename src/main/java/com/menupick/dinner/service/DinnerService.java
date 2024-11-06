package com.menupick.dinner.service;

import java.sql.Connection;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> dev

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.dao.DinnerDao;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Dinner;

public class DinnerService {
	DinnerDao dao;

	public DinnerService() {
		dao = new DinnerDao();
	}

	public ArrayList<Dinner> likeDinner(String dinnerNo, String dinnerName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> dinnerList = null;
		dinnerList = dao.likeDinner(conn, dinnerNo, dinnerName);
		JDBCTemplate.close(conn);
		System.out.println(dinnerList);
		return dinnerList;
	}

<<<<<<< HEAD
	public ArrayList<Address> getDinnerAddress() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Address> addList = dao.getDinnerAddress(conn);
		
		JDBCTemplate.close(conn);
		return addList;
	}

=======
	public ArrayList<Dinner> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Dinner> list = dao.selectAllMember(conn);
		JDBCTemplate.close(conn);

		return list;
	}

	
>>>>>>> dev
}
