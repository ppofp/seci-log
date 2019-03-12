<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%response.setStatus(200);%>

<!DOCTYPE html>
<html>
<head>
	<title>404 - 页面不存在</title>

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
        <span>404！</span>
    </div>
    <div class="font-blue">
        <span>抱歉，您要访问的页面不存在</span>
    </div>
    <div class="main-info">
        <p>非常抱歉，你访问的页面不存在，可能的原因有：</p>
        <p>1.地址错误。</p>
        <p>2.您正在访问一个过去的页面。</p>
    </div>
    <div>
        您可以<a href="${ctx}/index">去首页</a>逛逛!
    </div>
</div>

</body>
</html>