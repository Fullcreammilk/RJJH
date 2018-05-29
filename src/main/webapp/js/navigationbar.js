/**
 * Created by Dell on 2017/5/17.
 */
function  init(username) {
    if(username!=null)
    {
        $("#guildLabel").append('<a href="/MRAS/home" id="top_fir" class="nav-home" ref="nofollow">Home</a>')
        $("#guildLabel").append('<a href="/MRAS/login" id="top_sec" class="nav-login" ref="nofollow"></a>')
        $("#guildLabel").append('<a href="/" id="top_thr" class="nav-register" ref="nofollow" onclick="logOut()">Logout</a>')
    }
    else{
        $("#guildLabel").append('<a href="/MRAS/home" id="top_fir" class="nav-home" ref="nofollow">Home</a>')
        $("#guildLabel").append('<a href="/MRAS/login" id="top_sec" class="nav-login" ref="nofollow">Login</a>')
        $("#guildLabel").append('<a href="/" id="top_thr" class="nav-register" ref="nofollow" onclick="logOut()">Register</a>')
    }
}
function logOut() {
session.setAttribute("username",null);
    console.log(session.getAttribute("username"));
}