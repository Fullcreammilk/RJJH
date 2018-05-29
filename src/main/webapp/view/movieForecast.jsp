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

        $('.M-box').pagination({
            current:${nowPage},
            pageCount:${numOfPages},
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
                window.location.href = "/MRAS/forecast/maker?keyword=" + keyword + "&nationality=" + nationality + "&type=" + type+"&page="+page;
            }
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
                label_var.innerHTML = name;
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
                var spanObj = document.createElement('span');
                //把图片塞入提示框
                var picObj = document.createElement('img');
                picObj.src = pic;
                picObj.style.width = "120px";
                spanObj.append(picObj);
                label_var.appendChild(spanObj);
                //设置删除
                label_var.setAttribute("onclick", "deleteMakers(this)");
                document.getElementById('nav-creators').appendChild(label_var);
            }
        }
        if (sessionStorage.getItem("session_g") != null) {
            forecast_genres = sessionStorage.getItem("session_g").split(',');
            var i = 0;
            for (; i < forecast_genres.length; i++) {
                var gen = forecast_genres[i];
                document.getElementById(gen).setAttribute("class","label_choosed");
            }
        }
        //预测框中的流派标签
        $(".nav-genres label").click(function () {
            var gen = this.id;
            //如果该标签未选中，那么选中标签
            if(this.getAttribute("class")=="label_nature"){
                this.setAttribute("class","label_choosed");
                forecast_genres.push(gen);
            }
            //如果已经选中
            else if(this.getAttribute("class")=="label_choosed"){
                this.setAttribute("class","label_nature");
                remove(gen, forecast_genres);
            }
        });

        //设置点击标签改变底色，并去除其他标签的底色
        $(".nationality label").click(function () {
            nationality = this.id.replace("nat-", "");  //传到逻辑层
            //储存当前已经选好的人物
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
            window.location.href = "/MRAS/forecast/maker?keyword=" + keyword + "&nationality=" + nationality + "&type=" + type;
            $(this).addClass("tag_selected").siblings().removeClass("tag_selected");
        });
        $("#button_star").click(function () {
            type = "star";
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
            window.location.href = "/MRAS/forecast/maker?keyword=" + keyword + "&nationality=" + nationality + "&type=" + type;
            $("#star").css("display", "inline");
            $("#creator").css("display", "none");
        });

        $("#button_creator").click(function () {
            type = "creator";
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
            window.location.href = "/MRAS/forecast/maker?keyword=" + keyword + "&nationality=" + nationality  + "&type=" + type;
            $("#star").css("display", "none");
            $("#creator").css("display", "inline");
        });

        //初始化nationality底色
        nationality = GetQueryString("nationality");
        if (nationality == null) {
            nationality = "All";
        }
        $("#nat-" + nationality).addClass("tag_selected");

        // 初始化单选框
        type = GetQueryString("type");
        if (type == "star") {
            $("#star").css("display", "inline");
            $("#creator").css("display", "none");
        }
        else if (type == "creator") {
            $("#star").css("display", "none");
            $("#creator").css("display", "inline");
        }
        else if (type == null) {
            type = "star";
            $("#star").css("display", "inline");
            $("#creator").css("display", "none");
        }
        $(".name").click(function () {
            var thishref = this.href;
            this.href = thishref+"&type="+type;
        });
    });

    /**
     * 点击按钮添加该人物
     **/
    function addmakers(obj) {
        var name = obj.id;
        var pic = obj.name;

        if (type == "star") {
            //如果这个明星已经存在
            if(indexOf(name,forecast_stars)!=-1){
                alert("This maker has been selected.");
                return;
            }
            var label_var = document.createElement("label");
            label_var.innerHTML = name;
            label_var.id = name;
            label_var.name = pic;
            //储存名字和图片路径
            forecast_stars.push(name);
            stars_pic.push(pic+'/&/');
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
        } else if (type == "creator") {
            if(indexOf(name,forecast_creators)!=-1){
                alert("This maker has been selected.");
                return;
            }
            var label_var = document.createElement("label");
            label_var.innerHTML = name;
            label_var.id = name;
            label_var.name = pic;
            forecast_creators.push(name);
            creas_pic.push(pic+'/&/');
            //设置提示框
            label_var.setAttribute("class", "tooltips");
            label_var.setAttribute("tooltip-position", "bottom");
            var spanObj = document.createElement('span');
            //把图片塞入提示框
            var picObj = document.createElement('img');
            picObj.src = pic;
            picObj.style.width = "120px";
            spanObj.append(picObj);
            label_var.appendChild(spanObj);
            //设置删除
            label_var.setAttribute("onclick", "deleteMakers(this)");
            document.getElementById('nav-creators').appendChild(label_var);
        }

    }
    /**
     * 点击标签，可以删除该标签
     **/
    function deleteMakers(obj) {
        var name = obj.id;
        var pic = obj.name;
        if (obj.parentNode.id == 'nav-stars') {
            document.getElementById('nav-stars').removeChild(obj);
            remove(name, forecast_stars);
            remove(pic+'/&/',stars_pic);
        } else if (obj.parentNode.id == 'nav-creators') {
            document.getElementById('nav-creators').removeChild(obj);
            remove(name, forecast_creators);
            remove(pic+'/&/',creas_pic);
        }
    }
    //查找数组中的某个元素
    function indexOf(val, arr) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
    //移除该元素
    function remove(val, arr) {
        var index = indexOf(val, arr);
        if (index > -1) {
            arr.splice(index, 1);
        }
    }

    /**
     * 清除目前所有标签
     */
    function clearMakers() {
//        console.log(forecast_stars.length)
        var i = 0;
        var len = forecast_stars.length;
        for (; i < len; i++) {
            var all = document.getElementById('nav-stars');
            var del = all.childNodes;
//            console.log(del.length);
            var j = 1;
            for (; j < del.length; j++) {
                all.removeChild(del.item(j));
//                console.log(del.item(j));
            }
            forecast_stars.pop();
            stars_pic.pop()
        }
        i = 0;
        len = forecast_creators.length;
        for (; i < len; i++) {
            var all = document.getElementById('nav-creators');
            var del = all.childNodes;
            var j = 0;
            for (; j < del.length; j++) {
                all.removeChild(del.item(j));
            }
            forecast_creators.pop();
            creas_pic.pop();
        }
        i = 0;
        len = forecast_genres.length;
        for(; i < len; i++){
            forecast_genres.pop();
        }
        $(".nav-genres label").attr("class","label_nature");
        sessionStorage.removeItem("session_s");
        sessionStorage.removeItem("session_c");
        sessionStorage.removeItem("session_g");
        sessionStorage.removeItem("s_p");
        sessionStorage.removeItem("c_p");
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
        window.location.href = "/MRAS/forecast/result?genres=" + forecast_genres + "&stars=" + forecast_stars + "&creators=" + forecast_creators;
    }
    /**
     * 搜索明星
     */
    function searchstar(){
        keyword = $("#inp-stars").val();
        //储存当前已经选好的人物
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
        window.location.href = "/MRAS/forecast/maker?keyword=" + keyword + "&nationality=" + nationality  + "&type=" + type;
    }
