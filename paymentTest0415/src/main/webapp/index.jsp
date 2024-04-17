<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 가입</title>
</head>

<body>
<h3>로그인 해주세요.</h3>
<!-- 시작 --> 
<!-- 로그인 연동할때 로그인 여부 확인 코드 추가--> 
<form id="userIdForm" action="PaymentSelect.pay" method="post">
        <label for="user_id">사용자 아이디:</label>
        <input type="text" id="user_id" name="user_id">
        <input type="submit" value="확인">
</form>

<!-- 세션에 사용자 아이디 입력받은 거 저장--> 
<c:if test="${not empty param.user_id}">
    <c:set var="user_id" value="${param.user_id}" />
    <c:set target="${session}" property="user_id" value="${user_id}" />
</c:if>

</body>
</html>
