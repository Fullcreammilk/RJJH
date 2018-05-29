<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--s
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/11
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.bxslider.js"></script>

<script src="/js/jquery.js"></script>
<script>
    /**
     * jquery初始化方法
     */
    $(function () {
        if (${isInFavorite}) {
            $("#Fav").show();
            $("#notFav").hide();
        } else {
            $("#notFav").show();
            $("#Fav").hide();
        }
        $('.M-box').pagination({
            current:${nowPage},
            pageCount:${numOfPages},
            count: 2,
            jump: true,
            coping: true,
            homePage: 1,
            endPage:${numOfPages},
            prevContent: '<',
            nextContent: '>',
            jumpBtn: 'Jump',
            callback: function (api) {
                page = api.getCurrent();
                window.location = "/MRAS/movie/detail?movieName=" + movieNm + "&page=" + page + "#revs";

            }
        });
        $.ajax({
            url: '/MRAS/movie/recMovies',
            type: "get",
            dataType: "json",
            data: {movieName: "${movie.name}"},
            contentType: "application/json",
            traditional: false,
            success: function (data) {
                for (var j = 0; j < data.length; j++) {
                    var rec_href = document.createElement("a");
                    var rec_var = document.createElement("div");
                    var rec_pic = document.createElement("img");
                    var rec_name = document.createElement("a");

                    rec_pic.src = data[j].imgUrl;
                    rec_name.innerHTML = data[j].name;
                    rec_var.setAttribute("class", "la");
                    rec_var.appendChild(rec_pic);
                    rec_var.appendChild(rec_name);
                    rec_href.href = "/MRAS/movie/detail?movieName="+data[j].name;
                    rec_var.setAttribute("onclick","sendSameArray(["+data[j].sameArray+"])");
                    rec_href.appendChild((rec_var));
                    document.getElementById("rec_movie").appendChild(rec_href);

                }
//                $("#service_type").html("<option value='请选择'>请选择...</option> "+optionstring);
            },
        });
    });
    function sendSameArray(arr) {
        $.ajax({
            url: '/MRAS/movie/updateSameArray',
            type: "GET",
            dataType: "json",
            data: {sameArray: arr},
            cache: false,   //不用缓存
            async: false,
        })
    }
</script>
<script>

    function toPart1() {
        var p1 = document.getElementById("part1");
        p1.setAttribute("class", "active");
        var p2 = document.getElementById("part2");
        p2.setAttribute("class", "");
        $("#info").show();
        $("#chart").hide();
        $("#aside").show();
    }
    function toPart2() {
        var p1 = document.getElementById("part1");
        p1.setAttribute("class", "");
        var p2 = document.getElementById("part2");
        p2.setAttribute("class", "active");
        $("#info").hide();
        var p3 = document.getElementById("chart");
        p3.setAttribute("style", "visibility: visible;");
        $("#chart").show();
        $("#aside").hide();
    }
    //    <pre class="html" name="code"><script type="text/javascript">
    //兼容火狐、IE8
    //显示遮罩层
    function showMask() {
        $("#mask").css("height", $(document).height());
        $("#mask").css("width", $(document).width());
        $("#bigPoster").css("height", "1000px");
        $("#mask").show();
    }
    //隐藏遮罩层
    function hideMask() {
        $("#mask").hide();
    }
</script>
<script type="text/javascript">
    function initloading() {
//        $("#loading").fadeOut();
        initBar();

    }
    var movieNm = "${movie.name}";

</script>

<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/graphics/logo.png">
    <title>MovieInfo</title>
    <link rel="stylesheet" type="text/css" href="/css/movieDetail.css"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="/css/animsition.min.css"/>
    <%--<link rel="stylesheet" href="/css/common.css"/>--%>
    <link rel="stylesheet" href="/css/xgzb.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="/js/echarts.js"></script>
    <style type="text/css">
        .mask {
            position: absolute;
            top: 0px;
            background-color: rgba(0, 0, 0, 0.9);
            text-align: center;
            z-index: 1080;
            left: 0px;
            opacity: 1;
            -moz-opacity1: 1;
        }

        .bigPoster {
            height: 0;
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translateX(-50%) translateY(-50%);
            cursor: pointer;
        }

        .chart {
            visibility: hidden;
        }

    </style>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="http://www.jq22.com/jquery/jquery-ui-1.11.0.js"></script>
    <script src="/js/select-widget-min.js"></script>
    <script src="/js/animsition.min.js"></script>
    <%--<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>--%>


</head>
<body onload="initloading()">
<%--<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">--%>
    <%--<jsp:include page="/view/Loading.html"></jsp:include>--%>
