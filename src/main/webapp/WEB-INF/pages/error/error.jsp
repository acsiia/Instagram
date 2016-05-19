<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <spring:message code="pages.title.error" var="title"/>
    <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container" align="center">
    <h1 style="color: darkred"><spring:message code="error.label.errormessage"/></h1>
    <h3 style="color: #ffffff"><spring:message code="error.label.todo"/></h3>
    <!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
    </c:forEach>
    -->
</div>
</body>
</html>