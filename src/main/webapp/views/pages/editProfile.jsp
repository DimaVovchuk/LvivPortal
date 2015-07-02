<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<html>
<head>
	<title><cdg:l18n key="usercab.title"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>
<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<jsp:include page="/views/elements/header.jsp"/>
<div class="container">
	<div class="row">
		<h1><cdg:l18n key="usercab.editprofil"/></h1>
		<hr>
		<form method=post enctype=multipart/form-data action="portal?command=updateprofile">
			<c:set var="command" scope="session" value="updateprofile"/>
			<div class="col s4">
				<div class="text-center">
					<c:choose>
						<c:when test="${not empty avatar and empty vk_id}">
							<img src="${pageContext.request.contextPath}/upload/photo/${avatar}" width=70%
							     class="circle responsive-img" name="newAvatar">
						</c:when>
						<c:when test="${not empty vk_id and not empty avatar}">
							<img src="${avatar}" width=70%
								 class="circle responsive-img" name="newAvatar">
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/upload/photo/user.png" width=70%
							     class="circle responsive-img" name="newAvatar">
						</c:otherwise>
					</c:choose>
					<h6><cdg:l18n key="usercab.uploadphoto"/></h6>
					<input id="fileupload" type="file" name="sendfile">
				</div>
			</div>

			<div class="col s8">
				<h3><cdg:l18n key="usercab.info"/></h3>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="NewName"><cdg:l18n key="usercab.name"/></label>
						<input value="<c:out value="${userForEdit.name}"/>" id="NewName" type="text" name="NewName">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="surname"><cdg:l18n key="usercab.lastname"/></label>
						<input value="<c:out value="${userForEdit.surname}"/>" id="surname" type="text" name="surname">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="companyName"><cdg:l18n key="usercab.companyname"/></label>
						<%--<input value="${userForEdit.role}" id="role" type="text" type="hidden" name="role">--%>
						<input value="${userForEdit.companyName}" id="companyName" type="text" name="companyName">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="login"><cdg:l18n key="usercab.login"/></label>
						<input value="${userForEdit.login}" id="login" type="text" name="login" disabled>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="mail"><cdg:l18n key="usercab.mail"/></label>
						<input value="${userForEdit.mail}" id="mail" type="text" name="mail" disabled>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-communication-phone prefix"></i>
						<label class="active" for="phone"><cdg:l18n key="usercab.pnumber"/></label>
						<input value="${userForEdit.phone}" id="phone" type="text" name="phone">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="about"><cdg:l18n key="usercab.about"/></label>
						<input value="<c:out value="${userForEdit.about}"/>" id="about" type="text" name="about">
					</div>
				</div>

				<div class="row">
					<h6><cdg:l18n key="usercab.youmay"/><a href="/portal?command=resetEmail"> <cdg:l18n key="usercab.change"/></a> <cdg:l18n key="usercab.yourpas"/></h6>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<button class="btn waves-effect waves-light" type="submit" name="save"><cdg:l18n key="usercab.savechange"/><i class="mdi-content-send right"></i></button>
					<button class="btn waves-effect waves-light" type="reset" name="cancel"><cdg:l18n key="usercab.cancel"/></button>
				</div>
			</div>
		</form>
	</div>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>