<%--</div>--%>
<%--大图遮罩层--%>
<div id="mask" class="mask">
<c:if test="${bigImage!=null}">
    <img id="bigPoster" class="bigPoster" href="javascript:;" onclick="hideMask()" src="${bigImage}">
</c:if>
    </div>
<!-- 固定在屏幕顶端的导航栏
 ================================================== -->
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>
<div id="wrapper">
    <!--MovieContent-->
    <div id="content">
        <div id="dale_movie_subject_top_icon"></div>

        <h1 style="margin-bottom: 10px;">${movie.name}</h1>
        <img id="notFav" src="/graphics/movieInfo/fav.png" style="float: left;margin-top: 10px;cursor:pointer;"
             onclick="setFav()">
        <img id="Fav" src="/graphics/movieInfo/fav2.png" style="float: left;margin-top: 10px;cursor:pointer;"
             onclick="cancelFav()">
        <lable style="font-size: 16px;margin-left: 5px;line-height: 60px;font-weight: 600;">Favourite</lable>
        <a class="hiddenanchor" id="detailInfo"></a>
        <a class="hiddenanchor" id="statisticsChart"></a>
        <div class="grid-16-8 clearfix">
            <div class="article">
                <%--电影基本信息--%>
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="tabbable" id="tabs-517818">
                                <ul class="nav nav-tabs" style="width:810px;">
                                    <li id="part1" class="active">
                                        <a href="#toinfo" data-toggle="tab" onclick="toPart1()">Info</a>
                                    </li>
                                    <li id="part2">
                                        <a href="#tochart" data-toggle="tab" onclick="toPart2()">Chart</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="hiddenanchor" id="tochart"></a>
                <a class="hiddenanchor" id="toinfo"></a>
                <div id="info" class="animate form">
                    <c:if test="${bigImage!=null}">
                    <img href="javascript:;" onclick="showMask()" src="${bigImage}"
                         style="width: 250px;height: 380px;float: left;cursor:pointer;">
                    </c:if>
                    <c:if test="${bigImage==null}">
                        <img href="javascript:;" src="/graphics/movieInfo/nopic.png"
                             style="width: 250px;height: 380px;float: left;cursor:pointer;">
                    </c:if>
                    <div class="movie-info">
                        <div class="movie-score">
                            <img src="/graphics/movieInfo/scoreStar.png">
                            <label style="float: right;margin-top:5px;">${movie.ratingValue}<span>/10</span></label></br>

                        </div>
                        <label>Genres: <span>${movie.genres}</span></label></br>
                        <label>Country: <span>${movie.country}</span></label></br>
                        <label>Language: <span>${movie.language}</span></label></br>
                        <label>Release Date: <span>${movie.releDate}</span></label></br>
                        <c:if test="${movie.budget!=null}">
                            <label>Budget: <span>${movie.budget}</span></label></br>
                        </c:if>
                        <c:if test="${movie.gross!=null}">
                            <label>Gross: <span>${movie.gross}</span></label></br>
                        </c:if>

                        <label>RunTime: <span>${movie.runTime}</span></label></br></br>
                        <label>StoryLine: <span>${movie.storyLine}</span></label></br>
                    </div>
                    <c:if test="${!empty reward}">
                        <div class="movie-awards">
                            <img src="/graphics/movieInfo/AwardsLine.png">
                            <div class="awards" style="margin-left: 20px;">
                                <c:forEach var="awards" items="${reward}">
                                    <div style="float: left; width:130px">
                                        <h1>${awards.rewardName}<span> (${awards.rewardYear})</span></h1>
                                        <label>${awards.getType}</label></br>
                                    </div>
                                    <div style="height: 83px;">
                                        <label style=" margin-top: 20px; margin-left: 30px;"> ${awards.rewardType}</label></br>
                                        <a style=" margin-top: 20px; margin-left: 30px;">${awards.peopleName}</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${!empty movie.stars}">
                        <div class="movie-stars">
                            <img src="/graphics/movieInfo/starsLine.png">
                            <div class="pics">
                                <c:forEach var="stars" items="${movie.stars}">
                                    <div class="la"
                                         style="float: left;list-style: none;position: relative;width: 230px;margin-right: 33px;">
                                        <a href="/MRAS/maker/detail?makerName=${stars.name}&type=star">
                                        <img src="${stars.imgUrl}">
                                        <label>
                                            <div class="ha" style="width:214px;height: 40px;text-align: center;">
                                                ${stars.name}
                                            </div>
                                        </label>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${!empty movie.creators}">
                        <div class="movie-dandws">
                            <img src="/graphics/movieInfo/DandWLine.png">
                            <div class="pics">
                                <c:forEach var="directors" items="${movie.creators}">
                                    <div class="la"
                                         style="float: left;list-style: none;position: relative;width: 230px;margin-right: 33px;">
                                        <a href="/MRAS/maker/detail?makerName=${directors.name}&type=creator">
                                        <img src="${directors.imgUrl}">
                                        <label>
                                            <div class="ha" style="width:214px;height: 40px;text-align: center;">
                                                ${directors.name}
                                            </div>
                                        </label>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${!empty personReviews}">
                        <div class="movie-reviews" id="revs">
                            <img src="/graphics/movieInfo/reviewsLine.png">
                            <c:forEach var="personReview" items="${personReviews}">
                                <div class="review">
                                    <div class="rev-title">
                                        <h1>Author: </br><span>${personReview.author}</span></h1>
                                        <h1>helpfulness: </br><span>${personReview.useful}/${personReview.all}</span>
                                        </h1>
                                        <h2>${personReview.time}</h2>
                                    </div>
                                    <div class="rev-content">
                                        <h1>${personReview.titel}</h1>

                                        <label>${personReview.content}
                                        </label>
                                    </div>
                                </div>
                            </c:forEach>

                                <%--<s:hidden id="top" name="name"/>--%>
                            <!-- 分页栏 -->
                            <div class="M-box"></div>
                        </div>
                    </c:if>
                    <!--Co. Infomation-->
                    <div class="footer">
                        <span>© 2017 OTTT-MRAS, all rights reserved</span>
                    </div>
                </div>

                <%--表格从这里开始画--%>
                <div id="chart" class="chart">
                    <div style="width: 1270pX; margin-right: 110px; margin-top: 50px">
                        <%--<c:forEach var="item" items="${aixsCharts}">--%>
                        <c:if test="${fn:length(aixsCharts)!= 0}">
                            <div class="xhzb_wrap" style="width: 310px; display: inline-block">
                                <div id="${aixsCharts[0].name}" class="my_main1" style="width: 310px;"></div>
                            </div>
                        </c:if>

                        <c:if test="${fn:length(aixsCharts) == 2}">
                            <div class="xhzb_wrap" style="width: 310px; display: inline-block">
                                <div id="${aixsCharts[1].name}" class="my_main2" style="width: 310px;"></div>
                            </div>
                        </c:if>
                        <%--</c:forEach>--%>
                    </div>
                </div>

                <!--Co. Infomation-->
                <%--<div class="footer">--%>
                    <%--<footer>© 2017 OTTT-MRAS, all rights reserved</footer>--%>
                <%--</div>--%>
            </div>
            <div id="aside" class="aside" style="left: 644.5px;position:static;right:auto;bottom: 300px;top:auto;">

                <h1 class="try">Recommend movies</h1>
                <div id="rec_movie" class="aside_pics" style="height: 870px;">
                    <%--<c:forEach var="recMovie" items="${recMovie}">--%>
                    <%--<div class="la">--%>
                    <%--<img src="${recMovie.imgUrl}" >--%>
                    <%--<a href="/MRAS/movie/detail?movieName=${recMovie.name}">${recMovie.name} </a>--%>
                    <%--</div>--%>
                    <%--</c:forEach>--%>
                </div>

                <c:if test="${!empty favourites}">
                <h1 class="try">Your favourites</h1>
                <div class="aside_pics">
                    <c:forEach var="favourite" items="${favourites}">
                        <div class="la"
                             style="list-style: none;position: relative;width: 150px;  margin-left: 15px; margin-right: 15px;margin-bottom: 30px;">
                            <a href="/MRAS/movie/detail?movieName=${favourite.name}">
                            <img src="${favourite.imgUrl}" style="width: 150px;height: 222px;">
                            ${favourite.name} </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            </c:if>

        </div>
    </div>

