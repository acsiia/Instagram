<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<%--i18n--%>
<spring:message code="pages.title.signup" var="title"/>
<spring:message code="signup.label.input_inf" var="inputInf"/>
<spring:message code="signup.placeholder.login" var="Login"/>
<spring:message code="signup.placeholder.password" var="Password"/>
<spring:message code="signup.placeholder.repeatedlpassword" var="RepeatedPassword"/>
<spring:message code="signup.button.signup" var="SignUpButton"/>
<spring:message code="signup.button.login" var="SignInButton"/>
<spring:message code="signup.label.alreadyhaveanaccount" var="haveAccount"/>

<%--URLs--%>
<c:url value="/registration/registerUser" var="regUserUrl"/>
<c:url value="/login" var="loginUrl"/>


<html lang="en">
<head>
    <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:langnav/>
<div class="container frame-container" align="center">
    <h2><span class="signup-main-label">${inputInf}</span></h2>
    <form:form action="${regUserUrl}" method="post" commandName="userForm" class="form-horizontal" role="form">

        <form:errors path="login" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <form:input path="login" class="form-control" type="text" placeholder="${Login}" autofocus="true"/>
            </div>
        </div>


        <form:errors path="password" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <form:input path="password" class="form-control" type="password" placeholder="${Password}"
                            disabled="false"/>
            </div>
        </div>

        <form:errors path="repeatedPassword" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <form:input path="repeatedPassword" class="form-control" type="password"
                            placeholder="${RepeatedPassword}"
                            disabled="false"/>
            </div>
        </div>

        <form:errors path="email" cssClass="error" cssStyle="color:red"/>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <form:input path="email" class="form-control" type="email" placeholder="Email" disabled="false"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <input type="submit" class="btn-bg btn btn-info my-btn-size" value="${SignUpButton}"/>
            </div>
        </div>
    </form:form>
    <hr>
    <label for="signInLink" style="color:#808080">
        ${haveAccount}
    </label>
    <a href="${loginUrl}" id="signInLink">${SignInButton}</a>
    <br><br>
</div>

</body>
</html>
