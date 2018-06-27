<%--
  Created by hyx.
  User: thinkpad
  Date: 2017/5/13
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge">-->
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <!--&lt;!&ndash; 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ &ndash;&gt;-->
    <!--<meta name="description" content="">-->
    <!--<meta name="author" content="">-->
    <link rel="icon" href="/graphics/logo.png">

    <title>MRAS</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/css/bootstrap/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery.bxslider.js"></script>
    <%--<script type="text/javascript" src="../js/jquery.js"></script>--%>

    <!-- Custom styles for this template -->
    <link href="/css/movieHomepage.css" rel="stylesheet">
    <%--Loading.css--%>

    <script type="text/javascript">
        function initloading() {
            $("#loading").fadeOut();
            initBar();
        }
        //        function closeWin(){
        //            hasClosed = true;
        //            window.opener.location="javascript:reloadPage();";
        //            window.opener=null;
        //            window.close();
        //        }
        //        $(window).on('beforeunload', function () {
        //            return 'Your own message goes here...';
        //        });


    </script>
</head>
<body onload="initloading()">
<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>

<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>
<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <a href="/MRAS/movie/detail?movieName=Wonder Woman">
                <img class="first-slide" src="/graphics/movieHomepage/testPic.jpg" alt="First slide">
            </a>
        </div>
        <div class="item">
            <a href="/MRAS/movie/detail?movieName=Guardians of the Galaxy Vol. 2">
                <img class="second-slide" src="/graphics/movieHomepage/testPic2.jpg" alt="Second slide">
            </a>
        </div>
        <div class="item">
            <a href="/MRAS/movie/detail?movieName=Pirates of the Caribbean: Dead Men Tell No Tales">
                <img class="third-slide" src="/graphics/movieHomepage/testPic3.jpg" alt="Third slide">
            </a>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div><!-- /.carousel -->

<!-- movie slider
================================================== -->
<div class="slider_movie">
    <p class="title" style="text-align: left;font-size:40px">Top 10 Movies</p>
    <hr style="border: 0;border-bottom: 2px solid black;">
    <div class="slider">
        <c:forEach var="movie" items="${recMov}">
            <div class="slide">
                <a class="name" href="/MRAS/movie/detail?movieName=${movie.name}"
                   style="font-size: 1.2em; text-align: left">
                    <img src="${movie.imgUrl}" style="margin: 0 auto;">
                    <div class="info" style="width: 210px; height: 50px; margin: 0 auto; padding-top: 5px">
                            ${movie.name}
                        <p style="float: right">${movie.score}</p>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <a class="more" href="/MRAS/search/movSch"> more </a>
</div>

<!-- directors & writers slider
================================================== -->
<div class="slider_director">
    <p class="title" style="text-align: left;font-size:40px">Top 10 Creators</p>
    <hr style="border: 0;border-bottom: 2px solid black;">
    <div class="slider">
        <c:forEach var="director" items="${recCreator}">
            <div class="slide">
                <a class="name" href="/MRAS/maker/detail?makerName=${director.name}&type=creator"
                   style="font-size: 1.2em; text-align: left">
                    <img src="${director.imgUrl}" style="margin: 0 auto;">
                    <div class="info" style="width: 210px; height: 50px; margin: 0 auto; padding-top: 5px">
                            ${director.name}
                        <p style="float: right">${director.score}</p>
                    </div>
                </a>

                <%--<a class="name" href="/MRAS/maker/detail?makerName=${director.name}&type=creator">--%>
                    <%--<img src="${director.imgUrl}">--%>
                        <%--${director.name} </a>--%>
            </div>
        </c:forEach>
    </div>
    <a class="more" href="/MRAS/maker/maker?type=creator"> more </a>
</div>

<!-- Film stars slider
================================================== -->
<div class="slider_star">
    <p class="title" style="text-align: left;font-size:40px">Top 10 Stars</p>
    <hr style="border: 0;border-bottom: 2px solid black;">
    <div class="slider">
        <c:forEach var="star" items="${recStar}">
            <div class="slide">
                <a class="name" href="/MRAS/maker/detail?makerName=${star.name}&type=star"
                   style="font-size: 1.2em; text-align: left">
                    <img src="${star.imgUrl}" style="margin: 0 auto;">
                    <div class="info" style="width: 210px; height: 50px; margin: 0 auto; padding-top: 5px">
                            ${star.name}
                        <p style="float: right">${star.score}</p>
                    </div>
                </a>

                <%--<a class="name" href="/MRAS/maker/detail?makerName=${star.name}&type=star">--%>
                    <%--<img src="${star.imgUrl}">--%>
                        <%--${star.name} </a>--%>
            </div>
        </c:forEach>
    </div>
    <a class="more" href="/MRAS/maker/maker?type=star"> more </a>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('.slider').bxSlider({
            slideWidth: 230,
            minSlides: 2,
            maxSlides: 5,
            moveSlides: 2,
            slideMargin: 10
        });
    });
</script>

<!-- Bootstrap core JavaScript
================================================== -->
<script src="../js/bootstrap.min.js"></script>
<!--&lt;!&ndash; IE10 viewport hack for Surface/desktop Windows 8 bug &ndash;&gt;-->
<script src="../js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>
