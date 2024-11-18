package com.menupick.dinner.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.menupick.common.JDBCTemplate;
import com.menupick.dinner.vo.Address;
import com.menupick.dinner.vo.Book;
import com.menupick.dinner.vo.BookInfo;
import com.menupick.dinner.vo.Dinner;
import com.menupick.member.model.vo.Member;

public class DinnerDao {

	public ArrayList<Dinner> selectAllDinner(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "select * from tbl_dinner order by dinner_name asc";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				// 매장 DB가져오기
				Dinner d = new Dinner();

				d.setDinnerNo(rset.getString("dinner_no")); // 식당코드
				d.setDinnerName(rset.getString("dinner_name")); // 식당이름
				d.setDinnerAddr(rset.getString("dinner_addr")); // 주소
				d.setDinnerEmail(rset.getString("dinner_email")); // 이메일
				d.setDinnerPhone(rset.getString("dinner_phone")); // 매장번호
				d.setDinnerConfirm(rset.getString("dinner_confirm")); // 승인여부
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Dinner> likeDinner(Connection conn, String dinnerNo, String dinnerName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select dinner.dinner_no, dinner.dinner_name, dinner.dinner_addr, food.food_no, food.food_nation, food.food_cat from tbl_dinner dinner, tbl_menu menu, tbl_food food where dinner.dinner_no = menu.dinner_no and food.food_no = menu.food_no order by 1";
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setFoodNation(rset.getString("food_nation"));
				d.setFoodCat(rset.getString("food_cat"));

				dinnerList.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dinnerList;
	}

	// daniel
	public ArrayList<Book> checkReservation(Connection conn, String dinnerNo, String justMonth, String displayYear) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		ArrayList<Book> bookList = new ArrayList<>();
		String query = "select * from tbl_book where dinner_no = ? and extract(month from book_date) = ? and extract(year from book_date) = ?";
		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			pt.setInt(2, Integer.parseInt(justMonth));
			pt.setInt(3, Integer.parseInt(displayYear));
			rt = pt.executeQuery();
			while (rt.next()) {
				Book b = new Book();
				b.setBookNo(rt.getString("book_no"));
				b.setDinnerNo(rt.getString("dinner_no"));
				b.setMemberNo(rt.getString("member_no"));
				b.setBookDate(rt.getString("book_date"));
				b.setBookTime(rt.getString("book_time"));
				b.setBookCnt(rt.getInt("book_cnt"));
				bookList.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return bookList;
	}

	public String convertDateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
		return formatter.format(date);
	}

	// daniel
	public ArrayList<BookInfo> getReservationData(Connection conn, String dinnerNo, String date) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		ArrayList<BookInfo> book = new ArrayList<>();
		String query = "select b.book_no, b.book_date, b.book_cnt, b.book_time, b.member_no, m.member_name, m.member_phone, m.member_email from tbl_book b join tbl_member m on b.member_no = m.member_no where b.dinner_no = ? and b.book_date = ?";
		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			pt.setString(2, date);
			rt = pt.executeQuery();
			while (rt.next()) {
				BookInfo b = new BookInfo();
				b.setBookNo(rt.getString("book_no"));
				b.setMemberNo(rt.getString("member_no"));
				// Fetch `DATE` and convert to `String`
				Date bookDate = rt.getDate("book_date");
				b.setBookDate(convertDateToString(bookDate)); // Format the date to string
				b.setBookTime(rt.getString("book_time"));
				b.setBookCnt(rt.getInt("book_cnt"));
				b.setMemberName(rt.getString("member_name"));
				b.setMemberPhone(rt.getString("member_phone"));
				b.setMemberEmail(rt.getString("member_email"));
				book.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return book;
	}

	public ArrayList<Dinner> filterNation(Connection conn, String foodNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from TBL_FOOD";
		ArrayList<Dinner> foodList = new ArrayList<Dinner>();
		Dinner food = new Dinner();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				food.setFoodNo(rset.getString("FOOD_NO"));
				food.setFoodName(rset.getString("FOOD_NAME"));
				food.setFoodNation(rset.getString("FOOD_NATION"));
				food.setFoodCat(rset.getString("FOOD_CAT"));
				foodList.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return foodList;
	}

	// DB에서 식당 주소 리스트 및 이름 반환
	public ArrayList<Address> getDinnerAddress(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select dinner_name, dinner_addr from tbl_dinner where dinner_confirm='y'";
		ArrayList<Address> addressList = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				String name = rset.getString("dinner_name");
				String addr = rset.getString("dinner_addr");

				// Address 객체 생성 후 리스트에 추가
				addressList.add(new Address(name, addr));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return addressList;
	}

	public Dinner dinnerDetail(Connection conn, String dinnerNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Dinner d = new Dinner();
		String query = "select dinner_no, dinner_name, dinner_addr, dinner_open, dinner_close, dinner_phone, dinner_parking, food_no, food_name, food_nation, food_cat from tbl_dinner, tbl_food where dinner_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("DINNER_NAME"));
				d.setDinnerAddr(rset.getString("DINNER_ADDR"));
				d.setDinnerOpen(rset.getString("DINNER_OPEN"));
				d.setDinnerClose(rset.getString("DINNER_CLOSE"));
				d.setDinnerPhone(rset.getString("DINNER_PHONE"));
				d.setDinnerParking(rset.getString("DINNER_PARKING"));
				d.setFoodNo(rset.getString("FOOD_NO"));
				d.setFoodName(rset.getString("FOOD_NAME"));
				d.setFoodNation(rset.getString("FOOD_NATION"));
				d.setFoodCat(rset.getString("FOOD_CAT"));
				System.out.println(dinnerNo);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return d;
	}

