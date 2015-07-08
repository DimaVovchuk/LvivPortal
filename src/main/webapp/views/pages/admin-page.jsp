<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>
<head>
	<title><cdg:l18n key="admin.page"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="admin-page">
	<div class="row">
		<div class="col l8 offset-l2 m10 offset-m1 s12 z-depth-2">
			<h4 class="center-align"><cdg:l18n key="admin.page"/></h4>

			<div class="section">
				<div class="divider"></div>
			</div>
			<div class="row">
				<div class="col l4 m5 s6" style="margin-top: 10px">
					<div class="center-align">
						<c:choose>
							<c:when test="${empty adminAvatar}">
								<img src="${pageContext.request.contextPath}/upload/photo/user.png"
								     class="circle responsive-img" width="80%">
							</c:when>
							<c:otherwise>
								<img src="${pageContext.request.contextPath}/upload/photo/${adminAvatar}"
								     class="circle responsive-img" width="80%">
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="col l8 m7 s6">
					<h4><cdg:l18n key="company.info"/></h4>

					<cdg:l18n key="login.firstname"/>: ${adminData.name}<br>
					<cdg:l18n key="login.lastname"/>: ${adminData.surname}<br>
					<cdg:l18n key="login.email"/>: ${adminData.mail}<br>
					<cdg:l18n key="login.phone"/>: ${adminData.phone}<br>
					<cdg:l18n key="company.about"/>: ${adminData.about}<br><br>
					<br>
					<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=edit"><i
							class="mdi-action-settings left"></i><cdg:l18n key="admin.editprofile"/></a>
				</div>
			</div>

			<div class="section">
				<div class="divider"></div>
			</div>

			<div class="section center-align">
				<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=showAllUser&requestType=showAllUser"><i
						class="mdi-action-settings left"></i><cdg:l18n key="admin.allusers"/></a>
				<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=editPlacesAdminPage"><i
						class="mdi-action-settings left"></i><cdg:l18n key="admin.edit.place"/></a>
				<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=addNewPlace"><i
						class="mdi-action-settings left"></i><cdg:l18n key="admin.addnewplace"/></a>
				<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=adminStatistic"><i
						class="mdi-action-settings left"></i><cdg:l18n key="statistic.title"/></a>
				<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=adminConfirmCustomPlace"><i
						class="mdi-action-settings left"></i><cdg:l18n key="admin.confirm.custom.place"/></a>
				<a class="btn cyan darken-2 waves-effect waves-light" href="/portal?command=adminConfirmRecommendedPlace"><i
						class="mdi-action-settings left"></i><cdg:l18n key="admin.confirm.recommended.place"/></a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>
