<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>

<%--i18n--%>
<spring:message code="pages.title.admin" var="title"/>

<%--URLs--%>
<c:url value="/resources/js/admin.js" var="adminScript"/>

<html>
<head>
    <toolkit:header title="${title}"/>
    <script type="text/javascript">
        var contextPath='${pageContext.request.contextPath}';
    </script>
    <script src="${adminScript}"></script>
</head>
<body class="bg-common">
<toolkit:defaultnav/>
<div class="container" align="center">
    <br><br>
    <br><br>
    <table class="table table-stl">
        <tr class="tr-stl">
            <th style="color: #ffffff;"><spring:message code="admin.label.username"/></th>
            <th style="color: #ffffff;"><spring:message code="admin.label.email"/></th>
            <th style="color: #ffffff;"><spring:message code="admin.tablehead.delete"/></th>
            <th style="color: #ffffff;"><spring:message code="admin.tablehead.block"/></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr id="userInf${user.userId}">
                <td style="color: #ffffff;"><c:out value="${user.login}"/></td>
                <td style="color: #ffffff;"><c:out value="${user.email}"/></td>
                <td>
                    <input type="button" id="askedPopup${user.userId}" class="btn btn-danger"
                           data-toggle="modal" data-target="#verification"
                           value="<spring:message code="admin.button.delete"/>">
                </td>
                <td>
                    <spring:message code="admin.button.ban" var="ban"/>
                    <spring:message code="admin.button.unban" var="unban"/>
                    <div id="banLabel" ban="${ban}"></div>
                    <div id="unbanLabel" unban="${unban}"></div>

                    <c:choose>
                        <c:when test="${user.enable}">
                            <input style="min-width: 100px" id="banBtn${user.userId}" type="button"
                                   class="btn btn-success"
                                   value="${ban}">
                        </c:when>
                        <c:otherwise>
                            <input style="min-width: 100px" id="banBtn${user.userId}" type="button"
                                   class="btn btn-warning"
                                   value="${unban}">
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>


    <div id="verification" class="modal" role="dialog">
        <div class="modal-dialog modal-sm">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4><spring:message code="admin.popup.label.question"/></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <input id="popupDeleteBtn" style="min-width: 100px"
                               class="btn btn-danger col-lg-offset-1 col-sm-3"
                               data-dismiss="modal" value="<spring:message code="admin.button.delete"/>">
                        <input id="popupCancelBtn" style="min-width: 100px"
                               class="btn btn-default col-lg-offset-2 col-sm-3"
                               data-dismiss="modal" value="<spring:message code="admin.popup.button.cancel"/>">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
