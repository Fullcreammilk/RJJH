<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/3
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<script src="/js/jquery.js"></script>
<%--<script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>--%>
<%--<script type="text/javascript" src="../../static/js/log.js"></script>--%>
<script language="JavaScript">
//    window.onbeforeunload = function () {
//        //这里刷新方法有很多，具体要看子窗口是怎样出来的
//        window.opener.location.reload();
//        parent.location.reload();
//        self.opener.location.reload();
//        console.log(123);
//
//        if (parent.window.opener != null) {
//            parent.window.opener.location.href = parent.window.opener.location.href;
//        }
//    };
    function Login() {
//        alert("1");
        var loginname = $("#username").val();
//        //var是申明一个变量的关键字，loginname为变量名，
//        //$("#u")是找到一个标签ID为"u"的标签，.val() 是获取对应ID标签的值
//        if ("" == loginname) {  //u标签的值为空
//            //只有通过 $("#u") 的形式才能获取一个标签。
//
//            $("#username").getElementById("username").setAttribute("placeholder","你倒是输入密码啊")
//            $("#username").focus();    //让u标签获取输入焦点
//            return false;   //返回false，打断js的执行
//        }
        var loginpwd = $("#password").val();
//        alert("2");
//        if (loginpwd == "") {
//            $("#password").tips({
//                side: 2,
//                msg: '密码不得为空',
//                bg: '#AE81FF',
//                time: 3
//            });
//            $("#password").focus();
//            return false;
//        }

        $.ajax({    //使用jquery下面的ajax开启网络请求
            type: "POST",   //http请求方式为POST
            url: '/MRAS/user/login',  //请求接口的地址
            data: {name: loginname, pwd: loginpwd},  //存放的数据，服务器接口字段为loginId和pwd，分别对应用户登录名和密码
            dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
            cache: false,   //不用缓存
            async: false,
            success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                if (data.code == 0) {   //获判断json数据中的code是否为1，登录的用户名和密码匹配，通过效验，登陆成功
                    window.location.href = sessionStorage.getItem("nowUrl");    //跳转到之前的页面
                } else {
                    alert(data.msg);    //弹出对话框，提示返回的错误信息
                    $("#username").focus();
                }
            }
        });

    }

    function register() {
        var loginname = $("#username_register").val();
        var loginpwd = $("#password_register").val();
        var loginpwd_confirm = $("#password_confirm").val();

        if (loginpwd == loginpwd_confirm) {
            $.ajax({    //使用jquery下面的ajax开启网络请求
                type: "POST",   //http请求方式为POST
                url: '/MRAS/user/reg',  //请求接口的地址
                data: {name: loginname, pwd: loginpwd},  //存放的数据，服务器接口字段为loginId和pwd，分别对应用户登录名和密码
                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
                cache: false,   //不用缓存
                async: false,
                success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
                    if(data.code==0) {
                        alert(data.msg);
                        window.location.href = sessionStorage.getItem("nowUrl");
                    }else{
                        alert(data.msg);
                    }
                },
                error: function () {
                    alert("Error!");
                }
            });
        }
        else {
            alert("Two password entries are inconsistent.");
        }
    }
</script>

<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <link rel="icon" href="/graphics/logo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Login and Registration Form with HTML5 and CSS3"/>
    <meta name="author" content="Codrops"/>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    <link rel="stylesheet" href="/css/animate.min.css"/>
</head>
<body style="BACKGROUND: url('../graphics/login/bc.png') no-repeat top center;">
<div class="container">

    <section>
        <div id="container_demo" style="BACKGROUND: url('../graphics/login/bg2.png') no-repeat center center;">
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div class="top_left">
                <img src="../graphics/login/logo.png">
            </div>
            <div id="nav-welcome">
                <h1>Discover <span>the world of movies</span></h1>
            </div>
            <div id="wrapper">
                <div id="login" class="animate form">
                    <form autocomplete="off">
                        <p class="welcome">
                            Welcome <span>back to us !</span>
                        </p>
                        <p>
                            <label for="username" class="uname" data-icon="u"> Your username: </label>
                            <input id="username" name="username" required="required" type="text"
                                   placeholder="input your username"/>
                        </p>
                        <p>
                            <label for="password" class="youpasswd" data-icon="p"> Your password: </label>
                            <input id="password" name="password" required="required" type="password"
                                   placeholder="eg. X8df90EO"/>
                        </p>
                        <p class="keeplogin">
                            <%--<input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping"/>--%>
                            <label ></label>
                        </p>

                        <p class="login button">
                            <input type="button" value="Login" onclick="Login()"/>
                        </p>
                        <p class="change_link">Not a member yet ?
                            <a href="#toregister" class="to_register">Sign up</a>
                        </p>
                    </form>
                </div>

                <div id="register" class="animate form">
                    <form action="mysuperscript.php" autocomplete="on">
                        <p class="welcome">
                            <lable> Join in <span> us now ! </span></lable>
                        </p>
                        <p>
                            <label for="username_register" class="uname" data-icon="u"> Create your username: </label>
                            <input id="username_register" name="username" required="required" type="text"
                                   placeholder="create your username"/>
                        </p>
                        <p>
                            <label for="password_register" class="youpasswd" data-icon="p"> Set your password: </label>
                            <input id="password_register" name="password" required="required" type="password"
                                   placeholder="only use characters or numbers"/>
                        </p>
                        <p>
                            <label for="password_confirm" class="youpasswd_confirm" data-icon="p"> Confirm your
                                password: </label>
                            <input id="password_confirm" name="password" required="required" type="password"
                                   placeholder="confirm your password"/>
                        </p>
                        <p class="register button">
                            <input type="button" value="Sign up" onclick="register()"/>
                        </p>
                        <p class="change_link">Already have an account ?
                            <a href="#tologin" class="to_register">Login</a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>

