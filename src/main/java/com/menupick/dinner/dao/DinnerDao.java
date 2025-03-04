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
import com.menupick.dinner.vo.Menu;
import com.menupick.dinner.vo.Photo;
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

	public ArrayList<Dinner> likeDinner(Connection conn) {
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

	// daniel
	public int dinnerCancelReservaion(Connection conn, String bookNo) {
		PreparedStatement pt = null;
		int result = -1;
		String qeury = "delete from tbl_book where book_no = ?";
		try {
			pt = conn.prepareStatement(qeury);
			pt.setString(1, bookNo);
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pt);
		}
		return result;
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

	// 식당등록 (경래 + daniel)
	public boolean insertDinner(Connection conn, Dinner dinner) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO tbl_dinner VALUES ('d' || to_char(sysdate, 'yymmdd') || lpad (seq_dinner.nextval, 4, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'n')";
		boolean result = false;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinner.getDinnerName());
			pstmt.setString(2, dinner.getDinnerAddr());
			pstmt.setString(3, dinner.getDinnerOpen());
			pstmt.setString(4, dinner.getDinnerClose());
			pstmt.setString(5, dinner.getDinnerPhone());
			pstmt.setString(6, dinner.getDinnerEmail());
			pstmt.setString(7, dinner.getDinnerParking());
			pstmt.setString(8, dinner.getDinnerMaxPerson());
			pstmt.setString(9, dinner.getBusiNo());
			pstmt.setString(10, dinner.getDinnerId());
			pstmt.setString(11, dinner.getDinnerPw());

			int rowsAffected = pstmt.executeUpdate();
			result = rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// 식당등록 아이디 중복체크 (경래)
	public int idDuplChk(Connection conn, String dinnerId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) as cnt " + "from (" + "    select dinner_id as id from tbl_dinner "
				+ "    union all " + "    select member_id as id from tbl_member" + ") all_ids " + "where id = ?";
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerId); // 입력받은 ID를 쿼리에 바인딩
			rset = pstmt.executeQuery();

			if (rset.next()) {
				cnt = rset.getInt("cnt"); // 중복된 ID 개수를 가져옴
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return cnt; // 중복된 ID 개수 반환
	}

	public int updateDinner(Connection conn, Dinner updDinner) {
		PreparedStatement pstmt = null;
		int result = 0;

		// SQL 구문 정의
		String query = "UPDATE TBL_DINNER " + "SET dinner_name = ?, dinner_addr = ?, dinner_open = ?, dinner_id = ?, "
				+ "dinner_close = ?, dinner_phone = ?, dinner_email = ?, dinner_parking = ?, "
				+ "busi_no = ?, dinner_max_person = ?, dinner_confirm = ? " + "WHERE LOWER(dinner_no) = LOWER(?)";

		try {
			// 1. DINNER_ID 값 검증
			if (updDinner.getDinnerId() == null || updDinner.getDinnerId().trim().isEmpty()) {
				throw new IllegalArgumentException("DINNER_ID cannot be null or empty.");
			}

			pstmt = conn.prepareStatement(query);

			// 2. 매개변수 설정
			pstmt.setString(1, updDinner.getDinnerName());
			pstmt.setString(2, updDinner.getDinnerAddr());
			pstmt.setString(3, updDinner.getDinnerOpen());
			pstmt.setString(4, updDinner.getDinnerId()); // NOT NULL 필드
			pstmt.setString(5, updDinner.getDinnerClose());
			pstmt.setString(6, updDinner.getDinnerPhone());
			pstmt.setString(7, updDinner.getDinnerEmail());

			if (!"y".equalsIgnoreCase(updDinner.getDinnerParking())
					&& !"n".equalsIgnoreCase(updDinner.getDinnerParking())) {
				throw new IllegalArgumentException("Invalid value for dinner_parking: " + updDinner.getDinnerParking());
			}
			pstmt.setString(8, updDinner.getDinnerParking().toLowerCase());

			pstmt.setString(9, updDinner.getBusiNo());
			pstmt.setInt(10, Integer.parseInt(updDinner.getDinnerMaxPerson()));
			pstmt.setString(11, updDinner.getDinnerConfirm().toLowerCase());
			pstmt.setString(12, updDinner.getDinnerNo());

			// 3. SQL 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			System.err.println("SQL Query: " + query);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.err.println("Validation Error: " + e.getMessage());
			throw e; // 필요 시 재전달
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	// daniel
	public BookInfo bookInfoForCancelEmail(Connection conn, String bookNo) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		BookInfo b = new BookInfo();
		String query = "select m.member_name, m.member_email, b.book_date, b.book_time, d.dinner_name from tbl_book b join tbl_member m on b.member_no = m.member_no join tbl_dinner d on b.dinner_no = d.dinner_no where b.book_no = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, bookNo);
			rt = pt.executeQuery();

			if (rt.next()) {
				b.setBookDate(rt.getDate("book_date").toString());
				b.setBookTime(rt.getString("book_time"));
				b.setMemberName(rt.getString("member_name"));
				b.setMemberEmail(rt.getString("member_email"));
				b.setDinnerName(rt.getString("dinner_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return b;
	}

	public int deleteDinner(Connection conn, String dinnerNo) {
		PreparedStatement pt = null;
		int result = 0;
		String query = "DELETE FROM TBL_DINNER WHERE DINNER_NO = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);

			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pt);
		}
		return result;
	}

	public int updateMemberPw(Connection conn, String dinnerId, String newDinnerPw) {
		PreparedStatement pt = null;
		int result = 0;
		String query = "update tbl_dinner set dinner_pw = ? where dinner_id = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, newDinnerPw);
			pt.setString(2, dinnerId);

			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pt);
		}

		return result;
	}

	public Dinner getDinnerByNo(Connection conn, String dinnerNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dinner dinner = null;

		String query = "SELECT * FROM TBL_DINNER WHERE LOWER(dinner_no) = LOWER(?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dinner = new Dinner();
				dinner.setDinnerNo(rs.getString("dinner_no"));
				dinner.setDinnerName(rs.getString("dinner_name"));
				dinner.setDinnerId(rs.getString("dinner_id"));
				dinner.setDinnerAddr(rs.getString("dinner_addr"));

				// 시간 포맷팅 추가
				dinner.setDinnerOpen(formatTimeWithColon(rs.getString("dinner_open")));
				dinner.setDinnerClose(formatTimeWithColon(rs.getString("dinner_close")));

				dinner.setDinnerPhone(rs.getString("dinner_phone"));
				dinner.setDinnerEmail(rs.getString("dinner_email"));
				dinner.setDinnerParking(rs.getString("dinner_parking"));
				dinner.setBusiNo(rs.getString("busi_no"));
				dinner.setDinnerMaxPerson(rs.getString("dinner_max_person"));
				dinner.setDinnerConfirm(rs.getString("dinner_confirm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return dinner;
	}

	// 시간 데이터 포맷팅 메서드
	private String formatTimeWithColon(String time) {
		if (time != null && time.length() == 4) {
			return time.substring(0, 2) + ":" + time.substring(2); // "1200" -> "12:00"
		}
		return time;
	}

	public List<Menu> getMenuByDinnerNo(Connection conn, String dinnerNo, String foodNo) {
		List<Menu> menuList = new ArrayList<>();
		String sql = "SELECT m.dinner_no, m.food_no, m.price, f.food_name " + "FROM tbl_menu m "
				+ "JOIN tbl_food f ON m.food_no = f.food_no " + "WHERE m.dinner_no = ? AND m.food_no = ?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, dinnerNo);
			pstmt.setString(2, foodNo);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Menu menu = new Menu();
					menu.setDinnerNo(rs.getString("dinner_no"));
					menu.setFoodNo(rs.getString("food_no"));
					menu.setPrice(rs.getInt("price"));
					menu.setFoodName(rs.getString("food_name"));
					menuList.add(menu);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	// daniel
	public String getDinnerNoById(Connection conn, String dinnerId) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		String dinnerNo = "";
		String query = "select dinner_no from tbl_dinner where dinner_id = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerId);
			rt = pt.executeQuery();

			if (rt.next()) {
				dinnerNo = rt.getString("dinner_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return dinnerNo;
	}

	// daniel
	public int insertDinnerPhoto(Connection conn, Photo p) {
		PreparedStatement pt = null;
		int result = -1;
		String query = "insert into tbl_photo values ('p' || to_char(sysdate, 'yymmdd') || lpad (seq_photo.nextval, 4, '0'), ?, null, ?, ?)";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, p.getDinnerNo());
			pt.setString(2, p.getPhotoName());
			pt.setString(3, p.getPhotoPath());
			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pt);
		}
		return result;
	}

	// daniel
	public String dinnerPhotoPath(Connection conn, String dinnerNo) {
		PreparedStatement pt = null;
		ResultSet rt = null;
		String photoPath = "";
		String query = "select photo_path from tbl_photo where dinner_no = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			rt = pt.executeQuery();

			while (rt.next()) {
				photoPath = rt.getString("photo_path");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rt);
			JDBCTemplate.close(pt);
		}
		return photoPath;
	}

	// daniel
	public int updateDinnerPhoto(Connection conn, String dinnerNo, ArrayList<Photo> photoList) {
		PreparedStatement pt = null;
		int result = 0;
		String query = "update tbl_photo set photo_name = ?, photo_path = ? where dinner_no = ?";
		Photo p = photoList.get(0);

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, p.getPhotoName());
			pt.setString(2, p.getPhotoPath());
			pt.setString(3, dinnerNo);

			result = pt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pt);
		}
		return result;
	}

	public List<Menu> getMenuDetailsByDinnerNo(Connection conn, String dinnerNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Menu> menuList = new ArrayList<>();

		// SQL 쿼리
		String query = "SELECT m.food_no, f.food_name, f.food_cat, m.price " + "FROM tbl_menu m "
				+ "JOIN tbl_food f ON m.food_no = f.food_no " + "WHERE m.dinner_no = ?";

		try {
			// PreparedStatement 생성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dinnerNo); // 파라미터 바인딩
			rs = pstmt.executeQuery(); // 쿼리 실행

			// ResultSet 처리
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setFoodNo(rs.getString("food_no"));
				menu.setFoodName(rs.getString("food_name"));
				menu.setFoodCat(rs.getString("food_cat"));
				menu.setPrice(rs.getInt("price"));

				menuList.add(menu); // 결과 리스트에 추가
			}
		} catch (SQLException e) {
			// 예외 처리
			e.printStackTrace();
		} finally {
			// 리소스 정리
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return menuList; // 결과 반환
	}
}
