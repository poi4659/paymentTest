<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 정보</title>

<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>

</head>
<body>
 <header id="main-header" class="py-2 btn-dark text-white">
  <div class="container">
   <div class="row">
    <div class="col-md-6">
     <h1>상세 보기</h1>
    </div>
   </div>
  </div>
 </header>
     
<section class="py-4 mb-4 bg-light"></section>
 <section id="department">
  <div class="container">
   <div class="row">
    <div class="col-md-12">
     <div class="card">
      <div class="card-header">
		<h5>멤버십 상세 보기</h5>
      </div>
      <div class="card-body">
       <table class="table table-hover">
        <thead class="thead-light">
         <tr class="text-center">
			<th>사용자 아이디</th>
            <th>멤버십 등급</th>
            <th>결제 수단</th>
            <th>결제 금액</th>
            <th>다음 결제일</th>
         </tr>
        </thead>
        <tbody>
         <tr class="text-center">
          	<td>${paymentDTO.user_id}</td>
            <td>${paymentDTO.membership_grade}</td>
            <td>${paymentDTO.payment_method}</td>
            <td>${paymentDTO.payment_price}</td>
			<td id="nextPaymentDate"></td> <!-- 다음 결제일 -->
         </tr>
        </tbody>
       </table>
           <div class="row">
        <div class="col-md-4">
			<a href="./PaymentUpdateView.pay?user_id=${paymentDTO.user_id}" class="btn btn-warning btn-block">
    멤버십 수정하기</a>
        </div>
        <div class="col-md-4">
			<a href="./PaymentDeleteView.pay?user_id=${paymentDTO.user_id}" class="btn btn-danger btn-block">
    멤버십 해지하기</a>
        </div>
        <div class="col-md-4">
        	<a href="./PaymentHistory.pay?user_id=${paymentDTO.user_id}" class="btn btn-primary btn-block">결제 내역 보기</a>
        </div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </section>
 
<!-- JavaScript로 다음 결제일 계산 및 표시 -->
<script type="text/javascript">
    // 결제일을 LocalDate로 변환
    var paymentDate = new Date("${paymentDTO.payment_date}");
    // 다음 결제일 계산 (한 달 후)
    var nextPaymentDate = new Date(paymentDate.setMonth(paymentDate.getMonth() + 1));

    // 다음 결제일의 월과 일을 가져와서 앞에 0을 붙여 두 자리로 표시
    var nextPaymentMonth = ('0' + (nextPaymentDate.getMonth() + 1)).slice(-2);
    var nextPaymentDay = ('0' + nextPaymentDate.getDate()).slice(-2);

    // 다음 결제일을 yyyy-MM-dd 형식으로 출력
    document.getElementById("nextPaymentDate").innerText = nextPaymentDate.getFullYear() + "-" + nextPaymentMonth + "-" + nextPaymentDay;
</script>

 
</body>
</html>
