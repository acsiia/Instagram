<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--i18n--%>
<spring:message code="pages.title.profile" var="title"/>
<spring:message code="profile.datepicker.locale" var="datepickerLocale"/>
<spring:message code="profile.label.edit" var="editButton"/>
<spring:message code="profile.label.sex" var="sexLabel"/>
<spring:message code="profile.popup.select.option.male" var="maleLabel"/>
<spring:message code="profile.popup.select.option.female" var="femaleLabel"/>
<spring:message code="profile.label.firstname" var="firstNameLabel"/>
<spring:message code="profile.label.surname" var="surnameLabel"/>
<spring:message code="profile.label.secondname" var="secondNameLabel"/>
<spring:message code="profile.label.birthday" var="birthdayLabel"/>
<spring:message code="profile.label.country" var="countryLabel"/>
<spring:message code="profile.label.city" var="cityLabel"/>
<spring:message code="profile.popup.head" var="popupHead"/>
<spring:message code="profile.label.avatar" var="avatarLabel"/>
<spring:message code="profile.checkbox.nullcountryvalue" var="nullCountryValue"/>
<spring:message code="profile.placeholder.dateformat" var="dateFormat"/>
<spring:message code="profile.button.send" var="saveButton"/>
<spring:message code="profile.popup.button.cancel" var="cancelButton"/>
<spring:message code="profile.popup.avatar.head" var="avatarPopupHead"/>


<%--URLs--%>
<c:url value="/resources/bootstrap/datepicher/css/bootstrap-datepicker.min.css" var="datepickerStyleSheet"/>
<c:url value="/resources/bootstrap/datepicher/js/bootstrap-datepicker.min.js" var="datepickerScript"/>
<c:url value="/resources/js/profile.js" var="profileScript"/>
<c:url value="/resources/bootstrap/datepicher/locales/bootstrap-datepicker.${datepickerLocale}.min.js"
       var="localeScript"/>
<c:url value="/users/user/${profile.user}" var="userPageUrl"/>
<c:url value="/resources/photo/default_avatar.png" var="defaultAvatar"/>
<c:url value="/profile/updateProfile" var="updateProfileUrl"/>

<html>
<head>
    <toolkit:header title="${title}"/>
    <link href="${datepickerStyleSheet}" type="text/css" rel="stylesheet">
    <script src="${datepickerScript}"></script>
    <script src="${profileScript}"></script>
    <script src="${localeScript}"></script>
</head>
<body class="bg-common">
<toolkit:defaultnav/>

<c:choose>
    <c:when test="${isEditable}">
        <spring:message code="profile.button.myuserpage" var="userPageButton"/>
    </c:when>
    <c:otherwise>
        <spring:message code="profile.button.userpage" var="userPageButton"/>
    </c:otherwise>
</c:choose>
<c:if test="${isEditable}">
    <input id="editButton" type="button"
           class="user-btn-size btn btn-primary user-btn-fixed profile-edit-btn-position"
           data-toggle="modal" data-target="#profileModal"
           value="${editButton}">
</c:if>
<a href="${userPageUrl}">
    <input type="button"
           class="user-btn-size btn btn-primary user-btn-fixed profile-user-return-btn-position"
           value="${userPageButton}">
</a>
<br><br><br>

