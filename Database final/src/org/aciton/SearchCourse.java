package org.aciton;

import com.opensymphony.xwork2.ActionSupport;
import org.easybooks.models.Course;

/**
 * Created by Adrien on 2017/5/14.
 */
public class SearchCourse extends ActionSupport{
    private Course course;
    private DBop dBop=new DBop();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String execute(){
        if(dBop.courseselect(course.getCnumber()).equals("SUCCESS")){
            return SUCCESS;
        }
        else{
            addActionError("选修课程出错");
            return ERROR;
        }
    }
}
