<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.sql.*" %>
<%@page import="encript.BCrypt" %>

<%
   Connection conn= null;
   PreparedStatement pstmt = null;
   PreparedStatement pstmt2 = null;
   
   try{
	   //conectar con base de datos
	   String db="jdbc:mysql://localhost:3306/portal";
	   String user="root";
	   String pw="1234";
	   conn = DriverManager.getConnection(db,user,pw);
	   
	   //traer los datos introducidos
	   request.setCharacterEncoding("utf-8");
	   String email=request.getParameter("email");
	   String pwd=request.getParameter("pwd");
	   String name=request.getParameter("names");
	   
	   //instruccion de SQL
	   String sql="select * from member where email=?"; 
 
	   pstmt=conn.prepareStatement(sql);
	   pstmt.setString(1,email);
	   
	   ResultSet rs=pstmt.executeQuery();
	   if(rs.next()){
	     response.sendRedirect("register.jsp?ck=1");
	   }
	   else{
	   //instruccion de SQL
	   String sql2="insert into member(email, pwd, nombre, writeday)";
	   sql2=sql2+ " values(?,?,?, now())";
	   
	   //ejecucion
	   pstmt2=conn.prepareStatement(sql2);
	   pstmt2.setString(1,email);
	   pstmt2.setString(2, BCrypt.hashpw(pwd, BCrypt.gensalt(10)));
	   pstmt2.setString(3,name);
	   
	   pstmt2.executeUpdate();
	   
	   response.sendRedirect("../login/login.jsp");	
	   }
      } finally {
    	  if(pstmt != null) try {pstmt.close();} catch(SQLException ex) {}
    	  if(pstmt2 != null) try {pstmt.close();} catch(SQLException ex) {}
    	  if(conn != null) try {conn.close();} catch(SQLException ex) {}
      }   
%>

