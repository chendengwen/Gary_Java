<%-- <%@page import="org.apache.jasper.tagplugins.jstl.core.Param"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>

<%@ page info="jsp page info..." %> <!-- 定义JSP页面的描述信息 -->
<%@ page import="java.util.*"%>
  <head>
    <title>My JSP 'success.jsp' starting page</title>
  </head>
  <body>
  <form action="inputInfo.jsp" method="post">注册成功！<br><input type="submit"value="返回"/>
   </form>
   
   <%
   Enumeration<String> paramNames = request.getParameterNames();
	
   while(paramNames.hasMoreElements()) {
      String paramName = (String)paramNames.nextElement();
      out.print("<tr><td>" + paramName + "</td>\n");
      String paramValue = request.getParameter(paramName);
      out.println("<td> " + paramValue + "</td></tr>\n");
   }
   
	%>
	<p>${param["name"]}</p>
	
	<%-- <jsp:include page="inputInfo.jsp"/> --%>

  </body>
  
</html>