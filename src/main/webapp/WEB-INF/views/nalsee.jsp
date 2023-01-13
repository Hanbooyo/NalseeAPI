<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<style>
	#weather {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}
</style>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>${location} 날씨 정보</h1>
	<div id="weather">
		<c:forEach var="vo" items="${VOS}">
			<table border="1">
				<thead>
					<tr>
						<th colspan="2">${vo.time}시</th>
					</tr>
					<tr>
						<th>항목명</th>
						<th>항목 값</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>온도</td>
						<td>${vo.TMP}°C</td>
					</tr>
					<tr>
						<td>강수확률</td>
						<td>${vo.POP}%</td>
					</tr>
					<tr>
						<td>1시간 강수량(단위mm)</td>
						<td>${vo.PCP}</td>
					</tr>
					<tr>
						<td>하늘상태</td>
						<td>${vo.SKY}</td>
					</tr>
					<tr>
						<td>풍속(동서성분)</td>
						<td>${vo.UUU}m/s</td>
					</tr>
					<tr>
						<td>풍속(남북성분)</td>
						<td>${vo.VVV}m/s</td>
					</tr>
					<tr>
						<td>풍향</td>
						<td>${vo.VEC}deg</td>
					</tr>
	
				</tbody>
			</table>
		</c:forEach>
	</div>
	<br><br>
	<a href="${contextPath}/">홈으로</a>
</body>
</html>