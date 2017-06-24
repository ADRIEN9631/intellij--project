package org.aciton;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.opensymphony.xwork2.ActionContext;
//import org.easybooks.models.SCS;
import org.easybooks.test.factory.*;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.List;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import javax.jms.Session;
//import javax.security.auth.login.Configuration;
//import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Adrien on 2017/5/10.
 */
public class DBop {

    public User user=null;
    public Configuration config=null;
    public SessionFactory sessionfactory=null;
    public Session dbsession=null;
    public Transaction tx=null;
    public ActionContext actioncontext=null;
    public Map<String,Object> mapsession=null;

    public DBop(){
        config=new Configuration().configure("/hibernate.cfg.xml");
        sessionfactory=config.buildSessionFactory();
        dbsession=sessionfactory.openSession();
        tx=dbsession.beginTransaction();
        actioncontext=ActionContext.getContext();
        mapsession=actioncontext.getSession();
    }

    public String studentlogincheck(String username, String password){
        mapsession.clear();
        String hql = "from SEntity a where a.logn=:loginname and a.pswd=:loginpswd";
        Query query=(Query)dbsession.getSession().createQuery(hql);
        query.setString("loginname",username);
        query.setString("loginpswd",password);
        List result=query.list();
        Iterator it=result.iterator();
        if(result.size()==0){return "FAIL";}
        else {
            while (it.hasNext()) {
                SEntity stu = (SEntity) it.next();
                User newuser = new User();
                newuser.setName(stu.getSno());
                newuser.setPassword(stu.getPswd());
                mapsession.put("tempuser", newuser);
                System.out.println(stu.getSname());
                dbsession.close();
                return "SUCCESS";
            }
            dbsession.close();
            return "FAIL";
        }
    }

    public String teacherlogincheck(String username,String password){
        mapsession.clear();
        if(user==null||user!=null) {
            String hql = "from TEntity a where a.logn=:loginname and a.pswd=:loginpswd";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("loginname",username);
            query.setString("loginpswd",password);
            List result=query.list();
            Iterator it=result.iterator();
            while(it.hasNext()){
                TEntity tch=(TEntity)it.next();
                User newuser=new User();
                newuser.setName(tch.getTno());
                newuser.setPassword(tch.getPswd());
                mapsession.put("tempuser",newuser);
                System.out.println(tch.getTname());
                dbsession.close();
                return "SUCCESS";
            }
            return "FAIL";
        }
        else{
            return "SUCCESS";
        }
    }

    public String administorlogincheck(String username,String password){
        mapsession.clear();
        if(user==null||user!=null) {
            String hql = "from AdminEntity a where a.admincount=:loginname and a.adminpswd=:loginpswd";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("loginname",username);
            query.setString("loginpswd",password);
            List result=query.list();
            Iterator it=result.iterator();
            while(it.hasNext()){
                AdminEntity admin=(AdminEntity)it.next();
                User newuser=new User();
                newuser.setName(admin.getAdmincount());
                newuser.setPassword(admin.getAdminpswd());
                mapsession.put("tempuser",newuser);
                System.out.println(admin.getAdmincount());
                dbsession.close();
                return "SUCCESS";
            }
            dbsession.close();
            return "FAIL";
        }
        else{
            dbsession.close();
            return "SUCCESS";
        }
    }

