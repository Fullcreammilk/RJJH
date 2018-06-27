<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/31
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.bxslider.js"></script>

<script src="/js/jquery.js"></script>
<script>
    var genre = "All";
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }
    /**
     * jquery初始化方法
     */
    $(function(){
        $('.M-box').pagination({
            current:${presentPage},
            pageCount:${pageNum},
            count:2,
            jump:true,
            coping:true,
            homePage:'First',
            endPage:'Last',
            prevContent:'<',
            nextContent:'>',
            jumpBtn:'Jump',
            callback:function (api) {
                page = api.getCurrent();
                window.location = "/MRAS//company/detail?companyName=${company.name}&genre="+genre+"&page="+page;
            }
        });
        //设置点击标签改变底色，并去除其他标签的底色
        $(".tag label").click(function () {
            genre = this.id;
            sessionStorage.setItem("genre",genre);
            window.location.href = "/MRAS//company/detail?companyName=${company.name}&genre="+genre;
            $(this).addClass("tag_selected").siblings().removeClass("tag_selected");
        });
        //初始化tag底色
        if(!sessionStorage.getItem("genre")){
            genre = "All";
        }else{
            genre =sessionStorage.getItem("genre");
        }
//        var movieTag = GetQueryString("tag");
//        console.log(movieTag);
        $("#" +  genre).addClass("tag_selected");
        $("#detail-history").hide();
        $("#detail-background").hide();

    });
    function initloading() {
        $("#loading").fadeOut();
        initBar();
        $("#retract").hide();
    }

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
        $("#bigPoster").css("height", "600px");
        $("#mask").show();
    }
    //隐藏遮罩层
    function hideMask() {
        $("#mask").hide();
    }
    function moreHistory(){
        $("#intro-history").hide();
        $("#detail-history").show();
    }
    function hideHistory(){
        $("#intro-history").show();
        $("#detail-history").hide();
    }
    function moreBackground(){
        $("#intro-background").hide();
        $("#detail-background").show();
    }
    function hideBackground() {
        $("#intro-background").show();
        $("#detail-background").hide();
    }
</script>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/graphics/logo.png">
    <title>Company Info</title>
    <link rel="stylesheet" type="text/css" href="/css/movieDetail.css"/>

    <link rel="stylesheet" type="text/css" href="/css/companyDetail.css"/>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="/css/animsition.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
    <link rel="stylesheet" href="/css/xgzb.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="/js/echarts.js"></script>
    <link rel="stylesheet" href="/css/xgzb.css"/>

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
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

