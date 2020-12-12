<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Google Map</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.js"></script>
  <script>
  var map;
  var marker;
  var x = 36.625586;
  var y = 127.454361;

  var count = 3;

  // 아직 앱과 db가 연동 완료되지 못해서 좌표 데이터가 db에 있다는 가정하에 데이터를 받아서 띄워주는 기능 구현 
  function changeCenter(){
	  	if(count == 3){
			x = 36.625586;
			y = 127.454361;
		}
		else if(count == 2){
			x = 36.627545;
			y = 127.458814;
		}
		else{
			x = 36.628443;
			y = 127.457400;
		}
	  
	    var newPlace = { lat: x, lng: y };
	    map.panTo(newPlace);
	    map.setZoom(17);

	    marker.setPosition(newPlace);
	    
	    if(count == 1) alert("마지막 기록입니다.");
	  }
  // var button = document.getElementById('btn111');
  // 이전 로그 값으로 돌아가기 위한버튼 이벤트
  $(function(){
		$(".btn111").click(function(){
			changeCenter();
			count = count-1;
		});
  });
  
  function initMap() {
    var original = { lat: 36.628872 ,lng: 127.456040 };
    map = new google.maps.Map( document.getElementById('map'), {
        zoom: 17,
        center: original
      });

    marker = new google.maps.Marker({
      position: original,
      map: map,
    });
  }

  
  </script>
</head>

<body>
  <button class="btn111">이전 위치</button>
  <div id="map" style="width:500px;height:380px;"></div>
  <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMl8-apgSpVgF1ZfhGBkc9HxALytmSrho&callback=initMap&region=kr"></script>
</body>
</html>