<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="h-100 header bg-info d-flex justify-content-between">
	<%-- logo --%>
	<div class="logo d-flex align-items-center">
		<h1 class="text-white ml-3">Marondalgram</h1>
	</div>
	
	<%-- 로그인 정보 --%>
	<c:if test="${not empty userName}">
	<div class="login-info d-flex align-items-center mr-5">
		<span class="text-white">${userName}님 안녕하세요.</span>
		<a href="/user/sign-out" class="ml-2 text-white font-weight-bold">로그아웃</a>
	</div>
	</c:if>
</div>