</div>

<script type="text/javascript">

    <c:if test="${fn:length(aixsCharts)!=0}">
    // 基于准备好的dom，初始化echarts图表
    <%--var myChart = echarts.init(document.getElementById('${item.name}'));--%>
    var myChart = echarts.init(document.getElementById('${aixsCharts[0].name}'));

    var config = {
        min: ${aixsCharts[0].min},
        max: ${aixsCharts[0].max},
        value: ${aixsCharts[0].value},
        average: ${aixsCharts[0].avg},
        name: "${aixsCharts[0].name}",
        targetValue: {
            color: '#f43963',
            value: ${aixsCharts[0].avg}
        }
    }

    //根据target value ，生成相应位置的 graphic
    function _settingVerticalGraphic(config) {
        var graphic = [];
        var left = myChart.convertToPixel({
            xAxisIndex: 0
        }, 0);
        left = parseFloat(left, 10);
        left -= 26;
        var targetValue = config.targetValue.value;
        var min = config.min || 0;
        var max = config.max || 100;
        if (targetValue > max || targetValue < min) {
            return;
        }
        var top = _getyAxisValueTop(targetValue);
        graphic.push({
            z: 10,
            type: 'line',
            left: left,
            shape: {
                x1: 0,
                x2: 35
            },
            style: {
                stroke: '#1FBCD2',
                lineWidth: 2
            },
            silent: true,
            top: top
        });
        return {
            graphic: graphic
        };
    }

    function _getyAxisValueTop(value) {
        var top = myChart.convertToPixel({
            yAxisIndex: 0
        }, value);
        return parseFloat(top, 10).toFixed(2) - 2;
    }

    //防止自动 setOption
    var option1 = {
        "title": {
            left: '15%',
            text: config.name
        },
        "tooltip": {
            "axisPointer": {
                "type": ""
            }
        },
        "grid": {
            "left": 'middle',
            "width": 60
        },
        "yAxis": {
            "show": true,
            "offset": -5,
            "type": "value",
            "interval": 0.01,
            "axisTick": {
                "show": false
            },
            "axisLine": {
                "show": false
            },
            "axisLabel": {
                "show": true,
                "showMinLabel": true,
                "showMaxLabel": true,
                formatter: function (val) {
                    if (val === config.min || val === config.max || val === config.targetValue.value) {
                        return val;
                    }
                }
            },
            "splitLine": {
                "show": false
            },
            "max": config.max,
            "min": config.min
        },
        "xAxis": {
            "show": false,
            "type": "category"
        },
        "series": [{
            "name": "",
            "type": "bar",
            "barWidth": 30,
            "silent": true,
            "animation": false,
            "itemStyle": {
                "normal": {
                    "color": "#444"
                }
            },
            "barGap": "-100%",
            "data": [config.max]
        }, {
            "name": "成绩",
            "type": "bar",
            "barWidth": 30,
            "tooltip": {
                formatter: function () {
                    return [
                        'max: ' + config.max,
                        'min: ' + config.min,
                        'average: ' + config.average,
                        'value: ' + config.value
                    ].join('<br/>')
                }
            },
            "label": {
                "normal": {
                    "show": true,
                    "position": "insideTopRight",
                    "offset": [30, 0],
                    "textStyle": {
                        "color": "#63869e"
                    }
                }
            },
            "data": [{
                "value": config.average,
                "itemStyle": {
                    "normal": {
                        "color": "red"
                    }
                },
                "name": "分数"
            }]
        }],
    };

    function handleColor(option, config) {
        if (option.series[1].data[0].value >= config.targetValue.value) {
            option.series[1].data[0].itemStyle.normal.color = config.targetValue.color;
            option.series[1].label.normal.textStyle.color = config.targetValue.color;
        }
        return option;
    }
    //分数 50 不及格
    myChart.setOption(handleColor(option1, config));
    setTimeout(function () {
        //分数 80 及格
        option1.series[1].data[0].value = config.value;
        myChart.setOption(handleColor(option1, config));
    }, 0);
    //标记红线
    myChart.setOption(_settingVerticalGraphic(config), false);
    </c:if>
