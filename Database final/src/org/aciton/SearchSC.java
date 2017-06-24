package org.aciton;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Adrien on 2017/5/14.
 */
public class SearchSC extends ActionSupport{
    private DBop dBop=new DBop();

    public String execute(){
        if(dBop.searchSC().equals("SUCCESS")){
            return SUCCESS;
        }
        else{
            addActionError("搜索已选课程出错");
            return ERROR;
        }
    }
}
