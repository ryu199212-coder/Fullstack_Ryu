<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@include file="../inc/header.jsp"%>

<div class="container card my-5 p-4">
    <h3 class="card-header">MBTI 글삭제</h3>

    <sec:authorize access="isAuthenticated()">
        <form action="${pageContext.request.contextPath}/delete.quest" method="post">
            
            <input type="hidden" name="id" value="${id}" /> 
            
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="my-3">
                <label for="bpass" class="form-label">PASS:</label>
                <input type="password" class="form-control" id="bpass" name="bpass" required>
            </div>

            <div class="my-3 text-end">
                <button type="submit" class="btn btn-primary">글삭제</button>
                <a href="javascript:history.go(-1)" class="btn btn-danger">BACK</a>
            </div>
        </form>
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
        <p class="alert alert-warning">로그인 후 글삭제가 가능합니다.</p>
    </sec:authorize>
</div>

<%@include file="../inc/footer.jsp"%>