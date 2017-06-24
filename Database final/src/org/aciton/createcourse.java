package org.aciton;

import com.opensymphony.xwork2.ActionSupport;
import org.easybooks.models.Course;

/**
 * Created by Adrien on 2017/5/14.
 */
public class createcourse extends ActionSupport {


    private Course course;
    private DBop dBop = new DBop();

    @Override
    public String execute() {
        if (dBop.coursecreate(course.getCnumber(), course.getCdept(), course.getCname(), course.getCredit()).equals("SUCCESS")) {
            return SUCCESS;
        } else {
            addActionError("创建课程出错");
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
