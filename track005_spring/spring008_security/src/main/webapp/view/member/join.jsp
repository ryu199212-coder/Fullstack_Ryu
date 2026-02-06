<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../inc/header.jsp"%>

<!-- CSRF 토큰 AJAX 사용을 위해 meta 추가 -->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<script>
// 모든 AJAX 요청에 자동으로 CSRF 토큰 추가
$(document).ajaxSend(function(e, xhr, options) {
    xhr.setRequestHeader(
        $("meta[name='_csrf_header']").attr("content"),
        $("meta[name='_csrf']").attr("content")
    );
});
</script>

<div class="container mt-5">
    <h3>WELCOME! 회원가입</h3>

    <form action="${pageContext.request.contextPath}/security/join" 
          method="post" 
          enctype="multipart/form-data">

        <!-- Spring Security CSRF Token -->
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <div class="mb-3 mt-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email"
                   placeholder="이메일을 입력해주세요" required name="email">

            <div class="iddouble_result"></div>

            <script>
            $(function(){
                $("#email").on("keyup", function(){
                    let keyword = $(this).val().trim();

                    if(keyword === ""){
                        $(".iddouble_result")
                            .html("<span class='text-danger'>이메일을 입력해주세요</span>");
                        return;
                    }

                    $.ajax({
                        url: "${pageContext.request.contextPath}/iddouble",
                        type: "POST",
                        data: { email : keyword },   // ✅ 파라미터 이름을 email로 수정
                        success: function(res){
                            console.log(res);
                            if(res.cnt == 1){
                                $(".iddouble_result")
                                    .html("<span class='text-danger'>이미 사용중인 이메일입니다.</span>");
                            } else {
                                $(".iddouble_result")
                                    .html("<span class='text-primary'>사용 가능한 이메일입니다.</span>");
                            }
                        },
                        error: function(){
                            $(".iddouble_result")
                                .html("<span class='text-danger'>서버 요청 중 오류 발생</span>");
                        }
                    });
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
            <label for="mbtiTypeId" class="form-label">MBTI TYPE:</label>
            <select name="mbtiTypeId" id="mbtiTypeId" class="form-control">
                <option value="1">ISTJ</option>
                <option value="2">ISFJ</option>
                <option value="3">INFJ</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="file" class="form-label">Profile Image:</label>
            <input type="file" class="form-control" id="file" name="file">
        </div>

        <button type="submit" class="btn btn-primary">회원가입</button>

    </form>
</div>

<%@ include file="../inc/footer.jsp"%>
