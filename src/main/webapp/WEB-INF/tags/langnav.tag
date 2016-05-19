<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default bg-nav">
    <div class="container-fluid">
        <div class="navbar-header">
            <c:url value="/" var="defaultPageUrl"/>
            <a class="navbar-brand" href="${defaultPageUrl}"><span class="nav-comp">Instagram</span></a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-haspopup="true" aria-expanded="false"><span class="nav-comp">
                    <spring:message code="language.label.language"/></span><span class="caret nav-comp"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="?lang=en"><spring:message code="language.item.english"/></a></li>
                    <li><a href="?lang=ru"><spring:message code="language.item.russian"/></a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>