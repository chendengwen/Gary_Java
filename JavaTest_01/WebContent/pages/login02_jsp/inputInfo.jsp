<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  <head>
    <title>My JSP 'resig.jsp' starting page</title>
  </head>
  <body>
    <form action="success.jsp" method="post">
        <table>
            <tr><td>姓名：<input type="text" name="name"/></td><td>昵称<input type="text" value=<%= request.getParameter("username") %>></td></tr>
            <tr><td>性别：<input type="radio" name=sex/>男<input type="radio"name=sex/>女</td></tr>
            <tr><td>所在系部：<select>
                <option>信息系</option>
                <option>经管系</option>
                <option>文法系</option>
            </select></td></tr>
            <tr><td>所选课程：</td></tr>
            <tr><td><input type="checkbox" name="class01"/>python程序设计</td></tr>
            <tr><td><input type="checkbox" name="class02"/>计算机网络</td></tr>
            <tr><td><input type="checkbox" name="class03"/>高等数学</td></tr>
            <tr><td><input type="checkbox" name="class04"/>jspweb</td></tr>
            <tr><td><input type="checkbox" name="class05"/>java</td></tr>
            <tr><td><input type="submit" value="提交"/>&nbsp&nbsp<input type="reset"value="重置"/></td></tr>
        </table>
    </form>
  </body>
</html>
