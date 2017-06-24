package org.aciton;

import com.opensymphony.xwork2.ActionSupport;
import sun.security.pkcs11.Secmod;

/**
 * Created by Adrien on 2017/5/17.
 */
public class CreateStu extends ActionSupport {
    private DBop dBop=new DBop();
    private String crsno;
    private String sname;
    private String sex;
    private String age;
    private String sdept;
    private String logn;
    private String pswd;

    public String getCrno() {
        return crsno;
    }

    public void setCrsno(String crsno) {
        this.crsno = crsno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
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

    @Override
    public String execute(){
        if(dBop.createstu(crsno,sname,sex,age,sdept,logn,pswd).equals("SUCCESS")){
            addActionError("创建学生成功");
            return SUCCESS;
        }
        else{
            addActionError("创建学生失败");
            return ERROR;
        }
    }
}
