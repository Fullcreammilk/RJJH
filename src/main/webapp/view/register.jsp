<%--
  Created by hyx.
  User: hyx
  Date: 2017/5/4
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<script src="/js/jquery.js"></script>
<script language="JavaScript">
    function register() {
        var loginname = $("#username").val();
        var loginpwd = $("#password").val();
        var loginpwd_confirm = $("#password_confirm").val();

        $.ajax({    //使用jquery下面的ajax开启网络请求
            type: "POST",   //http请求方式为POST
            url: '/MRAS/user/reg',  //请求接口的地址
            data: {name: loginname, pwd: loginpwd},  //存放的数据，服务器接口字段为loginId和pwd，分别对应用户登录名和密码
            dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
            cache: false,   //不用缓存
            async: false,
            success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                alert(data.msg);
            }
        });
    }

</script>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Login and Registration Form with HTML5 and CSS3"/>
    <meta name="author" content="Codrops"/>
    <link rel="stylesheet" type="text/css" href="../css/register.css"/>
</head>
<body style="BACKGROUND: url('../graphics/register/bc.png') no-repeat;">
<div class="container">
    <section>
        <div id="container_demo"  style="BACKGROUND: url('../graphics/register/bg2.png') no-repeat center center;">
            <div class="top_left">
                <img src="../graphics/register/logo.png">
            </div>
            <div id="wrapper">
                <div id="register" class="animate form">
                    <h1>Discover <span>the world of movies</span></h1>
                    <p class="welcome">
                        <lable> Join in <span> us from now on! </span></lable>
                    </p>
                    <p>
                        <label for="username" class="uname" data-icon="u" > Create your username: </label>
                        <input id="username" name="username" required="required" type="text" placeholder="create your username"/>
                    </p>
                    <p>
                        <label for="password" class="youpasswd" data-icon="p"> Set your password: </label>
                        <input id="password" name="password" required="required" type="password" placeholder="only use characters or numbers" />
                    </p>
                    <p>
                        <label for="password_confirm" class="youpasswd_confirm" data-icon="p"> Confirm your password: </label>
                        <input id="password_confirm" name="password" required="required" type="password" placeholder="confirm your password" />
                    </p>
                    <p class="register button">
                        <input type="submit" value="Register" onclick="register()"/>
                    </p>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