</head>
<body onload="initloading()">
<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>
<%--大图遮罩层--%>
<div id="mask" class="mask">
    <img id="bigPoster" class="bigPoster" href="javascript:;" onclick="hideMask()" src="${company.imageurl}">
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
        <h1 style="margin-bottom: 30px;">${company.name}</h1>
        <a class="hiddenanchor" id="detailInfo"></a>
        <a class="hiddenanchor" id="statisticsChart"></a>
        <div class="grid-16-8 clearfix">
            <div class="article" style="width: 1200px;">
                <%--电影基本信息--%>
                <div class="container" >
                    <div class="row">
                        <div class="span12">
                            <div class="tabbable" id="tabs-517818">
                                <ul class="nav nav-tabs">
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
                <div id="info" class="animate form"style="width: 1200px;text-align: center;">
                    <img href="javascript:;" onclick="showMask()" src="${company.imageurl}"
                         style="width: 400px;cursor:pointer;float: left;">
                    <div class="movie-info" style="    max-width: 600px;    margin-left: 450px;     margin-top: 0px; min-height: 250px;    text-align: left;">
                        <label>Founded Time: <span>${company.founded}</span></label></br>
                        <label>Founder: <span>${company.founder} </span></label></br>
                        <label id="intro-history">History: <span style="font-style: italic;text-decoration: underline;cursor: pointer;" onclick="moreHistory()">Click here to see more</span></label></br>
                        <label id="detail-history">History: <span> ${company.history}</span><span style="float:right;font-style: italic;text-decoration: underline;cursor: pointer;font-weight: bold;" onclick="hideHistory()">Retract</span></label>
                        <label id="intro-background">Background: <span style="font-style: italic;text-decoration: underline;cursor: pointer;" onclick="moreBackground()">Click here to see more</span></label>
                        <label id="detail-background">Background: <span> ${company.background}</span><span style="float:right;font-style: italic;text-decoration: underline;cursor: pointer; font-weight: bold;" onclick="hideBackground()">Retract</span></label>

                    </div>
                    <%--<div class="movie-info" style="max-width:1000px;margin:0 auto;">--%>
                        <%----%>
                    <%--</div>--%>

                    <div class="movie-stars" style="margin-top: 50px;text-align: center;">
                        <img src="/graphics/movieInfo/moviesLine.png" style="margin-bottom: 30px;">
                        <!--标签栏-->
                        <div class="tag">
                            <c:forEach var="tag" items="${genres}" >
                                <label id="${tag}">${tag}</label>
                            </c:forEach>
                        </div>

                        <div class="pics">
                            <c:forEach var="movies" items="${movies}">
                                <div class="la"
                                     style="float: left;list-style: none;position: relative;width: 210px;margin-right: 30px;">
                                    <a href="/MRAS/movie/detail?movieName=${movies.name}">
                                    <img src="${movies.imgUrl}" style="width:210px;">
                                    <label>
                                        <div class="ha" style="width:210px;height: 40px;text-align: center;">
                                            ${movies.name}
                                        </div>
                                    </label>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- 分页栏 -->
                    <div class="M-box"></div>
                </div>
                <div id="chart" class="chart">
                    <c:if test="${canDraw}">
                        <%--画雷达图--%>
                        <%--<div class="chart1" style="margin-top: 0px;height: 400px;float: left;">--%>


                            <div style="margin-top: 50px;float: left;width: 400px;">
                                <div id="main1" class="my_main1" ></div>
                                <p class="chart1_hint" style="color: #333333; font-size: 28px">Genre Radar Chart</p>
                                <p style="color: black;">This radar chart is to show you the top several tags of movies the
                                   company has produced by now.</p>
                            </div>

                            <%--<div class="chart1_description">--%>
                                    <%--<p class="chart1_hint" style="color: #333333; font-size: 28px">Genre Radar Chart</p>--%>
                                <%--<p id="chart1_hint" style="    margin-left: 600px;width: 40%;--%>
    <%--margin-top: 150px;">This radar chart is to show you the top several tags of movies the--%>
                                    <%--company has produced by now.</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </c:if>

                    <%--画柱状图--%>
                    <%--<div class="chart3"style="float: right">--%>
                        <%--<div class="chart3_description" style="text-align: center">--%>
                            <%--<p class="chart3_hint" style="color: #333333; font-size: 28px">Movie Amount and Box-office</p>--%>
                        <%--</div>--%>

                        <div  style="margin-top: 50px;float: right;width: 770px;">
                            <div id="main3" class="my_main3" ></div>
                            <div style="width:700px;float: right;">
                            <p class="chart1_hint" style="color: #333333; font-size: 28px">Movie Amount and Box-office</p>
                            <p style="color: black;">This chart is to show you that the amount of movies the company produced
                                per year, as well as the total box office of these movies.</p>
                            </div>
                        </div>

                        <%--<div class="chart3_description">--%>
                            <%--<p id="chart3_hint" style="width: 40%;">This chart is to show you that the amount of movies the company produced--%>
                                <%--per year, as well as the total box office of these movies.</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <!--Co. Infomation-->
                    <div class="footer">
                        <footer>© 2017 OTTT-MRAS, all rights reserved</footer>
                    </div>
                </div>

            </div>
            <%--<div id="aside" class="aside" style="left: 644.5px;position:static;right:auto;bottom: 300px;top:auto;">--%>
            <%--<h1 class="try">我修改了哟！</h1>--%>
            <%--</div>--%>
        </div>
    </div>

