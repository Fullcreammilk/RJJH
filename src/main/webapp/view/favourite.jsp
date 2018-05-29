<%--
  Created by IntelliJ IDEA.
  User: hyx
  Date: 2017/6/8
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>favourite</title>
    <link rel="icon" href="/graphics/logo.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="msapplication-tap-highlight" content="no"/>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>

    <script src='/js/jquery.js'></script>
    <script type="text/javascript" src="/js/lib/jquery.min.js"></script>

    <script type="text/javascript">
        function initloading() {
            $("#loading").fadeOut();
            initBar();
        }

        var isMovie = 1;
        var isStar = 0;
        var isCreator = 0;

        function to_myMovie() {
            isMovie = 1;
            isStar = 0;
            isCreator = 0;
            $("#myMovie_li").css("background", "#00b5b5");
            $("#myStar_li").css("background", "#232323");
            $("#myCreator_li").css("background", "#232323");
            $("#myMovie_link").css("color", "#FFFFFF");
            $("#myStar_link").css("color", "#cbc4c5");
            $("#myCreator_link").css("color", "#cbc4c5");
            $(".movie_container").show();
            $(".star_container").hide();
            $(".creator_container").hide();
            $(".checkbox").hide();
            $(".confirm").hide();
            $(".cancel").hide();
        }

        function to_myStar() {
            isMovie = 0;
            isStar = 1;
            isCreator = 0;
            $("#myMovie_li").css("background", "#232323");
            $("#myStar_li").css("background", "#00b5b5");
            $("#myCreator_li").css("background", "#232323");
            $("#myMovie_link").css("color", "#cbc4c5");
            $("#myStar_link").css("color", "#FFFFFF");
            $("#myCreator_link").css("color", "#cbc4c5");
            $(".movie_container").hide();
            $(".star_container").show();
            $(".creator_container").hide();
            $(".checkbox").hide();
            $(".confirm").hide();
            $(".cancel").hide();
        }

        function to_myCreator() {
            isMovie = 0;
            isStar = 0;
            isCreator = 1;
            $("#myMovie_li").css("background", "#232323");
            $("#myStar_li").css("background", "#232323");
            $("#myCreator_li").css("background", "#00b5b5");
            $("#myMovie_link").css("color", "#cbc4c5");
            $("#myStar_link").css("color", "#cbc4c5");
            $("#myCreator_link").css("color", "#FFFFFF");
            $(".movie_container").hide();
            $(".star_container").hide();
            $(".creator_container").show();
            $(".checkbox").hide();
            $(".confirm").hide();
            $(".cancel").hide();
        }

        function movie_mouseIn() {
            $("#myMovie_link").css("color", "#FFFFFF");
        }

        function movie_mouseOut() {
            if (!isMovie) {
                $("#myMovie_link").css("color", "#cbc4c5");
            }
        }

        function star_mouseIn() {
            $("#myStar_link").css("color", "#FFFFFF");
        }

        function star_mouseOut() {
            if (!isStar) {
                $("#myStar_link").css("color", "#cbc4c5");
            }
        }

        function creator_mouseIn() {
            $("#myCreator_link").css("color", "#FFFFFF");
        }

        function creator_mouseOut() {
            if (!isCreator) {
                $("#myCreator_link").css("color", "#cbc4c5");
            }
        }

        // 删除收藏方法
        function delete_collections() {
            if (isMovie) {
                $(".movie_wrapper .checkbox").show();
                $(".movie_container .confirm").show();
                $(".movie_container .cancel").show();
            }
            else if (isStar) {
                $(".star_wrapper .checkbox").show();
                $(".star_container .confirm").show();
                $(".star_container .cancel").show();
            }
            else {
                $(".creator_wrapper .checkbox").show();
                $(".creator_container .confirm").show();
                $(".creator_container .cancel").show();
            }
        }

        // 确认删除方法
        function confirm_delete() {
            var msg = "Are you sure to delete?";
            if (confirm(msg) == true) {
                var code_Values = document.getElementsByName("check_value");
//                console.log(code_Values.length+":1");
                if (isMovie) {

                    //alert(code_Values.length);
//                    var code_Values = document.all['check_value'];
                    for (var i = 0; i < code_Values.length; i++) {
//                        console.log(code_Values.length+":"+i);
                        if (code_Values[i].checked == true) {
//                            console.log(code_Values.length+":"+i);
                            var name = code_Values[i].id;
                            $.ajax({
                                type: "POST",   //http请求方式为POST
                                url: '/MRAS/favourite/delete',  //请求接口的地址
                                data: {userName: "${sessionScope.username}", name: name, type: "movie"},
                                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                                cache: false,   //不用缓存
                                async: false,
                                error: function () {
                                    alert("Error!");
                                }
                            })
                        }
                    }
                }
                else if (isStar) {
//                    var code_Values = document.all['check_value'];
                    for (var i = 0; i < code_Values.length; i++) {
                        if (code_Values[i].checked == true) {
                            var name = code_Values[i].id;
                            $.ajax({
                                type: "POST",   //http请求方式为POST
                                url: '/MRAS/favourite/delete',  //请求接口的地址
                                data: {userName: "${sessionScope.username}", name: name, type: "star"},
                                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                                cache: false,   //不用缓存
                                async: false,
                                error: function () {
                                    alert("Error!");
                                }
                            })
                        }
                    }
                }
                else if (isCreator) {
                    //var code_Values = document.all['check_value'];
                    //var name = code_Values[i].id;
                    //alert(code_Values.length);

                    for (var i = 0; i < code_Values.length; i++) {
                        if (code_Values[i].checked == true) {
                            var name = code_Values[i].id;
                            $.ajax({
                                type: "POST",   //http请求方式为POST
                                url: '/MRAS/favourite/delete',  //请求接口的地址
                                data: {userName: "${sessionScope.username}", name: name, type: "creator"},
                                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                                cache: false,   //不用缓存
                                async: false,
                                error: function () {
                                    alert("Error!");
                                }
                            })
                        }
                    }
                }
                uncheckAll();
                location.reload(true);
            }
        }

        // 取消删除方法
        function cancel_delete() {
            $(".confirm").hide();
            $(".cancel").hide();
            $(".checkbox").hide();
            uncheckAll();
        }

        // 取消全部选中
        function uncheckAll() {
            var code_Values = document.all['check_value'];
            if (code_Values.length) {
                for (var i = 0; i < code_Values.length; i++) {
                    code_Values[i].checked = false;
                }
            } else {
                code_Values.checked = false;
            }
        }

        // 初始化方法
        $(function () {
            <%--alert(${movie.size()});--%>
            $(".checkbox").hide();
            $(".confirm").hide();
            $(".cancel").hide();
            to_myMovie();
        })
    </script>

    <link rel="stylesheet" href="/css/bamboo.css">


