<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<html>
<head>
	<title><cdg:l18n key="usercab.edit"/></title>
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
		<h1>Edit Profile</h1>
		<hr>
		<form method=post enctype=multipart/form-data action="portal/update">
			<c:set var="command" scope="session" value="updateprofile"/>
			<c:set var="typePhoto" scope="session" value="avatarFoto"/>
			<div class="col s4">
				<div class="text-center">
					<c:choose>
						<c:when test="${empty avatar}">
							<img src="${pageContext.request.contextPath}/upload/photo/user.png" width=70%
							     class="circle responsive-img" name="newAvatar">
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/upload/photo/${avatar}" width=70%
							     class="circle responsive-img" name="newAvatar">
						</c:otherwise>
					</c:choose>
					<h6>Upload a different photo...</h6>
					<input id="fileupload" type="file" name="sendfile">
				</div>
			</div>

			<div class="col s8">
				<h3>Personal info</h3>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="NewName">First Name:</label>
						<input value="<c:out value="${userForEdit.name}"/>" id="NewName" type="text" name="NewName">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="surname">Last Name:</label>
						<input value="<c:out value="${userForEdit.surname}"/>" id="surname" type="text" name="surname">
				</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="login">Login:</label>
						<input value="${userForEdit.login}" id="login" type="text" name="login" disabled>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="mail">Email:</label>
						<input value="${userForEdit.mail}" id="mail" type="text" name="mail">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-communication-phone prefix"></i>
						<label class="active" for="phone">Phone number:</label>
						<input value="${userForEdit.phone}" id="phone" type="text" name="phone">
					</div>
				</div>

				<div class="row">
					<div class="input-field col s12">
						<i class="mdi-action-account-circle prefix"></i>
						<label class="active" for="about">About:</label>
						<input value="<c:out value="${userForEdit.about}"/>" id="about" type="text" name="about">
					</div>
				</div>

				<div class="row">
					<h6>You may <a href="/portal?command=userPlace">change</a> your password</h6>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label"></label>
					<button class="btn waves-effect waves-light" type="submit" name="save">Save Changes
						<i class="mdi-content-send right"></i></button>
					<button class="btn waves-effect waves-light" type="reset" name="cancel">Cancel</button>
				</div>
			</div>
		</form>
	</div>
</div>
<hr>
<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>