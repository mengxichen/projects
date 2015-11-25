

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact</title>
<style>
body {

    background-color;
    background-repeat: no-repeat;
    background-position: left top;
    margin-top: 20px;
    margin-left: 20px;
    margin-right: 20px;
}
p { 
    word-spacing: 50px;
}
body {background-color:lightblue}
</style>
</head>
<a href="Homepage.jsp"><font color="grey" face="Comic sans MS"
	size="3"> HOME </font></a>
<body>
<h1>CONTACT US</h1>
<p>BELLEVUE</p>
<pre>
City Center Bellevue, 
500 118th Ave NE, 
Bellevue, WA 98009
</pre>
<br>
<p>SEATTLE</p>
<pre>
319 Southlake Ave North, 
Seattle, WA 98109
</pre>
<br>
<p>KIRKLAND</p>
<pre>
121 Lake Street South, 
Kirkland, WA 98033
</pre>

<head> 
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <script src="http://maps.google.com/maps/api/js?sensor=false" 
          type="text/javascript"></script>
</head> 
<body>
  <div id="map" style="width: 500px; height: 400px;"></div>

  <script type="text/javascript">
    var locations = [
      ['Bellevue', 47.618390, -122.183265, 3],
      ['Seattle', 47.621692,-122.338794, 2],
      ['Kirkland', 47.674588, -122.206064, 1],

    ];

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 10,
      center: new google.maps.LatLng(47.618390, -122.183265),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }
  </script>
</body>





</body>
</html>