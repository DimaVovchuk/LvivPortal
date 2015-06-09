<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08.06.2015
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta charset="utf-8">
  <title>Travel modes in directions</title>
  <style>
    html, body, #map-canvas {
      height: 100%;
      margin: 0px;
      padding: 0px
    }

    #panel {
      position: relative;
      top: 5px;
      left: 50%;
      margin-left: -400px;
      z-index: 5;
      background-color: #fff;
      padding: 5px;
      border: 1px solid #999;
    }

    #directions-panel {
      height: 100%;
      float: right;
      width: 390px;
      overflow: auto;
    }

    #map-canvas {
      margin-right: 400px;
    }

    #control {
      background: #fff;
      padding: 5px;
      font-size: 14px;
      font-family: Arial;
      border: 1px solid #ccc;
      box-shadow: 0 2px 2px rgba(33, 33, 33, 0.4);
      display: none;
    }

    @media print {
      #map-canvas {
        height: 500px;
        margin: 0;
      }

      #directions-panel {
        float: none;
        width: auto;
      }
    }
  </style>
  <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
  <script>
    var stepDisplay;
    var markerArray = [];
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    var map;
    var time = 0;
    var timeArray = [];
    var haight = new google.maps.LatLng(${from_x}, ${from_y});
    function initialize() {

      directionsDisplay = new google.maps.DirectionsRenderer();
      var chicago = new google.maps.LatLng(41.850033, -87.6500523);
      var mapOptions = {
        zoom: 7,
        center: haight
      };
      var map = new google.maps.Map(document.getElementById('map-canvas'),
              mapOptions);
      var rendererOptions = {
        map: map
      }
      directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
      directionsDisplay.setMap(map);
      stepDisplay = new google.maps.InfoWindow();
    }

    function createResponse() {
      var start = "Lviv Topolna";
      var end = "Lviv Dragana";
    //  var selectedMode = document.getElementById('mode').value;
      var request = {
        origin: start,
        destination: end,
        waypoints: [
          {
            location:"Lviv Svobody",
            stopover:true
          },{
            location:"Lviv Saharova 23",
            stopover:true
          }],
        optimizeWaypoints: true,
        travelMode: google.maps.TravelMode.DRIVING
      };
      directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
          directionsDisplay.setDirections(response);
        }
      });
      directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
          var route = response.routes[0];
          var time = 0;
          for (var i = 0; i < route.legs.length; i++) {

            time += route.legs[i].duration.value;
              timeArray[i] = route.legs[i].duration.value;
            //  x[i] = route.legs[i].duration.value;
          }
          alert("Time is - " + parseInt(time/60) + " minuts");

            document.getElementById('form').innerHTML = '<input type="hidden" name="time" value="'+timeArray+'">';

            doGet = function(s) {
                alert(s + '   - эта строка будет отправлена на сервер');
                $.get('http://localhost:8080/timeapi', { time: time });
            }
        }
      });
      showSteps(response);
        getMyData();
    }
    function showSteps(directionResult) {
      // For each step, place a marker, and add the text to the marker's
      // info window. Also attach the marker to an array so we
      // can keep track of it and remove it when calculating new
      // routes.
      var myRoute = directionResult.routes[0];
      geocoder.geocode({'address': route.legs[i].address}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
          map.setCenter(results[0].geometry.location);
          var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
          });
        } else {
          alert('Geocode was not successful for the following reason: ' + status);
        }
      });

    }

    function getMyData(){
        var myData = new Request.JSON({
            'url':'http://localhost:8080/timeapi',
            'method':'get',
            data:{
                'time': JSON.encode(timeArray)
            },
            onSuccsess:function(jsn,text){
                console.log(jsn);// Выводим в консоль полученный JSON.

            }
        }).send()//Отправляем
    }


    google.maps.event.addDomListener(window, 'load', initialize);


  </script>

    <title>Time betwen roads</title>
</head>
<body>

<div id="panel">
  <b>Mode of Travel: </b>

  <input type="button" value="Time" onclick="createResponse();"><br>
    <form action="http://localhost:8080/timeapi" method="get">
        <div id="form"></div>
        <input type="submit" value="Submit">
    </form>

</div>
<div id="directions-panel"></div>
<div id="map-canvas"></div>

</body>
</html>
