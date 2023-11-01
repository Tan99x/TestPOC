<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Confirmation Page</title>
</head>
<body>
	<H4 style="text-align: center;">${confirmMsg}</H4>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>