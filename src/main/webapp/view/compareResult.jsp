<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/20
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<!-- Bootstrap core JavaScript
================================================== -->
<script type="text/javascript" src="/js/jquery.js"></script>

<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.bxslider.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!--&lt;!&ndash; IE10 viewport hack for Surface/desktop Windows 8 bug &ndash;&gt;-->
<script src="/js/ie10-viewport-bug-workaround.js"></script>
<script src="/js/awTooltip/index.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

<![endif]-->
<script language="JavaScript">
    var canDraw_radar = 0;
    var tags = [];
    var star1_data_radar = [];
    var star2_data_radar = [];
    var time = [];
    var star1_movie_amount = [];
    var star1_gross = [];
    var star2_movie_amount = [];
    var star2_gross = [];
    var left_star;
    var right_star;
    forecast_stars = sessionStorage.getItem("session_s").split(',');
    var temp_pic = new Array();
    temp_pic =  sessionStorage.getItem("s_p").split("/&/,");

    left_star = forecast_stars[0];
    right_star = forecast_stars[1];

    // 画雷达图方法
    function draw_radar() {
        // 基于准备好的dom，初始化echarts图表
        //console.log(tags[1]);
        var myChart = echarts.init(document.getElementById('main1'));
        //alert(star1_data_radar.length);
        myChart.clear();

        <%--var tags = [];--%>
        <%--var star1_data = [];--%>
        <%--var star2_data = [];--%>
        <%--<c:forEach var="item" items="${star1Radar}">--%>
        <%--indicator_item = {--%>
        <%--name: '${item.attr}',--%>
        <%--max: 10,--%>
        <%--min: 0--%>
        <%--};--%>
        <%--tags.push(indicator_item);--%>
        <%--star1_data.push(${item.value});--%>
        <%--</c:forEach>--%>

        <%--<c:forEach var="item" items="${star2Radar}">--%>
        <%--star2_data.push(${item.value});--%>
        <%--</c:forEach>--%>

        console.log(left_star);
        option1 = {
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
            legend: {
                data: [left_star, right_star]
            },
            radar: {
                // shape: 'circle',
                indicator: [
                    { text: tags[0] ,max:10},
                    { text: tags[1] ,max:10},
                    { text: tags[2],max:10 },
                    { text: tags[3],max:10 },
                    { text: tags[4] ,max:10}
                ]
            },
            series: [{
                name: 'Abilities comparison',
                type: 'radar',
                data: [
                    {
                        value: [star1_data_radar[0],star1_data_radar[1],star1_data_radar[2],star1_data_radar[3],star1_data_radar[4]],
                        name: left_star
                    },
                    {
                        value: [star2_data_radar[0],star2_data_radar[1],star2_data_radar[2],star2_data_radar[3],star2_data_radar[4]],
                        name: right_star
                    }
                ]
            }]
        };
        // 使用指定的配置项和数据显示图表
        //console.log(star2_data_radar[0],star2_data_radar[1],star2_data_radar[2],star2_data_radar[3],star2_data_radar[4]);
        myChart.setOption(option1,true);

    }

    // 画票房图方法
    function draw_gross() {
        var myChart2 = echarts.init(document.getElementById('main2'));
        myChart2.clear();

        <%--var year = [];--%>
        <%--var star1_box_office = [];--%>
        <%--var star1_movie_nums = [];--%>
        <%--var star2_box_office = [];--%>
        <%--var star2_movie_nums = [];--%>

        <%--<c:forEach var="star1" items="${gross1}">--%>
        <%--year.push(${star1.attr});--%>
        <%--star1_box_office.push(${star1.v1});--%>
        <%--star1_movie_nums.push(${star1.v2});--%>
        <%--</c:forEach>--%>

        <%--<c:forEach var="star2" items="${gross2}">--%>
        <%--year.push(${star2.attr});--%>
        <%--star2_box_office.push(${star2.v1});--%>
        <%--star2_movie_nums.push(${star2.v2});--%>
        <%--</c:forEach>--%>

        option2 = {
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
            tooltip: {
                trigger: 'axis',
                formatter: function (params, ticket, callback) {

                    var res = params[0].name;

                    for (var i = 0, l = params.length; i < l; i++) {
                        if (params[i].seriesType === 'line') {
                            res += '<br/>' + params[i].seriesName + ' : ' + (params[i].value ? params[i].value : '-');
                        } else {
                            res += '<br/>' + params[i].seriesName + ' : ' + (params[i].value ? params[i].value : '-');
                        }
                    }
                    return res;

                }
            },
            grid: {
                containLabel: true
            },
            legend: {
                data: [left_star + ": movie amount", left_star + ": box-office", right_star + ":movie amount", right_star + ": box-office"]
            },
            xAxis: [{
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: time
            }],
            dataZoom: [{
                type: 'slider',
                xAxisIndex: 0,
                filterMode: 'empty',
                start: 0,
                end: 100
            }, {
                type: 'slider',
                yAxisIndex: 0,
                filterMode: 'empty',
                start: 0,
                end: 100
            }, {
                type: 'inside',
                xAxisIndex: 0,
                filterMode: 'empty',
                start: 0,
                end: 100
            }, {
                type: 'inside',
                yAxisIndex: 0,
                filterMode: 'empty',
                start: 0,
                end: 100
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
                name: left_star + ": movie amount",
                type: 'line',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
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
                data: star1_movie_amount
            }, {
                name: left_star + ": box-office",
                type: 'bar',
                yAxisIndex: 1,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: star1_gross
            }, {
                name: right_star + ": movie amount",
                type: 'line',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
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
                data: star2_movie_amount
            }, {
                name: right_star + ": box-office",
                type: 'bar',
                yAxisIndex: 1,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: star2_gross
            }]
        };
        // 使用指定的配置项和数据显示图表
        myChart2.setOption(option2,true);
    }
    var nationality = "All";//国籍
    //    var genre = "All";//流派
    var type = "star";//类型
    var keyword = "";
    var forecast_stars = new Array();
    var forecast_creators = new Array();
    var forecast_genres = new Array();
    var stars_pic = new Array();
    var creas_pic = new Array();
    function init() {
        initBar();
    }
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return unescape(r[2]);
        return null;
    }

    /**
     * jquery初始化方法(自动会调)
     */
    $(function () {
        document.addEventListener("error", function (e) { var elem = e.target; if (elem.tagName.toLowerCase() == "img") { elem.src = "/graphics/error.jpg"; } }, true);

        var datalist = [];
        var namelist = new Array();
        $(function () {
            $('#inp-stars').keyup(function(event) {
                //    处理文本框中的键盘事件
                //    如果输入字母、退格键、删除键，则将信息发送到服务器
                var myEvent = event || window.event;
                var keyCode = myEvent.keyCode;
                if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46) {
                    //     1.首先获取文本框内容
                    var wordText = $('#inp-stars').val();
                    if(wordText!=""){
                        $.ajax({
                            type: "POST",   //http请求方式为POST
                            url: '/MRAS/search/help',  //请求接口的地址
                            data: {keyword:wordText, type:type},
                            dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                            cache: false,   //不用缓存
                            async: false,
                            success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                                for(var i=0;i<datalist.length;i++){
                                    datalist.pop();
                                }
                                for(var i=0;i<data.list.length;i++){
                                    namelist[i]=data.list[i];
                                }
                                for(var i=0;i<data.list.length;i++){
                                    datalist[i] = {  'label': namelist[i] };
                                }
                                return  datalist;
                            }
                        });
                    }
                }
            });
            $('#inp-stars').autocompleter({
                limit: 5,
                source:datalist
            });
            });



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
                var inner = name+" ×";
                label_var.innerHTML = inner;
                label_var.id = name;
                label_var.name = pic;
                //设置提示框
                label_var.setAttribute("class", "tooltips");
                label_var.setAttribute("tooltip-position","bottom");
                var spanObj = document.createElement('span');
                //把图片塞入提示框
                var picObj = document.createElement('img');
                picObj.src = pic;
                picObj.style.width = "120px";
                spanObj.append(picObj);
                label_var.appendChild(spanObj);
                //设置删除
                label_var.setAttribute("onclick", "deleteMakers(this)");
                document.getElementById('nav-stars').appendChild(label_var);
            }

        }

        left_star = forecast_stars[0];
        right_star = forecast_stars[1];
        $.ajax({
            url: '/MRAS/comparison/radar',
            type: "get",
            dataType: "json",
            data: {star1: left_star, star2: right_star},
            contentType: "application/json",
            traditional: false,
            success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                tags=[];
                star1_data_radar=[];
                star2_data_radar=[];

                var star1_data = data.star1;
                var star2_data = data.star2;
                canDraw_radar = data.canDrawRadar;

                if(canDraw_radar) {
                    $.each(star1_data, function (index, json) {
//                            alert(json.value);
                        //alert(json.attr);

                        tags.push(json.attr);
                        star1_data_radar.push(json.value);
                        console.log(json.value);
                    });
                    $.each(star2_data, function (index, json) {
                        star2_data_radar.push(json.value);
                    });
                }
                $(".chart").show();
                // alert(canDraw_radar);
                if (canDraw_radar){
                    //console.log("done");
                    draw_radar();
                }
               // window.location.hash = "#chart";
            }
        });
        $.ajax({
            url: '/MRAS/comparison/gross',
            type: "get",
            dataType: "json",
            data: {star1: left_star, star2: right_star},
            contentType: "application/json",
            traditional: false,
            success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                time=[];
                star1_movie_amount=[];
                star1_gross=[];
                star2_movie_amount=[];
                star2_gross=[];

                var star1_data = data.star1;
                var star2_data = data.star2;

                $.each(star1_data, function (index, json) {
                    time.push(json.attr);
                    star1_gross.push(json.v1);
                    star1_movie_amount.push(json.v2);
                });
                $.each(star2_data, function (index, json) {
                    star2_gross.push(json.v1);
                    star2_movie_amount.push(json.v2);
                });

                draw_gross();
            }
        });
    });



    /**
     * 清除目前所有标签
     */
    function returnToCompare() {
        window.location.href = "/MRAS//comparison/star";
    }
    /**
     * 跳转到预测界面
     */
    function forecastMovie(){
        if (forecast_stars != "") {
            sessionStorage.setItem("session_s", forecast_stars);
            sessionStorage.setItem("s_p", stars_pic);
        }if (forecast_stars == "") {
            sessionStorage.removeItem("session_s");
            sessionStorage.removeItem("s_p");
        }
        if (forecast_creators != ""){
            sessionStorage.setItem("session_c", forecast_creators);
            sessionStorage.setItem("c_p", creas_pic);
        }
        if (forecast_creators == ""){
            sessionStorage.removeItem("session_c");
            sessionStorage.removeItem("c_p");
        }
        if(forecast_genres!="")
            sessionStorage.setItem("session_g", forecast_genres);
        if(forecast_genres=="")
            sessionStorage.removeItem("session_g");
        window.location.href = "/MRAS//comparison/result";
    }

