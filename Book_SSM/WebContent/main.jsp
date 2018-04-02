<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>BACKSTAGE</title>
</head>

<frameset rows="98,*,25" frameborder="no" border="0" framespacing="0">
  <frame src="<%=basePath %>top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
  <frame src="<%=basePath %>center.html" name="mainFrame" id="mainFrame" />
  <frame src="<%=basePath %>down.html" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
