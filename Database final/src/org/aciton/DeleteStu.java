package org.aciton;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Adrien on 2017/5/17.
 */
public class DeleteStu extends ActionSupport{
    private DBop dBop=new DBop();
    private String sno;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String execute(){
        if(dBop.deletestu(sno).equals("SUCCESS")){
            addActionError("删除学生成功");
            return SUCCESS;
        }
        else{
            addActionError("删除学生失败");
            return ERROR;
        }
    }

    public String deleteSRSC(){
        if(dBop.deleteSRSC(sno).equals("SUCCESS")){
            addActionError("删除该学生的选课记录成功");
            return SUCCESS;
        }
        else{
            addActionError("删除该学生选课记录失败");
            return ERROR;
        }
    }
}
