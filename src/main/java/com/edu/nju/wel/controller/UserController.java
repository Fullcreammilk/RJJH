package com.edu.nju.wel.controller;

import com.alibaba.fastjson.JSONObject;
import com.edu.nju.wel.model.User;
import com.edu.nju.wel.service.UserService;
import com.edu.nju.wel.util.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by qianzhihao on 2017/3/2.
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Resource(name = "UserService")
    UserService userService;

    /**
     * 用户登陆方法
     * @param name 用户名
     * @param pwd 密码
     * @return 结果信息，code为0则表示成功，其他为失败，失败原因在msg中
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    @ResponseBody
    public String login(String name,String pwd,HttpSession httpSession){
        //查找用户
        User user1 = userService.find(name);
        JSONObject result = new JSONObject();
        //用户名不存在
        if(user1==null){
            result.put("code",ResultCode.FAIL);
            result.put("msg","The username does not exist.");
        }
        else {
            //登陆成功
            if (pwd.equals(pullpassword(user1.getPassword()))) {
                result.put("code", ResultCode.SUCCESS);
                result.put("msg","Success");
                result.put("nowUrl",httpSession.getAttribute("nowUrl"));
                //登陆成功后添加session信息
                httpSession.setAttribute("username",name);
            }
            //密码错误
            else {
                result.put("code", ResultCode.FAIL);
                result.put("msg","Username or password is incorrect.");
            }
        }
        return result.toJSONString();
    }

    /**
     * 用户注册方法
     * @param name 用户名
     * @param pwd 密码
     * @return 结果信息，code为0则表示成功，其他为失败，失败原因在msg中
     */
    @RequestMapping(value = "/reg" ,method = RequestMethod.POST)
    @ResponseBody
    public String reg(String name,String pwd,HttpSession httpSession){
        User user1 = userService.find(String.valueOf(name));
        JSONObject result = new JSONObject();
        User user = new User();
        if(user1==null){
            user.setName(name);
            user.setPassword(pushpassword(pwd));
            userService.addUser(user);
            result.put("code",ResultCode.SUCCESS);
            result.put("msg","Welcome to IMoive, "+name+"!");
            //设置session
            httpSession.setAttribute("username",name);
        }
        //用户名重复
        else {
            result.put("code",ResultCode.FAIL);
            result.put("msg","The username has existed.");
        }
        return result.toJSONString();
    }

    /**
     * 登出方法
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.setAttribute("username",null);
    }

    private String pushpassword(String s){
        char[]list=s.toCharArray();
        String Result="";
        int random=(int)(Math.random()*9)+1;
        for(int i=0;i<list.length;i++){
            Result+=(char)((list[i]+random*random)%127);
        }
        return Result+random;
    }

    private String pullpassword(String s){
        char[]list=s.substring(0,s.length()-1).toCharArray();
        String Result="";
        int random=Integer.valueOf(s.charAt(s.length()-1)+"");
        for(int i=0;i<list.length;i++){
            Result+=(char)((list[i]-random*random+127)%127);
        }
        return Result;
    }
}
