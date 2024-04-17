<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 내역 조회</title>
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
     <h1>결제 내역 조회</h1>
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
		<h5>결제 내역</h5>
      </div>
      <div class="card-body">
       <table class="table table-hover">
        <thead class="thead-light">
         <tr class="text-center">
			<th>사용자 아이디</th>
            <th>멤버십 등급</th>
            <th>결제 날짜</th>
            <th>결제 수단</th>
            <th>결제 금액</th>
         </tr>
        </thead>
        <tbody>
         <c:forEach items="${paymentList}" var="paymentDTO">
            <tr class="text-center">
                <td>${paymentDTO.user_id}</td>
                <td>${paymentDTO.membership_grade}</td>
                <td>${paymentDTO.payment_date}</td> 
                <td>${paymentDTO.payment_method}</td>
                <td>${paymentDTO.payment_price}</td>
            </tr>
        </c:forEach>
        </tbody>
       </table>
           
      </div>
     </div>
    </div>
   </div>
  </div>
 </section>
    
</body>
</html>
