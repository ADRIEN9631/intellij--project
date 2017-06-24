package org.aciton;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.SQLException;

/**
 * Created by Adrien on 2017/5/16.
 */
public class DeleteCourse extends ActionSupport{
    private DBop dBop=new DBop();
    private String coursenumber;

    public String getCoursenumber() {
        return coursenumber;
    }

    public void setCoursenumber(String coursenumber) {
        this.coursenumber = coursenumber;
    }

    @Override
    public String execute() throws Exception{
        try{
            if(dBop.deleteCourse(coursenumber).equals("SUCCESS")){
                addFieldError("coursenumbererror","删除成功");
                return SUCCESS;
            }
            else{
                addFieldError("coursenumbererror","删除课程出错");
                return ERROR;
            }
        }catch (Exception e){
            dBop.closeconnect();
            e.printStackTrace();
            return ERROR;
        }
    }

    public String deleteCRSC(){
        if(dBop.deleteCrelatedSC(coursenumber).equals("SUCCESS")){
            addActionError("删除成功");
            return SUCCESS;
        }
        else{
            addActionError("删除相关选课记录出错");
            return ERROR;
        }
    }
}
