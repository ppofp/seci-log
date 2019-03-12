<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>后台首页</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/skin/css/common.css" />
    <script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
    <sitemesh:head/>
</head>

<body>
    <div class="nav header">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
    </div>
    <div class="main">
    	<%@ include file="/WEB-INF/layouts/left.jsp"%>
    	<div class="center">
    		<sitemesh:body/>
    	</div>
    </div>
    <script type="text/javascript" src="${ctx}/static/skin/js/common.js"></script>
</body>
</html>