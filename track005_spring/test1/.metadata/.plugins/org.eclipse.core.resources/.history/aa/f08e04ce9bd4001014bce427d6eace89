<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../inc/header.jsp" %>
   <script>
   $(function(){
	   let result = '${success}';
	   console.log(result); 
	   if(result == "글쓰기 실패"){   alert( result );   history.go(-1); }
	   else if(result == '비밀번호를 확인해주세요'){  alert( result  );  history.go(-1); }
	   else if(result.length  != 0 ){  alert(result); }  //아까 처음 값이없을때 공백 
   });
   
   </script>
   <div class="container card  my-5 p-4">
      <h3 class="card-header"> MBTI QUEST BOARD</h3>  
      <table class="table table-striped table-bordered table-hover">
      	<caption>mbti </caption>
      	<thead>
      		<tr>
      			<th scope="col">NO</th>
      			<th scope="col">TITLE</th>
      			<th scope="col">NAME</th>
      			<th scope="col">DATE</th>
      			<th scope="col">HIT</th>
      		</tr>	
      	</thead>
      	<tbody>  
      	  <c:forEach  var="dto"  items="${list}"  varStatus="status">  	
	  		<tr> 
	  			<td>${list.size()-status.index} 
	  			<td> <a href="${pageContext.request.contextPath}/detail.quest?id=${dto.id}">
	  				${dto.btitle}
	  			</a> </td>
	  			<td>${dto.appUserId}</td>
	  			<td>${dto.createdAt}</td>
	  			<td>${dto.bhit}</td>
	  		 <tr>
	  	  </c:forEach> 
      	</tbody>
      </table> 
		<p class="text-end">
			<a href="${pageContext.request.contextPath}/write.quest" class="btn btn-primary">글쓰기</a>
		</p>	 
		<p class="text-end alert alert-primary">로그인을 하면 글쓰기가능합니다.</p> 
		
	  <div class="mb-3 mt-3 alert alert-primary">
	    <label for="search" class="form-label">	SEARCH</label>
	    <input type="search" class="form-control" id="search"  placeholder="검색어를 입력해주세요" name="search">
	    <!-- 						 -->
	    <!-- 						 -->
	    <div  id="resultArea">
	      <table class="table table-striped table-bordered table-hover  my-3">
		    <caption>mbti</caption>
		    <thead>
		      <tr>
		        <th scope="col">NO</th>
		        <th scope="col">TITLE</th>
		        <th scope="col">NAME</th>
		        <th scope="col">DATE</th>
		        <th scope="col">HIT</th>
		      </tr> 
		    </thead>
		    <tbody>
		      <!-- AJAX 결과가 여기에 들어감 -->
		    </tbody>
		  </table> 
	    </div>
	    <!-- 						 -->
	    <!-- 						 -->	    	
	  </div>		
	  <script>
	  $(function(){
		  $("#search").on("keyup" , function(){    // keyup (키보드뗐을때)
				console.log( $(this).val().trim()  ); 
				let keyword = $(this).val().trim();
				/////////////////////////////////////////////
				if(keyword ===""){ //빈칸일때
					$("#resultArea  tbody")
					.empty()
					.append("<tr><td colspan='5'>검색어를 입력하세요.</td></tr>");
				}else{ // 서버요청
					$.ajax({
						url:"${pageContext.request.contextPath}/selectSearch",
						type:"GET",  //GET, POST,PUT
						data:{search:keyword},
						success:function( res ){ 
							console.log(res); 
							$("#resultArea  tbody").empty();  //초기화
							$.each( res , function( index, dto  ){ 
								let row = "<tr>"
								+"<td>"+(res.length-index)+"</td>"
								+"<td><a href='${pageContext.request.contextPath}/detail.quest?id="+dto.id+"'>"
								+dto.btitle+"</a></td>"
								+"<td>"+dto.appUserId+"</td>"
								+"<td>"+dto.createdAt+"</td>"
								+"<td>"+dto.bhit+"</td>"
								+"</tr>";
								$("#resultArea  tbody").append(row);
							});
						}
					});
				} 
				/////////////////////////////////////////////
		  });
	  });
	  </script>	
		
   </div>
   
<%@include file="../inc/footer.jsp" %>

<!-- [ mbtiBoard - list.jsp ]  -->