</div>
<script>
    $(document).ready(function () {
        /**
         * 雷达图
         * @type {Element}
         */
        // 如果数据能生成图表
        if (${canDraw}) {
            var myChart = document.getElementById('main1');
            var myChartContainer = function () {
                myChart.style.width = '400px';
                myChart.style.height = '400px';
            };
            myChartContainer();
            // 基于准备好的dom，初始化echarts图表
            var myChart = echarts.init(myChart);

            var tags = [];  // maker所擅长的不同标签的列表
            var tags_data = [];  // 各项标签的能力值
            <c:forEach var="item" items="${topsix}">
            indicator_item = {
                name: '${item.attr}',
                max: 10,
                min: 3
            };
            tags.push(indicator_item);
            tags_data.push(${item.value});
            </c:forEach>

            option = {
                title: {
                    text: ''
                },
                toolbox: {
                    feature: {
                        dataView: {
                            show: true,
                            readOnly: false
                        },
                        saveAsImage: {}
                    },
                    right: 25
                },
                tooltip: {},
                radar: {
                    indicator: tags
                },
                series: [{
                    name: 'Genres and Scores:',
                    type: 'radar',
                    data: [{
                        value: tags_data,
                        name: ''
                    }]
                }],

            };

            // 使用指定的配置项和数据显示图表
            myChart.setOption(option);
            $(window).resize(function() {//这是能够让图表自适应的代码
                myChart.resize();

            });
        }
    });


    /**
     * 双y轴柱状折线图
     */
    var year = [];
    var box_office = [];
    var movie_nums = [];

    // 基于准备好的dom，初始化echarts图表
    var myChart3 = document.getElementById('main3');
    var myChart3Container = function () {
        myChart3.style.width = '770px';
        myChart3.style.height = '400px';
    };
    myChart3Container();
    // 基于准备好的dom，初始化echarts图表
    var myChart3 = echarts.init(myChart3);
   // var myChart3 = echarts.init(document.getElementById('main3'));

    <c:forEach var="boxOffice_and_movieNums" items="${gross}">
    year.push(${boxOffice_and_movieNums.attr});
    box_office.push(${boxOffice_and_movieNums.v1});
    movie_nums.push(${boxOffice_and_movieNums.v2});
    </c:forEach>

    option3 = {
        tooltip: {
            trigger: 'axis'
        },
        dataZoom: {
            bottom: 20,
            fillerColor: 'rgba(126, 224, 209, 0.2)',
            borderColor: '#334b5c',
            handleStyle: {
                color: '#334b5c'
            },
            start: 60,
            end: 100
        },
        toolbox: {
            feature: {
                dataView: {
                    show: true,
                    readOnly: false
                },
                saveAsImage: {}
            },
            right: 25
        },
        grid: {
            containLabel: true
        },
        legend: {
            data: ['movie amount', 'box office']
        },
        xAxis: [{
            type: 'category',
            axisTick: {
                alignWithLabel: true
            },
            data: year
        }],
        yAxis: [{
            type: 'value',
            name: 'movie amount',
            min: 0,
            max: 5,
            position: 'right',
        }, {
            type: 'value',
            name: 'box office($)',
            min: 0,
//            max: 1E9,
            position: 'left'
        }],
        series: [{
            name: 'movie amount',
            type: 'line',
            stack: 'total',
            label: {
                normal: {
                    show: true,
                    position: 'top',
                }
            },
            lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(0,0,0,0.4)',
                    shadowBlur: 10,
                    shadowOffsetY: 10
                }
            },
            data: movie_nums
        }, {
            name: 'box office',
            type: 'bar',
            yAxisIndex: 1,
            stack: 'total',
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            data: box_office
        }]
    };

    // 使用指定的配置项和数据显示图表
    myChart3.setOption(option3);

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
