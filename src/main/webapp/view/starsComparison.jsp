<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/7
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/echarts.js"></script>

<script language="JavaScript">
    //左边的明星名字
    var left_star;
    //右边的明星名字
    var right_star;
    $(function () {
        $(".chart").hide();
        $(function () {
            var r_datalist = [];
            $('#r-inp-stars').keyup(function (event) {
                var myEvent = event || window.event;
                var keyCode = myEvent.keyCode;
                if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46) {
                    var wordText = $('#r-inp-stars').val();

                    keywordHelp(wordText, r_datalist);
                }
            });
            $('#r-inp-stars').autocompleter({
                limit: 5,
                source: r_datalist
            });
            var l_datalist = [];
            $('#l-inp-stars').keyup(function (event) {
                var myEvent = event || window.event;
                var keyCode = myEvent.keyCode;
                if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46) {
                    var wordText = $('#l-inp-stars').val();
                    keywordHelp(wordText, l_datalist);
                }
            });
            $('#l-inp-stars').autocompleter({
                limit: 5,
                source: l_datalist
            });

        });
    });
    function initloading() {
        $("#loading").fadeOut();
        initBar();
        $("#chart").hide();
        $("#introleft").attr("style","margin-top:40%;");
        $("#introright").attr("style","margin-top:40%;");
    }
    function goLeft() {
        $("#go_btn").hide();
        initStars("", "l");
        $("#sideleft").attr("style","position:fixed");
        $("#chart").hide();
        $("#introleft").attr("style","margin-top:50%;");
        $(".page-left").show();
    }
    function goRight() {
        $("#go_btn").hide();
        initStars("", "r");
        $("#sideright").attr("style","position:fixed");
        $("#chart").hide();
        $("#introright").attr("style","margin-top:50%;");
        $(".page-right").show();
    }
    function backCenter() {
        $("#go_btn").show();
        $("#sideright").attr("style","position:relative");
        $("#sideleft").attr("style","position:relative");
        $("#introright").attr("style","margin-top:40%;");
        $("#introleft").attr("style","margin-top:40%;");
        $(".page-left").hide();
        $(".page-right").hide();
//        $("#chart").show();
    }
    /**
     *  初始化明星列表
     * */
    function initStars(val, dir) {
        $.ajax({
            type: "POST",   //http请求方式为POST
            url: '/MRAS/comparison/list',  //请求接口的地址
            data: {keyword: val},
            dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
            cache: false,   //不用缓存
            async: false,
            success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                clearStar();
                var namelist = new Array();
                var piclist = new Array();
                for (var i = 0; i < data.names.length; i++) {
                    namelist[i] = data.names[i];
                    piclist[i] = data.imgUrls[i];
                    var div_var = document.createElement("div");
                    div_var.setAttribute("class", "info");
                    var img_var = document.createElement("img");
                    img_var.src = piclist[i];
//                    img_var.setAttribute("onclick","gotoStar('"+namelist[i]+"')");
                    img_var.setAttribute("style","cursor:pointer;");
//                    img_var.href="/MRAS/maker/detail?makerName="+namelist[i]+"&type=star";
//                    img_var.setAttribute("onclick","/MRAS/maker/detail?makerName="+namelist[i]+"&type=star");
                    var a_var = document.createElement("a");
                    a_var.innerHTML = namelist[i];
                    div_var.id = namelist[i];
                    div_var.name = piclist[i];
                    if (dir == "r") {
                        div_var.setAttribute("onclick", "setRightStar(this)");
                        document.getElementById('right-stars').appendChild(div_var);
                    } else if (dir == "l") {
                        div_var.setAttribute("onclick", "setLeftStar(this)");
                        document.getElementById('left-stars').appendChild(div_var);
                    }
                    div_var.appendChild(img_var);
                    div_var.appendChild(a_var);
                }
            },
        })
    }
    /**
     * 搜索右边的明星
     **/
    function searchRightStar() {
        var keyword = $("#r-inp-stars").val();
        initStars(keyword, "r");
    }
    function searchLeftStar() {
        var keyword = $("#l-inp-stars").val();
        initStars(keyword, "l");
    }
    /**
     * 清空列表
     */
    function clearStar() {
        document.getElementById('right-stars').innerHTML = "";
        document.getElementById('left-stars').innerHTML = "";
    }

    function setRightStar(obj) {
        $.when(function () {
        document.getElementById("r-name").innerHTML = obj.id;
        document.getElementById("r-pic").src = obj.name;
        document.getElementById("r-more").innerHTML = "";
        right_star = obj.id;}).then(backCenter());
    }
    function setLeftStar(obj) {
        $.when(function () {
        document.getElementById("l-name").innerHTML = obj.id;
        document.getElementById("l-pic").src = obj.name;
        document.getElementById("l-more").innerHTML = "";
        left_star = obj.id;}).then(backCenter());
    }
    function gotoStar(val) {
//        alert(val);
        window.location.href="/MRAS/maker/detail?makerName="+val+"&type=star";
    }
    /**
     * 关键词自动补全
     * @param val 关键词
     * @param arr 补全列表
     */
    function keywordHelp(val, arr) {
        if (val != "") {
            $.ajax({
                type: "POST",   //http请求方式为POST
                url: '/MRAS/search/help',  //请求接口的地址
                data: {keyword: val, type: "star"},
                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                cache: false,   //不用缓存
                async: false,
                success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                    var namelist = new Array();
                    for (var i = 0; i < arr.length; i++) {
                        arr.pop();
                    }
                    for (var i = 0; i < data.list.length; i++) {
                        namelist[i] = data.list[i];
                        arr[i] = {'label': namelist[i]};
                    }
                    return arr;
                }
            });
        }
    }

    // go按钮监听
    var canDraw_radar = 0;
    var tags = [];
    var star1_data_radar = [];
    var star2_data_radar = [];
    var time = [];
    var star1_movie_amount = [];
    var star1_gross = [];
    var star2_movie_amount = [];
    var star2_gross = [];

    function compare() {
        if(left_star==right_star){
            alert("please choose two different stars");
            return;
        }
        if ((left_star.size != 0) && (right_star.size != 0)) {
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
                    window.location.hash = "#chart";
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
//            $(".chart").show();
//            alert(canDraw_radar);
//            if (canDraw_radar){
//                draw_radar();
//            }
//            draw_gross();
        }
        else {
            alert("Please complete choosing stars.");
        }
    }

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
</script>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">
    <title>Stars Comparison</title>

    <link rel="stylesheet" type="text/css" href="/css/splitLayout/demo.css"/>

    <link rel="stylesheet" type="text/css" href="/css/splitLayout/component.css"/>
    <link rel="stylesheet" type="text/css" href="/css/hover.css"/>
    <link rel="stylesheet" type="text/css" href="/css/comparison.css"/>
    <link rel="stylesheet" href="/css/xgzb.css"/>
    <script src="/js/splitLayout/modernizr.custom.js"></script>

