package org.aciton;

import com.opensymphony.xwork2.ActionSupport;
import org.models.User;

/**
 * Created by Adrien on 2017/5/10.
 */
public class UserLogin extends ActionSupport{
    private User user;
    private DBop loginop=new DBop();

    public DBop getLogincheck() {
        return loginop;
    }

    public void setLogincheck(DBop logincheck) {
        this.loginop = logincheck;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String studentlogin() throws Exception{
        if(loginop.studentlogincheck(user.getName(),user.getPassword()).equals("FAIL")){
            this.addActionError("用户名或密码有误");
            return "input";
        }
        else{
            return "studentloginsuccess";
        }
    }

    public String teacherlogin() throws Exception{
        if(loginop.teacherlogincheck(user.getName(),user.getPassword()).equals("FAIL")){
            this.addActionError("用户名或密码有误");
            return "input";
        }
        else{
            return "teacherloginsuccess";
        }
    }

    public String administorlogin() throws Exception{
        if(loginop.administorlogincheck(user.getName(),user.getPassword()).equals("FAIL")){
            this.addActionError("用户名或密码有误");
            return "input";
        }
        else{
            return "administorloginsuccess";
        }
    }
}
