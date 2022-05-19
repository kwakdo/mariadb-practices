package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BookVo;

public class BookDao {
	public boolean insert(BookVo vo) {
	boolean result = false;
	Connection connection = null;
	PreparedStatement pstmt = null;
	
	try {
		connection = getConnection();
		
		String sql = "insert into book values(null, ?, ?, ?)";
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, vo.getTitle());
		pstmt.setLong(2, vo.getPrice());
		pstmt.setLong(3, vo.getCategoryNo());
		
		int count = pstmt.executeUpdate();
		
		result = count == 1;
	} catch (SQLException e) {
		System.out.println("error: " + e);
	} finally {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
		


public List<BookVo> findAll() {
	List<BookVo> result = new ArrayList<>();
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		connection = getConnection();
		
		String sql = "select b.no, b.title, b.price, c.category " + 
				" from book b, category c " + 
				" where b.category_no=c.no " + 
				" order by no asc ";
		
		pstmt = connection.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Long no = rs.getLong(1);
			String title = rs.getString(2);
			Long price = rs.getLong(3);
			String category = rs.getString(4);
			
			BookVo vo = new BookVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setPrice(price);
			vo.setCategory(category);
			
			result.add(vo);
		}
	} catch (SQLException e) {
		System.out.println("error: " + e);
	} finally {

		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return result;
}

	
private Connection getConnection() throws SQLException {
	Connection connection = null;
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mysql://192.168.10.42:3306/bookmall?charset=utf8?";
		connection = DriverManager.getConnection(url, "bookmall", "bookmall");
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패 " + e);
	}

	return connection;
}
}

