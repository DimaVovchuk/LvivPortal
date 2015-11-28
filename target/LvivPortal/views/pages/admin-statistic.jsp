<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cdg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<jsp:include page="/views/elements/css.jsp"/>

<head>
	<title><cdg:l18n key="statistic.title"/></title>
	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/highcharts-3d.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>
	<style>
		#container {
			height: 450px;
			min-width: 310px;
			margin: 0 auto;
		}
	</style>

	<script>
		$(function () {
			$('#container').highcharts({
				chart: {
					type: 'column',
					margin: 75,
					options3d: {
						enabled: true,
						alpha: 10,
						beta: 15,
						depth: 70
					}
				},
				title: {
					text: '<cdg:l18n key="statistic.placestat"/>'
				},
				subtitle: {
					text: '<cdg:l18n key="statistic.topten"/>'
				},
				plotOptions: {
					column: {
						depth: 35
					}
				},
				xAxis: {
					categories: [
						<c:forEach var="topPlacesMap" items="${topPlacesMap}">
						"${topPlacesMap.key}",
						</c:forEach>
					]
				},
				series: [{
					name: '<cdg:l18n key="statistic.rating"/>',
					data: [
						<c:forEach var="topPlacesMap" items="${topPlacesMap}">
						${topPlacesMap.value},
						</c:forEach>
					]
				}],
				yAxis: {
					title: {
						text: '<cdg:l18n key="statistic.rating"/>'
					}
				}
			});
		});
	</script>

	<script>
		$(function () {
			$('#circle-quantity-container').highcharts({
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45,
						beta: 0
					}
				},
				title: {
					text: '<cdg:l18n key="statistic.userstat"/>'
				},
				subtitle: {
					text: '<cdg:l18n key="statistic.quantityofusers"/>'
				},
				tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						depth: 35,
						dataLabels: {
							enabled: true,
							format: '{point.name}'
						}
					}
				},

				series: [{
					type: 'pie',
					name: '<cdg:l18n key="statistic.percentage"/>',
					data: [
						['<cdg:l18n key="role.admin"/>', ${usersMap[0]}],
						['<cdg:l18n key="role.user"/>', ${usersMap[1]}],
						{
							name: '<cdg:l18n key="role.guide"/>',
							y: ${usersMap[2]},
							sliced: true,
							selected: true
						},
						['<cdg:l18n key="role.company"/>', ${usersMap[3]}]
					]
				}]
			});
		});
	</script>

	<script>
		$(function () {
			$('#circle-status-container').highcharts({
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45,
						beta: 0
					}
				},
				title: {
					text: '<cdg:l18n key="statistic.userstat"/>'
				},
				subtitle: {
					text: '<cdg:l18n key="statistic.usersstatus"/>'
				},
				tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						depth: 35,
						dataLabels: {
							enabled: true,
							format: '{point.name}'
						}
					}
				},

				series: [{
					type: 'pie',
					name: '<cdg:l18n key="statistic.percentage"/>',
					data: [
						['<cdg:l18n key="admin.active"/>', ${statusList[0]}],
						{
							name: '<cdg:l18n key="admin.notactive"/>',
							y: ${statusList[1]},
							sliced: true,
							selected: true
						},
						['<cdg:l18n key="admin.baned"/>', ${statusList[2]}],
					]
				}]
			});
		});
	</script>
</head>

<body>

<jsp:include page="/views/elements/header.jsp"/>

<div class="full-height">
	<div id="edit-place">
		<div class="row">
			<div class="col l8 offset-l2 m12 s12 z-depth-2" style="padding: 20px">
				<h4 class="center-align"><cdg:l18n key="statistic.title"/></h4>
				<div class="divider"></div>
				<div id="container" style="height: 450px"></div>

				<br>
				<div class="divider"></div>
				<div id="circle-quantity-container" style="height: 400px"></div>

				<br>
				<div class="divider"></div>
				<div id="circle-status-container" style="height: 400px"></div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/views/elements/footer.jsp"/>

</body>
</html>
