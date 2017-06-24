<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.easybooks.test.factory.CEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Adrien
  Date: 2017/5/12
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
    <title>管理员页面</title>
    <script src="jquery.js"></script>
    <script>
        function ass(obj){
            document.getElementById("CreateStu").innerHTML = "ass♂we♂can";
        }
        function notass(){
            document.getElementById("CreateStu").innerHTML = "创建学生";
        }
    </script>
    <style>
        body{

            margin:100px;
            background-image : url('bgd.png');
            width:1400px;
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
            flex-wrap:wrap;
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
            <s:actionerror name="adminselectcourseerror" id="ss1"></s:actionerror>
            <p><a href = "#"
                  style = "color:white;font-size:20px;position:relative;top:70px;" onclick="javascript:
        if(confirm('确定退出？'))
        {
            parent.location.href='login.jsp';
        }
        else{
            return false;
        }">退出</a></p>
        </div>

        <div class = "function">
            <button id = "QueryCourse">查询课程</button>
            <button id = "DeleteCourse">删除课程</button>
            <button id = "CreateStu" onmouseover = ass(); onmouseout = notass();>创建学生</button>
            <button id = "DelStu">删除学生</button>
            <button id = "CreateTea">创建教师</button>
            <button id = "DelTea">删除教师</button>
            <s:fielderror name="coursenumbererror"></s:fielderror>
        </div>
    </div>
    <div class = "showpad">
        <s:form action="adminselectcourse" theme="simple" method="post" cssClass = "QC">
            <s:textfield name="course.cnumber" value="请输入要查询的课程号" cssClass = "input"></s:textfield>
            <br><br>
            <s:submit value="查询" cssClass = "mybutton"></s:submit>
        </s:form>

        <table class = "showtable1">
            <s:iterator value="#session.coursesearchresult" var="mycontent" status="tempsta">
                <tr>
                    <td>课号：<s:property value="#mycontent.cno"></s:property></td><td>    </td>
                    <td>课程名：<s:property value="#mycontent.cname"></s:property></td><td>     </td>
                    <td>学分：<s:property value="#mycontent.credit"></s:property></td><td>     </td>
                    <td>所属院系：<s:property value="#mycontent.cdept"></s:property></td><td>    </td>
                    <td>教师名：<s:property value="#mycontent.tname"></s:property></td><td>     </td>
                </tr>
            </s:iterator>
            <%
                ActionContext tempo = ActionContext.getContext();
                Map<String,Object> tempsession=tempo.getSession();
                List<CEntity> tempC= (List<CEntity>) tempsession.get("coursesearchresult");
                if(tempC!=null) {
                    tempsession.remove("coursesearchresult");
                }
            %>
        </table>


        <s:form action="deletecourse" theme="simple" method="POST" cssClass = "DC">
            <s:textfield value="请输入要删除的课程号" name="coursenumber" cssClass = "input"></s:textfield>
            <br><br>
            <s:submit value="删课" cssClass = "mybutton"></s:submit>
            <br>
            <s:submit value="删除该课相关的选课记录" method="deleteCRSC" cssClass = "mybutton"></s:submit>
        </s:form>


        <s:form action="createstu" theme="simple" method="POST" cssClass = "CS">
            学号：&nbsp;&nbsp;<s:textfield name="crsno" cssClass = "input"></s:textfield>
            <br><br>
            姓名：&nbsp;&nbsp;<s:textfield name="sname" cssClass = "input"></s:textfield>
            <br><br>
            性别：&nbsp;&nbsp;<s:textfield name="sex" cssClass = "input"></s:textfield>
            <br><br>
            年龄：&nbsp;&nbsp;<s:textfield name="age" cssClass = "input"></s:textfield>
            <br><br>
            院系：&nbsp;&nbsp;<s:textfield name="sdept" cssClass = "input"></s:textfield>
            <br><br>
            登录账户：&nbsp;&nbsp;<s:textfield name="logn" cssClass = "input"></s:textfield>
            <br><br>
            密码：&nbsp;&nbsp;<s:textfield name="pswd" cssClass = "input"></s:textfield>
            <br><br>
            <s:submit value="提交" cssClass = "mybutton"></s:submit>
        </s:form>

        <s:form action="deletestu" theme="simple" method="POST" cssClass = "DS">
            学号：&nbsp;&nbsp;<s:textfield name="sno" cssClass = "input"></s:textfield>
            <br><br>
            <s:submit value="删除该学生" cssClass = "mybutton"></s:submit>
            <br>
            <s:submit value="删除该学生相关的选课记录" method="deleteSRSC" cssClass = "mybutton"></s:submit>
        </s:form>

        <s:form action="createteacher" theme="simple" method="POST" cssClass = "CT">
            教师号：&nbsp;&nbsp;<s:textfield name="tno" cssClass = "input"></s:textfield>
            <br><br>
            姓名：&nbsp;&nbsp;<s:textfield name="tname" cssClass = "input"></s:textfield>
            <br><br>
            性别：&nbsp;&nbsp;<s:textfield name="xb" cssClass = "input"></s:textfield>
            <br><br>
            院系：&nbsp;&nbsp;<s:textfield name="dept" cssClass = "input"></s:textfield>
            <br><br>
            登录账户：&nbsp;&nbsp;<s:textfield name="logn" cssClass = "input"></s:textfield>
            <br><br>
            密码：&nbsp;&nbsp;<s:textfield name="pswd" cssClass = "input"></s:textfield>
            <br><br>
            职称：&nbsp;&nbsp;<s:textfield name="zc" cssClass = "input"></s:textfield>
            <br><br>
            <s:submit value="创建" cssClass = "mybutton"></s:submit>
        </s:form>

        <s:form action="deleteteacher" theme="simple" method="POST" cssClass = "DT">
            教师号：&nbsp;&nbsp;<s:textfield name="dttno" cssClass = "input"></s:textfield>
            <br><br>
            <s:submit value="删除该教师" cssClass = "mybutton"></s:submit>
            <br>
            <s:submit value="删除该教师相关的课程" method="deleteTRC" cssClass = "mybutton"></s:submit>
        </s:form>
    </div>
</div>

<script>
    function vanish() {
        x = $(".showpad").children();
        x.fadeOut("fast");
    }
   $("#QueryCourse").click(function(){
      vanish();
       $(".QC").delay("slow").fadeIn();
   });

    $("#DeleteCourse").click(function(){
        vanish();
        $(".DC").delay("slow").fadeIn();
    });

    $("#CreateStu").click(function(){
        vanish();
        $(".CS").delay("slow").fadeIn();
    });

    $("#DelStu").click(function(){
        vanish();
        $(".DS").delay("slow").fadeIn();
    });

    $("#CreateTea").click(function(){
        vanish();
        $(".CT").delay("slow").fadeIn();
    });

    $("#DelTea").click(function(){
        vanish();
        $(".DT").delay("slow").fadeIn();
    });

    $(".showtable1").delay("slow").fadeIn();
</script>
</body>
</html>


