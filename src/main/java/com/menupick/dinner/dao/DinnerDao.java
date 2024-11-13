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
import com.menupick.dinner.vo.Dinner;
<<<<<<< HEAD

=======
import com.menupick.dinner.vo.DinnerFoodDetail;
import com.menupick.dinner.vo.Food;
import com.menupick.member.model.vo.Member;
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d

public class DinnerDao {

	public ArrayList<Dinner> selectAllDinner(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Dinner> list = new ArrayList<>();
		String query = "SELECT * FROM tbl_dinner ORDER BY dinner_name ASC";

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
		String query = "select * from tbl_menu, tbl_dinner, tbl_food";
		ArrayList<Dinner> dinnerList = new ArrayList<Dinner>();

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Dinner d = new Dinner();
				d.setDinnerNo(rset.getString("DINNER_NO"));
				d.setDinnerName(rset.getString("DINNER_NAME"));
				d.setDinnerAddr(rset.getString("DINNER_ADDR"));
				d.setDinnerOpen(rset.getString("DINNER_OPEN"));
				d.setDinnerClose(rset.getString("DINNER_CLOSE"));
				d.setDinnerPhone(rset.getString("DINNER_PHONE"));
				d.setDinnerEmail(rset.getString("DINNER_EMAIL"));
				d.setDinnerParking(rset.getString("DINNER_PARKING"));
				d.setDinnerMaxPerson(rset.getString("DINNER_MAX_PERSON"));
				d.setBusiNo(rset.getString("BUSI_NO"));
				d.setDinnerId(rset.getString("DINNER_ID"));
				d.setDinnerPw(rset.getString("DINNER_PW"));
				d.setDinnerConfirm(rset.getString("DINNER_CONFIRM"));
				d.setFoodNo(rset.getString("FOOD_NO"));
				d.setFoodName(rset.getString("FOOD_NAME"));
				d.setFoodNation(rset.getString("FOOD_NATION"));
				d.setFoodCat(rset.getString("FOOD_CAT"));

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

<<<<<<< HEAD
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
=======
	public ArrayList<Food> filterNation(Connection conn, String foodNo) {
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
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
<<<<<<< HEAD
				d.setFoodNo(rset.getString("FOOD_NO"));
				d.setFoodName(rset.getString("FOOD_NAME"));
				d.setFoodNation(rset.getString("FOOD_NATION"));
				d.setFoodCat(rset.getString("FOOD_CAT"));
				System.out.println(dinnerNo);
=======
				System.out.println(d);

>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
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

<<<<<<< HEAD
	public Dinner foodDetail(Connection conn, String foodNo) {
=======


	

	public String convertDateToString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
		return formatter.format(date);
	}

	public ArrayList<Book> getReservationData(Connection conn, String dinnerNo, String date) {

		PreparedStatement pt = null;
		ResultSet rt = null;
		ArrayList<Book> book = new ArrayList<Book>();
		Book b = null;
		String query = "select * from tbl_book where dinner_no = ? and book_date = ?";

		try {
			pt = conn.prepareStatement(query);
			pt.setString(1, dinnerNo);
			pt.setString(2, date);
			rt = pt.executeQuery();

			while (rt.next()) {
				b = new Book();
				b.setBookNo(rt.getString("book_no"));
				b.setDinnerNo(rt.getString("dinner_no"));
				b.setMemberNo(rt.getString("member_no"));

				// Fetch `DATE` and convert to `String`
				Date bookDate = rt.getDate("book_date");
				b.setBookDate(convertDateToString(bookDate)); // Format the date to string

				b.setBookTime(rt.getString("book_time"));
				b.setBookCnt(rt.getInt("book_cnt"));
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


	public Food foodDetail(Connection conn, String foodNo) {
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Dinner food = new Dinner();
		String query = "select * from tbl_food";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				food.setFoodNo(rset.getString("food_no"));
				food.setFoodName(rset.getString("food_name"));
				food.setFoodCat(rset.getString("food_cat"));
				food.setFoodNation(rset.getString("food_nation"));
			}else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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

	    // 승인 여부를 필터링하는 쿼리
	    String query = "SELECT * FROM tbl_dinner WHERE dinner_confirm = ?";

	    try {
	        pstmt = conn.prepareStatement(query);
	        
	        // 매개변수 바인딩
	        pstmt.setString(1, approved); // approved 값을 첫 번째 ?에 바인딩

	        // 쿼리 실행
	        rset = pstmt.executeQuery();

	        // 결과 처리
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
<<<<<<< HEAD


=======
	
	//tbl_dinner과 tbl_food를 동시에 가지고 오려고 만든 메소드

	    public DinnerFoodDetail getDinnerDetailByNo(Connection conn, String dinnerNo) throws SQLException {
	        String query = "SELECT d.dinner_no, d.dinner_name, d.dinner_addr, d.dinner_open, d.dinner_close, "
	                     + "d.dinner_phone, d.dinner_email, d.dinner_parking, d.dinner_max_person, "
	                     + "d.busi_no, d.dinner_id, d.dinner_pw, d.dinner_confirm, "
	                     + "f.food_no, f.food_name, f.food_nation, f.food_cat "
	                     + "FROM tbl_dinner d "
	                     + "JOIN tbl_food f ON d.food_no = f.food_no "
	                     + "WHERE d.dinner_no = ?";
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        DinnerFoodDetail dinnerDetail = null;

	        try {
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, dinnerNo);

	            rset = pstmt.executeQuery();

	            if (rset.next()) {
	                // Dinner 객체 생성 및 데이터 설정
	                Dinner dinner = new Dinner();
	                dinner.setDinnerNo(rset.getString("dinner_no"));
	                dinner.setDinnerName(rset.getString("dinner_name"));
	                dinner.setDinnerAddr(rset.getString("dinner_addr"));
	                dinner.setDinnerOpen(rset.getString("dinner_open"));
	                dinner.setDinnerClose(rset.getString("dinner_close"));
	                dinner.setDinnerPhone(rset.getString("dinner_phone"));
	                dinner.setDinnerEmail(rset.getString("dinner_email"));
	                dinner.setDinnerParking(rset.getString("dinner_parking"));
	                dinner.setDinnerMaxPerson(rset.getString("dinner_max_person"));
	                dinner.setBusiNo(rset.getString("busi_no"));
	                dinner.setDinnerId(rset.getString("dinner_id"));
	                dinner.setDinnerPw(rset.getString("dinner_pw"));
	                dinner.setDinnerConfirm(rset.getString("dinner_confirm"));

	                // Food 객체 생성 및 데이터 설정
	                Food food = new Food();
	                food.setFoodNo(rset.getString("food_no"));
	                food.setFoodName(rset.getString("food_name"));
	                food.setFoodNation(rset.getString("food_nation"));
	                food.setFoodCat(rset.getString("food_cat"));

	             // Dinner와 Food 객체를 포함하는 DinnerFoodDetail 생성
	                dinnerDetail = new DinnerFoodDetail(dinner, food);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();  // SQL 예외 발생 시 오류 출력
	        } finally {
	            JDBCTemplate.close(rset);
	            JDBCTemplate.close(pstmt);
	        }
	        return dinnerDetail;
	    }
>>>>>>> 0d0d4f418ce2c2b05e83697f801818c28af3ac4d
}

	
