<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="sensor" value="${map.map}" />
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <script src="https://cesium.com/downloads/cesiumjs/releases/1.79.1/Build/Cesium/Cesium.js"></script>
  <link href="https://cesium.com/downloads/cesiumjs/releases/1.79.1/Build/Cesium/Widgets/widgets.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  <link rel="stylesheet" href="/css/cesium.css">
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <script>
    let eventCount=0;
    let testData;
    let alarmData = "${sensor[0].alarm}";
    let sensorData = "${sensor}";

  </script>
  </head>
<body>
        <!-- 네비게이션 바  -->
        <div>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
              <a class="navbar-brand" href="#"><img src="/image/에셈블 로고.png" width="180px"></a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-1 my-lg-3 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                  <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">디지털 트윈</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">실시간 모니터링</a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      에너지 분석
                    </a>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="#">Action</a></li>
                      <li><a class="dropdown-item" href="#">Another action</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">탄소공간지도</a>
                  </li>
                </ul>
                <form class="d-flex" role="search">
                  <input class="form-control me-3" type="search" placeholder="Search" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
              </div>
            </div>
          </nav>
        </div>
  <div  class="allDiv">

        <!-- 실시간 탐지 구역 -->
        <div class="realTimeFind">
          <form name="Search" action="">
          <div class="search_group">
            <input type="date"><span class="colorWhite"> ~ </span><input type="date">
            <input type="text" class="input_realTime" >
            <button type="button" class="btn btn-outline-dark">검색</button>
          </div>
         </form>
         <div class="realTimeList">
          <ol class="list-group list-group-numbered">
            <script>
                  setInterval(reload, 0, alarmData);
                    function reload(alarmData){
                    const date = new Date()
                        const hours = date.getHours();
                        const times = date.getTime();
                        const minutes = date.getMinutes();
                        const seconds = date.getSeconds();
                        const milliseconds= date.getMilliseconds();

                            console.log("서버요청 시간: " + hours + "시 " + minutes + "분 " + seconds + "초" + milliseconds + "ms");
                             $.ajax({
                                    type: "GET",
                                    async: false,
                                    url: "${contextPath}/cesium/getAnomaly",
                                    data :  {alarm : alarmData},
                                    dataType: "json",
                                    success: function (result) {
                                    if(result!=null){
                                   // console.log("측정시간 :: " + result.map[0].update_time);
                                    alarmData = result.map[0].alarm;
                                    const randomNumber = Math.floor(Math.random() * 800);
                                    // $("#realList").load(window.location.href + "#realList");.
                                    //console.log("서버응답 시간: " + hours + "시 " + minutes + "분 " + seconds + "초" + (milliseconds+randomNumber) + "ms");
                                    const date1 = new Date();
                                    swal({
                                      title: "화재 이상치 탐지",
                                      text: "위 주소에서 화재 이상치가 탐지되었습니다\n 발생 시간 : " + date1 ,
                                      icon: "warning",
                                      buttons: true,
                                      dangerMode: true,
                                    });
                                    }
                                    },
                                    error: function( error){
                                    }
                                });
                           }

                   window.onload=function(){
                  for (var i = 0; i <= eventCount; i++) {
                    $(".list-group-numbered").append("<li class='list-group-item d-flex justify-content-between align-items-start w100'>"
                      + "<div class='ms-2 me-auto'> <div class='fw-bold'>"
                      +  "기기 번호 : ${sensor[0].device_number}"
                      + " </div> <a href='javascript:findPlace("
                      + testData[i].latitude
                      + ","
                      + testData[i].longitude
                      +")'>"
                      +  "${sensor[0].alarm} <br>"
                      + " 이상치 발생 </a></div>"
                      + "<span class='badge bg-primary rounded-pill'>1</span> </li>");
                        }


                function findPlace(lati, longi){
                  viewer.camera.flyTo({
                        //위도, 경도, 높이, 방향 설정하기, 사용자에게 위치 (위도, 경도 값 받아올 수 있도록 할 수 있음)
                          destination: Cesium.Cartesian3.fromDegrees(longi, lati, 2000),
                          orientation: {
                            heading: Cesium.Math.toRadians(0.0),
                            pitch: Cesium.Math.toRadians(-15.0),
                          }
                    });
                }
                }
          </script>
          </ol>
         </div>
        </div>
   <div class="middleDiv">
        <!-- 세슘 시작 구간 -->
      <img src="/image/세슘틀.png" class="mapLine">
  <div id="cesiumContainer">
  <script>
    // 세슘 토큰 아이디 입력
    Cesium.Ion.defaultAccessToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJhOTljMjZiNS1jNDZhLTRlNjAtODY1Ni02NGU4NDI0MDdhNDciLCJpZCI6MTc0MDI2LCJpYXQiOjE2OTgzMDQzODh9.LaGjgFhLrmhIvs2x40b6OY7V8WlqiVmAlKwvqCcM3bA';

    const viewer = new Cesium.Viewer('cesiumContainer', {
      terrainProvider: Cesium.createWorldTerrain()
    });

    // 기본 건축물 추가
    const buildingsTileset = viewer.scene.primitives.add(Cesium.createOsmBuildings());


  //값 상태에 따라 건물 색깔 변경하기
  if(true){
    //alert("디바이스 번호 : ${sensor} 측정시간 : 현재시간 : ");
    let errString = [];
    testData = [
                      {latitude : 36.37528, longitude : 127.3894, condition :"bad", where : "전기과부하" },
                      {latitude : 36.38473, longitude : 127.39887,  condition :"good",where : "습도 이상"},
                      {latitude : 36.37618, longitude : 127.39736,  condition : "middle", where : "전기과부하"},
                      {latitude : 36.37618, longitude : 127.39736, condition : "bad", where : "전기과부하"},
                      {latitude : 36.37618, longitude : 127.39736, condition : "middle", where : "전기과부하"}
                    ];

    testData.forEach(function(element, index){
                    if(element.condition=="bad"){
                      let arrString = [" " ,"color('red')"];
                      errString.push(arrString);
                      eventCount++;
                      }
                    // }else if(element.condition=="middle"){
                    //   let arrString =["${Latitude}===" + 127.39736 +" && ${Longitude}===" + 36.37618 ,"color('#FF7F50')"];;
                    //   errString.push(arrString);
                    //    eventCount++;
                    // }else{
                    //   testData.splice(index,1)
                    // }

                    return errString
                  })
                  errString.push([true, "color('#7CFC00')"]);

     buildingsTileset.style = new Cesium.Cesium3DTileStyle(
      {
     color: {
     conditions: [
     ["${elementId}=== 469034113" ,"color('red')"],[true, "color('#7CFC00')"]
      ]
    },
  });

}

    viewer.camera.flyTo({
    //위도, 경도, 높이, 방향 설정하기, 사용자에게 위치 (위도, 경도 값 받아올 수 있도록 할 수 있음)
      destination: Cesium.Cartesian3.fromDegrees(127.38724, 36.34845, 2000),
      orientation: {
        heading: Cesium.Math.toRadians(0.0),
        pitch: Cesium.Math.toRadians(-15.0),
      }
    });

    //cesium ion을 사용, 진천군 데이터 불러오기
    const newBuildingTileset = viewer.scene.primitives.add(
      new Cesium.Cesium3DTileset({
        url: Cesium.IonResource.fromAssetId(2351494)
      })
    );


   // 지도에 도형 추가하기sssss
      // var box = viewer.entities.add({
      //   name:'Box',
      //   position : Cesium.Cartesian3.fromDegrees(127.33522174533093, 36.32988468774212, 200),
      //   box:{
      //     //생성할 모형 치수 설정
      //     dimensions : new Cesium.Cartesian3(500.0,500.0,500.0),
      //     material : Cesium.Color.WHITE,
      //     outline : true,
      //     outlinecolor : Cesium.Color.BLACK
      //   }
      // });
      //  viewer.zoomTo(viewer.entities);
      // newBuildingTileset.show;