</script>

<script type="text/javascript">

    <c:if test="${fn:length(aixsCharts)== 2}">
    // 基于准备好的dom，初始化echarts图表
    var myChart2 = echarts.init(document.getElementById('${aixsCharts[1].name}'));

    var config2 = {
        min: ${aixsCharts[1].min},
        max: ${aixsCharts[1].max},
        value: ${aixsCharts[1].value},
        average: ${aixsCharts[1].avg},
        name: "${aixsCharts[1].name}",
        targetValue: {
            color: '#f43963',
            value: ${aixsCharts[1].avg}
        }
    }

    //根据target value ，生成相应位置的 graphic
    function _settingVerticalGraphic(config) {
        var graphic = [];
        var left = myChart2.convertToPixel({
            xAxisIndex: 0
        }, 0);
        left = parseFloat(left, 10);
        left -= 26;
        var targetValue = config.targetValue.value;
        var min = config.min || 0;
        var max = config.max || 100;
        if (targetValue > max || targetValue < min) {
            return;
        }
        var top = _getyAxisValueTop(targetValue);
        graphic.push({
            z: 10,
            type: 'line',
            left: left,
            shape: {
                x1: 0,
                x2: 35
            },
            style: {
                stroke: '#1FBCD2',
                lineWidth: 2
            },
            silent: true,
            top: top
        });
        return {
            graphic: graphic
        };
    }

    function _getyAxisValueTop(value) {
        var top = myChart2.convertToPixel({
            yAxisIndex: 0
        }, value);
        return parseFloat(top, 10).toFixed(2) - 2;
    }

    //防止自动 setOption
    var option2 = {
        "title": {
            left: '15%',
            text: config2.name
        },
        "tooltip": {
            "axisPointer": {
                "type": ""
            }
        },
        "grid": {
            "left": 'middle',
            "width": 60
        },
        "yAxis": {
            "show": true,
            "offset": -5,
            "type": "value",
            "interval": 0.01,
            "axisTick": {
                "show": false
            },
            "axisLine": {
                "show": false
            },
            "axisLabel": {
                "show": true,
                "showMinLabel": true,
                "showMaxLabel": true,
                formatter: function (val) {
                    if (val === config2.min || val === config2.max || val === config2.targetValue.value) {
                        return val;
                    }
                }
            },
            "splitLine": {
                "show": false
            },
            "max": config2.max,
            "min": config2.min
        },
        "xAxis": {
            "show": false,
            "type": "category"
        },
        "series": [{
            "name": "",
            "type": "bar",
            "barWidth": 30,
            "silent": true,
            "animation": false,
            "itemStyle": {
                "normal": {
                    "color": "#444"
                }
            },
            "barGap": "-100%",
            "data": [config2.max]
        }, {
            "name": "成绩",
            "type": "bar",
            "barWidth": 30,
            "tooltip": {
                formatter: function () {
                    return [
                        'max: ' + config2.max,
                        'min: ' + config2.min,
                        'average: ' + config2.average,
                        'value: ' + config2.value
                    ].join('<br/>')
                }
            },
            "label": {
                "normal": {
                    "show": true,
                    "position": "insideTopRight",
                    "offset": [30, 0],
                    "textStyle": {
                        "color": "#63869e"
                    }
                }
            },
            "data": [{
                "value": config2.average,
                "itemStyle": {
                    "normal": {
                        "color": "red"
                    }
                },
                "name": "分数"
            }]
        }],
    };

    function handleColor(option, config) {
        if (option.series[1].data[0].value >= config.targetValue.value) {
            option.series[1].data[0].itemStyle.normal.color = config.targetValue.color;
            option.series[1].label.normal.textStyle.color = config.targetValue.color;
        }
        return option;
    }
    //分数 50 不及格
    myChart2.setOption(handleColor(option2, config2));
    setTimeout(function () {
        //分数 80 及格
        option2.series[1].data[0].value = config2.value;
        myChart2.setOption(handleColor(option2, config2));
    }, 0);
    //标记红线
    myChart2.setOption(_settingVerticalGraphic(config2), false);
    </c:if>

