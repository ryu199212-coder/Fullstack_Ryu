<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
      <h3  class="card-header"> 부서검색 </h3>
	   <table class="table table-dark table-striped">
	       <thead>
	         <tr>
	           <th scope="col">DEPTNO</th>
	           <th scope="col">DNAME</th>
	           <th scope="col">LOC</th>
	         </tr>
	       </thead>
	       <tbody>
	         <tr>
	           <td>${dto.deptno}</td>
	           <td>${dto.dname}</td>
	           <td>${dto.loc}</td>
	         </tr>
	       </tbody>
      </table> 
    </div>
</body>
</html>

