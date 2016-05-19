<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="toolkit" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/taglib/postimage" prefix="pi" %>

<%--i18n--%>
<spring:message code="pages.title.user" var="title"/>
<spring:message code="user.button.addpost" var="addPostButton"/>
<spring:message code="user.label.username" var="usernameLabel"/>
<spring:message code="user.tooltip.delete" var="deleteTooltip"/>
<spring:message code="user.post.popup.head" var="postPopupHead"/>
<spring:message code="user.placeholder.sendcomment" var="commentPlaceholder"/>
<spring:message code="user.button.send" var="saveButton"/>
<spring:message code="user.button.delete" var="deleteButton"/>
<spring:message code="user.button.cancel" var="cancelButton"/>
<spring:message code="user.popup.label.question" var="questionLabel"/>

<%--URLs--%>
<c:url value="/resources/js/user.js" var="userScripts"/>
<c:url value="/post?postOwnerId=${profile.user}" var="addPostUrl"/>
<c:url value="/profile/user=${profile.user}" var="showProfileUrl"/>
<c:url value="/resources/photo/default_avatar.png" var="defaultAvatar"/>


<html>
<head>
    <toolkit:header title="${title}"/>
    <script type="text/javascript">
        var contextPath='${pageContext.request.contextPath}';
    </script>
    <script src="${userScripts}"></script>
</head>

<body class="bg-common">
<toolkit:navbar/>
<c:choose>
    <c:when test="${isEditable}">
        <spring:message code="user.button.myshowprofile" var="showProfileButton"/>
    </c:when>
    <c:otherwise>
        <spring:message code="user.button.showprofile" var="showProfileButton"/>
    </c:otherwise>
</c:choose>
<%--<div style="margin:0 auto">--%>
<a href="${addPostUrl}">
    <input type="button" class="user-btn-size btn btn-primary user-btn-fixed user-add-btn-position"
           value="${addPostButton}">
</a>

<a href="${showProfileUrl}">
    <input type="button" class="user-btn-size btn btn-primary user-btn-fixed user-profile-btn-position"
           value="${showProfileButton}">
</a>
<%--</div>--%>

<div class="container user-layer" align="center">
    <br><br>
    <br><br>
    <h2 class="user-label-fixed">${username}</h2>
    <div id="allPosts">
        <c:forEach var="post" items="${posts}">
            <div id="fullPostContent${post.id}" class="user-post-br" align="center">
                <div class="col-sm-1 user-post-position">
                    <div class="thumbnail">
                        <c:url value="/profile/user=${post.sender}" var="userProfile"/>
                        <a href="${userProfile}">
                            <c:choose>
                                <c:when test="${post.senderAvatar != null}">
                                    <img class="img-responsive user-photo"
                                         src="<pi:image imageByte="${post.senderAvatar}"/>">
                                </c:when>
                                <c:otherwise>
                                    <img class="img-responsive user-photo"
                                         src="${defaultAvatar}">
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </div>
                </div>
                <div class="col-sm-5 user-post-position">
                    <div class="panel panel-default">
                        <div class="left panel-heading">
                            <div align="left">
                                <span class="text-muted">${usernameLabel}:</span>
                                <strong>${post.senderName}</strong>

                                <div id="remove${post.id}" class="post-del cursor-pointer"
                                     data-toggle="modal" data-target="#sureQuestion">
                                    <c:if test="${authUser.userId eq post.sender or authUser.userId eq post.owner}">
                                        <span data-toggle="tooltip" title="${deleteTooltip}"
                                              class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </c:if>

                                </div>
                            </div>
                        </div>

                        <div id="post${post.id}" class="cursor-pointer" data-toggle="modal" data-target="#myModal"
                             id-parameter="${post.id}">

                            <div class="user-post-scroll">
                            <pre id="contentPost${post.id}" class="pre-post">
                                <p>${post.postContent}</p>
                            </pre>
                            </div>
                            <hr>
                            <div id="imgPost${post.id}">
                                <c:if test="${post.imageByte != null}">
                                    <img class="post-popup-img" src="<pi:image imageByte="${post.imageByte}"/>">
                                </c:if>
                            </div>

                        </div>
                        <br>

                        <div class="panel-footer">
                            <div class="row">
                                <div id="likeButton${post.id}" class="cursor-pointer col-sm-1">
                                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
                                </div>

                                <span id="likeCount${post.id}" class="col-sm-1">${post.like}</span>

                                <div id="dislikeButton${post.id}" class="cursor-pointer col-sm-1">
                                    <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>
                                </div>

                                <span id="dislikeCount${post.id}" class="col-sm-1">${post.dislike}</span>

                                <span class="col-sm-offset-4 col-sm-4">${post.dateDispatch}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div id="myModal" class="modal" role="dialog">
        <div class="modal-dialog modal-lg">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">${postPopupHead}</h4>
                </div>
                <div class="modal-body">
                    <div id="popupContent">
                        <pre class="pre-post"></pre>
                    </div>
                    <hr>

                    <div id="popupImg">
                        <img class="post-popup-img">
                    </div>
                    <hr>
                    <div id="commentsOfPost" class="row"></div>

                </div>
                <div id="authUser${authUser.userId}" current-user="${profile.user}"></div>
                <div class="row">
                    <form id="commentInput">
                        <textarea id="commentContent" placeholder="${commentPlaceholder}" rows="4"
                                  class="form-control comment-textarea col-sm-offset-1 col-sm-5"></textarea>

                        <p>
                            <input id="sendComment" type="submit"
                                   class="btn btn-primary col-sm-3 comment-btn"
                                   value="${saveButton}">
                        </p>
                    </form>
                </div>
                <br>
            </div>
        </div>
    </div>

    <div id="sureQuestion" class="modal" role="dialog">
        <div class="modal-dialog modal-sm">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>${questionLabel}</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <input id="popupDeleteBtn" style="min-width: 100px"
                               class="btn btn-danger col-lg-offset-1 col-sm-3"
                               data-dismiss="modal" value="${deleteButton}" id-parameter="">
                        <input id="popupCancelBtn" style="min-width: 100px"
                               class="btn btn-default col-lg-offset-2 col-sm-3"
                               data-dismiss="modal" value="${cancelButton}">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="sureQuestionComment" class="modal" role="dialog" style="top:5%">
        <div class="modal-dialog modal-sm">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4>${questionLabel}</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <input id="popupDeleteCommentBtn" style="min-width: 100px"
                               class="btn btn-danger col-lg-offset-1 col-sm-3"
                               data-dismiss="modal" value="${deleteButton}" id-parameter="">
                        <input id="popupCancelCommentBtn" style="min-width: 100px"
                               class="btn btn-default col-lg-offset-2 col-sm-3"
                               data-dismiss="modal" value="${cancelButton}">
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
