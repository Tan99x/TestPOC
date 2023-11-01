<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Refund Management</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="refund" method="post">
					<fieldset class="form-group">
						<label>Amount</label> <input type="text"
							value="<c:out value='${refundObj.amount}' />"
							class="form-control" name="amount" required="required">
					</fieldset>

					<fieldset class="form-group">
						<label>Transaction Id</label> <input type="text"
							value="<c:out value='${refundObj.transactionId}' />"
							class="form-control" name="transactionId">
					</fieldset>

					<fieldset class="form-group">
						<label>Description</label> <input type="text"
							value="<c:out value='${refundObj.desc}' />" class="form-control"
							name="desc">
					</fieldset>

					<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
