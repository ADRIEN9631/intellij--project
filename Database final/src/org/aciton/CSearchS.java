package org.aciton;

import com.opensymphony.xwork2.ActionSupport;
import org.easybooks.test.factory.ScEntity;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Adrien on 2017/5/15.
 */
public class CSearchS extends ActionSupport {
    private DBop dBop=new DBop();
    private List<ScEntity> templist;

    public List<ScEntity> getTemplist() {
        return templist;
    }

    public void setTemplist(List<ScEntity> templist) {
        this.templist = templist;
    }

    public String saveop(){
        if(templist.size()==0){
            System.out.println("对象为空");
        }
        if(dBop.UpdateSC(templist).equals("SUCCESS")){
            return SUCCESS;
        }
        else{
            addActionError("更新出错");
            return ERROR;
        }
    }

    @Override
    public String execute(){
        if(dBop.Searchselectedstu().equals("SUCCESS")){
            return SUCCESS;
        }
        else{
            addActionError("查找选课学生出错");
            return ERROR;
        }
    }
}
