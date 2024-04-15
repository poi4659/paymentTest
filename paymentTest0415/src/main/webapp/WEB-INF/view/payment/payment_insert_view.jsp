<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>멤버십 가입 결제 완료</title>
</head>
<body>
<script type="text/javascript">
	alert("결제가 성공적으로 완료되었습니다.");
	location.href = "./PaymentSelectDetail.pay?user_id=${paymentDTO.user_id}";
		
</script>
</body>
</html>
