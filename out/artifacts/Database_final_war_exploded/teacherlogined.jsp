<%@ page import="org.easybooks.test.factory.CEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.aciton.DBop" %>
<%@ page import="org.models.User" %>
<%@ page import="org.hibernate.query.Query" %>
<%@ page import="org.easybooks.test.factory.ScEntity" %><%--
  Created by IntelliJ IDEA.
  User: Adrien
  Date: 2017/5/12
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>教师页面</title>
    <script src="jquery.js"></script>
    <style>
        body{

            margin:100px;
            background-image : url('bgd.png');
            width:1600px;
            height:1000px;
            color:white;
        }

        .container{
            text-align:center;
            width:80%;
            height:80%;
        }

        .notmain{
            float:left;
            width:30%;
            height:100%;
        }

        .welcome{
            height:30%;
            width:100%;
        }

        .function{
            height:70%;
            width:100%;
        }

        .mybutton {
            width:240px;
            margin-top:30px;
            border:gray solid 3px;
            border-radius:10px;
            height:50px;
            background-color:rgba(0,0,0,0.1);
            color:white;
            font-size:20px;
        }

        .mybutton:hover{
            background-color:gray;
        }

        button {
            width:240px;
            margin-top:30px;
            border:gray solid 3px;
            border-radius:10px;
            height:50px;
            background-color:rgba(0,0,0,0.1);
            color:white;
            font-size:20px;
        }

        button:hover{
            background-color:gray;
        }

        .showpad{
            float :right;
            height:100%;
            width:65%;
            display:flex;
            justify-content: center;
            position:relative;
        }

        .showpad form{
            align-self:center;
            display:none;
            width:100%;
        }

        .input{
            background-color:rgba(0,0,0,0.1);
            padding-left:2%;
            border-radius: 10px;
            color:white;
            height:32px;
            width:150px;
        }

        .showpad table {
            width:1000px;
            background-color:rgba(0,0,0,0.1);
            border-radius:10px;
            display:none;
            font-size:18px;
            position:absolute;
            top:0;
            left:0;
        }
    </style>
