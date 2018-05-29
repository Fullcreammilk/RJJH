<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html style="height: 100%">
<head>
    <title>Charts</title>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="/css/chart.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/xgzb.css"/>

    <script>
        function initloading() {
            $("#loading").fadeOut();
            initBar();
        }
    </script>
</head>
<body style="height: 100%; margin: 0" onload="initloading()">
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>
<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>

<%--图表从这里开始画--%>
<div id="chart" class="chart" style="margin-top: 170px">

    <%--画盒状图--%>
    <div class="chart1">
        <div class="chart1_description" style="text-align: center">
            <p class="chart1_hint" style="color: #333333; font-size: 28px">Genre Radar Chart</p>
        </div>

        <div class="xhzb_wrap">
            <div id="tag_rating" class="my_main1" style="width: 130%"></div>
        </div>
    </div>


    <%--画柱状图--%>
    <div class="chart2">
        <div class="chart2_description">
            <p id="chart2_hint" style="padding-right: 30px;">
                This line chart is to show you the awards the star won per year.
            </p>
        </div>

        <div class="xhzb_wrap">
            <div id="tag_num" class="my_main2" style="width: 130%"></div>
        </div>
    </div>

    <%--画散点图--%>
    <div class="chart3">
        <div class="chart3_description">
            <p id="chart3_hint" style="padding-right: 30px;">
                This chart is to show you that the amount of movies the star starred in
                per year, as well as the total box office of these movies.
            </p>
        </div>

        <div class="xhzb_wrap">
            <div id="main3" class="my_main3" style="width: 130%"></div>
        </div>
    </div>

</div>

<%--<div id="tag_rating" style="top: 15%;height: 70%;width: 130%;"></div>--%>
<%--<div id="tag_num" style="top: 75%;height:70%;width:130%;"></div>--%>


<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript"
        src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript">
    var dom = document.getElementById("tag_rating");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var data = [];
    <c:forEach var="para" items="${boxPlot}">
    var d = [];
    <c:forEach var="data" items="${para.datas}">
    d.push(${data});
    </c:forEach>
    data.push(d)
    </c:forEach>

    data = echarts.dataTool.prepareBoxplotData(data);

    var names = [];
    <c:forEach var="para" items="${boxPlot}">
    names.push("${para.xAxis}");
    </c:forEach>

    option = {
        title: [
            {
                text: 'Tag and Rating Value',
                left: 'center',
                top: '90%',
                textStyle: {
                    fontSize: 24
                }
            },
            {
                text: 'upper: Q3 + 1.5 * IRQ \nlower: Q1 - 1.5 * IRQ',
                borderColor: '#999',
                borderWidth: 1,
                textStyle: {
                    fontSize: 14
                },
                left: '10%',
                top: '90%'
            }
        ],
        tooltip: {
            trigger: 'item',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: {
            type: 'category',
            data: names,
            boundaryGap: true,
            nameGap: 30,
            splitArea: {
                show: false
            },
//            axisLabel: {
//                formatter: ['Drama','Action','Romance','Adventure','Music']
//            },
            splitLine: {
                show: false
            }
        },
        yAxis: {
            max: 10,
            min: 0,
            type: 'value',
            name: 'RatingValue',
            splitArea: {
                show: true
            }
        },
        series: [
            {
                name: 'boxplot',
                type: 'boxplot',
                data: data.boxData,
                tooltip: {
                    formatter: function (param) {
                        return [
                            'Experiment ' + param.name + ': ',
                            'upper: ' + param.data[4].toFixed(2),
                            'Q3: ' + param.data[3].toFixed(2),
                            'median: ' + param.data[2].toFixed(2),
                            'Q1: ' + param.data[1].toFixed(2),
                            'lower: ' + param.data[0].toFixed(2)
                        ].join('<br/>')
                    }
                }
            },
            {
                name: 'outlier',
                type: 'scatter',
                data: data.outliers,
                tooltip: {
                    formatter: function (param) {
                        return [
                            "outlier",
                            param.name + ":" + param.data[1],
                        ].join('<br/>')
                    }
                }
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>

<script type="text/javascript">

    var dom = document.getElementById("tag_num");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    // 基于准备好的dom，初始化echarts图表

    var axisData = [];
    <c:forEach items="${tag_num}" var="c">
    axisData.push("${c.attr}");
    </c:forEach>

    var value = [];
    <c:forEach items="${tag_num}" var="c">
    value.push(${c.value});
    </c:forEach>


    option = {
        backgroundColor: "white",
        color: ['#913941'],
        title: {
            x: 'center',
            y: '90%',
            text: 'Tag Number',
            textStyle: {
                fontSize: 24,
                color: '#5c5f60'
            }
        },
        tooltip: {
            trigger: 'axis',
            showDelay: 0,             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms

        },
        calculable: true,
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: axisData,
                axisLine: {
                    lineStyle: {
                        color: '#5c5f60'
                    }
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                axisLine: {
                    lineStyle: {
                        color: '#5c5f60'
                    }
                }
            }
        ],
        series: [
            {
                name: 'Number',
                type: 'bar',
                //itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: value
            }

        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>

<script type="text/javascript">
    var data = [];
    <c:forEach var="item" items="${ratingAndGross}">
    var temp = [${item.attr}, ${item.value}];
    data.push(temp);
    </c:forEach>

    var myChart = echarts.init(document.getElementById('main3'));
    var dataAll = [
        [10.0, 8.04],
        [8.0, 6.95],
        [13.0, 7.58],
        [9.0, 8.81],
        [11.0, 8.33],
        [14.0, 9.96],
        [6.1, 7.24],
        [4.0, 4.26],
        [12.0, 10.84],
        [7.0, 4.82],
        [5.0, 5.68]
    ];

    option = {
        color: ['#23c2aa', '#15a08b', '#0d6457'],
        grid: {
            left: 80,
            right: 40,
            bottom: 100
        },
        tooltip: {
            formatter: '{a}: ({c})',
            // axisPointer: {
            //     show: true,
            //     type: 'cross',
            //     lineStyle: {
            //         type: 'dashed',
            //         width: 1
            //     }
            // }
        },
        xAxis: [
            {gridIndex: 0, min: 4, max: 10}

        ],
        yAxis: [
            {gridIndex: 0, min: 0, max: 1000000000},

        ],
        dataZoom: {
            left: 20,
            yAxisIndex: 0,
            fillerColor: 'rgba(126, 224, 209, 0.2)',
            borderColor: 'rgba(126, 224, 209, 0.75)',
            handleStyle: {
                color: '#23c2aa'
            }
        },
        series: [
            {
                name: 'value',
                type: 'scatter',
                xAxisIndex: 0,
                yAxisIndex: 0,
                data: data
            }
        ]
    };
    myChart.setOption(option);
</script>

</body>
</html>