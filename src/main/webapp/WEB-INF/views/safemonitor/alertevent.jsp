<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>告警日志</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>告警日志</span></div>
                    <div class="body">
                        <div class="layout-inner row">
							<div class="btn-group">
                                <a class="btn btn-default" id="alertConfirm" onclick="history.back()">返回</a>
                            </div>
                            <div class="data-ctrl">
								告警内容：${objects.ruleName}:${objects.displayDetail}
                            </div>

                            <div class="data-body">
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead><tr><th width="5%">用户</th><th width="10%">日志时间</th><th width="5%">事件类型</th>
                                    	       <th width="8%">事件名称</th><th width="5%">结果</th><th width="5%">级别</th><th width="8%">源Ip</th>
                                    	       <th width="4%">源端口</th><th width="8%">目标Ip</th><th width="4%">目标端口</th><th width="4%">数量</th><th width="3%">协议</th>
                                    	       <th width="30%">内容</th></tr></thead>
                                    <tbody>
                                    <tbody>
                                    <c:forEach items="${objects.eventLogs}" var="object">
                                        <tr>
                                            <td>${object.userName}</td>
                                            <td>
                                                <fmt:formatDate value="${object.eventDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                            </td>
                                            <td>${object.eventType}</td>
                                            <td>${object.eventName}</td>
                                            <td>${object.result}</td>
                                            <td>${object.level}</td>
                                            <td>${object.sourceIp}</td>
                                            <td>${object.sourcePort}</td>
                                            <td>${object.targetIp}</td>
                                            <td>${object.targetPort}</td>
                                            <td>${object.eventCount}</td>
                                            <td>${object.protocol}</td>
                                            <td>${object.eventDesc}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
