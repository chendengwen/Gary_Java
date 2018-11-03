<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form action="inputInfo.jsp" method="get">
    <table>
        <tr>
            <td>*昵称<input type="text" name="username"/></td>
        </tr>
        <tr>
            <td><font color=silver size=2>(昵称将在csdn账户中显示)</font></td>
        </tr>
        <tr>
            <td>*密码<input type="text" name="password"/></td>
        </tr>
        <tr>
            <td><font color=silver size=2>（为了您的账户安全安全，建议使用字符英文等多种类型）</font></td>
        </tr>
        <tr>
            <td>*再次输入密码<input type="text" name="apassword"/></td>
        </tr>
        <tr>
            <td><font color=silver size=2>(确保您记住密码)</font></td>
        </tr>
        <tr>
            <td>*注册条款 <input type="radio"/>阅读并同意<a href="csdn.jsp">CSDN</a>协议</td>
        </tr>
        <tr>
            <td> <input type="submit"value="注册"/></td>
        </tr>
    </table>
   </form>

</body>
</html>