    public String coursesearch(String coursenumber){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            System.out.println("No User Logined");
            dbsession.close();
            return "FAIL";
        }
        else{
            String hql="from CEntity a where a.cno like :number";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("number",'%'+coursenumber+'%');
            List<CEntity> result=query.list();
            mapsession.put("coursesearchresult",result);
            Iterator it=result.iterator();
            while(it.hasNext()){
                CEntity c=(CEntity)it.next();
                System.out.println(c.getCname());
                dbsession.close();
                return "SUCCESS";
            }
            dbsession.close();
            return "ERROR";
        }
    }

    public String courseselect(String coursenumber){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            System.out.println("No User Logined");
            dbsession.close();
            return "FAIL";
        }
        else{
            String hql="from CEntity a where a.cno=:number";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("number",coursenumber);
            List<CEntity> exist= query.list();
            if(exist.size()==0){
                dbsession.close();
                return "FAIL";
            }
            ScEntity newrecord=new ScEntity();
            CEntity sociatedcourse=(CEntity)dbsession.get(CEntity.class,coursenumber);
            SEntity stu=(SEntity)dbsession.get(SEntity.class,user.getName());
            stu.setSno(user.getName());
            sociatedcourse.setCno(coursenumber);
            newrecord.setSno(user.getName());
            newrecord.setCno(coursenumber);
            newrecord.setsBySno(stu);
            newrecord.setcByCno(sociatedcourse);
            System.out.println(newrecord.getCno()+newrecord.getSno());
            dbsession.save(newrecord);
            tx.commit();
            dbsession.close();
            return "SUCCESS";
        }
    }

    public String searchSC(){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            System.out.println("No User Logined");
            dbsession.close();
            return "FAIL";
        }
        else{
            String hql="select a.cno,b.cname,b.credit,b.tname,a.grade from ScEntity a,CEntity b where a.sno=:number and b.cno=a.cno";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("number",user.getName());
            List<Object[]> result=query.list();
            mapsession.put("tuCourses",result);
            Iterator it=result.iterator();
            if(result.size()==0){
                dbsession.close();
                return "FAIL";
            }
            else {
                while (it.hasNext()) {
                    Object[] c = (Object[]) it.next();
                    System.out.println("课号："+c[0]);
                    System.out.println("课名："+c[1]);
                }
                dbsession.close();
                return "SUCCESS";
            }
        }
    }

    public String coursecreate(String coursenumber,String dept,String coursename,int credit){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            System.out.println("No User Logined");
            return "FAIL";
        }
        else{
            String hql="from CEntity a where a.cno=:number";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("number",coursenumber);
            List<CEntity> exist= query.list();
            if(exist.size()!=0){
                dbsession.close();
                return "FAIL";
            }
            CEntity newrecord=new CEntity();
            TEntity sociatedteacher=(TEntity)dbsession.get(TEntity.class,user.getName());
            newrecord.setCno(coursenumber);
            newrecord.setCname(coursename);
            newrecord.setCredit(credit);
            newrecord.setCdept(dept);
            newrecord.settByTno(sociatedteacher);
            newrecord.setTname(sociatedteacher.getTname());
            System.out.println(newrecord.getCno()+newrecord.getTname());
            dbsession.save(newrecord);
            tx.commit();
            dbsession.close();
            return "SUCCESS";
        }
    }

    public String tsearchcourse(){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            return "FAIL";
        }
        else{
            String hql="from CEntity a where a.tByTno.tno=:number";
            TEntity teacher=(TEntity)dbsession.get(TEntity.class,user.getName());
            Query query=(Query)dbsession.getSession().createQuery(hql);
            query.setString("number",teacher.getTno());
            List<CEntity> result=query.list();
            if(result.size()==0){
                dbsession.close();
                return "FAIL";
            }
            else{
                mapsession.put("tscresult",result);
                Iterator it=result.iterator();
                while (it.hasNext()) {
                    CEntity c = (CEntity) it.next();
                    System.out.println(c.getCno());
                }
                dbsession.close();
                return "SUCCESS";
            }
        }
    }

    public String Searchselectedstu(){
        List<CEntity> list= (List) mapsession.get("courses");
        user=(User)mapsession.get("tempuser");
        Iterator it=list.iterator();
        int count=0;
        while(it.hasNext()) {
            CEntity cc=(CEntity) it.next();
            String hql = "select a from ScEntity a,CEntity b where a.cno=b.cno and b.tByTno=:number and b.cno=:codee";
            Query query = (Query) dbsession.getSession().createQuery(hql);
            TEntity teacher = (TEntity) dbsession.get(TEntity.class, user.getName());
            query.setString("number", teacher.getTno());
            query.setString("codee",cc.getCno());
            List<ScEntity> result = query.list();
            if (result.size() == 0) {
                dbsession.close();
                return "FAIL";
            } else {
                mapsession.put("scsresult"+count, result);
                count++;
                continue;
            }
        }
        dbsession.close();
        return "SUCCESS";
    }

    public String UpdateSC(List<ScEntity> templist){
        Iterator it=templist.iterator();
        while(it.hasNext()){
            String hql="update ScEntity a set a.grade=:tempgrade,a.pgrade=:temppgrade,a.egrade=:tempegrade where a.sno=:tempsnumber and a.cno=:tempcnumber and a.cByCno.cno=:tempcnumber and a.sBySno=:tempsnumber";
            Query query=(Query)dbsession.getSession().createQuery(hql);
            ScEntity scEntity=(ScEntity)it.next();
            System.out.println(scEntity.getSno()+scEntity.getCno()+scEntity.getGrade()+scEntity.getPgrade()+scEntity.getEgrade());
            query.setParameter("tempgrade",scEntity.getGrade());
            query.setParameter("temppgrade",scEntity.getPgrade());
            query.setParameter("tempegrade",scEntity.getEgrade());
            query.setString("tempsnumber",scEntity.getSno());
            query.setString("tempcnumber",scEntity.getCno());
            dbsession.joinTransaction();
            query.executeUpdate();
            tx.commit();
            tx.begin();
        }
        dbsession.close();
        return "SUCCESS";
    }

    public String deleteCourse(String coursenumber) throws Exception{
        try {
            user = (User) mapsession.get("tempuser");
            String hql = "delete CEntity a where a.cno=:number";
            Query query = (Query) dbsession.getSession().createQuery(hql);
            //TEntity teacher=(TEntity)dbsession.get(TEntity.class,user.getName());
            query.setString("number", coursenumber);
            dbsession.joinTransaction();
            query.executeUpdate();
            tx.commit();
            dbsession.close();
            return "SUCCESS";
        }catch(ConstraintViolationException e){
            dbsession.close();
            e.printStackTrace();
            return "FAIL";
        }
    }

    public String deleteCrelatedSC(String coursenumber){
        try {
            user = (User) mapsession.get("tempuser");
            if (user == null) {
                //dbsession.close();
                return "FAIL";
            } else {
                String hql = "delete ScEntity a where a.cno=:number";
                Query query = (Query) dbsession.getSession().createQuery(hql);
                query.setString("number", coursenumber);
                query.executeUpdate();
                tx.commit();
                //dbsession.close();
                return "SUCCESS";
            }
        }catch(Exception e){
            dbsession.close();
            e.printStackTrace();
            return "FAIL";
        }
    }

    public String createstu(String sno,String sname,String sex,String age,String sdept,String logn,String pswd){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            dbsession.close();
            return "FAIL";
        }
        else{
            try{
                SEntity newstu=new SEntity();
                newstu.setSno(sno);
                newstu.setAge(age);
                newstu.setLogn(logn);
                newstu.setPswd(pswd);
                newstu.setSdept(sdept);
                newstu.setSname(sname);
                newstu.setSex(sex);
                dbsession.save(newstu);
                tx.commit();
                dbsession.close();
                return "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                return "FAIL";
            }
        }
    }

    public String deletestu(String sno){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            dbsession.close();
            return "FAIL";
        }
        else{
            try{
                String hql = "delete SEntity a where a.sno=:number";
                Query query = (Query) dbsession.getSession().createQuery(hql);
                query.setString("number",sno);
                query.executeUpdate();
                tx.commit();
                dbsession.close();
                return "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                dbsession.close();
                return "FAIL";
            }
        }
    }

    public String deleteSRSC(String sno){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            dbsession.close();
            return "FAIL";
        }
        else{
            try{
                String hql = "delete ScEntity a where a.sno=:number";
                Query query = (Query) dbsession.getSession().createQuery(hql);
                query.setString("number",sno);
                query.executeUpdate();
                tx.commit();
                dbsession.close();
                return "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                dbsession.close();
                return "FAIL";
            }
        }
    }

    public String createteacher(String tno,String tname,String logn,String pswd,boolean xb,String zc,String dept){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            dbsession.close();
            return "FAIL";
        }
        else{
            try{
                TEntity newteacher=new TEntity();
                newteacher.setTno(tno);
                newteacher.setTname(tname);
                newteacher.setLogn(logn);
                newteacher.setPswd(pswd);
                newteacher.setDept(dept);
                newteacher.setXb(xb);
                newteacher.setZc(zc);
                dbsession.save(newteacher);
                tx.commit();
                dbsession.close();
                return "SUCCESS";
            }catch(Exception e){
                dbsession.close();
                e.printStackTrace();
                return "FAIL";
            }
        }
    }

    public String deleteteacher(String tno){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            dbsession.close();
            return "FAIL";
        }
        else{
            try{
                String hql = "delete TEntity a where a.tno=:number";
                Query query = (Query) dbsession.getSession().createQuery(hql);
                query.setString("number",tno);
                query.executeUpdate();
                tx.commit();
                dbsession.close();
                return "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                dbsession.close();
                return "FAIL";
            }
        }
    }

    public String deleteTRC(String tno){
        user=(User)mapsession.get("tempuser");
        if(user==null){
            dbsession.close();
            return "FAIL";
        }
        else{
            try{
                String hql2="from  CEntity b where b.tByTno.tno=:number";
                String hql = "delete CEntity a where a.tByTno.tno=:number";
                Query query = (Query) dbsession.getSession().createQuery(hql);
                Query query2=(Query) dbsession.getSession().createQuery(hql2);
                query.setString("number",tno);
                query2.setString("number",tno);
                List<CEntity> result=query2.list();
                Iterator it=result.iterator();
                while(it.hasNext()){
                    CEntity course=(CEntity)it.next();
                    String coursenumber=course.getCno();
                    if(deleteCrelatedSC(coursenumber).equals("SUCCESS")){
                        tx.begin();
                        continue;
                    }
                    else{
                        tx.rollback();
                        return "FAIL";
                    }
                }
                query.executeUpdate();
                tx.commit();
                dbsession.close();
                return "SUCCESS";
            }catch(Exception e){
                e.printStackTrace();
                dbsession.close();
                return "FAIL";
            }
        }
    }


    void closeconnect(){
        dbsession.close();
    }
}