</head>
<body onload="initloading()">
<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>

<div style="margin-top: 116px;">
    <div class="main_container">
        <div id="splitlayout" class="splitlayout">
            <a onclick="compare()" id="go_btn" class="button buzz-out">GO!</a>
            <div class="intro">
                <div class="side side-left" id="sideleft">
                    <div id="introleft" class="intro-content" onclick="goLeft()">
                        <div class="profile"><img id="l-pic" src="/graphics/forecast/icon3.png" alt="profile1"></div>
                        <h1><span id="l-name">Click Here</span><span id="l-more">to select a star</span></h1>
                    </div>
                    <div class="overlay"></div>
                </div>
                <div class="side side-right" id="sideright">
                    <div id="introright" class="intro-content" onclick="goRight()">
                        <div class="profile"><img id="r-pic" src="/graphics/forecast/icon3.png" alt="profile2"></div>
                        <h1><span id="r-name">Click Here</span><span id="r-more">to select another star</span></h1>
                    </div>
                    <div class="overlay"></div>
                </div>
            </div><!-- /intro -->
            <div class="page page-right">
                <div class="page-inner">
                    <section>
                        <%--<h2>Web Development</h2>--%>
                        <div class="maker_search">
                            <fieldset>
                                <!--<legend>搜索：</legend>-->
                                <div class="inp">
                                    <input id="r-inp-stars" name="search_text" size="22" maxlength="60" value=""
                                           placeholder="Search stars" autocomplete="off">
                                </div>
                                <div class="inp-btn">
                                    <input type="button" value="Search" onclick="searchRightStar()">
                                </div>
                            </fieldset>
                        </div>
                        <div id="right-stars" class="star-pages right-star-pages" style="width:100%;">

                        </div>
                    </section>
                </div><!-- /page-inner -->
            </div><!-- /page-right -->
            <div class="page page-left">
                <div class="page-inner">
                    <section>
                        <div class="maker_search">
                            <fieldset>
                                <!--<legend>搜索：</legend>-->
                                <div class="inp">
                                    <input id="l-inp-stars" name="search_text" size="22" maxlength="60" value=""
                                           placeholder="Search stars" autocomplete="off">
                                </div>
                                <div class="inp-btn">
                                    <input type="button" value="Search" onclick="searchLeftStar()">
                                </div>
                            </fieldset>
                        </div>
                        <div id="left-stars" class="star-pages left-star-pages" style="width:100%;">

                        </div>
                    </section>
                </div><!-- /page-inner -->
            </div><!-- /pageleft -->
            <%--<a href="#" class="back back-right" title="back to intro" onclick="backCenter()">→</a>--%>
            <%--<a href="#" class="back back-left" title="back to intro" onclick="backCenter()">←</a>--%>
        </div><!-- /splitlayout -->
    </div><!-- /container -->
    <%--图表从这里开始画--%>
    <div id="chart" class="chart" style="position: absolute;
    z-index: 500;margin-top:-20%;">

            <%--画雷达图--%>
            <div class="chart1">
                <div class="chart1_description" style="text-align: center">
                    <p class="chart1_hint" style="color: #333333; font-size: 28px">Abilities Radar Chart</p>
                </div>

                <div class="xhzb_wrap">
                    <div id="main1" class="my_main1"></div>
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
                <div id="main2" class="my_main2"></div>
            </div>

            <div class="chart2_description">
                <p id="chart2_hint">This chart is to show you that the amount of movies the two stars starred in
                    per year, as well as the total box office of these movies.</p>
            </div>
        </div>

    </div>
</div>

<script src="/js/splitLayout/classie.js"></script>
<script src="/js/splitLayout/cbpSplitLayout.js"></script>

</body>
</html>
