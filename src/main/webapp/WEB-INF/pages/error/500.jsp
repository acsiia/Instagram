<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<html>
<head>
  <spring:message code="pages.title.500" var="title"/>
  <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container" align="center">
  <h1 style="color: red">500</h1>
  <h2 style="color: red"><spring:message code="500.label.servererror"/> </h2>
</div>
</body>
</html>