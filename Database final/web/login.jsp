<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="org.easybooks.test.factory.CEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: Adrien
  Date: 2017/5/10
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
    <title>Login</title>
    <style type = "text/css">
        body {
            background-color: pink;
            background-size: 100%;
            background-repeat: no-repeat;
        }

        .mainframe{
            align:center;
            background-color:#f0ffff;
            border-style:solid;
            border-radius:20px;
            text-align:center;
            width:50%;
            margin-left:25%;
            margin-right:25%;
            margin-top:10%;
        }
        .mainframe div{
            color : black;
            float : left;
            width:40%;
            margin-left:7%;
        }

        .hint{
            position:relative;
            left:35%;
            margin-top:10px;
        }

        .input{
            border-style:solid;
            border-radius:5px;
            position:relative;
            left:-25%;
            padding-left:2%;
            margin-top:10px;
        }

        .button {
            background: white;
            border-style: solid;
            border-color: black;
            border-radius: 5px;
            width: 25%;
            margin-top: 40px;
            margin-bottom: 10px;
            clear: both;
        }
    </style>
</head>
<body>
    <%
        ActionContext tempo=ActionContext.getContext();
        Map<String,Object> tempsession=tempo.getSession();
        tempsession.clear();
    %>
    <s:form action="login" theme="simple" method="post" cssClass="mainframe">
        <p><s:actionerror/></p>
        <p style = "clear:both;margin-top:3%"><b>Course&nbsp;Manegement&nbsp;System</b></p>
        <br>
        <div>
            <p class = "hint">ID</p>
            <p class = "hint">Password</p>
        </div>

        <div>
            <p><s:textfield name="user.name" cssClass="input"></s:textfield></p>
            <p><s:textfield type="password" name="user.password" cssClass="input"></s:textfield></p>
        </div>
        <!--<p>ID</p><p><s:textfield name="user.name" cssClass="input"></s:textfield></p>
        <p>Password</p><p><s:textfield type="password" name="user.password" cssClass="input"></s:textfield></p>-->
        <s:submit name="学生登录" value="学生登录" method="studentlogin" cssClass="button"/>
        <s:submit name="教师登录" value="教师登录" method="teacherlogin" cssClass="button"/>
        <s:submit name="管理员登录" value="管理员登录" method="administorlogin" cssClass="button"/>
    </s:form>
</body>
</html>

<!--<table>
<td>用户名</td><br>
<tr>
<td><s:textfield name="user.name"></s:textfield></td>
</tr>
<td>密码</td>
<tr>
<td><s:textfield type="password" name="user.password"></s:textfield></td>
</tr>
<tr><td><s:submit name="学生登录" value="学生登录" method="studentlogin"/></td></tr>
<br>
<tr><td><s:submit name="教师登录" value="教师登录" method="teacherlogin"/></td></tr>
<br>
<tr><td><s:submit name="管理员登录" value="管理员登录" method="administorlogin"/></td></tr>
<br>
<tr><td><s:reset name="重置" value="重置"/></td></tr>
</table>-->