</head>

<body onload="initloading()">

<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>

<nav id="main-nav" class="navigation overflow">
    <ul style="margin-top: 50px">
        <li id="myMovie_li"><a id="myMovie_link" onclick="to_myMovie()" onmousemove="movie_mouseIn()"
                               onmouseleave="movie_mouseOut()">My movies</a></li>
        <li id="myStar_li"><a id="myStar_link" onclick="to_myStar()" onmousemove="star_mouseIn()"
                              onmouseleave="star_mouseOut()">My stars</a></li>
        <li id="myCreator_li"><a id="myCreator_link" onclick="to_myCreator()" onmousemove="creator_mouseIn()"
                                 onmouseleave="creator_mouseOut()">My creators</a></li>
    </ul>
</nav>

<div id="container">
    <!-- 收藏的电影
     =================================================
    -->
    <div class="movie_container">
        <c:if test="${!empty movie}">
            <div class="delete_add">
                <a class="add" href="/MRAS/search/movSch">add</a>
                <a class="delete" onclick="delete_collections()">delete</a>
                <a class="confirm" onclick="confirm_delete()">confirm</a>
                <a class="cancel" onclick="cancel_delete()">cancel</a>
            </div>

            <div class="inner">
                <c:forEach var="movie" items="${movie}">
                    <div class="movie_wrapper">
                        <a class="name" href="/MRAS/movie/detail?movieName=${movie.name}"
                           style="font-size: 1.2em">
                            <img src="${movie.imgUrl}">
                            <div class="info" style="overflow: hidden;height: 4.4em;
    text-overflow:ellipsis;
    position:relative; ">
                                    ${movie.name}
                                <p>${movie.score}</p>
                            </div>
                        </a>

                        <!-- 多选框 -->
                        <div class="checkbox">
                            <input type="checkbox" name="check_value" id="${movie.name}"
                                   class="regular-checkbox big-checkbox"/>
                            <label for="${movie.name}"></label>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty movie}">
            <p class="none_collections">
                You haven't had favourite movies by now.Click <a href="/MRAS/search/movSch">here</a> to discover your
                favourite movies.
            </p>
        </c:if>
    </div>

    <!-- 收藏的明星
     =================================================
    -->
    <div class="star_container">
        <c:if test="${!empty star}">
            <div class="delete_add">
                <a class="add" href="/MRAS/maker/maker?keyword=&nationality=All&type=star">add</a>
                <a class="delete" onclick="delete_collections()">delete</a>
                <a class="confirm" onclick="confirm_delete()">confirm</a>
                <a class="cancel" onclick="cancel_delete()">cancel</a>
            </div>

            <div class="inner">
                <c:forEach var="star" items="${star}">
                    <div class="star_wrapper">
                        <a class="name" href="/MRAS/maker/detail?makerName=${star.name}&type=star"
                           style="font-size: 1.2em">
                            <img src="${star.imgUrl}">
                            <div class="info" style="overflow: hidden;height: 4.4em;
    text-overflow:ellipsis;
    position:relative; text-align: center">

                                    ${star.name}

                            </div>
                        </a>
                        <!-- 多选框 -->
                        <div class="checkbox">
                            <input type="checkbox" name="check_value" id="${star.name}"
                                   class="regular-checkbox big-checkbox"/>
                            <label for="${star.name}"></label>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty star}">
            <p class="none_collections">
                You haven't had favourite stars by now.Click <a
                    href="/MRAS/maker/maker?keyword=&nationality=All&type=star">here</a> to discover your favourite
                stars.
            </p>
        </c:if>
    </div>

    <!-- 收藏的导演
     =================================================
    -->
    <div class="creator_container">
        <c:if test="${!empty creator}">
            <div class="delete_add">
                <a class="add" href="/MRAS/maker/maker?keyword=&nationality=All&type=creator">add</a>
                <a class="delete" onclick="delete_collections()">delete</a>
                <a class="confirm" onclick="confirm_delete()">confirm</a>
                <a class="cancel" onclick="cancel_delete()">cancel</a>
            </div>

            <div class="inner">

                <c:forEach var="creator" items="${creator}">
                    <div class="creator_wrapper">
                        <a class="name" href="/MRAS/maker/detail?makerName=${creator.name}&type=creator"
                           style="font-size: 1.2em">
                            <img src="${creator.imgUrl}">
                            <div class="info" style="overflow: hidden;height: 4.4em;
    text-overflow:ellipsis;
    position:relative; text-align: center">
                                    ${creator.name}
                            </div>
                        </a>
                        <!-- 多选框 -->
                        <div class="checkbox">
                            <input type="checkbox" name="check_value" id="${creator.name}"
                                   class="regular-checkbox big-checkbox"/>
                            <label for="${creator.name}"></label>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty creator}">
            <p class="none_collections">
                You haven't had favourite creators by now.Click <a
                    href="/MRAS/maker/maker?keyword=&nationality=All&type=creator">here</a> to discover your favourite
                creators.
            </p>
        </c:if>
    </div>
</div>

</body>
</html>
