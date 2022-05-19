package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MemberVo;

public class MemberDao {
public boolean insert(MemberVo vo) {
	boolean result = false;
	Connection connection = null;
	PreparedStatement pstmt = null;
	
	try {
		connection = getConnection();
		
		String sql = "insert into member values(null, ?, ?, ?, ?)";
		pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPhone());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getPassword());
		
		
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
		


public List<MemberVo> findAll() {
	List<MemberVo> result = new ArrayList<>();
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		connection = getConnection();
		
		String sql = "select no, name, phone, email " + 
					" from member " + 
					" order by no asc ";
		
		pstmt = connection.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Long no = rs.getLong(1);
			String name = rs.getString(2);
			String phone = rs.getString(3);
			String email = rs.getString(4);
			
			MemberVo vo = new MemberVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setPhone(phone);
			vo.setEmail(email);
			
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