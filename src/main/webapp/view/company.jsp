<%--
  Created by IntelliJ IDEA.
  User: hyx
  Date: 2017/6/3
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>company</title>
    <link rel="icon" href="/graphics/logo.png">

    <script type="text/javascript">
        function initloading() {
            $("#loading").fadeOut();
            initBar();
        }
        function clearGenre() {
            sessionStorage.removeItem("genre");
        }
        $(function () {
            document.addEventListener("error", function (e) { var elem = e.target; if (elem.tagName.toLowerCase() == "img") { elem.src = "/graphics/error.jpg"; } }, true);

        })
    </script>

    <link href="/css/company.css" rel="stylesheet">
</head>

<body onload="initloading()">
<!-- loading
================================================== -->
<div id="loading" class="loading" style="　position:fixed;width: 100%;height: 100%;">
    <jsp:include page="/view/Loading.html"></jsp:include>
</div>
<!-- 导航栏
================================================== -->
<div>
    <jsp:include page="/view/navigetionbar.jsp"></jsp:include>
</div>

<!-- 公司栏
================================================== -->
<div class="company_outer">
    <div class="company_container">
        <c:forEach var="company" items="${allCompany}" >
            <div class="company_wrapper">
                <a class="name" href="/MRAS/company/detail?companyName=${company.name}" style="font-size: 1.2em" onclick="clearGenre()">
                <img src = "${company.imageurl}">
                <div class="info" style="overflow: hidden; height: 4.4em; text-overflow:ellipsis; position:relative">
                    ${company.name}
                        <%--<p>${company.founder}</p>--%>
                </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>


</body>
</html>
