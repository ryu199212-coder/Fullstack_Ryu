<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
   <div class="container card  my-5">
      <h3  class="card-header">  </h3>
	  <pre class="alert alert-success">
	  [1. JDBC란?]
	  1. JDBC(Java Database Connectivity) :
	  	자바에서 데이터 베이스와 연결해 SQL을 실행하고 결과를 처리 할 수 있도록 해주는 표준 API
	  2. java와 db사이의 다리역할
	  
	  [2. 셋팅]
	  1. C:\oraclexe\app\oracle\product\11.2.0\sever\jdbc\lib\ojdbc6.jar
	  2. [webapp]-[WEB-INF]-[lib]- ojdbc6.jar
	  
	  [3. JDBC 주요 구성 요소] - 드커프로 (*드라이버가 *커넥션만들고 *프리페어드로 sql날려서 *리절트셋 결과)
	  1. DriverManager      - db연결생성
	  2. Connection			- db연결객체
	  3. PreparedStament	- 동적 sql 실행
	  4. ResultSet			- sql 실행결과
	  </pre>
	  <%@page import="java.sql.*"%>
	  <%
	  	//드커프리
	  	//1. 드라이버 연결
	  	Class.forName("oracle.jdbc.driver.OracleDriver"); //회사명.프로젝트.클래스명
	  	
		//2. db연결
		String url 		= "jdbc:oracle:thin:@localhost:1521:xe";
	  	String user 	= "Scott";
	  	String password = "tiger";
		Connection conn = DriverManager.getConnection(url, user, password);
		
		if(conn !=null){out.println("db연동성공!"); conn.close();}
	  %>
   </div>
</body>
</html>