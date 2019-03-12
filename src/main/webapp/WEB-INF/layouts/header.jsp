<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page import="org.apache.shiro.SecurityUtils"%>

<div class="navbar">
	<input type="hidden" id='webpath' value="${ctx}" /> <span><img
		src="${ctx}/static/skin/images/logo.png" height="50" alt="" /></span>
	<ul class="pull-right nav m-user">
		<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
				<i class="fa fa-user"></i> <shiro:principal property="name" /> <i
				class="caret"></i>
		</a>

			<div class="dropdown-menu">
				<ul>
					<li><a
						href="${ctx}/account/user/changepwd/<shiro:principal property="name"/>"
						title="修改密码"> <i class="fa fa-user"></i> 修改密码
					</a></li>
				</ul>
			</div></li>
		<li><a href="#" title="关于" id="btn_about">关于</a></li>
		<li><a href="${ctx}/login/out" title="退出">退出</a></li>
	</ul>

</div>