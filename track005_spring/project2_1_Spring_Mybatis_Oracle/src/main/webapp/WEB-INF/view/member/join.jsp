<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<!-- 	header		 --> 
<div class="container mt-5">
	<h3>WELCOME! 회원가입</h3>
	<form action="${pageContext.request.contextPath}/security/join"   method="post"    id="form"
		  encType="multipart/form-data" >
	  	<input  type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<div class="mb-3 mt-3">
			<label  for="email" class="form-label">Email:</label> 
			<input  type="email" class="form-control" id="email"
					placeholder="이메일을 입력해주세요" required  name="email">
			<div class="iddouble_result" data-role="no" ></div>	
		</div>
		<div class="mb-3 mt-3">
			<label  for="nickname" class="form-label">Nickname:</label> 
			<input  type="text" class="form-control" id="nickname"
					placeholder="닉네임을 입력해주세요" required  name="nickname"> 
		</div>
		<div class="mb-3 mt-3">
			<label  for="mobile" class="form-label">Mobile:</label> 
			<input  type="text" class="form-control" id="mobile"
					placeholder="모바일을 입력해주세요" required  name="mobile"> 
		</div> 		
		<div class="mb-3">
			<label for="password" class="form-label">Password:</label> 
			<input type="password" class="form-control" id="password" placeholder="비밀번호를 입력해주세요" name="password">
		</div>
		  <div class="mb-3">
		    <label for="file" class="form-label">프로필사진 (추후에 업데이트가능합니다!) </label>
		    <input type="file" class="form-control" id="file" placeholder="파일을 입력해주세요" name="file">
		  </div>			
		<div class="mb-3">
			<label class="form-check-label"  for="mbti">MBTI TYPE : </label>  
			<select   name="mbtiTypeId"  id="mbti"  class="form-control">
				<option value="1">ISTJ</option>
				<option value="2">ISFJ</option>
				<option value="3">INFJ</option>
			</select> 
		</div>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
	<script>
			$(function(){
				//1. 대상찾아오기   아이디가 이메일의 값을 찾아오려고함. 
					console.log( "1. " +  (0 ==  false )   );   // true   자동형변환
					console.log( "2. " +  (0 === false )   );   // false  자료형까지
					console.log( "3. " +  (0 ==  "" )   );   // true   자동형변환
					console.log( "4. " +  (0 === "" )   );   // false  자료형까지 
					
				$("#form").on("submit" , function(){
					//1. iddouble_result 안에 상태가 go로 바뀌면 데이터 전송하기
					let status = $(".iddouble_result").attr("data-role");   console.log(  status );
					if(  status == "no" ){ alert("이메일 중복체크를 확인해주세요! ");  $("#email").focus();   return false;}
					else {  return true;                }
					
				}); 
				$("#email").on("keyup" , function(){
					console.log( $(this).val().trim() ); 
					let keyword = $(this).val().trim();
 
					
					if(keyword === ""){// 만약 빈칸인지 검사
						$(".iddouble_result").empty().append("<span  class='text-danger p-3'>이메일을 입력해주세요</span>");
						return;
					}else{ // 아니라면 ajax  - iddouble /POST / email / 
						$.ajax({
							url:"${pageContext.request.contextPath}/security/iddouble",
							type:"GET",  //## post는 무조건 인증해야함.
							data:{email :    keyword } , 
						    dataType:"json", 
							success:function( res ){  //res = {cnt: 1}
								console.log(res);  //Object
								if(res.cnt == 1){
									$(".iddouble_result").attr("data-role", "no");  
									$(".iddouble_result").empty().append("<span  class='text-danger p-3'>이미 사용중인 이메일입니다.</span>");
								}else{ 
									$(".iddouble_result").attr("data-role", "ok");  
									console.log(  $(".iddouble_result").attr("data-role" ));
									$(".iddouble_result").empty().append("<span  class='text-success p-3'>사용가능한 이메일입니다.</span>");
								}
							} ,
							error:function( ){
								$(".iddouble_result").empty().append("<span  class='text-danger p-3'>서버요청 중 오류가 발생했습니다.</span>");
							}
						});
					}
				}); 
			});
	</script>	
</div> 
<!-- 	footer		 --> 
<%@ include file="../inc/footer.jsp"%>





	
<!-- 				
	action="join_process.jsp"
	method="post"
	name="email"   name="password"  name="mbti_type_id" 
-->