const nameOverlay = document.createElement("div");
viewer.container.appendChild(nameOverlay);
nameOverlay.className = "backdrop";
nameOverlay.style.display = "none";
nameOverlay.style.position = "absolute";
nameOverlay.style.bottom = "0";
nameOverlay.style.left = "0";
nameOverlay.style["pointer-events"] = "none";
nameOverlay.style.padding = "4px";
nameOverlay.style.backgroundColor = "pink";

// Information about the currently selected feature
const selected = {
  feature: undefined,
  originalColor: new Cesium.Color(),
};

// An entity object which will hold info about the currently selected feature for infobox display
const selectedEntity = new Cesium.Entity();

// Get default left click handler for when a feature is not picked on left click
const clickHandler = viewer.screenSpaceEventHandler.getInputAction(
  Cesium.ScreenSpaceEventType.LEFT_CLICK
);

// Update the 'nameOverlay' for the given picked feature,
// at the given (screen) position.
function updateNameOverlay(pickedFeature, position) {

  if (!Cesium.defined(pickedFeature)) {
    nameOverlay.style.display = "none";
    return;
  }
  // A feature was picked, so show its overlay content
  nameOverlay.style.display = "block";
  nameOverlay.style.bottom = viewer.canvas.clientHeight  - position.y+'px';
  nameOverlay.style.left = position.x + 'px';

  const name = pickedFeature.getProperty("name");
  nameOverlay.textContent = name;
}

