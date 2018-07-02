<%--
  Created by hyx.
  User: hyx
  Date: 2017/5/16
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery.pagination.js"></script>

<script language="JavaScript">

    function initloading() {
        $("#loading").fadeOut();
        initBar();
    }
    var movieTag = "All";
    var sortWay = "Score";
    var page = 1;

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
        document.addEventListener("error", function (e) { var elem = e.target; if (elem.tagName.toLowerCase() == "img") { elem.src = "/graphics/error.jpg"; } }, true);

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
                window.location = "/MRAS/search/movSch?tag="+movieTag+"&sortWay="+sortWay+"&page="+page;
            }
        });

        //设置点击标签改变底色，并去除其他标签的底色
        $(".tag label").click(function () {
            movieTag = this.id;
            window.location.href = "/MRAS/search/movSch?tag="+movieTag+"&sortWay="+sortWay+"&page="+page;
            $(this).addClass("tag_selected").siblings().removeClass("tag_selected");
        });

        $("#button_score").click(function () {
            sortWay = "Score";
            window.location.href = "/MRAS/search/movSch?tag="+movieTag+"&sortWay="+sortWay+"&page="+page;
            $("#sort_by_score").css("display", "inline");
            $("#sort_by_time").css("display", "none");
        });

        $("#button_time").click(function () {
            sortWay = "Time";
            window.location.href = "/MRAS/search/movSch?tag="+movieTag+"&sortWay="+sortWay+"&page="+page;
            $("#sort_by_score").css("display", "none");
            $("#sort_by_time").css("display", "inline");
        });

        //初始化搜索框
//        var keyword = GetQueryString("keyword");
//        $("#inp-query").val();

        //初始化tag底色
        movieTag = GetQueryString("tag");
        if(movieTag == null){
            movieTag = "All";
        }
        $("#" + movieTag).addClass("tag_selected");

        // 初始化单选框
        sortWay = GetQueryString("sortWay");
        if(sortWay == "Score"){
            $("#sort_by_score").css("display", "inline");
            $("#sort_by_time").css("display", "none");
        }
        else if(sortWay == "Time"){
            $("#sort_by_score").css("display", "none");
            $("#sort_by_time").css("display", "inline");
        }
        else if(sortWay == null){
            sortWay = "Score";
            $("#sort_by_score").css("display", "inline");
            $("#sort_by_time").css("display", "none");
        }
    });

</script>

<head>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">
    <title>Movie_Home</title>
    <link href="/css/movieSearch.css" rel="stylesheet">

</head>
<body onload="initloading()">
<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>

<!--标签栏-->
<div class="tag">
    <c:forEach var="tag" items="${tags}" >
        <label id="${tag}">${tag}</label>
    </c:forEach>
</div>

<!--排序栏-->
<div class="sortWay">
    <em class="sortWay_hint">Sort way:</em>
    <a class="checkbox_item" id="button_score">
        <span class="search_checkbox">
            <i class="search_checkbox_inner" id="sort_by_score" style="display: inline"></i>
        </span>
        <em class="checkbox_inline">Score</em>
    </a>
    <a class="checkbox_item" id="button_time">
        <span class="search_checkbox">
            <i class="search_checkbox_inner" id="sort_by_time"></i>
        </span>
        <em class="checkbox_inline">Time</em>
    </a>
</div>

<!-- 电影栏 -->
<div class="movie_container">
    <c:forEach var="movie" items="${movies}" >
        <div class="movie_wrapper">
            <a class="name" href="/MRAS/movie/detail?movieName=${movie.name}" style="font-size: 1.2em">
            <c:if test="${movie.imgUrl!=null}">
            <img src = "${movie.imgUrl}" style="width: 182px">
            </c:if>
            <c:if test="${movie.imgUrl==null}">
                <img src = "/graphics/movieInfo/nopic.png" style="width: 182px">
            </c:if>

            <div class="info" style="overflow: hidden;height: 5.2em;
    text-overflow:ellipsis;
    position:relative; " >
                ${movie.name}
                <p>${movie.score}</p>
            </div>
            </a>
        </div>
    </c:forEach>
</div>

<!-- 分页栏 -->
<div class="M-box"></div>

</body>
</html>
