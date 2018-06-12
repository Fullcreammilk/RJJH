<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/16
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.bxslider.js"></script>

<script src="/js/jquery.js"></script>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<script>
    function initloading() {
//        $("#loading").fadeOut();
        initBar();
        $("#retract").hide();
        <%--alert(${fn:length(detail.bio)<900});--%>
        if (${fn:length(detail.bio)<850}) {
            $("#more").hide();
        }

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
    function showMoreBio() {
        $("#bio").css("overflow", "visible");
        $("#bio").css("height", "auto");
        $("#more").hide();
//        $(".bio").after("");
        $("#retract").show();
    }
    function concealMoreBio() {
        $("#bio").css("overflow", "hidden");
        $("#bio").css("height", "16.6em");
        $("#more").show();
//        $(".bio").after("");
        $("#retract").hide();
        $("html, body").animate({
            scrollTop: $("#dale_movie_subject_top_icon").offset().top }, {duration: 500,easing: "swing"});
    }
    $(function () {
        if (${isInFavorite}) {
            $("#Fav").show();
            $("#notFav").hide();
        } else {
            $("#notFav").show();
            $("#Fav").hide();
        }
        $.ajax({
            url: '/MRAS/maker/partner',
            type: "get",
            dataType: "json",
            data: {name: "${detail.name}", type: "${detail.type}"},
            contentType: "application/json",
            traditional: false,
            success: function (data) {
                for (var j = 0; j < data.length; j++) {
                    var title = document.createElement("h1");
                    var all_var = document.createElement("div");
                    var pat_var = document.createElement("div");
                    var pat_pic = document.createElement("img");
                    var pat_name = document.createElement("a");
                    var pat_href = document.createElement("a");
                    title.innerHTML = data[j].title+" & their movie";
                    title.setAttribute("class", "try");
                    pat_pic.src = data[j].imgUrl;
                    pat_pic.setAttribute("title",data[j].title);
                    pat_name.innerHTML = data[j].name;
                    pat_var.setAttribute("class", "la");
                    all_var.setAttribute("style", "height:400px;");
                    all_var.setAttribute("class", "aside_pics");
                    pat_var.appendChild(pat_pic);
                    pat_var.appendChild(pat_name);
                    if(${detail.type=="star"})
                        pat_href.href = "/MRAS/maker/detail?makerName="+data[j].name+"&type=creator";
                    else if(${detail.type=="creator"})
                        pat_href.href = "/MRAS/maker/detail?makerName="+data[j].name+"&type=star";
                    pat_href.appendChild(pat_var);
                    all_var.appendChild(pat_href);
                    var mov_href = document.createElement("a");
                    var mov_var = document.createElement("div");
                    var mov_pic = document.createElement("img");
                    var mov_name = document.createElement("a");
                    mov_pic.src = data[j].movieImgUrl;
                    mov_pic.setAttribute("title","Presentative Movie");
                    mov_name.innerHTML = data[j].presentativeMovie;
                    mov_href.href = "/MRAS/movie/detail?movieName="+data[j].presentativeMovie;
                    mov_var.setAttribute("class", "la");
                    mov_var.appendChild(mov_pic);
                    mov_var.appendChild(mov_name);
                    mov_href.appendChild(mov_var);
                    all_var.appendChild(mov_href);
                    var percent = document.createElement("label");
                    percent.innerHTML="Tips: The cooperation between ${detail.name} and "+data[j].name+" can improve movie score by "+data[j].percent+".";
                    all_var.appendChild(percent);
                    document.getElementById("aside").appendChild(title);
                    document.getElementById("aside").appendChild(all_var);
//                    document.getElementById("aside_pics").appendChild(mov_var);

                }
//                $("#service_type").html("<option value='请选择'>请选择...</option> "+optionstring);
            },
        })
    })
</script>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/graphics/logo.png">
    <title>MakerInfo</title>

    <link rel="stylesheet" href="/css/movieDetail.css"/>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <%--<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">--%>
    <link rel="stylesheet" href="/css/animsition.min.css"/>
    <link rel="stylesheet" href="/css/common.css"/>
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
    <img id="bigPoster" class="bigPoster" href="javascript:;" onclick="hideMask()" src="${bigUrl}">
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
        <h1 style="margin-bottom: 30px;">${detail.name}</h1>
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
                <div id="info" class="animate form">
                    <img href="javascript:;" onclick="showMask()" src="${detail.imageUrl}"
                         style="width: 250px;height: 360px;float: left;cursor:pointer;">
                    <div class="movie-info">

                        <label>Born: <span>${detail.birthday}</span></label></br>
                        <c:if test="${detail.height!=null}">
                            <label>Height: <span>${detail.height}</span></label></br>
                        </c:if>
                        <label>Type: <span>${detail.type}</span></label></br></br>
                        <label id="bio" class="bio">Bio: <span>${detail.bio}</span></label></br>
                        <a id="more" onclick="showMoreBio()"
                           style="float: right;color: black;font-style:italic;margin-right: 15px;text-decoration: underline;font-weight: bold;cursor: pointer;">More
                            ></a>
                        <a id="retract" onclick="concealMoreBio()"
                           style="float: right;color: black;font-style:italic;margin-right: 15px;text-decoration: underline;font-weight: bold;cursor: pointer;">Retract
                            <</a>
                    </div>
                    <c:if test="${!empty reward}">
                    <div class="movie-awards">
                        <img src="/graphics/movieInfo/AwardsLine.png">
                        <div class="awards">
                            <c:forEach var="awards" items="${reward}">
                                <div style="float: left; width:130px">
                                    <h1>${awards.rewardName}<span> (${awards.rewardYear})</span></h1>
                                    <label>${awards.getType}</label></br>
                                </div>
                                <div style="height: 83px;">
                                    <label style=" margin-top: 20px; margin-left: 30px;"> ${awards.rewardType}</label></br>
                                    <a style=" margin-top: 20px; margin-left: 30px;">${awards.movieName}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    </c:if>
                    <div class="movie-stars" style="height: ${fn:length(detail.movies)/3*450+200}px">
                        <img src="/graphics/movieInfo/moviesLine.png">
                        <div class="pics">
                            <c:forEach var="movies" items="${detail.movies}">
                                <div class="la"
                                     style="float: left;list-style: none;position: relative;width: 230px;margin-right: 33px;">
                                    <a href="/MRAS/movie/detail?movieName=${movies.name}">
                                    <img src="${movies.imgUrl}">
                                    <label>
                                        <div class="ha" style="width:214px;height: 40px;text-align: center;">
                                            ${movies.name}
                                        </div>
                                    </label>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!--Co. Infomation-->
                    <div class="footer">
                        <span>© 2017 OTTT-MRAS, all rights reserved</span>
                    </div>
                </div>

                <%--图表从这里开始画--%>
                <div id="chart" class="chart">
                    <c:if test="${canDraw}">
                        <%--画雷达图--%>
                        <div class="chart1" style="height: 450px;">
                            <%--<div class="chart1_description" style="text-align: center">--%>
                                <%--<p class="chart1_hint" style="color: #333333; font-size: 28px">Genre Radar Chart</p>--%>
                            <%--</div>--%>

                            <div class="xhzb_wrap" style="float: left;width: 60%;" >
                                <div id="main1" class="my_main1"></div>
                            </div>

                            <div class="chart1_description" style="float: right;width: 30%;margin-top:60px;">
                                <p class="chart1_hint" style="color: #333333; font-size: 28px;margin-bottom: 50px">Genre Radar Chart</p>
                                <p id="chart1_hint" style="width: 630px">This radar chart is to show you the ability of the star or the
                                    director & writer to interpret films with different tags.</p>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${!empty awardAndNom}">
                        <%--画堆叠图--%>
                        <div class="chart2" style="height: 750px">
                            <div class="chart2_description" style="text-align: center">
                                <p class="chart2_hint" style="color: #333333; font-size: 28px">Winner and Nominee</p>
                            </div>

                            <div class="xhzb_wrap">
                                <div id="main2" class="my_main2"></div>
                            </div>

                            <div class="chart2_description" >
                                <p id="chart2_hint">This line chart is to show you the awards the star won per year.</p>
                            </div>
                        </div>
                    </c:if>

                    <%--画柱状图--%>
                    <div class="chart3" style="height: 750px">
                        <div class="chart3_description" style="text-align: center">
                            <p class="chart3_hint" style="color: #333333; font-size: 28px">Movie Amount and Box-office</p>
                        </div>

                        <div class="xhzb_wrap">
                            <div id="main3" class="my_main3"></div>
                        </div>

                        <div class="chart3_description">
                            <p id="chart3_hint" style="width: 80%;margin: 0 auto">This chart is to show you that the amount of movies the star starred in
                                per year, as well as the total box office of these movies.</p>
                        </div>

                    </div>

                    <!--Co. Infomation-->
                    <div class="footer">
                        <footer>© 2017 OTTT-MRAS, all rights reserved</footer>
                    </div>
                </div>

            </div>
            <div id="aside" class="aside" style="left: 644.5px;position:static;right:auto;bottom: 300px;top:auto;">
                <%--<h1 class="try">Best Partner & Their Movie</h1>--%>
                <%--<div id="aside_pics" class="aside_pics" style="height: 861px;">--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>
<script>
    /**
     * 雷达图
     * @type {Element}
     */
    // 如果数据能生成图表
    if (${canDraw}) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = document.getElementById('main1');
        var myChartContainer = function () {
            myChart.style.width = '600px';
            myChart.style.height = '400px';
        };
        myChartContainer();
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(myChart);


        var tags = [];  // maker所擅长的不同标签的列表
        var tags_data = [];  // 各项标签的能力值
        <c:forEach var="item" items="${genreAndAvg}">
        indicator_item = {
            name: '${item.attr}',
            max: 10,
            min: 3
        };
        tags.push(indicator_item);
        tags_data.push(${item.value});
        </c:forEach>

        option = {
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
                name: 'Different Abilities:',
                type: 'radar',
                data: [{
                    value: tags_data,
                    name: ''
                }]
            }]
        };

        // 使用指定的配置项和数据显示图表
        myChart.setOption(option);
    }


    /**
     * 堆叠图
     */
        <c:if test="${!empty awardAndNom}">

    var won = [];
    var nominee = [];
    var time = [];

    <c:forEach var="award_and_num" items="${awardAndNom}">
    time.push(${award_and_num.year});
    won.push(${award_and_num.won});
    nominee.push(${award_and_num.nominated});
    </c:forEach>

    // 基于准备好的dom，初始化echarts图表
    var myChart2 = echarts.init(document.getElementById('main2'));
    option2 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['Awards winner', 'Awards nominee']
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
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: time
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: 'awards amount'
            }
        ],
        series: [
            {
                name: 'Awards winner',
                type: 'line',
                smooth: true,
                stack: 'total',
                areaStyle: {normal: {}},
                data: won
            },
            {
                name: 'Awards nominee',
                type: 'line',
                smooth: true,
                stack: 'total',
                areaStyle: {normal: {}},
                data: nominee
            }
        ]
    };

    // 使用指定的配置项和数据显示图表
    myChart2.setOption(option2);

    </c:if>


    /**
     * 双y轴柱状折线图
     */
    var year = [];
    var box_office = [];
    var movie_nums = [];

    // 基于准备好的dom，初始化echarts图表
    var myChart3 = echarts.init(document.getElementById('main3'));

    <c:forEach var="boxOffice_and_movieNums" items="${gross}">
    year.push(${boxOffice_and_movieNums.attr});
    box_office.push(${boxOffice_and_movieNums.v1});
    movie_nums.push(${boxOffice_and_movieNums.v2});
    </c:forEach>

    option3 = {
        tooltip: {
            trigger: 'axis'
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
                data: {userName: "${sessionScope.username}", name: "${detail.name}", type:"${detail.type}"},
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
                data: {userName: "${sessionScope.username}",name: "${detail.name}", type:"${detail.type}"},
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