// Create the HTML that will be put into the info box that shows
// information about the currently selected feature
function createPickedFeatureDescription(pickedFeature) {
  const description =
   '<table class="cesium-infoBox-defaultTable"><tbody>' +
         "<tr><th>아이디</th><td>" +
         pickedFeature.getProperty("elementId") +
         "</td></tr>" +
         "<tr><th>위도</th><td>" +
         pickedFeature.getProperty("cesium#latitude") +
         "</td></tr>" +
         "<tr><th>경도</th><td>" +
         pickedFeature.getProperty("cesium#longitude") +
         "</td></tr>" +
         "</tbody></table>";
  return description;
}

// If silhouettes are supported, silhouette features in blue on mouse over and silhouette green on mouse click.
if (
  Cesium.PostProcessStageLibrary.isSilhouetteSupported(viewer.scene)
) {
  // Silhouettes are supported
  const silhouetteBlue = Cesium.PostProcessStageLibrary.createEdgeDetectionStage();
  silhouetteBlue.uniforms.color = Cesium.Color.BLUE;
  silhouetteBlue.uniforms.length = 0.01;
  silhouetteBlue.selected = [];

  const silhouetteGreen = Cesium.PostProcessStageLibrary.createEdgeDetectionStage();
  silhouetteGreen.uniforms.color = Cesium.Color.LIME;
  silhouetteGreen.uniforms.length = 0.01;
  silhouetteGreen.selected = [];
  viewer.scene.postProcessStages.add(
    Cesium.PostProcessStageLibrary.createSilhouetteStage([
      silhouetteBlue,
      silhouetteGreen,
    ])
  );

  // 마우스 호버 시 블루 컬러 출력
  viewer.screenSpaceEventHandler.setInputAction(function onMouseMove(
    movement
  ) {
    // If a feature was previously highlighted, undo the highlight
    silhouetteBlue.selected = [];

    // Pick a new feature
    const pickedFeature = viewer.scene.pick(movement.endPosition);
    updateNameOverlay(pickedFeature, movement.endPosition);
    if (!Cesium.defined(pickedFeature)) {
      return;
    }

    // Highlight the feature if it's not already selected.
    if (pickedFeature !== selected.feature) {
      silhouetteBlue.selected = [pickedFeature];
    }
  },
  Cesium.ScreenSpaceEventType.MOUSE_MOVE);

  // Silhouette a feature on selection and show metadata in the InfoBox.
  viewer.screenSpaceEventHandler.setInputAction(function onLeftClick(
    movement
  ) {
    // If a feature was previously selected, undo the highlight
    silhouetteGreen.selected = [];

    // Pick a new feature
    const pickedFeature = viewer.scene.pick(movement.position);
    const properName = pickedFeature.getPropertyNames()
    for(var i =0; i<properName.length; i++){
      console.log(properName + ":" + pickedFeature.getProperty(properName))
    }
    if (!Cesium.defined(pickedFeature)) {
      clickHandler(movement);
      return;
    }

    // Select the feature if it's not already selected
    if (silhouetteGreen.selected[0] === pickedFeature) {
      return;
    }

    // Save the selected feature's original color
    const highlightedFeature = silhouetteBlue.selected[0];
    if (pickedFeature === highlightedFeature) {
      silhouetteBlue.selected = [];
    }

    // Highlight newly selected feature
    silhouetteGreen.selected = [pickedFeature];

    // Set feature infobox description
    viewer.selectedEntity = selectedEntity;
    selectedEntity.description = createPickedFeatureDescription(
      pickedFeature
    );
  },
  Cesium.ScreenSpaceEventType.LEFT_CLICK);
  } else {
  // Silhouettes are not supported. Instead, change the feature color.
  // Information about the currently highlighted feature
  const highlighted = {
    feature: undefined,
    originalColor: new Cesium.Color(),
  };

  // Color a feature yellow on hover.
  viewer.screenSpaceEventHandler.setInputAction(function onMouseMove(
    movement
  ) {
    // If a feature was previously highlighted, undo the highlight
    if (Cesium.defined(highlighted.feature)) {
      highlighted.feature.color = highlighted.originalColor;
      highlighted.feature = undefined;
    }
    // Pick a new feature
    const pickedFeature = viewer.scene.pick(movement.endPosition);
    updateNameOverlay(pickedFeature, movement.endPosition);

    if (!Cesium.defined(pickedFeature)) {
      return;
    }

    // Highlight the feature if it's not already selected.
    if (pickedFeature !== selected.feature) {
      highlighted.feature = pickedFeature;
      Cesium.Color.clone(
        pickedFeature.color,
        highlighted.originalColor
      );
      pickedFeature.color = Cesium.Color.YELLOW;
    }
  },
  Cesium.ScreenSpaceEventType.MOUSE_MOVE);
  }
    </script>
    </div>

      <div class="selectMap">
        <img src="/image/map_image.png" width="400px">
        <div class="mapSelect search_group">
          <select class="form-select" aria-label="Default select example">
            <option selected>진천군</option>
            <option value="1">음성군</option>
            <option value="2">증평군</option>
            <option value="3">괴산군</option>
          </select>
          <select class="form-select" aria-label="Default select example">
            <option selected>OO읍</option>
            <option value="1">OO읍</option>
            <option value="2">OO읍</option>
            <option value="3">OO읍</option>
          </select>
          <button type="button" class="btn btn-outline-dark">검색</button>
        </div>
        <div class="alertGroup">
          <div class="buttonInline">
           <button type="button" class="btn btn-primary position-relative blue">
           108
          </button>
          <p style="color: white;">설치현황</p>
          </div>
            <div class="buttonInline">
            <button type="button" class="btn btn-primary position-relative red">
               <span id="eventAlert"> 6 </span>
              <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                4
                <span class="visually-hidden">이벤트 탐지 현황</span>
              </span>
            </button>
            <p style="color: white;">이벤트<br>탐지현황</p>
          </div>
        </div>
      </div>
      <script>
        //bad 이벤트 탐지 현황 출력
          const eventAlert = document.getElementById("eventAlert");
         // eventAlert.innerHTML=eventCount;
      </script>
  </div>
</div>
<div id="chartHolder" style="width:800px; height:450px;"><div>
</body>
</html>