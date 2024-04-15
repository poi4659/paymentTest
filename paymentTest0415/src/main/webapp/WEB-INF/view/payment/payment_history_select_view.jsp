<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 내역 조회</title>
</head>
<body>
<h1>결제 내역 조회</h1>
        <table border="1">
        <tr>
            <th>사용자 아이디</th>
            <th>멤버십 등급</th>
            <th>결제 날짜</th>
            <th>결제 수단</th>
            <th>결제 금액</th>
        </tr>
        <c:forEach items="${paymentList}" var="paymentDTO">
            <tr>
                <td>${paymentDTO.user_id}</td>
                <td>${paymentDTO.membership_grade}</td>
                <td>${paymentDTO.payment_date}</td> 
                <td>${paymentDTO.payment_method}</td>
                <td>${paymentDTO.payment_price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