<div class="container profile-layer" align="center">
    <c:choose>
        <c:when test="${not empty profile.avatar}">
            <img class="profile-img" src="<pi:image imageByte="${profile.avatar}"/>"
                 data-toggle="modal" data-target="#profileAvatarModal">
        </c:when>
        <c:otherwise>
            <img class="profile-default-img" src="${defaultAvatar}">
        </c:otherwise>
    </c:choose>
    <c:if test="${not empty profile.sex}">
        <hr>
    </c:if>
    <div class="row">
        <c:if test="${not empty profile.firstName}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b>${firstNameLabel}:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">
                    ${profile.firstName}
            </h3>
        </c:if>
    </div>
    <div class="row">
        <c:if test="${not empty profile.surname}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b>${surnameLabel}:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">
                    ${profile.surname}
            </h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.secondName}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b>${secondNameLabel}:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">
                    ${profile.secondName}
            </h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.birthday}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b>${birthdayLabel}:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">
                    ${profile.birthday}
            </h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.country}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b>${countryLabel}:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">
                    ${profile.country}
            </h3>
        </c:if>
    </div>

    <div class="row">
        <c:if test="${not empty profile.city}">
            <h3 class="col-sm-3 profile-header-text" align="right">
                <b>${cityLabel}:</b>
            </h3>

            <h3 class="col-sm-6 profile-text" align="left">
                    ${profile.city}
            </h3>
        </c:if>
    </div>
    <div class="row">
        <c:if test="${not empty profile.sex}">
            <c:choose>
                <c:when test="${profile.sex eq 'male'}">
                    <h3 class="col-sm-3 profile-header-text" align="right">
                        <b>${sexLabel}:</b>
                    </h3>

                    <h3 class="col-sm-6 profile-text" align="left">
                            ${maleLabel}
                    </h3>
                </c:when>
                <c:when test="${profile.sex eq 'female'}">
                    <h3 class="col-sm-3 profile-header-text" align="right">
                        <b>${sexLabel}:</b>
                    </h3>

                    <h3 class="col-sm-6 profile-text" align="left">
                            ${femaleLabel}
                    </h3>
                </c:when>
            </c:choose>
        </c:if>
    </div>
    <c:if test="${not empty profile.sex}">
        <hr>
    </c:if>


    <div id="profileModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">${popupHead}</h4>
                </div>
                <div class="modal-body">
                    <form:form action="${updateProfileUrl}" method="post" commandName="editedProfile"
                               id="profileSubmit" enctype="multipart/form-data">
                    <form:hidden path="id"/>
                    <form:hidden path="user"/>
                    <form:hidden path="avatar"/>
                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="avatarChooser" align="right">
                                ${avatarLabel}:
                        </label>
                        <form:input id="avatarChooser" path="picture" value="${profile.avatar}"
                                    class="btn btn-primary btn-file col-sm-4" type="file" name="photo"
                                    accept="image/*"/>
                    </div>
                    <br><br>

                    <form:errors path="firstName" cssClass="error" cssStyle="color:red"/>
                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="firstNameChooser" align="right">
                                ${firstNameLabel}:
                        </label>
                        <form:input id="firstNameChooser" path="firstName"
                                    class="col-sm-4" type="text" maxlength="35"
                                    value="${profile.firstName}" placeholder="${firstNameLabel}"/>
                    </div>
                    <br>

                    <form:errors path="surname" cssClass="error" cssStyle="color:red"/>
                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="surnameChooser" align="right">
                                ${surnameLabel}:
                        </label>
                        <form:input id="surnameChooser" class="col-sm-4" type="text" value="${profile.surname}"
                                    maxlength="35"
                                    placeholder="${surnameLabel}" path="surname"/><br>
                    </div>
                    <br>

                    <form:errors path="secondName" cssClass="error" cssStyle="color:red"/>
                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="secondNameChooser" align="right">
                                ${secondNameLabel}:
                        </label>
                        <form:input id="secondNameChooser" class="col-sm-4" type="text"
                                    value="${profile.secondName}" maxlength="35"
                                    placeholder="${secondNameLabel}" path="secondName"/><br>
                    </div>
                    <br>

                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="countryChooser" align="right">
                                ${countryLabel}:
                        </label>
                        <form:select id="countryChooser" path="countryID" class="col-sm-4"
                                     itemValue="${editedProfile.countryID}">
                            <form:option value="0" label="${nullCountryValue}"/>
                            <form:options items="${countryList}"/>
                        </form:select>
                    </div>
                    <br>

                    <form:errors path="city" cssClass="error" cssStyle="color:red"/>
                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="cityChooser" align="right">
                                ${cityLabel}:
                        </label>
                        <form:input id="cityChooser" class="col-sm-4" type="text" path="city" maxlength="35"
                                    placeholder="${cityLabel}" value="${profile.city}"/><br>
                    </div>
                    <br>

                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="sexSelect" align="right">
                                ${sexLabel}:
                        </label>
                        <form:select path="sex" class="col-sm-2" id="sexSelect">
                            <form:option value="male" label="${maleLabel}"/>
                            <form:option value="female" label="${femaleLabel}"/>
                        </form:select>
                    </div>
                    <br>

                    <div class="row">
                        <label class="col-sm-offset-2 col-sm-2" for="datepickerBirthday" align="right">
                                ${birthdayLabel}:
                        </label>

                        <div class='col-sm-3'>
                            <div class="form-group">
                                <div class='input-group date' id='birthdayDatepicker'>
                                    <form:input path="birthday" id="datepickerBirthday"
                                                class="form-control input-sm"
                                                style="cursor: default" locale="${datepickerLocale}"
                                                placeholder="${dateFormat}" value="${profile.birthday}"/>
                                    <label class="input-group-addon cursor-pointer" for="datepickerBirthday">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <hr>
                    <div class="row">
                        <input type="submit" class="col-sm-offset-4 col-sm-2 btn btn-primary"
                               value="${saveButton}">
                        <input type="submit" data-dismiss="modal" class="col-sm-offset-1 col-sm-2 btn btn-default"
                               value="${cancelButton}">
                        </form:form>
                    </div>
                    <br>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${isNotValid}">
        <script>
            $('#profileModal').modal('show');
        </script>
    </c:if>
    <%--avatar popup--%>
    <div id="profileAvatarModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">${avatarPopupHead}</h4>
                </div>
                <div class="modal-body">
                    <img class="profile-popup-avatar"
                         src="<pi:image imageByte="${profile.avatar}"/>">
                </div>
                <br>
            </div>
        </div>
    </div>
</div>

</body>
</html>
