<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/header.jsp"%>
<!--    header       -->
<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
<div class="container mt-5">
    <h3>WELCOME! 회원가입</h3>
    <form action="${pageContext.request.contextPath}/joinUpload.user" 
          method="post" 
          enctype="multipart/form-data">
        <div class="mb-3 mt-3">
            <label for="email" class="form-label">Email:</label> 
            <input type="email" class="form-control" id="email"
                   placeholder="이메일을 입력해주세요" required name="email">
            <div class="countByEmail_result"></div>
            <script>
				$(function(){
				    $("#email").on("keyup", function(){
				        let keyword = $(this).val().trim();
				        console.log(keyword);
				
				        // 1-1 빈칸검사
				        if(keyword === ""){
				            $(".countByEmail_result")
				                .empty()
				                .append("<span class='text-danger p-3'>이메일을 입력해주세요</span>");
				            return;
				        } else { // 1-2 중복검사 (AJAX)
				            $.ajax({
				                url: "${pageContext.request.contextPath}/countByEmail", // 중복체크용 컨트롤러 매핑
				                type: "POST",
				                data: { email : keyword },
				                success: function(res){
				                    console.log(res);
				                    if(res.cnt == 1){
				                        $(".countByEmail_result")
				                            .empty()
				                            .append("<span class='text-danger p-3'>이미 사용중인 이메일 입니다</span>");
				                    }
				                },
				                error: function(error){
				                    console.log(error);
				                }
				            });
				        }
				    });
				});
			</script>



        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label> 
            <input type="password" class="form-control" id="password"
                   placeholder="비밀번호를 입력해주세요" name="password">
        </div>
        <div class="mb-3">
            <label class="form-check-label" for="mbti">MBTI TYPE : </label> 
            <select name="mbtiTypeId" id="mbti" class="form-control">
                <option value="1">ISTJ</option>
                <option value="2">ISFJ</option>
                <option value="3">INFJ</option>       
            </select>
        </div>
        <div class="mb-3">
            <label for="file" class="form-label">프로필 사진:</label>
            <input type="file" class="form-control" id="file" name="file" accept="image/*">
        </div>
        <button type="submit" class="btn btn-primary">회원가입</button>
    </form>
</div>

<%@ include file="../inc/footer.jsp"%>
