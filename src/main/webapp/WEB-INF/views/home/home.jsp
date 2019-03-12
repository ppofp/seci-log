<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="webroot"
	   value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>后台首页</title>
	<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<script src="${ctx}/static/echarts/echarts-plain.js"></script>	
	${menutitle}
     <div class="center-body">
         <div class="container-fluid">
             <div class="row">
             	 <div  class=" col-xs-12">
           	       <div class="search-term">
               	 		<div id="vultypeIerm">
               				<div class="term">
               					<label>时间区间:</label>
               					<select id="timeType">
               						<option value="1">一周内</option>
               						<option value="2">一月内</option>
               						<option value="3">三个月内</option>
               						<option value="4">一年内</option>
               						<option value="5">区间查询</option>
               					</select>
               				</div>
               				<div class="term">
                				<label for="txt_vultypeStartTime">开始时间:</label>
                				<input type="text" id="txt_vultypeStartTime" onClick="WdatePicker()" name="txt_vultypeStartTime" value="">
               				</div>
               				<div class="term">
                				<label for="txt_vultypeEndTime">结束时间:</label>
                				<input type="text" id="txt_vultypeEndTime" onClick="WdatePicker()" name="txt_vultypeEndTime" value="">
               				</div>
               				<div>
               					<a class="btn btn-primary" id="btn_search" href="javascript:void(0)" data-type="0">查询</a>
               				</div>

               			</div>
               		</div> 
             	 </div>
                 <div class="col-xs-6">
                 	<div class="index-channle">
						<div class="g-body f-jiankang-body">
							<div class="chart-box">
								<div id="eventlogStat" style="height:320px">日志数量统计</div>
							</div>
						</div>                	
                 	</div>
                 </div>
                 <div class="col-xs-6">
                 	<div class="index-channle">
						<div class="g-body f-jiankang-body">
							<div class="chart-box">
								<div id="alertStat" style="height:320px">告警分布</div>
							</div>
						</div>
                 	</div>
                 </div>
                 <div class="col-xs-6">
                     <div href="javascript:void(0)" class="index-channle" id="topEvent" style="min-height:320px;">
                     </div>
                 </div>
                 <div class="col-xs-6">
                     <div href="javascript:void(0)" class="index-channle" id="topAlert" style="min-height:320px;">
                     </div>
                 </div>
             </div>
         </div>
     </div>
<script type="text/javascript">

var _webroot = "${webroot}";

</script>
<script type="text/javascript" src="${ctx}/static/skin/js/home.js"></script>
</body>
</html>