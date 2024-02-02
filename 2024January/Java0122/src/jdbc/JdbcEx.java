package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class JdbcEx {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		String url = "jdbc:mysql://localhost:3306/firm";
		String id = "root";
		String pass = "mysql";
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("MySQL 드라이버 로딩!");
		Connection conn = DriverManager.getConnection(url, id, pass);
		System.out.println("MySQL 접속 성공!");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from dept");
		while (rs.next()) {
			int deptno = rs.getInt("deptno");
			String dname = rs.getString("dname");
			String loc = rs.getString("loc");
			System.out.printf("%d, %s, %s\n", deptno, dname, loc);
		}
		
		//입력 테스트
		Scanner sc= new Scanner(System.in);
		System.out.print("deptno 입력: ");
		String inDeptno=sc.nextLine();
		System.out.print("dname 입력: ");
		String inDname=sc.nextLine();
		System.out.print("loc 입력: ");
		String inIoc=sc.nextLine();
		String sql=String.format("insert into dept"+" values('"+inDeptno+"', '"+inDname+"', '"+inIoc+"')");
//		stmt = conn.createStatement();
		int res = stmt.executeUpdate(sql);
		conn.close();
	}
}
