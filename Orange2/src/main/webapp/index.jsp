<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>메인 페이지</h1>
<%
if(session.getAttribute("userId")!=null){
		
%>
	<form action="logout.jsp" method="post">
	<%=session.getAttribute("userName")%> 님 환영합니다.
	<input type="submit" value="로그아웃">
	<input type="button" value="회원 정보 수정"
			onclick="window.open('memberUpdateForm.jsp', 'popup', 'width=400, height=250')">
	
	</form>
	
<%
}
%>
<input type="button" value="로그인" onclick="location.href='loginForm.jsp'">
<input type="button" value="회원가입" onclick="location.href='memberJoinForm.jsp'">
<input type="button" value="게시판 이동" onclick="location.href='list.jsp'">


</body>
</html>