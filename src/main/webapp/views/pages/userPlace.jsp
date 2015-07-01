<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<title><cdg:l18n key="places.title"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content=""/>
</head>

<jsp:include page="/views/elements/css.jsp"/>
<jsp:include page="/views/elements/script.jsp"/>

<body>
<div class="full-height">
	<jsp:include page="/views/elements/header.jsp"/>
	<div clacc="user-places" id="user-places">
		<div class="places">
			<div class="section">
				<h3 class="center-align"><cdg:l18n key="places.head"/></h3>

				<div class="row">
					<div class="col l9 m8 s7">
						<div class="row z-depth-2">
							<%--<c:forEach var="place" items="${requestScope.places}">--%>
							<div id="userPlace-info-collection" class="place-page-content"></div>
							<%--</c:forEach>--%>
						</div>
					</div>
					<div class="col l3 m4 s5">
						<div class="collection with-header z-depth-2">
							<div class="collection-header"><h4>Categories</h4></div>
							<div id="userPlacecategory-place">
								<a href="portal?command=userPlaceJSONCommand&userPlaceCategory=favoritePlaces"
								   data-category="favoritePlaces"
								   class="collection-item black-text ${requestScope.active_hotels}">Favorite Place</a>
								<a href="portal?command=userPlaceJSONCommand&userPlaceCategory=customPlaces"
								   data-category="customPlaces"
								   class="collection-item black-text ${requestScope.active_restaurants}">Custom
									Place</a>
								<a href="portal?command=userPlaceJSONCommand"
								   class="collection-item black-text ${requestScope.active_allplaces}">All Place</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="/views/elements/footer.jsp"/>
			<jsp:include page="/views/modals/add-place-to-route-recomended.jsp"/>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/pages/userFavorPlacePage.js"></script>
	<script id="userPlace-info-template" type="text/x-handlebars-template">
		{{#each this}}
		<div class="match-col col l4 m6 s12">
			<div class="card z-depth-2" style="padding:10px; height:95%">
				<form name="button"
				      method="post" style="position:absolute;padding:5px">
					<button class="btn modal-trigger btn-floating btn-large waves-effect waves-light red darken-2"
					        type="submit" data-target="delete-place"
					        id="btn" onclick="$('#place_id').val('{{id}}')">
						<i class="material-icons">delete</i>
					</button>
					<c:if test="${userDataTrip!=null}">
						<button class="btn modal-trigger btn-floating btn-large waves-effect waves-light cyan darken-2"
						        type="submit" data-target="chooseDayRecomended"
						        id="btn1" onclick="$('#place_id_add').val('{{id}}')">
							<i class="mdi-content-add"></i>
						</button>
					</c:if>
				</form>
				<a href="portal?command=placeInformation&place_id={{id}}"><img
						class="responsive-img place-img"
						src="${pageContext.request.contextPath}/upload/photo/{{imageReference}}"></a>
				<a href="portal?command=placeInformation&place_id={{id}}">
					<h5><c:out value="{{name}}"/></h5></a>
						<c:out value="{{adress}}"/>
				<div class="right-align"></div>
			</div>
		</div>
		{{/each}}
	</script>

	<script>
		$(".match-col").matchHeight({
			property: 'height'
		});
	</script>

	<div id="delete-place" class="modal">
		<div class="modal-content">
			<p><cdg:l18n key="sure.delete"/></p>

			<form id="sure" action="/portal/deletePlace" method="post">
				<input type="hidden" name="command" value="deletePlace">

				<div class="input-field col s6">
					<input placeholder="Placeholder" id="place_id" name="place_id" type="hidden">
				</div>

				<div class="ok-footer">
					<div class="modal-footer">
						<button class="btn waves-effect waves-light cyan darken-2" type="submit" value="true"
						        name="yes">Ok
						</button>
						<button class="btn waves-effect waves-light cyan darken-2" type="submit" value="false" name="no"
								>Cancel
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>
<script>
	$('.datepicker').pickadate({
		selectMonths: true, // Creates a dropdown to control month
		selectYears: 15 // Creates a dropdown of 15 years to control year
	});
</script>

<script>
	function myFunction() {
		var x = document.getElementById("ID").value;
		document.getElementById("demo").innerHTML = x;
	}
</script>
</body>
</html>
