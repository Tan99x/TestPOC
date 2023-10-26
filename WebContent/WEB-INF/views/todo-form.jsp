<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Card Management Application</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Card
					Management App</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Cards</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${todo != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${todo == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${todo != null}">
            			Edit Card
            		</c:if>
						<c:if test="${todo == null}">
            			Add New Card
            		</c:if>
					</h2>
				</caption>

				<c:if test="${todo != null}">
					<input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Card Number</label> <input type="text"
						value="<c:out value='${todo.cardNumber}' />" class="form-control"
						name="cardNumber" required="required" minlength="16">
				</fieldset>

				<fieldset class="form-group">
					<label>Card Expiry</label> <input type="text"
						value="<c:out value='${todo.cardExpiry}' />" class="form-control"
						name="cardExpiry" minlength="6">
				</fieldset>

				<fieldset class="form-group">
					<label>Cvv</label> <input type="text"
						value="<c:out value='${todo.cvv}' />" class="form-control"
						name="cvv" minlength="3">
				</fieldset>


				<fieldset class="form-group">
					<label>Card Holder Name</label> <input type="text"
						value="<c:out value='${todo.cardHolderName}' />"
						class="form-control" name="cardHolderName" minlength="4">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Card
					Management App</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Cards</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${todo != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${todo == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${todo != null}">
            			Edit Card
            		</c:if>
						<c:if test="${todo == null}">
            			Add New Card
            		</c:if>
					</h2>
				</caption>

				<c:if test="${todo != null}">
					<input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Card Number</label> <input type="text"
						value="<c:out value='${todo.cardNumber}' />" class="form-control"
						name="cardNumber" required="required" minlength="16">
				</fieldset>

				<fieldset class="form-group">
					<label>Card Expiry</label> <input type="text"
						value="<c:out value='${todo.cardExpiry}' />" class="form-control"
						name="cardExpiry" minlength="6">
				</fieldset>

				<fieldset class="form-group">
					<label>Cvv</label> <input type="text"
						value="<c:out value='${todo.cvv}' />" class="form-control"
						name="cvv" minlength="3">
				</fieldset>


				<fieldset class="form-group">
					<label>Card Holder Name</label> <input type="text"
						value="<c:out value='${todo.cardHolderName}' />"
						class="form-control" name="cardHolderName" minlength="5">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
