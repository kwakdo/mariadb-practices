package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionTest {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			// 1. JDBC Driver 로딩 (JDBC Class 로딩: class loader)
			Class.forName("driver.MyDriver");

			// 2. 연결하기
			String url = "jdbc:mysql://192.168.10.55:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("Connected!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}