<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-default bg-nav navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <c:url value="/" var="pageUrl"/>
            <a class="navbar-brand" href="${pageUrl}"><span class="nav-comp">Instagram</span></a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-haspopup="true" aria-expanded="false"><span class="nav-comp">
                    <spring:message code="language.label.language"/></span><span class="caret nav-comp"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="?lang=en"><spring:message code="navbar.item.english"/> </a></li>
                    <li><a href="?lang=ru"><spring:message code="navbar.item.russian"/></a></li>
                </ul>
            </li>
            <c:url value="/j_spring_security_logout" var="logoutUrl"/>
            <li><a href="${logoutUrl}"><span class="nav-comp"><spring:message code="navbar.label.logout"/></span></a>
            </li>
        </ul>
    </div>
</nav>