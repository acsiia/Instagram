<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<%--i18n--%>
<spring:message code="pages.title.search" var="title"/>
<spring:message code="search.header.firstname" var="firstNameLabel"/>
<spring:message code="search.label.lastname" var="lastNameLabel"/>
<spring:message code="search.label.middlename" var="middleNameLabel"/>
<%--URLs--%>
<c:url value="/resources/photo/default_avatar.png" var="defaultAvatar"/>

<html>
<head>
  <toolkit:header title="${title}"/>
</head>
<body class="bg-common">
<toolkit:defaultnav/>
<div class="container search-container" align="center">
  <br><br>
  <br><br>
  <table class="table table-search-stl">
    <c:forEach var="user" items="${users}">
      <tr class="tr-stl">
        <th></th>
        <th>${firstNameLabel}</th>
        <th>${lastNameLabel}</th>
        <th>${middleNameLabel}</th>
      </tr>
      <tr id="userInf${user.id}">
        <td>
          <c:url value="/users/user/${user.user}" var="userPageUrl"/>
          <a href="${userPageUrl}">
          <c:choose>
            <c:when test="${not empty user.avatar}">
                <img class="search-icon"
                     src="<pi:image imageByte="${user.avatar}"/>">
            </c:when>
            <c:otherwise>
                <img class="search-icon" src="${defaultAvatar}">
            </c:otherwise>
          </c:choose>
          </a>
        </td>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.surname}"/></td>
        <td><c:out value="${user.secondName}"/></td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
