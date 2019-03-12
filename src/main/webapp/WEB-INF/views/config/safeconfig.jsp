<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>安全配置</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>安全配置</span></div>
                    <div class="body">
                        <div class="layout-inner row">
                        	<input type="hidden" name="dicid" id="dicid" value="${dicid}" />
                        	<div class="data-body col-xs-3">
                                <table width="100%" class="table table-bordered  table-hover">
                                    <thead><tr><th width="5%">字典名</th></tr></thead>
                                    <tbody>
                                    <c:forEach items="${dicList}" var="dic">
                                        <tr>
                                            <td id="dic-${dic.id}"><a href="${ctx}/config/safeconfig/${dic.id}" title="详情">${dic.remark}</td> 
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="data-body col-xs-9">
                            	<div class="btn-group">
                                    <a class="btn btn-default" href="${ctx}/config/safeconfig/detail/add/${dicid}" title="增加">增加</a>
                                </div>
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead><tr><th width="5%">规则名称</th><th width="5%">规则值</th><th width="15%">备注</th><th width="15%">操作</th></tr></thead>
                                    <tbody>
                                    <c:forEach items="${detailList}" var="detail">
                                        <tr>
                                        	<td>${detail.name}</td>
                                        	<td>${detail.code}</td>
                                            <td>${detail.remark}</td>
                                            <td>
                                            	<a href="${ctx}/config/safeconfig/detail/change/${detail.id}" title="修改">修改</a>
                                                <a href="${ctx}/config/safeconfig/detail/delete/${detail.id}" title="删除">删除</a>
                                            </td>
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

<div class="modal fade" id="modal">
    <div class="modal-dialog error-box">
        <div class="modal-content" style="margin:200px auto 0px; width:600px;">
        </div>
    </div>
</div>

<script type="text/javascript">
	<c:if test="${not empty message}">
	$(function(){
	    var _code = ${code};
	    $('#modal').find('.modal-content').html('<div id="message" class="alert ' + (function(){if(_code == 100)return 'alert-success';else return 'alert-danger';})() + '" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>${message}</div>');
	    $('#modal').modal('toggle');
	    setTimeout(function(){$('#modal').modal('hide')},2000)
	});
	</c:if>
	
    $(function(){
        var _dicId = $('#dicid').val();
        if(_dicId && _dicId != "") {
            $("#dic-" + _dicId).css("background","#0e6");
        }
    });
</script>
</body>
</html>
