package org.aciton;

import org.easybooks.models.Course;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Adrien on 2017/5/13.
 */
public class selectcourse extends ActionSupport {
    private Course course;
    private DBop dBop=new DBop();

    @Override
    public String execute(){
        if(dBop.coursesearch(course.getCnumber()).equals("SUCCESS")){
            return SUCCESS;
        }
        else{
            addActionError("查找课程出错");
            return ERROR;
        }
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
