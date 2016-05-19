<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ attribute name="title" required="true" %>

<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="<c:url value="/resources/photo/favicon.png"/>" type="image/png">
<script src="<c:url value="/resources/bootstrap/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/select2/js/select2.js"/>"></script>
<spring:message code="select2.i18n.locale" var="locale"/>
<script src="<c:url value="/resources/select2/js/i18n/${locale}.js"/>"></script>
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet">
<link href="<c:url value="/resources/css/common.css"/>" type="text/css" rel="stylesheet">
<link href="<c:url value="/resources/select2/css/select2.css"/>" type="text/css" rel="stylesheet">
<link href="<c:url value="/resources/bootstrap/css/bootstrap-glyphicons.css"/>" type="text/css" rel="stylesheet">
