<%@ page import="javax.naming.Context" %>
<%@ page import="org.omg.CORBA.Request" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/5/17
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>navigationbar</title>

    <link rel="icon" href="/graphics/logo.png">

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <%--<link rel="stylesheet" href="/css/autoComplete/normalize.css">--%>

    <link rel="stylesheet" href="/css/autoComplete/main.css">
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="/js/jquery.bxslider.js"></script>
    <script src="/js/echarts.js"></script>
    <!-- Custom styles for this template -->
    <link href="/css/movieHomepage.css" rel="stylesheet">

    <script type="text/javascript">
        var datalist = [];
        var namelist = new Array();
        $(function () {
            $('#inp-query').keyup(function(event) {
            //    处理文本框中的键盘事件
            //    如果输入字母、退格键、删除键，则将信息发送到服务器
                var myEvent = event || window.event;
                var keyCode = myEvent.keyCode;
                if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46) {
                    //     1.首先获取文本框内容
                    var wordText = $('#inp-query').val();
                    if(wordText!=""){
                        $.ajax({
                            type: "POST",   //http请求方式为POST
                            url: '/MRAS/search/help',  //请求接口的地址
                            data: {keyword:wordText, type:"movie"},
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
            $('#inp-query').autocompleter({
                limit: 5,
                source:datalist
            });
        });
        function initBar() {
            if (${sessionScope.username!=null}) {
                $("#guildLabel").append('<a href="/MRAS/home" id="top_fir" class="nav-home" ref="nofollow">Home</a>')
                $("#guildLabel").append('<a href="/MRAS/favourite/all" id="top_sec" class="nav-login" ref="nofollow">${sessionScope.username}</a>')
                $("#guildLabel").append('<a id="top_thr" class="nav-register" ref="nofollow" onclick="logOut()">Logout</a>')
            }
            else {
                $("#guildLabel").append('<a href="/MRAS/home" id="top_fir" class="nav-home" ref="nofollow">Home</a>')
                $("#guildLabel").append('<a href="/MRAS/login" id="top_sec" class="nav-login" ref="nofollow" onclick="leave()">Login</a>')
                $("#guildLabel").append('<a href="/MRAS/login#toregister" id="top_thr" class="nav-register" ref="nofollow" onclick="leave()">Register</a>')
            }

        }
        function leave(){
            var url = window.location.href;
            sessionStorage.setItem("nowUrl", url);
        }
        function logOut() {
            if(window.location.href == "http://localhost:8080/MRAS/favourite/all"){
                window.location.href = "/MRAS/home";
            }else {
                    window.location.href = window.location.href;
            }

            $.ajax({
                type: "POST",   //http请求方式为POST
                url: '/MRAS/user/logout',  //请求接口的地址
                data: {},
                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                cache: false,   //不用缓存
                async: false,
                success: function () {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                }
            });
        }

        //进行搜索
        function search() {
            window.location.href = "/MRAS/search/movSch?keywords=" + $("#inp-query").val();
        }
        function chooseThis(obj) {
            var name = obj.id;
            var p = document.getElementById(name);
            p.setAttribute("class","active");
        }
    </script>
</head>
<body onload="initBar()">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div id="global-nav" class="global-nav">
        <div id="guildLabel" class="top-nav-info">

        </div>
        <div class="nav-search">
            <fieldset>
                <!--<legend>搜索：</legend>-->
                <div class="inp">
                    <input id="inp-query" name="search_text" size="22" maxlength="60" value=""
                           placeholder="Search movie from now on !" autocomplete="off">
                </div>
                <div class="inp-btn">
                    <input type="button" value="Search" onclick="search()">
                </div>
            </fieldset>
        </div>
        <div class="top-nav-welcome">
            <img src="/graphics/movieHomepage/logo.png">
        </div>
    </div>

    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/MRAS/home">MRAS</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/MRAS/search/movSch" onclick="chooseThis(this)" id="nav-cs">Movie</a></li>
                <li><a href="/MRAS/maker/maker" onclick="chooseThis(this)" id="nav-ms">Maker</a></li>
                <li><a href="/MRAS/company/all"onclick="chooseThis(this);" id="nav-mg">Company</a></li>
                <%--<li><a href="/MRAS/chart/all"onclick="chooseThis(this);" id="nav-sc">Statistics</a></li>--%>
                <li><a href="/MRAS/forecast/maker"onclick="chooseThis(this);" id="nav-mf">Forecast</a></li>
                <li><a href="/MRAS//comparison/star"onclick="chooseThis(this);" id="nav-scs">Stars Comparison</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<%--<script src="/js/autoComplete/jquery.min.js"></script>--%>

<script src="/js/autoComplete/jquery.autocompleter.js"></script>

<script src="/js/autoComplete/main.js"></script>
</body>
</html>