</script>


<!-- Bootstrap core JavaScript
  ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
<%--<script>window.jQuery || document.write('<script src="/js/jquery.min.js"><\/script>')</script>--%>
<%--<script src="/js/bootstrap.min.js"></script>--%>
<%--<!--&lt;!&ndash; IE10 viewport hack for Surface/desktop Windows 8 bug &ndash;&gt;-->--%>
<%--<script src="/js/ie10-viewport-bug-workaround.js"></script>--%>
</body>
</html>
<script type="text/javascript">
    window.onbeforeunload = function () {
        //提示是否离开该页面
        if (favleave) {
            return "Do you want to leave this page to login?";
        }
    }
    var favleave = false;
    function setFav() {
        if (${sessionScope.username==null}) {
            var url = window.location.href;
            sessionStorage.setItem("nowUrl", url);
            window.location.href = "/MRAS/login";
            favleave = true;
        } else {
            $.ajax({
                type: "POST",   //http请求方式为POST
                url: '/MRAS/favourite/add',  //请求接口的地址
                data: {userName: "${sessionScope.username}", name: "${movie.name}", type:"movie"},
                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                cache: false,   //不用缓存
                async: false,
                success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                    $("#Fav").show();
                    $("#notFav").hide();
                },
                error: function () {
                    alert("Error!");
                }
            })
        }
    }
    function cancelFav() {
        if (${sessionScope.username==null}) {
            window.open("/MRAS/login");
        } else {
            $.ajax({
                type: "POST",   //http请求方式为POST
                url: '/MRAS/favourite/delete',  //请求接口的地址
                data: {userName: "${sessionScope.username}", name: "${movie.name}", type:"movie"},
                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                cache: false,   //不用缓存
                async: false,
                success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                    $("#notFav").show();
                    $("#Fav").hide();
                },
                error: function () {
                    alert("Error!");
                }
            })
        }

    }
</script>