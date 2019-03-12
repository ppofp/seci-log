<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>seci-log登录</title>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery/jquery.base64.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/skin/css/login.css" />
</head>
<body>
 <div class="login-body">
     <div class="login-header">
         <h4>赛克蓝德日志分析软件</h4>
     </div>
     <form id="loginForm" action="${ctx}/login" method="post" >
         <div class="form-group has-feedback">
             <label class="control-label" for="username">用户名</label>
             <input type="text" class="form-control" id="username"  name="username" value="${username}" placeholder="用户名" autofocus="">
             <i class="glyphicon glyphicon-user text-success form-control-feedback"></i>
         </div>
         <div class="form-group has-feedback">
             <label class="control-label" for="password">用户密码</label>
             <input type="password" class="form-control" id="password"  name="password" placeholder="用户密码" autofocus=""> 
             <i class="glyphicon glyphicon-lock text-success form-control-feedback"></i>
         </div>
         <div class="form-group">
             <div class="checkbox">
                 <label>
                     <input type="checkbox" id="remberpassword"/>记住密码</label>
             </div>
         </div>
         <input id="submit_btn" class="btn btn-lg btn-block" type="submit" name="submit_btn" value="登录" title="登录"/>
         <p class="text-danger">
<%
String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
if (error != null) {
%>
登录失败，请重试.
<%
}
%>
</p>
              </form>
                  
          </div>

<script type="text/javascript" src="${ctx}/static/skin/js/login.js"></script>
</body>
</html>