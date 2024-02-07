<%@page import="com.test.WeatherDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.WeatherDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<%
	// 이전 페이지(WeatherInfo.jsp)로부터 넘어온 데이터 수신 stnId
	String stnId = request.getParameter("stnId");
	
	if(stnId == null)	// 최초 요청 시 디폴트 값
		stnId = "108";	// 전국
		
	StringBuffer sb = new StringBuffer();
	WeatherDAO dao = new WeatherDAO(stnId);
	 
	// 타이틀
  	String title = dao.weatherTitle();
	
	// 육상 중기 예보
	String weatherInfo = dao.weatherInfo();
	
	// 도시 리스트
	ArrayList<String> weatherCityList = dao.weatherCityList();
	
	// 날씨 리스트
	//ArrayList<WeatherDTO> weatherList = dao.weatherList("1");
	
	for(int i = 0; i < weatherCityList.size(); i++)
	{
		sb.append("<h3>" + weatherCityList.get(i) + "</h3>");
		sb.append("<table class='table'>");
		sb.append("				<thead>");
		sb.append("					<tr>");
		sb.append("						<th>날짜</th>");
		sb.append("						<th>날씨</th>");
		sb.append("						<th>최저/최고 기온</th>");
		sb.append("						<th>강수확률</th>");
		sb.append("					</tr>");
		sb.append("				</thead>");
		sb.append("				<tbody>");
		
		ArrayList<WeatherDTO> weatherList = dao.weatherList(String.valueOf(i+1));
		
		for(int j = 0; j < weatherList.size(); j++)
		{
			sb.append("<tr>");
			
				sb.append("		<td>" + weatherList.get(j).getTmEf() + "</td>");
				sb.append(String.format("<td><img src='images/%s'> %s</td>", weatherList.get(j).getImg(), weatherList.get(j).getWf()));
				sb.append("		<td>" + weatherList.get(j).getTmn() + "℃ ~ " + weatherList.get(j).getTmx() + "℃</td>");
				sb.append("		<td>" + weatherList.get(j).getRnSt() + "%</td>");
				
			sb.append("</tr>");	
		}
		

		sb.append("			   </tbody>");
		sb.append("</table>");	
	}
	
	 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기상철 육상 중기 예보(WeatherInfo.jsp)</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/main.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

	$(document).ready(function()
	{
		//alert("확인");	페이지 나올 때 확인
		
		// 페이지 최초 요청 이후
		// 선택한 라디오 버튼의 상태를 선택된 상태(checked)로 유지할 수 있도록 처리
		
		// 관찰
		
		// $(":radio:eq(3)").attr("checked", "checked");
		
		// $(":radio[value='109']").attr("checked", "checked");
		
		$(":radio[value='<%=stnId%>']").attr("checked", "checked");
		
		
	});

</script>

</head>
<body>
<!-- 
stnId = 108 전국
stnId = 109 서울 경기도
stnId = 105 강원도
stnId = 131 충북
stnId = 133 충남
stnId = 146 전북
stnId = 156 전남
stnId = 143 경북
stnId = 159 경남
stnId = 184 제주특별자치도
 -->
 
 <div class="container">
 
 	<h2>
 		기상 정보 <small>중기 예보</small>
 	</h2>
 	
 	<div class="panel-group" role="group">
 		
 		<div class="panel panel-default" role="group">
 		
 			<div class="panel-heading">지역 선택</div>
 			<div class="panel-body">
 			
 				<!-- action 속성 생략 -> 요청 및 수신처는 자기 자신 -->
 				<form method="get" role="form">
 					<input type="radio" name="stnId" value="108" checked="checked" /> 전국
 					<input type="radio" name="stnId" value="109" /> 서울 경기
 					<input type="radio" name="stnId" value="105" /> 강원
 					<input type="radio" name="stnId" value="131" /> 충북
 					<input type="radio" name="stnId" value="133" /> 충남
 					<input type="radio" name="stnId" value="146" /> 전북
 					<input type="radio" name="stnId" value="156" /> 전남
 					<input type="radio" name="stnId" value="143" /> 경북
 					<input type="radio" name="stnId" value="159" /> 경남
 					<input type="radio" name="stnId" value="184" /> 제주특별자치도
 					
 					<button class="btn btn-default">Submit</button>
 				</form>
 			</div>
 		
 		</div><!-- close div.panel.panel-default -->
 		<br>
 		
 		<div class="panel panel-default" role="group">
 			
 			<div class="panel-heading">기상 정보 출력</div>
 			<div class="panel-body">
 				<p>
 					<!-- <b>서울,경기도 육상중기예보 - 2024년 02월 05일 (월)요일 06:00 발표</b> -->
 					<b><%=title %></b>
 				</p>
 			    <p>
 					<!-- ○ (하늘상태) 8일(목)~10일(토)은 전국이 대체로 맑겠으나, 제주도는 구름많겠고, 11일(일)~15일(목)은 구름많거나 흐린 날이 많겠습니다.
 					○ (기온) 이번 예보기간 아침 기온은 -6~8도, 낮 기온은 3~14도로 평년(최저기온 -8~2도, 최고기온 3~11도)과 비슷하거나 조금 높겠습니다.
 					○ (주말전망) 10일(토)은 전국이 대체로 맑겠으나, 11일(일)은 전국이 구름많겠습니다. 아침 기온은 -5~2도, 낮 기온은 5~10도가 되겠습니다. ]]> -->
 					<%=weatherInfo %>
 				</p>
 				
 				
 				<%=sb.toString() %>
 				<!-- <h3>서울</h3>
 				<table class="table">
 					<thead>
 						<tr>
 							<th>날짜</th>
 							<th>날씨</th>
 							<th>최저/최고 기온</th>
 							<th>강수확률</th>
 						</tr>
 					</thead>
 					<tbody>
 						<tr>
 							<td>2024-02-08 00:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>30</td>
 						</tr>
 						<tr>
 							<td>2024-02-08 12:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>30</td>
 						</tr>
 						<tr>
 							<td>2024-02-09 00:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>10</td>
 						</tr>
 						<tr>
 							<td>2024-02-09 12:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>10</td>
 						</tr>
 					</tbody>
 				</table>
 				
 				<h3>인천</h3>
 				<table class="table">
 					<thead>
 						<tr>
 							<th>날짜</th>
 							<th>날씨</th>
 							<th>최저/최고 기온</th>
 							<th>강수확률</th>
 						</tr>
 					</thead>
 					<tbody>
 						<tr>
 							<td>2024-02-08 00:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>30</td>
 						</tr>
 						<tr>
 							<td>2024-02-08 12:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>30</td>
 						</tr>
 						<tr>
 							<td>2024-02-09 00:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>10</td>
 						</tr>
 						<tr>
 							<td>2024-02-09 12:00</td>
 							<td>구름많음</td>
 							<td>-3 ~ 5</td>
 							<td>10</td>
 						</tr>
 					</tbody>
 				</table> -->
 			</div>
 			
 		</div>
 		
 	</div><!-- close div.panel-group -->
 
 </div><!-- close div.container -->
 
</body>
</html>


























