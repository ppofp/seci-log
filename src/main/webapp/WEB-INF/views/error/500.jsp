<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	response.setStatus(200);
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(exception.getMessage(), exception);
%>

<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
    <style>
        body,html{margin: 0px; padding:0px; background-color: #F0F4F7; font-family: "microsoft yahei"}
        div{ width: 100%;}
        .main-div { left: 50%; top:150px; margin-left: -250px; width: 500px; height: 300px; background-color: #fff; position: absolute; padding: 10px}
        .main-info{ width: 370px; text-align: left;  border-bottom:1px dashed #666; margin: 0px 40px 10px 40px; padding: 0px 25px;}
        .font-orange{ color: #f60; }
        .font-big-size{ font-size: 60px;}
        .text-center{ text-align: center;}
        .font-blue{ color: #017398;}
        a{ display: inline-block; padding: 4px 15px;background-color: #017398; color: #fff; text-decoration: none; margin: 0px 8px; border-radius: 3px;}
    </style>
</head>

<body>
<div class="main-div text-center">
    <div class="font-orange font-big-size">
        <span>500！</span>
    </div>
    <div class="font-blue">
        <span>抱歉，系统发生错误</span>
    </div>
    <div class="main-info">
        <p>非常抱歉，系统内部发生错误，我们正在努力调整，</p>
        <p>本系统错误给您带来的不便我们深表歉意</p>

    </div>
    <div>
       在bug解决之前，您可以<a href="${ctx}/index">去首页</a>逛逛!
    </div>
</div>

</body>
</html>
