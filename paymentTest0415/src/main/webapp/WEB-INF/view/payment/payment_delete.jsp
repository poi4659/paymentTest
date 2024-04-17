<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 해지</title>
<link rel="stylesheet" type="text/css"
	href="./css/bootstrap.min_4.5.0.css">
<link rel="stylesheet" type="text/css" href="./css/global.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.0.js" type="text/javascript"></script>
</head>
<body>
	<header id="main-header" class="py-2 btn-dark text-white">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h1>멤버십 해지</h1>
				</div>
			</div>
		</div>
	</header>

	<section id="actions" class="py-4 mb-4 bg-light"></section>
	<section id="details">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h5>멤버십 해지</h5>
						</div>
						<div class="card-body">
							<form action="./PaymentDelete.pay" method="post">
								<fieldset>
									<div class="form-group row">
										<label for="user_id" class="ml-sm-3 col-form-label">
											사용자 아이디 </label>
										<div class="ml-sm-3">
											<input type="text" name="user_id" id="user_id"
												class="form-control form-control-sm bg-white"
												value="${param.user_id}" readonly>
										</div>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-secondary">삭제</button>
										<button type="reset" class="btn btn-secondary">취소</button>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
