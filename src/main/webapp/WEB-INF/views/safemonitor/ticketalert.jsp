<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>工单详情</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>工单详情</span></div>
                    <div class="body">
                        <div class="layout-inner row">
							<div class="btn-group">
                                <a class="btn btn-default" id="alertConfirm" onclick="history.back()">返回</a>
                            </div>
                            <div class="data-ctrl">
								工单内容：${objects.message}
                            </div>

                            <div class="data-body">
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead><tr><th width="10%">规则编码</th><th width="8%">规则名称</th><th width="15%">告警时间</th><th width="10%">目标IP</th>
                                    	       <th width="5%">级别</th><th width="5%">日志数量</th><th width="25%">描述</th><th width="8%">状态</th></tr></thead>
                                    <tbody>
                                    <tbody>
                                    <c:forEach items="${objects.alerts}" var="object">
                                        <tr>
                                            <td>${object.alertCode}</td>
                                            <td>${object.ruleName}</td>
                                            <td>
                                                <fmt:formatDate value="${object.alertDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                            </td>
                                            <td>${object.objectAddress}</td>
                                            <td>${object.degree}</td>
                                            <td>${object.eventCount}</td>
                                            <td>${object.displayDetail}</td>
                                            <td>${object.processType}</td>
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
