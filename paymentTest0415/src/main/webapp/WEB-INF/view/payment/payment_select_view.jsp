<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 가입</title>
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
     <h1>멤버십</h1>
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
       <h5>멤버십</h5>
      </div>
      <div class="card-body">
       <table class="table table-hover">
        
        <tbody>
	         <c:choose>
			<%-- c:when 조건이 참인 경우에만 내부 코드 실행--%>
		    <c:when test="${empty paymentDTO}">
		        <p>멤버십에 가입하지 않았습니다.</p>
		        <a href="./PaymentInsertView.pay?user_id=${user_id}" class="btn btn-success btn-block">멤버십 가입하기</a>    
			</c:when>
		
			<%-- c:otherwise 조건이 거짓인 경우에만 내부 코드 실행--%>
		    <c:otherwise>
		        <h3>${paymentDTO.user_id}님</h3>
		        <h3>${paymentDTO.membership_grade}</h3><br>
		       	<a href="./PaymentSelectDetail.pay?user_id=${paymentDTO.user_id}" class="btn btn-success btn-block">멤버십 상세보기</a>
		    </c:otherwise>
		</c:choose>
        </tbody>
       </table>
       <div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
  <%-- 세션에 저장된 사용자 아이디 확인 --%>
<c:if test="${not empty sessionScope.user_id}">
    <p>세션에 저장된 사용자 아이디: ${sessionScope.user_id}</p>
</c:if>
<c:if test="${empty sessionScope.user_id}">
    <p>세션에 저장된 사용자 아이디가 없습니다.</p>
</c:if>
 </section>
</body>
</html>