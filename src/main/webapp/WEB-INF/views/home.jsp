<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<html>
<head>
	<script src="${contextPath}/resources/img/jquery-3.6.0.min.js"></script>
	<style>
		a {
			text-decoration: none;
		}
        .map {
            position: absolute;
        }

        .hide {
            opacity: 0;
        }

        .hover_area {
            position: absolute;
		    padding: 70px 30px;
		    vertical-align: middle;
		    text-align: center;
		    z-index: 9999;
		    color: #222;
		    font-size: 1.5em;
		    font-weight: bolder;
		    text-shadow: 1px 1px 1px white;
        }

        .seoul {
            top: 80px;
            left: 61px;
        }

        .gwd {
            top: 55px;
            left: 222px;
        }

        .gwjn {
            top: 411px;
            left: 49px;
        }

        .bsgn {
            top: 400px;
            left: 218px;
        }

        .dj {
            top: 230px;
            left: 61px;
        }

        .dggb {
            top: 264px;
            left: 249px;
        }

        .jj {
            top: 633px;
            left: 33px;
        }
    </style>
	<script>
        $(function () {
            $('.seoul').hover(
                function () { $('.h_sw').removeClass('hide'); },
                function () { $('.h_sw').addClass('hide'); }
            )
            $('.jj').hover(
                function () { $('.h_jj').removeClass('hide'); },
                function () { $('.h_jj').addClass('hide'); }
            )
            $('.dggb').hover(
                function () { $('.h_dggb').removeClass('hide'); },
                function () { $('.h_dggb').addClass('hide'); }
            )
            $('.gwjn').hover(
                function () { $('.h_gwjn').removeClass('hide'); },
                function () { $('.h_gwjn').addClass('hide'); }
            )
            $('.dj').hover(
                function () { $('.h_dj').removeClass('hide'); },
                function () { $('.h_dj').addClass('hide'); }
            )
            $('.bsgn').hover(
                function () { $('.h_bsgn').removeClass('hide'); },
                function () { $('.h_bsgn').addClass('hide'); }
            )
            $('.gwd').hover(
                function () { $('.h_gwd').removeClass('hide'); },
                function () { $('.h_gwd').addClass('hide'); }
            )
 

        });
        
        function fn_remove_article(url, location) {
            let newForm = document.createElement("form");
            newForm.setAttribute("method","get");
            newForm.setAttribute("action",url);
            let articleNoInput = document.createElement("input");
            articleNoInput.setAttribute("type","hidden");
            articleNoInput.setAttribute("name","location");
            articleNoInput.setAttribute("value",location);
            newForm.appendChild(articleNoInput);
            document.body.appendChild(newForm);
            newForm.submit();
        }
    </script>
	<title>Home</title>
</head>
<body>

		<div>
	        <img class="map bg" src="${contextPath}/resources/img/korea_backless.png" alt="">
	        <img class="map hide h_sw" src="${contextPath}/resources/img/seoul.png" alt="">
	        <img class="map hide h_gwd" src="${contextPath}/resources/img/gwd.png" alt="">
	        <img class="map hide h_gwjn" src="${contextPath}/resources/img/gwjn.png" alt="">
	        <img class="map hide h_bsgn" src="${contextPath}/resources/img/bsgn.png" alt="">
	        <img class="map hide h_dj" src="${contextPath}/resources/img/dj.png" alt="">
	        <img class="map hide h_dggb" src="${contextPath}/resources/img/dggb.png" alt="">
	        <img class="map hide h_jj" src="${contextPath}/resources/img/jj.png" alt="">
	    </div>
	    <a href="${contextPath}/nalsee?location=서울" class="hover_area seoul">서울/경기</a>
	    <a href="${contextPath}/nalsee?location=강원도"  class="hover_area gwd">강원도</a>
	    <a href="${contextPath}/nalsee?location=광주"  class="hover_area gwjn">광주/전라</a>
	    <a href="${contextPath}/nalsee?location=부산"  class="hover_area bsgn">부산/경남</a>
	    <a href="${contextPath}/nalsee?location=대전"  class="hover_area dj">대전/충청</a>
	    <a href="${contextPath}/nalsee?location=대구"  class="hover_area dggb">대구/경북</a>
	    <a href="${contextPath}/nalsee?location=제주"  class="hover_area jj">제주</a>
	</body>
</html>