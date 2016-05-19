<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--i18n--%>
<spring:message code="navbar.item.english" var="englishItem"/>
<spring:message code="navbar.item.russian" var="russinItem"/>
<spring:message code="language.label.language" var="langugeLabel"/>
<spring:message code="navbar.label.members" var="membersLabel"/>
<%--URLs--%>
<c:url value="/allUserSearch" var="allUserSearchUrl"/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>

<nav class="navbar navbar-default bg-nav navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <c:url value="/users/user" var="ownPageUrl"/>
            <a class="navbar-brand" href="${ownPageUrl}"><span class="nav-comp">Instagram</span></a>
        </div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <spring:message code="navbar.placeholder.search" var="search"/>
                <spring:message code="select2.i18n.locale" var="locale"/>
                <select id="navbarSearch" data-placeholder="${search}" locale="${locale}"
                         style="width:300px" onchange="window.location.href=this.value;"></select>
            </div>
        </form>

        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="${allUserSearchUrl}">
                    <span class="nav-comp">
                        ${membersLabel}
                    </span>
                </a>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                   aria-haspopup="true" aria-expanded="false">
                    <span class="nav-comp">
                    ${langugeLabel}
                    </span>
                    <span class="caret nav-comp"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="?lang=en">${englishItem}</a></li>
                    <li><a href="?lang=ru">${russinItem}</a></li>
                </ul>
            </li>
            <li><a href="${logoutUrl}"><span class="nav-comp"><spring:message code="navbar.label.logout"/></span></a>
            </li>
        </ul>
    </div>
</nav>