	public Dinner memberLogin(Connection conn, String loginId, String loginPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from tbl_dinner where dinner_id =? and dinner_pw =?";
		Dinner d = null;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return d;
	}

	public Dinner foodDetail(Connection conn, String foodNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Dinner food = new Dinner();
		String query = "select * from tbl_food";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				food.setFoodNo(rset.getString("food_no"));
				food.setFoodName(rset.getString("food_name"));
				food.setFoodCat(rset.getString("food_cat"));
				food.setFoodNation(rset.getString("food_nation"));
			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return food;
	}

	public ArrayList<Dinner> selectAllAdminDinner(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "SELECT * FROM tbl_dinner";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Dinner> searchDinnerByName(Connection conn, String dinnerName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Dinner> dinners = new ArrayList<>();

		String query = "SELECT * FROM tbl_dinner WHERE dinner_name LIKE ? ORDER BY dinner_no";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + dinnerName + "%");
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));

				dinners.add(d);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dinners;
	}

	public List<Dinner> getDinners(Connection conn, int page, int pageSize) {

		PreparedStatement pstmt = null;
		List<Dinner> dinners = new ArrayList<>();
		ResultSet rset = null;

		// Oracle에서의 페이징 쿼리
		String query = "SELECT * FROM ( SELECT a.*, ROW_NUMBER() OVER (ORDER BY dinner_no) AS rnum FROM tbl_dinner a ) WHERE rnum > ? AND rnum <= ?";

		try {
			pstmt = conn.prepareStatement(query);

			// 페이지 시작과 끝 설정
			int startRow = (page - 1) * pageSize; // 페이지의 시작 레코드 위치
			int endRow = page * pageSize; // 페이지의 마지막 레코드 위치

			pstmt.setInt(1, startRow); // 마지막 레코드 위치
			pstmt.setInt(2, endRow); // 시작 레코드 위치

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
				dinners.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dinners;
	}

	public int getTotalDinnerCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = "select count(*) from tbl_dinner";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				return rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return 0;
	}

	public Dinner getDinnerNo(Connection conn, String dinnerNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Dinner d = null;
		String query = "select * from tbl_dinner where dinner_no =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return d;
	}

	public List<Dinner> getDinnersSortedByName(Connection conn, String order) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Dinner> dinners = new ArrayList<>(); // 리스트 초기화

		// order 변수가 'asc' 또는 'desc'인지 확인하여 SQL 구문 작성
		String query = "SELECT * FROM tbl_dinner ORDER BY dinner_name"
				+ ("desc".equalsIgnoreCase(order) ? "DESC" : "ASC");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
				dinners.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dinners;
	}

	public List<Dinner> getDinnersByApproval(Connection conn, String approved) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Dinner> dinners = new ArrayList<>(); // 리스트 초기화

		// order 변수가 'asc' 또는 'desc'인지 확인하여 SQL 구문 작성
		String query = "SELECT * FROM tbl_dinner WHERE dinner_confirm = ?"
				+ ("desc".equalsIgnoreCase(approved) ? "DESC" : "ASC");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("dinner_no"));
				d.setDinnerName(rset.getString("dinner_name"));
				d.setDinnerAddr(rset.getString("dinner_addr"));
				d.setDinnerOpen(rset.getString("dinner_open"));
				d.setDinnerClose(rset.getString("dinner_close"));
				d.setDinnerPhone(rset.getString("dinner_phone"));
				d.setDinnerEmail(rset.getString("dinner_email"));
				d.setDinnerParking(rset.getString("dinner_parking"));
				d.setDinnerMaxPerson(rset.getString("dinner_max_person"));
				d.setBusiNo(rset.getString("busi_no"));
				d.setDinnerId(rset.getString("dinner_id"));
				d.setDinnerPw(rset.getString("dinner_pw"));
				d.setDinnerConfirm(rset.getString("dinner_confirm"));
				dinners.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return dinners;
	}

	// daniel
	public Member getMemberDataForCancelingReservation(Connection conn, String memberNo) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		Member m = new Member();
		String query = "select member_name, member_phone, member_email from tbl_member where member_no = ?";
		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, memberNo);
			rt = pt.executeQuery();
			if (rt.next()) {
				m.setMemberName(rt.getString("member_name"));
				m.setMemberPhone(rt.getString("member_phone"));
				m.setMemberEmail(rt.getString("member_email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return m;
	}

	public int updateDinner(Connection conn, Dinner updDinner) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TBL_DINNER SET DINNER_NAME = ?, DINNER_ADDR  = ?, DINNER_OPEN  = ?,DINNER_CLOSE  = ?, DINNER_PHONE  = ?,DINNER_EMAIL  = ?, BUSI_NO  = ?,DINNER_MAX_PERSON  = ?,DINNER_CONFIRM  = ?,DINNER_NO  = ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1,updDinner.getDinnerName());
			pstmt.setString(2,updDinner.getDinnerAddr());
			pstmt.setString(3,updDinner.getDinnerOpen());
			pstmt.setString(4,updDinner.getDinnerClose());
			pstmt.setString(5,updDinner.getDinnerPhone());
			pstmt.setString(6,updDinner.getDinnerEmail());
			pstmt.setString(7,updDinner.getDinnerParking());
			pstmt.setString(8,updDinner.getBusiNo());
			pstmt.setString(9,updDinner.getDinnerMaxPerson());
			pstmt.setString(10,updDinner.getDinnerConfirm());
			pstmt.setString(11,updDinner.getDinnerNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
