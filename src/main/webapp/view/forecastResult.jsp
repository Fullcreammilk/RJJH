<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/2
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<%--<script type="text/javascript" src="/js/animataNumber/jquery.animateNumber.js"></script>--%>

<script type="text/javascript">
    function init() {
        initBar();
    }
    var forecast_stars = new Array();
    var forecast_creators = new Array();
    var forecast_genres = new Array();
    var stars_pic = new Array();
    var creas_pic = new Array();


    $(function () {

        //初始化已经选择好的预测方案
        if (sessionStorage.getItem("session_s") != null) {
            forecast_stars = sessionStorage.getItem("session_s").split(',');
            var temp_pic = new Array();
            temp_pic =  sessionStorage.getItem("s_p").split("/&/,");
            var i = 0;
            for (; i < forecast_stars.length; i++) {
                var name = forecast_stars[i];
                var pic = temp_pic[i].replace("/&/","");
                stars_pic.push(temp_pic[i]+'/&/');
                var label_var = document.createElement("label");
                label_var.innerHTML = name;
                label_var.id = name;
                label_var.name = pic;
                //设置提示框
                label_var.setAttribute("class", "tooltips");
                label_var.setAttribute("tooltip-position","bottom");
                label_var.setAttribute("tooltip-type","primary");

                var spanObj = document.createElement('span');
                //把图片塞入提示框
                var picObj = document.createElement('img');
                picObj.src = pic;
                picObj.style.width = "120px";
                spanObj.append(picObj);
                label_var.appendChild(spanObj);
                //设置删除
//                label_var.setAttribute("onclick", "deleteMakers(this)");
                document.getElementById('nav-stars').appendChild(label_var);
            }

        }
        if (sessionStorage.getItem("session_c") != null) {
            forecast_creators = sessionStorage.getItem("session_c").split(',');
            var temp_pic = new Array();
            temp_pic =  sessionStorage.getItem("c_p").split('/&/,');
            var i = 0;
            for (; i < forecast_creators.length; i++) {
                var name = forecast_creators[i];
                var pic = temp_pic[i].replace("/&/","");
                creas_pic.push(temp_pic[i]+'/&/');
                var label_var = document.createElement("label");
                label_var.innerHTML = name;
                label_var.id = name;
                label_var.name = pic;
                //设置提示框
                label_var.setAttribute("class", "tooltips");
                label_var.setAttribute("tooltip-position","bottom");
                label_var.setAttribute("tooltip-type","primary");

                var spanObj = document.createElement('span');
                //把图片塞入提示框
                var picObj = document.createElement('img');
                picObj.src = pic;
                picObj.style.width = "120px";
                spanObj.append(picObj);
                label_var.appendChild(spanObj);
                document.getElementById('nav-creators').appendChild(label_var);
            }
        }
        if (sessionStorage.getItem("session_g") != null) {
            forecast_genres = sessionStorage.getItem("session_g").split(',');
            var i = 0;
            for (; i < forecast_genres.length; i++) {
                var gen = forecast_genres[i];
                var label_var = document.createElement("label");
                label_var.innerHTML = gen;
                document.getElementById('nav-genres').appendChild(label_var);
            }
        }
    });
</script>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">
    <title>Forecast Result</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/forecastResult.css" media="screen" rel="stylesheet" type="text/css">
    <link href="/css/awTooltip/style.css" media="screen" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="/css/3dTurn/base.css"/>
    <link rel="stylesheet" href="/css/3dTurn/index-content.css"/>
    <script type="text/javascript" src="/js/3dTurn/global.js"></script>

</head>
<body onload="init()">
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>

<%--选择的明星栏--%>
<%--<div class="nav-forecast">--%>
    <%--<div class="forecast-content">--%>
    <%--Stars:--%>
    <%--<div id="nav-stars">--%>

    <%--</div>--%>
    <%--Creators:--%>
    <%--<div id="nav-creators">--%>
    <%--</div>--%>
    <%--Genres:--%>
    <%--<div id="nav-genres">--%>
    <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--预测结果分析框--%>
<div class="cont-box">
    <div class="regularly-box">
        <div class="regularly-head">
            <strong>Forecast Result :</strong><label id="result-num">0</label>
            <%--<p>Fun level <span id="target" style="font-size: 10px; color: red;">0 %</span>.</p>--%>
            <div class="forecast-content">
                Stars :
                <div id="nav-stars">

                </div>
                Creators :
                <div id="nav-creators">
                </div>
                Genres :
                <div id="nav-genres">
                </div>
            </div>
        </div>
        <div class="regularly-contBody">
            <ul class="regularly-item-list">
                <div style="margin: 0 auto;width: ${fn:length(extraEffect)*302}px;">
                    <c:forEach var="effect" items="${extraEffect}">
                        <li class="viewport-flip">
                            <div class="flip-box flip out">
                                <c:if test="${effect.title!='Worst Partner'}">
                                    <img src="/graphics/forecast/sun.png" style="  position: relative;left: 105px;top: 12px;">
                                </c:if>
                                <c:if test="${effect.title=='Worst Partner'}">
                                    <img src="/graphics/forecast/rain.png" style="position: relative;left: 105px;top: 12px;">
                                </c:if>
                                <label style="    text-align: center;
    background-color: #97a8b2;
    color: white;
    margin: 0 auto;
    top: 70px;
    position: relative;
    font-size: 20px;
    vertical-align: middle;">${effect.msg}</label>
                                <div class="flip-foot">${effect.title}</div>
                                    <%--</dl>--%>
                            </div>
                            <div class="flip-box flip">
                                <div class="item-title">${effect.title}</div>
                                    <%--如果数组长度为1--%>
                                <c:if test="${fn:length(effect.names)==1}">
                                    <div class="flip-item-pic"><img src="${effect.imgUrls[0]}"></div>
                                    <div class="flip-foot">${effect.names[0]}</div>
                                </c:if>
                                <c:if test="${fn:length(effect.names)==2}">
                                    <div class="flip-item-pic"><img src="${effect.imgUrls[0]}"><img
                                            src="${effect.imgUrls[1]}"></div>
                                    <div class="flip-foot">${effect.names[0]} & ${effect.names[1]}</div>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>
    </div>
</div>
<%--<script src="http://www.jq22.com/jquery/1.8.3/jquery.min.js"></script>--%>

<script src="/js/animataNumber/jquery.animateNumber.min.js"></script>
<script src="/js/animataNumber/jquery.color.min.js"></script>
<script>
    var result=${result};
    //小数位数
    var decimal_places = 1;
    var decimal_factor = decimal_places === 0 ? 1 : decimal_places * 10;
    $('#result-num').animateNumber(
        {
            number: result.toFixed(decimal_places)*10,

            color: 'rgb(251, 199, 46)',
//            'font-size': '30px',

            numberStep: function(now, tween) {
                var floored_number = Math.floor(now) / decimal_factor,
                    target = $(tween.elem);
                if (decimal_places > 0) {
                    floored_number = floored_number.toFixed(decimal_places);
                }

                target.text(floored_number );
            }
        },
        5000
    )
</script>

</body>
</html>
