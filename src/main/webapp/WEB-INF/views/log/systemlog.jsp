<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>系统日志</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>系统日志</span></div>
                    <div class="body">
                        <div class="layout-inner row">

                            <div class="data-ctrl">
                           	    <div class="btn-group">
	                                <a id="btnDownload" class="btn btn-primary">导出</a>
	                            </div>

                                <form class="col-xs-5">
                                    <div class="input-group">
                                    	<input type="text" placeholder="操作人" name="search_LIKE_operateName" id="search_LIKE_operateName" class="form-control" value="${param.search_LIKE_operateName}"/>
                                        <span class="input-group-addon" id="btnSearch" title="搜索"><i class="glyphicon glyphicon-search"></i></span>
                                    </div>
                                </form>
                            </div>

                            <div class="data-body">
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead><tr><th width="10%">操作账号</th><th width="10%">操作人</th><th width="25%">操作时间</th><th width="10%">操作ip</th><th width="10%">操作对象</th><th width="10%">操作内容</th><th width="10%">结果</th><th width="25%">备注</th></tr></thead>
                                    <tbody>
                                    <tbody>
                                    <c:forEach items="${objects.content}" var="object">
                                        <tr>
                                            <td>${object.operateCode}</td>
                                            <td>${object.operateName}</td>
                                            <td>
                                                <fmt:formatDate value="${object.operateDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                            </td>
                                            <td>${object.operateIp}</td>
                                            <td>${object.objectName}</td>
                                            <td>${object.operateType}</td>
                                            <td>${object.result}</td>
                                            <td>${object.content}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="data-page"><tags:pagination page="${objects}" paginationSize="5"/></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $('#btnSearch').on('click',function(){
        	$('form').attr('action',"${ctx}/log/systemlog");
            $('form').submit();
        });
        $('#btnDownload').on('click',function(){
        	$('form').attr('action',"${ctx}/log/download/systemlog");
            $('form').submit();
        });
        $('#search_LIKE_userCode').on('keyup',function(e){
            if(e.keyCode == 13){
            	$('form').attr('action',"${ctx}/log/systemlog");
                $('form').submit();
            };
        });
    });
</script>
</body>
</html>
