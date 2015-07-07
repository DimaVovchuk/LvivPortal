<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<header>
	<div class="navbar-fixed">
		<nav>
			<div class="nav-wrapper blue-grey darken-4">
				<a href="/portal?command=index" class="brand-logo"><img class="logo"
				                                                        src="${pageContext.request.contextPath}/images/logo.png"></a>
				<a href="#" data-activates="mobile-navbar" class="button-collapse"><i
						class="mdi-navigation-menu"></i></a>

				<c:choose>
					<c:when test="${login!=null}">
						<ul class="right">
							<li><a href="#" data-activates="user-navbar" class="button-menu"><i
									class="mdi-action-account-circle left"></i></a></li>
						</ul>

						<ul class="right hide-on-med-and-down">
							<li class="header-link" data-link="index"><a href="/portal?command=index"><cdg:l18n
									key="header.home"/></a></li>
							<li class="header-link" data-link="places"><a href="/portal?command=place"><cdg:l18n
									key="header.places"/></a></li>
							<c:choose>
								<c:when test="${userDataTrip != null }">
									<li><a href="/portal?command=showMap"><cdg:l18n key="header.plan"/></a></li>
								</c:when>
								<c:otherwise>
									<li class="header-link" data-link="plan"><a class="modal-trigger"
									                                            href="#set-date-time"><cdg:l18n
											key="header.plan"/></a></li>
								</c:otherwise>
							</c:choose>
							<li class="header-link" data-link="about"><a href="/portal?command=about"><cdg:l18n
									key="header.about"/></a></li>
							<c:set var="pageNumber" scope="request" value="1"/>
							<li><a class="dropdown-button" href="#" data-activates="lang-dropdown"><cdg:l18n
									key="header.lang"/><i class="mdi-navigation-arrow-drop-down right"></i></a></li>
						</ul>

						<ul class="side-nav" id="mobile-navbar">
							<li class="header-link" data-link="index"><a href="/portal?command=index"><cdg:l18n
									key="header.home"/></a></li>
							<li class="header-link" data-link="places"><a href="/portal?command=place"><cdg:l18n
									key="header.places"/></a></li>
							<c:choose>
								<c:when test="${userDataTrip != null }">
									<li class="header-link" data-link="plan"><a href="/portal?command=showMap"><cdg:l18n
											key="header.plan"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a class="modal-trigger" href="#set-date-time"><cdg:l18n key="header.plan"/></a>
									</li>
								</c:otherwise>
							</c:choose>
							<li class="header-link" data-link="about"><a href="/portal?command=about"><cdg:l18n
									key="header.about"/></a></li>
							<c:set var="pageNumber" scope="request" value="1"/>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="right hide-on-med-and-down">
							<li class="header-link" data-link="index"><a href="/portal?command=index"><cdg:l18n
									key="header.home"/></a></li>
							<li class="header-link" data-link="places"><a href="/portal?command=place"><cdg:l18n
									key="header.places"/></a></li>
							<c:choose>
								<c:when test="${userDataTrip != null }">
									<li class="header-link" data-link="plan"><a href="/portal?command=showMap"><cdg:l18n
											key="header.plan"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a class="modal-trigger" href="#set-date-time"><cdg:l18n key="header.plan"/></a>
									</li>
								</c:otherwise>
							</c:choose>
							<li class="header-link" data-link="about"><a href="/portal?command=about"><cdg:l18n
									key="header.about"/></a></li>
							<c:set var="pageNumber" scope="request" value="1"/>
							<li><a class="dropdown-button" href="#" data-activates="lang-dropdown"><cdg:l18n
									key="header.lang"/><i
									class="mdi-navigation-arrow-drop-down right"></i></a></li>
							<li><a class="modal-trigger" href="#sign-in"><i
									class="mdi-action-account-circle left"></i><cdg:l18n
									key="header.sign"/></a>
							</li>
						</ul>

						<ul class="side-nav" id="mobile-navbar">
							<li class="header-link" data-link="index"><a href="/portal?command=index"><cdg:l18n
									key="header.home"/></a></li>
							<li class="header-link" data-link="places"><a href="/portal?command=place"><cdg:l18n
									key="header.places"/></a></li>
							<c:choose>
								<c:when test="${userDataTrip != null }">
									<li class="header-link" data-link="plan"><a href="/portal?command=showMap"><cdg:l18n
											key="header.plan"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a class="modal-trigger" href="#set-date-time"><cdg:l18n key="header.plan"/></a>
									</li>
								</c:otherwise>
							</c:choose>
							<li class="header-link" data-link="about"><a href="/portal?command=about"><cdg:l18n
									key="header.about"/></a></li>
							<c:set var="pageNumber" scope="request" value="1"/>
							<li class="divider"></li>
							<li><a class="modal-trigger" href="#sign-in"><i
									class="mdi-action-account-circle right"></i><cdg:l18n key="header.sign"/></a>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>

				<ul class="side-nav" id="user-navbar">
					<%--<c:choose>--%>
					<%--<c:when test="${not empty ava and not empty avatar_id}">--%>
					<%--<li class="center-align" style="padding-top: 10px"><img class="circle responsive-img"--%>
					<%--src="${pageContext.request.contextPath}/upload/photo/${ava}"--%>
					<%--width="160"></li>--%>
					<%--</c:when>--%>
					<%--<c:when test="${not empty ava and not empty vk_id and empty avatar_id}">--%>
					<%--<li class="center-align" style="padding-top: 10px"><img class="circle responsive-img"--%>
					<%--src="${ava}" width="160"></li>--%>
					<%--</c:when>--%>
					<%--<c:otherwise>--%>
					<%--<li class="center-align" style="padding-top: 10px"><img class="circle responsive-img"--%>
					                                                        <%--src="${pageContext.request.contextPath}/upload/photo/user.png"--%>
					                                                        <%--width="160"></li>--%>
					<%--</c:otherwise>--%>
					<%--</c:choose>--%>
						<li class="center-align" style="padding-top: 10px"><img class="circle responsive-img"
							                                             src="${pageContext.request.contextPath}/upload/photo/${avatarReference}" width="160"></li>
						<c:choose>
						<c:when test="${role == 1}">
							<li><a href="/portal?command=adminCabinet"><i class="material-icons left">perm_identity</i><cdg:l18n
										key="header.mycabinet"/></a></li>
						</c:when>
						<c:when test="${role == 3}">
							<li><a href="/portal?command=companyInformation&id=" + ${userID}><i
									class="material-icons left">perm_identity</i><cdg:l18n
									key="header.mycabinet"/></a></li>
						</c:when>
							<c:when test="${role == 4}">
								<li><a href="/portal?command=companyInformation&id=" + ${userID}><i
										class="material-icons left">perm_identity</i><cdg:l18n
										key="header.mycabinet"/></a></li>
							</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					<li><a href="/portal?command=userWays"><i class="mdi-maps-directions-bike left"></i><cdg:l18n
							key="header.myrotes"/></a>
					</li>
					<li><a href="/portal?command=userPlace"><i class="mdi-action-account-balance left"></i><cdg:l18n
							key="header.myplaces"/></a>
					</li>
					<c:if test="${role != 1 and role != 2}">
						<li><a href="/portal?command=allUserPhoto"><i class="mdi-image-photo left"></i><cdg:l18n
								key="header.gallery"/></a></li>
					</c:if>
					<li><a href="/portal?command=commercial"><i class="material-icons left">assignment_ind</i><cdg:l18n
							key="header.agenceguide"/></a></li>
					<li><a href="/portal?command=edit"><i class="mdi-action-settings left"></i><cdg:l18n
							key="header.settings"/></a></li>

					<li class="divider"></li>
					<c:choose>
						<c:when test="${userDataTrip != null && userDataTrip.isSaved == false && userDataTrip.isFull == true}">
							<li><a class="modal-trigger" href="#sure-save-sign-out"><i
									class="mdi-navigation-close left"></i><cdg:l18n key="header.signout"/></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/portal?command=signOut"><i class="mdi-navigation-close left"></i><cdg:l18n
									key="header.signout"/></a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
	</div>

	<ul id="lang-dropdown" class="dropdown-content">
		<li>
			<a href="#" onclick="language('en')">
				<img class="text-middle" src="${pageContext.request.contextPath}/images/lang/EN.png"> <cdg:l18n
					key="header.lang.en"/>
			</a>
		</li>
		<li>
			<a href="#" onclick="language('ua')">
				<img class="text-middle" src="${pageContext.request.contextPath}/images/lang/UA.png"> <cdg:l18n
					key="header.lang.ua"/>
			</a>
		</li>
	</ul>
</header>

<jsp:include page="/views/modals/login.jsp"/>
<jsp:include page="/views/modals/reset-password.jsp"/>
<jsp:include page="/views/modals/new-route.jsp"/>
<jsp:include page="/views/modals/sure-save.jsp"/>

<script src="${pageContext.request.contextPath}/js/pages/header.js"></script>
<script src='${pageContext.request.contextPath}/js/<cdg:l18n key="validator.js"/>'></script>
<div id="start-modal" data-modal="${requestScope.modal}"></div>