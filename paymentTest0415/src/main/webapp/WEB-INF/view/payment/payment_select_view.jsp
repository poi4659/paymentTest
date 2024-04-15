<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 가입</title>
</head>

<body>
<header id="main-header">
<div class="container">
<div class="row">
<div class="col-md-6">
<h1> 멤버십 </h1>
</div>
</div>
</div>
</header>


<div class="card-body">
<c:choose>
	<!-- c:when 조건이 참인 경우에만 내부 코드 실행-->
    <c:when test="${empty paymentDTO}">
        <p>멤버십에 가입하지 않았습니다.</p>
		<!-- 이전 페이지에서 받은 user_id -->
        <a href="./PaymentInsertView.pay?user_id=${user_id}">멤버십 가입하기</a>    </c:when>

	<!-- c:otherwise 조건이 거짓인 경우에만 내부 코드 실행-->
    <c:otherwise>
        <!-- 멤버십 가입 정보 -->
        <h3>${paymentDTO.user_id}님</h3>
        <h3>${paymentDTO.membership_grade}</h3><br>
        
        <!-- PaymentDTO 객체에서 가져온 user_id -->
        <a href="./PaymentSelectDetail.pay?user_id=${paymentDTO.user_id}">멤버십 상세보기</a>
    </c:otherwise>
</c:choose>

</div>

</body>
</html>
