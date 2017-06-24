package org.aciton;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Adrien on 2017/5/17.
 */
public class DeleteTeacher extends ActionSupport{
    private DBop dBop=new DBop();
    private String dttno;
    private String tname;
    private String logn;
    private String pswd;
    private String xb;
    private String zc;
    private String dept;

    public String getDttno() {
        return dttno;
    }

    public void setDttno(String dttno) {
        this.dttno = dttno;
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

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
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

    @Override
    public String execute(){
        if(dBop.deleteteacher(dttno).equals("SUCCESS")){
            addActionError("删除教师成功");
            return SUCCESS;
        }
        else{
            addActionError("删除教师失败");
            return ERROR;
        }
    }

    public String deleteTRC(){
        if(dBop.deleteTRC(dttno).equals("SUCCESS")){
            addActionError("删除教师相关课程成功");
            return SUCCESS;
        }
        else{
            addActionError("删除教师相关课程失败");
            return ERROR;
        }
    }
}