</script>
<script>

</script>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">
    <link rel="stylesheet" href="/css/awTooltip/style.css" media="screen" type="text/css"/>

    <title>Compare Stars</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/forecast.css" media="screen" rel="stylesheet" type="text/css">
    <%--图标css--%>
    <link rel="stylesheet" href="http://icono-49d6.kxcdn.com/icono.min.css">
</head>
<body onload="init()">

<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>
<div class="all" >
    <div class="aside">
        <%--预测栏--%>
        <div class="nav-forecast" style="max-width:360px;    margin-top: 0;">
            <label>Stars:</label>
            <div id="nav-stars">

            </div>
            <%--<label>Creators:</label>--%>
            <%--<div id="nav-creators">--%>
            <%--</div>--%>
            <%--<hr/>--%>
            <%--<label>Genres</label>--%>
            <%--<div id="nav-genres" class="nav-genres">--%>
            <%--<c:forEach var="genre" items="${genres}">--%>
            <%--<label id="${genre}" class="label_nature">${genre}</label>--%>
            <%--</c:forEach>--%>
            <%--</div>--%>

            <div class="nav-btn">
                <label onclick="forecastMovie()">Compare</label>
                <label onclick="returnToCompare() ">Return</label>

            </div>
            <hr/>
            <label style="width: 100%;height: 200px;">此处广告招租<br>
                详情请联系万先生<br>
                QQ：852805936</label>
        </div>
    </div>
    <div class="search">
        <%--图表从这里开始画--%>
        <div id="chart" class="chart" style="">

            <%--画雷达图--%>
            <div class="chart1">
                <div class="chart1_description" style="text-align: center">
                    <p class="chart1_hint" style="color: #333333; font-size: 28px">Abilities Radar Chart</p>
                </div>

                <div class="xhzb_wrap">
                    <div id="main1" class="my_main1" style="width:90%;height: 500px;margin-bottom: 50px;margin: 0 auto;"></div>
                </div>

                <div class="chart1_description">
                    <p id="chart1_hint">This radar chart is to show you the abilities comparison of the two stars.</p>
                </div>
            </div>


            <%--画票房对比图--%>
            <div class="chart2">
                <div class="chart2_description" style="text-align: center">
                    <p class="chart2_hint" style="color: #333333; font-size: 28px">Movie Amount and Box-office</p>
                </div>

                <div class="xhzb_wrap">
                    <div id="main2" class="my_main2"  style="width:90%;height: 500px;margin-bottom: 50px;margin: 0 auto;"></div>
                </div>

                <div class="chart2_description">
                    <p id="chart2_hint">This chart is to show you that the amount of movies the two stars starred in
                        per year, as well as the total box office of these movies.</p>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
