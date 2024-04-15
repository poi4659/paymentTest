<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>멤버십 가입</title>
</head>
<body>
    <h1>멤버십 정보</h1>
    <table border="1">
        <tr>
            <th>사용자 아이디</th>
            <th>멤버십 등급</th>
            <th>결제 날짜</th>
            <th>결제 수단</th>
            <th>결제 금액</th>
        </tr>
        <tr>
            <td>${paymentDTO.user_id}</td>
            <td>${paymentDTO.membership_grade}</td>

            <td>${paymentDTO.payment_date}</td> 
            
            <td>${paymentDTO.payment_method}</td>
            <td>${paymentDTO.payment_price}</td>
        </tr>
    </table>
</body>
</html>
