<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.easybooks.test.factory.CEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.aciton.DBop" %><%--
  Created by IntelliJ IDEA.
  User: Adrien
  Date: 2017/5/12
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="jquery.js"></script>
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
            width:200px;
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
            width:200px;
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
            <button id = "choose">选课</button>
            <button id = "searchcourse">查询课程</button>
            <s:form  action="SearchSC" theme="simple" method="post">
                <s:actionerror></s:actionerror>
                <s:submit cssClass= "mybutton" value="查询选课记录"  id = "QueryCourse"></s:submit>
            </s:form>
        </div>
    </div>
	
    <div class = "showpad">
        <s:form action="selectcourse" theme="simple" method="post" cssClass = "SearchCourse">
            <label for = "Coursenum1" style = "font-size:24px;">课号:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<!--<input type = "text" id = "Coursenum1" class = "input"/>-->
			<s:textfield name="course.cnumber" value="no2" cssClass = "input" ></s:textfield>
            <br><br>
            <s:submit value="查询" cssClass = "mybutton" id = "Coursenum1"/>
        </s:form>

        <s:form cssClass = "ChooseCourse" action="searchcourse" theme="simple" method="post">
			<s:fielderror name="searchcourseerror"></s:fielderror>
            <label for = "Coursenum2" style = "font-size:24px;">课号:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<!--<input type = "text" id = "Coursenum2" class = "input"/>-->
			<s:textfield name="course.cnumber" value="no2" cssClass = "input" ></s:textfield>
            <br><br>
            <s:submit value="选课" cssClass = "mybutton" id = "Coursenum2"></s:submit>
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
                ActionContext tempo=ActionContext.getContext();
                Map<String,Object> tempsession=tempo.getSession();
                List<CEntity> tempC= (List<CEntity>) tempsession.get("coursesearchresult");
                if(tempC!=null) {
                    tempsession.remove("coursesearchresult");
                }
            %>
        </table>
        <table class = "showtable2">
        <s:iterator value="#session.tuCourses" var="mycontent" status="tempsta">
            <tr>
                <td>课号：<s:property value="#mycontent[0]"></s:property></td>
                <td>课名：<s:property value="#mycontent[1]"></s:property></td>
                <td>学分：<s:property value="#mycontent[2]"></s:property></td>
                <td>教师：<s:property value="#mycontent[3]"></s:property></td>
                <td>总评成绩：<s:property value="#mycontent[4]"></s:property></td>
            </tr>
        </s:iterator>
            <%
                tempsession.remove("tuCourses");
            %>
        </table>
    </div>
</div>
    <script>
        function vanish() {
            x = $(".showpad").children();
            x.fadeOut("fast");
        }
        $("#choose").click(function()
        {
            vanish();
            $(".ChooseCourse").delay("slow").fadeIn();
        });

        $("#searchcourse").click(function()
        {
            vanish();
            $(".SearchCourse").delay("slow").fadeIn();
        });

        $(".showtable1").delay("slow").fadeIn();
        $(".showtable2").delay("slow").fadeIn();
    </script>
</body>
</html>




