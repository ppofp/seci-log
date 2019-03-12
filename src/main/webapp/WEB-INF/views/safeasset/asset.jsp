<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>安全资产查看</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>安全资产查看</span></div>
                    <div class="body">
                        <div class="layout-inner row">

                            <div class="data-ctrl">
                                <div class="btn-group">
                                    <a class="btn btn-default" href="${ctx}/safeasset/asset/add" >增加</a>
                                    <a class="btn btn-default" href="${ctx}/safeasset/asset/scanadd" >扫描资产</a>
                                </div>
                                <form class="col-xs-5">
                                    <div class="input-group">
                                    	<div class="control-group col-xs-6" style="height:34px;">
                                    		<div class="controls">
                                    			<input type="text" placeholder="资产编码" name="search_LIKE_safeAssetCode" id="search_LIKE_safeAssetCode" class="form-control  col-xl-5" value="${param.search_LIKE_safeAssetCode}"/>
                                    		</div>
                                    	</div>
                                    	<div class="control-group col-xs-6" style="padding-right:0px; height:34px;">
                                    		<div class="controls">
	                                   			<input type="text" placeholder="资产IP" name="search_LIKE_ip" id="search_LIKE_ip" class="form-control col-xl-5" value="${param.search_LIKE_ip}"/>
	                                   		</div>
	                                   	</div>
                                        <span class="input-group-addon" id="btnSearch" title="搜索"><i class="glyphicon glyphicon-search"></i></span>
                                    </div>
                               </form>
                            </div>

                            <div class="data-body">
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead><tr><th width="15%">资产编码</th><th width="15%">资产名称</th><th width="10%">IP</th>
                                    	       <th width="5%">厂家</th><th width="5%">负责人</th><th width="25%">备注</th><th width="25%">操作</th></tr></thead>
                                    <tbody>
                                    <tbody>
                                    <c:forEach items="${objects.content}" var="object">
                                        <tr>
                                            <td>${object.safeAssetCode}</td>
                                            <td>${object.safeAssetName}</td>
                                            <td>${object.ip}</td>
                                            <td>${object.factory}</td>
                                            <td>${object.userName}</td>
                                            <td>${object.remark}</td>
                                            <td>
                                            	<a href="${ctx}/safeasset/asset/${object.id}" title="修改">修改</a>
                                            	<a href="javascript:void(0)" data-id="${object.id}" class="op-delete" title="删除">删除</a>
                                            </td>
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
        $('.op-delete').on('click',function(){
            var _id =$(this).attr('data-id');
            var _modalStr = "";
            _modalStr += '<div class="modal-header">';
            _modalStr += '<h4 class="modal-title">删除记录</h4>';
            _modalStr += '</div>';
            _modalStr += '<div class="modal-body">确定要删除么？</div>';
            _modalStr += '<div class="modal-footer">';
            _modalStr += '<a type="button" id="btn_submit" href="${ctx}/safeasset/delete/';
            _modalStr += _id + '"class="btn">删除</a>';
            _modalStr += '<button type="button" class="btn" data-dismiss="modal">取消</button>';
            _modalStr += '</div>';
            $('#modal').find('.modal-content').html(_modalStr);
            $('#modal').modal('show');

        });
        
        $('#btnSearch').on('click',function(){
        	$('form').attr('action',"${ctx}/safeasset/asset");
            $('form').submit();
        });
        $('#btnDownload').on('click',function(){
        	$('form').attr('action',"${ctx}/safeasset/asset");
            $('form').submit();
        });
    });
</script>
</body>
</html>
