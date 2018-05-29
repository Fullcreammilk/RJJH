<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script language="JavaScript">
    var nationality = "All";//国籍
    var type = "star";//类型
    var keyword;
    function initloading() {
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
                if(keyword!=null) {
                    window.location.href = "/MRAS/maker/maker?keyword=" + keyword + "&nationality=" + nationality + "&type=" + type + "&page=" + page;
                }else{
                    window.location.href = "/MRAS/maker/maker?keyword=&nationality=" + nationality + "&type=" + type + "&page=" + page;
                }

            }
        });
        //设置点击标签改变底色，并去除其他标签的底色
        $(".nationality label").click(function () {
            nationality = this.id.replace("nat-", "");  //传到逻辑层
            window.location.href = "/MRAS/maker/maker?keyword=&nationality=" + nationality + "&type=" + type;
            $(this).addClass("tag_selected").siblings().removeClass("tag_selected");
        });
        $("#button_star").click(function () {
            type = "star";
            window.location.href = "/MRAS/maker/maker?keyword=&nationality=" + nationality + "&type=" + type;
            $("#star").css("display", "inline");
            $("#creator").css("display", "none");
        });

        $("#button_creator").click(function () {
            type = "creator";
            window.location.href = "/MRAS/maker/maker?keyword=&nationality=" + nationality  + "&type=" + type;
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
     * 搜索明星
     */
    function searchstar(){
        keyword = $("#inp-stars").val();
        window.location.href = "/MRAS/maker/maker?keyword=" + keyword + "&nationality=" + nationality  + "&type=" + type;
    }

</script>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/graphics/logo.png">

    <title>Maker Search</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/makerSearch.css" rel="stylesheet">


</head>
<body onload="initloading()">

<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>

<!--国籍栏-->
<div class="nationality">
    <c:forEach var="nationality" items="${nationalities}">
        <label id="nat-${nationality}">${nationality}</label>
    </c:forEach>
</div>

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
            <a class="name" href="/MRAS/maker/detail?makerName=${maker.name}" style="font-size: 1.2em;">
            <div class="maker_wrapper-img">
                <img src="${maker.imgUrl}" alt="item" id="pic:${maker.name}" style="width:180px;">
            </div>
            <div class="info" style="text-align: center;">
                <%--<input type="button" id="${maker.name}" name="${maker.imgUrl}" class="plus_bt" />--%>
                ${maker.name}
                <%--<canvas id="myCanvas" width="500" height="500"></canvas>--%>
            </div>
            </a>
        </div>
    </c:forEach>
</div>
<!-- 分页栏 -->
<div class="M-box"></div>

</body>
</html>