</head>
<body>
    <div class = "container">
        <div class = "notmain">
            <div class = "welcome">
                <p style = "font-size:20px;position:relative;top:30%;">Hello,<s:property value="#session.tempuser.name"></s:property></p>

                <p><a href = "#"
                      style = "color:white;font-size:20px;position:relative;top:70px;" onclick="javascript:
            if(confirm('确定退出？'))
            {
                parent.location.href='login.jsp';
            }
            else{
                return false;
            }">退出</a></p>
                <s:actionerror></s:actionerror>
            </div>
            <div class = "function">
                <button id = "OpenCourse">开课</button>
                <s:form action="TSearchC" theme="simple" method="post"  cssClass = "QC" cssStyle= "align-self:center;width:100%;">
                    <s:submit value="查询开课记录" cssClass="mybutton" id = "OpenCourseData"></s:submit>
                </s:form>
                <button id = "StuSelect">查询选课学生</button>
            </div>
        </div>
        <div class = "showpad">
            <s:form action="createcourse" theme="simple" method="post" cssClass = "OC">
                课号：&nbsp;&nbsp;<s:textfield name="course.cnumber" cssClass="input"></s:textfield>
                <br><br>
                课名：&nbsp;&nbsp;<s:textfield name="course.cname" cssClass="input"></s:textfield>
                <br><br>
                学分：&nbsp;&nbsp;<s:textfield name="course.credit" cssClass="input"></s:textfield>
                <br><br>
                院系：&nbsp;&nbsp;<s:textfield name="course.cdept" cssClass="input"></s:textfield>
                <br><br>
                <s:submit value="开课" cssClass = "mybutton"/>
            </s:form>

            <table class = "showtable2">
                <s:iterator value="#session.tscresult" var="mycontent" status="tempsta">
                    <tr>
                        <td>课号：<s:property value="#mycontent.cno"></s:property></td><td>    </td>
                        <td>课名：<s:property value="#mycontent.cname"></s:property></td><td>     </td>
                        <td>院系：<s:property value="#mycontent.cdept"></s:property></td><td>     </td>
                        <td>学分：<s:property value="#mycontent.credit"></s:property></td><td>     </td>
                        <td>开课教师：<s:property value="#mycontent.tname"></s:property></td><td>     </td>
                    </tr>
                </s:iterator>
                <%
                    ActionContext tempo=ActionContext.getContext();
                    Map<String,Object> tempsession=tempo.getSession();
                    List<CEntity> tempC= (List<CEntity>) tempsession.get("tscresult");
                    if(tempC!=null) {
                        tempsession.remove("tscresult");
                    }
                %>
            </table>
            <%
                DBop dBop=new DBop();
                dBop.user=(User)dBop.mapsession.get("tempuser");
                String hql="select distinct(a) from CEntity a where a.tByTno.tno=:number";
                Query query=(Query)dBop.dbsession.getSession().createQuery(hql);
                query.setString("number",dBop.user.getName());
                List<CEntity> list=query.list();
                dBop.mapsession.put("courses",list);
                dBop.dbsession.close();
                int count=0;
            %>

            
            <s:iterator value="#session.courses" var="thecourse" status="nowstatus">
                <s:form action="CSearchS" theme="simple" method="post" cssClass = "showtable1">
                    <tr><td><s:property value="%{#thecourse.cname}"></s:property></td></tr>
                    <tr><td><s:property value="%{#nowstatus.index}"/></td></tr>
                    <br><br>
                    <%
                        List<ScEntity> tempp= (List<ScEntity>) dBop.mapsession.get("scsresult"+count);
                        dBop.mapsession.put("tempccourse",tempp);
                    %>
                    <s:iterator value="#session.tempccourse" var="mycontent" status="tempsta">
                        课号：<s:textfield name="%{'templist['+#tempsta.index+'].cno'}" value="%{#mycontent.cno}" cssClass="input"></s:textfield>
                        &nbsp;&nbsp;
                        学号：<s:textfield name="%{'templist['+#tempsta.index+'].sno'}" value="%{#mycontent.sno}" cssClass="input"></s:textfield>
                        &nbsp;&nbsp;
                        总成绩：<s:textfield name="%{'templist['+#tempsta.index+'].grade'}" value="%{#mycontent.grade}" cssClass="input"></s:textfield>
                        <br>
                        平时成绩：<s:textfield name="%{'templist['+#tempsta.index+'].pgrade'}" value="%{#mycontent.pgrade}" cssClass="input"> </s:textfield>
                        &nbsp;&nbsp;
                        考试成绩：<s:textfield name="%{'templist['+#tempsta.index+'].egrade'}" value="%{#mycontent.egrade}" cssClass="input"></s:textfield>
                        &nbsp;&nbsp;
                        <br><br>
                    </s:iterator>
                    <%
                        dBop.mapsession.remove("tempccourse");
                    %>
                            <s:submit name="%{#nowstatus.index}+'save'" value="保存" method="saveop" cssClass="mybutton"></s:submit>
                    &nbsp;&nbsp;  &nbsp;&nbsp;  &nbsp;&nbsp; 
                    <s:submit name="%{#nowstatus.index}+'submit'" value="查询选课学生" cssClass="mybutton" ></s:submit>
                </s:form>
                <%
                    count++;
                %>
            </s:iterator>
        </div>
    </div>
<script>

    function vanish() {
        x = $(".showpad").children();
        x.fadeOut("fast");
    }
    $("#OpenCourse").click(function(){
       vanish();
       $(".OC").delay("slow").fadeIn();
    });

    $("#StuSelect").click(function(){
        vanish();
        $(".showtable1").delay("slow").fadeIn();
    });

    $(".showtable2").delay("slow").fadeIn();

</script>
</body>
</html>
