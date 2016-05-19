<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<%--i18n--%>
<spring:message code="pages.title.login" var="title"/>
<spring:message code="login.placeholder.login" var="loginPlaceholder"/>
<spring:message code="login.placeholder.password" var="passwordPlaceholder"/>
<spring:message code="login.label.rememberme" var="rememberMeLabel"/>
<spring:message code="login.button.signin" var="submitLabel"/>
<spring:message code="login.label.signin" var="siginLabel"/>
<spring:message code="login.label.donothaveaccountyet" var="donotHaveAccount"/>
<spring:message code="login.label.joinnow" var="joinNow"/>
<spring:message code="login.label.invalidlogin" var="invalidLoginLabel"/>

<%--URLs--%>
<c:url value="/j_spring_security_check" var="loginUrl"/>
<c:url value="/registration" var="regUrl"/>


<html>
<head>
    <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container frame-container" align="center">
    <h2><span id="login-main-label">${siginLabel}</span></h2>
    <c:if test="${message}">
        <h6 class="login-exc-col">${invalidLoginLabel}</h6>
    </c:if>

    <form action="${loginUrl}" method="post" class="form-horizontal">
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input class="form-control" name="username" type="text"
                       placeholder="${loginPlaceholder}" autofocus/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input class="form-control" name="password" type="password"
                       placeholder="${passwordPlaceholder}"/>
            </div>
        </div>
        <br>
        <div class="form-group">
            <div class="col-sm-7">
                <i>${rememberMeLabel}</i>
                <input type="checkbox" name="remember-me"/>
            </div>
        </div>
        <input type="submit" class="btn btn-info btn-bg my-btn-size" value="${submitLabel}"/>
    </form>
    <hr>
    <label for="signInLink" style="color:#808080">
        ${donotHaveAccount}
    </label>
    <a href="${regUrl}" id="signInLink">${joinNow}</a>
    <br><br>
</div>

</body>
</html>