</script>
<script>

</script>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">
    <link rel="stylesheet" href="/css/awTooltip/style.css" media="screen" type="text/css"/>

    <title>Statistics Forecast</title>

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
    <label>Creators:</label>
    <div id="nav-creators">
    </div>
    <hr/>
    <label>Genres</label>
    <div id="nav-genres" class="nav-genres">
        <c:forEach var="genre" items="${genres}">
            <label id="${genre}" class="label_nature">${genre}</label>
        </c:forEach>
    </div>

    <div class="nav-btn">
        <label onclick="forecastMovie()">Forecast</label>
        <label onclick="clearMakers() ">Clear</label>

    </div>
</div>
</div>
<div class="search">
<!--国籍栏-->
<div class="nationality">
    <c:forEach var="nationality" items="${nationalities}">
        <label id="nat-${nationality}">${nationality}</label>
    </c:forEach>
</div>
<%--流派栏--%>
<%--<div class="genre">--%>
    <%--<c:forEach var="genre" items="${genres}">--%>
        <%--<label id="gen-${genre}">${genre}</label>--%>
    <%--</c:forEach>--%>
<%--</div>--%>

<!--类型栏-->
<div class="sortWay">
    <div class="maker_search">
    <fieldset style="float: right;">
        <!--<legend>搜索：</legend>-->

        <div class="inp">
            <input id="inp-stars" name="search_text" size="22" maxlength="60" value=""
                   placeholder="Search stars" autocomplete="off">
        </div>
        <div class="inp-btn">
            <input type="button" value="Search" onclick="searchstar()">
        </div>

    </fieldset>
</div>
    <em class="sortWay_hint">Occupation:</em>
    <a class="checkbox_item" id="button_star">
        <span class="search_checkbox">
            <i class="search_checkbox_inner" id="star" style="display: inline"></i>
        </span>
        <em class="checkbox_inline">Star</em>
    </a>
    <a class="checkbox_item" id="button_creator">
        <span class="search_checkbox">
            <i class="search_checkbox_inner" id="creator"></i>
        </span>
        <em class="checkbox_inline">Creator</em>
    </a>
</div>

<!-- 电影栏 -->
<div class="maker_container">

    <c:forEach var="maker" items="${makers}">
        <div class="maker_wrapper" id="maker:${maker.name}">
            <div class="maker_wrapper-img">
                <img src="${maker.imgUrl}" alt="item" id="pic:${maker.name}">
            </div>
            <div class="info">
                <input type="button" id="${maker.name}" name="${maker.imgUrl}" class="plus_bt" onclick="addmakers(this)"/>
                <a class="name" href="/MRAS/maker/detail?makerName=${maker.name}">${maker.name}</a>
                <canvas id="myCanvas" width="500" height="500"></canvas>
            </div>
        </div>
    </c:forEach>
</div>
<!-- 分页栏 -->
<div class="M-box"></div>
</div>
</div>
</body>
</html>
