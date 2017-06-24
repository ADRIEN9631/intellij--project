package org.aciton;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Adrien on 2017/5/17.
 */
public class CreateTeacher extends ActionSupport{
    private DBop dBop=new DBop();
    private String tno;
    private String tname;
    private String logn;
    private String pswd;
    private boolean xb;
    private String zc;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getLogn() {
        return logn;
    }

    public void setLogn(String logn) {
        this.logn = logn;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public boolean getXb() {
        return xb;
    }

    public void setXb(boolean xb) {
        this.xb = xb;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    private String dept;

    public String execute(){
        if(dBop.createteacher(tno,tname,logn,pswd,xb,zc,dept).equals("SUCCESS")){
            addActionError("创建教师成功");
            return SUCCESS;
        }
        else{
            addActionError("创建教师失败");
            return ERROR;
        }
    }
}
