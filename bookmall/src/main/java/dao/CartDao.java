package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CartVo;

public class CartDao {
	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			connection = getConnection();
			
			// 3. SQL 준비
			String sql = "insert into cart values(?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			
			// 4. 바인딩(binding)
			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getAmount());
			
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			// clean up
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
	
	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			// 3. SQL 준비
			String sql = "select m.name, b.title, c.amount, (b.price * c.amount) as price " + 
						" from cart c, book b, member m " + 
						" where c.book_no = b.no " + 
						"	and c.member_no = m.no " + 
						" order by m.name asc ";
			pstmt = connection.prepareStatement(sql);
			
			// 4. 바인딩(binding)
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				Long amount = rs.getLong(3);
				Long price = rs.getLong(4);
				
				CartVo vo = new CartVo();
				vo.setName(name);
				vo.setTitle(title);
				vo.setAmount(amount);
				vo.setPrice(price);
				
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
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf8?";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 " + e);
		}

		return conn;
	}
}