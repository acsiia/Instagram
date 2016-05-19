<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<%--i18n--%>
<spring:message code="pages.title.welcome" var="title"/>
<spring:message code="welcome.label.signin" var="signinLabel"/>
<spring:message code="welcome.label.signup" var="signupLabel"/>

<%--URLs--%>
<c:url value="/login" var="loginUrl"/>
<c:url value="/registration" var="regUrl"/>
<c:url value="/resources/photo/insta.png" var="instaImg"/>
<c:url value="/resources/css/welcome.css" var="welcomeStyleSheet"/>

<html>
<head>
    <toolkit:header title="${title}"/>
    <link href="${welcomeStyleSheet}" type="text/css" rel="stylesheet">
</head>
<body class="welcome-bg">
<toolkit:langnav/>

<div class="container my-container" align="center">
    <div id="welcome-cont">
        <img src="${instaImg}"><br><br>
        <a href="${loginUrl}" class="btn btn-info btn-lg welcome-btn">
            ${signinLabel}</a><br><br>
        <a href="${regUrl}" class="btn btn-info btn-lg welcome-btn">
            ${signupLabel}</a><br><br>
    </div>
</div>
</body>
</html>
