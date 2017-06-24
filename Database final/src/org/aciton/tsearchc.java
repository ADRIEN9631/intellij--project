package org.aciton;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Adrien on 2017/5/15.
 */
public class tsearchc extends ActionSupport{
    private DBop dBop=new DBop();

    @Override
    public String execute(){
        if(dBop.tsearchcourse().equals("SUCCESS")){
            return SUCCESS;
        }
        else{
            addActionError("搜索已开课程出错");
            return ERROR;
        }
    }
}
