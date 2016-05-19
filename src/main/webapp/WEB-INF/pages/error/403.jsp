<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <spring:message code="pages.title.403" var="title"/>
    <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container" align="center">
    <h1 style="color: #ffffff">403</h1>
    <h2 style="color: #ffffff"><spring:message code="403.label.accessdenied"/> </h2>
</div>
</body>
</html>
