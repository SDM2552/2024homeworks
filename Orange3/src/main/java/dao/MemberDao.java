package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;
import encript.BCrypt;

public class MemberDao {
	private static Connection conn;
	private static MemberDao dao = new MemberDao();
	private MemberDao() {	}
	PreparedStatement pstmt;
	ResultSet rs;

	public static MemberDao getInstance() {
		MemberDao.getConnection();
		return dao;
	}

	public static void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root", "mysql");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Member selectForLogin(String id, String pw) { //로그인용
		Member member=null;
		String sql = "select * from secured_member where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(BCrypt.checkpw(pw, rs.getString("pw"))) {
				member = new Member(rs.getString("id"), rs.getString("pw"), rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;	
	}
	
	public Member select(String id) {
		Member member = null;
		String sql = "select * from secured_member where id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member(rs.getString("id"), rs.getString("pw"),
						            rs.getString("name"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;		
	}
	
	public int insert(Member member) { //회원가입
		String sql = "insert into secured_member(id, pw, name) values (?,?,?)";
	    try ( 
	        PreparedStatement pstmt = conn.prepareStatement(sql);            
	    ) {
	        
	        // 쿼리 실행
	    	pstmt.setString(1, member.getId());
	    	pstmt.setString(2, BCrypt.hashpw(member.getPw(), BCrypt.gensalt(10))); //BCrypt.hashpw(member.getPw(), BCrypt.gensalt(10))
	    	String hashedPassword = BCrypt.hashpw(member.getPw(), BCrypt.gensalt(10));
	    	System.out.println("암호화 후: " + hashedPassword);
	    	pstmt.setString(3, member.getName());
	        return pstmt.executeUpdate();
	    
	    } catch(Exception e) {
	        e.printStackTrace();
	    } 
		return 0;
	}
	
	public int update(Member member) {
		String sql = "update secured_member set name=? where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
