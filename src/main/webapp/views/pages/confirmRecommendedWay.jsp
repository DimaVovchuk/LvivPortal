<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>

<head>
	<title><cdg:l18n key="admin.title"/></title>
</head>

<jsp:include page="/views/elements/css.jsp"/>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div id="company-page" class="full-height">
	<h3 class="center-align"><cdg:l18n key="admin.title"/></h3>
	<div class="row">
		<div class="col l8 offset-l2 m10 offset-m1 s12 z-depth-2">
			<ul class="collapsible popout" data-collapsible="accordion">
				<c:forEach var="mapelem" items="${allWayInfo}">
					<li>
						<div class="collapsible-header">
							<div class="col s6 left-align">
								<cdg:l18n key="company.routename"/>: <b>${mapelem.key.name}</b>
							</div>
							<div class="col s6 right-align">
								<a class="btn modal-trigger" href="#orderWayModel"
								   onclick="$('#sendOrderOnMail').attr('rel','/portal?command=orderWaySendMail&wayId=${mapelem.key.id}&gidId=${pageContext.request.getParameter('id')}&type=way');"
								   style="text-transform: uppercase"><cdg:l18n key="admin.edit.places.confirm"/></a>
								<a class="btn modal-trigger" href="#orderWayModel"
								   onclick="$('#sendOrderOnMail').attr('rel','/portal?command=orderWaySendMail&wayId=${mapelem.key.id}&gidId=${pageContext.request.getParameter('id')}&type=way');"
								   style="text-transform: uppercase"><cdg:l18n key="admin.edit.places.cancel"/></a>
							</div>
						</div>

						<div class="collapsible-body">
							<c:forEach var="MapPlaceElem" items="${mapelem.value}">
								<ul class="collapsible popout" data-collapsible="accordion">
									<li>
										<div class="collapsible-header grey lighten-3">
											<b><cdg:l18n key="map.route.day"/> ${MapPlaceElem.key}</b>
										</div>
										<div class="collapsible-body">
											<div class="collection">
												<c:forEach var="listWayPlace" items="${MapPlaceElem.value}">
													<a href="#" class="collection-item black-text">
														<img class="circle responsive-img"
														     src="${pageContext.request.contextPath}/upload/photo/${listWayPlace.imageReference}">

														<div class="valign-wrapper" style="height:100%">
															<div class="valign">
																<div class="truncate">

																	<b>${listWayPlace.name}</b><br>${listWayPlace.adress}<br><cdg:l18n
																		key="company.rating"/>: ${listWayPlace.rating}
																</div>
															</div>
														</div>
													</a>
												</c:forEach>
											</div>
										</div>
									</li>
								</ul>
							</c:forEach>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
	<jsp:include page="/views/elements/footer.jsp"/>
</body>
</html>