<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
     <h1>멤버십 가입</h1>
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
       <h5>멤버십 가입</h5>
      </div>
      <div class="card-body">
       <form action="./PaymentInsert.pay" method="post" id="PaymentInsert">
        <fieldset>
	       아이디: <input type="text" name="user_id" id="user_id"
	        value="${param.user_id}"><br>
         <div class="form-group row">
          <label for="membership_grade" class="ml-sm-3 col-form-label"> 멤버십 등급 </label>
          <div class="ml-sm-3">
           	<input type="radio" name="membership_grade" value="Gold"> Gold (10000원)
        	<input type="radio" name="membership_grade" value="Silver"> Silver (7000원)<br>
          </div>
         </div>
         
         <div class="form-group row">
          <label for="payment_method" class="ml-sm-3 col-form-label"> 결제 수단 </label>
          <div class="ml-sm-3">
          	<input type="radio" name="payment_method" value="card"> 카드
        	<input type="radio" name="payment_method" value="kakaopay"> 카카오페이<br>
          </div>
         </div>
         
         <div class="form-group">
          <button type="submit" class="btn btn-secondary">가입</button>
          <button type="reset" class="btn btn-secondary">취소</button>
         </div>
        </fieldset>
       </form>
       <div>
       </div>
      </div>
     </div>
    </div>
   </div>
  </div>
 </section>
</body>
</html>
