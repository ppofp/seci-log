<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户管理</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>组织管理</span></div>
                    <div class="body">
                        <div class="layout-inner row">

                            <div class="data-ctrl">
                                <div class="btn-group">
                                    <a class="btn btn-default" href="${ctx}/organize/add" >增加</a>
                                </div>
                                <form class="col-xs-5">
                                    <div class="input-group">
                                    	<input type="hidden" name="sortType" id="sortType"  value="${param.sortType}" />
                                        <input type="text" class="form-control" name="search_LIKE_groupName" id="search_LIKE_groupName"  placeholder="组织名称" class="form-control" value="${param.search_LIKE_groupName}">
                                        <span class="input-group-addon" id="btn-search" title="搜索"><i class="glyphicon glyphicon-search"></i></span>
                                    </div>
                                </form>

                            </div>

                            <div class="data-body">
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead>
                                    	<tr>
                                    		<th width="15%"><a id="btn-sortType-groupName" href="javascript:void(0)" title="按组织名称进行排序">组织名称<i class="glyphicon glyphicon-arrow-down"></i></a></th>
                                    		<th width="25%"><a id="btn-sortType-createdDate" href="javascript:void(0)" title="按创建时间进行排序">创建时间<i class="glyphicon glyphicon-arrow-down"></i></a></th>
                                    		<th width="10%">状态</th>
                                    		<th width="30%">备注</th>
                                    		<th width="20%">管理</th>
                                    	</tr>
                                    </thead>
                                    <tbody>
                                    <tbody>
                                    <c:forEach items="${organizes.content}" var="item">
                                        <tr>
                                            <td><a href="javascript:void(0)" data-url="${item.id}">${item.groupName}</a></td>
                                            <td>
                                            	<fmt:formatDate value="${item.createdDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                            </td>
                                            
                                            <td> 
                                            <c:if test="${item.activeFlag=='Y'}">可用</c:if>
                                            <c:if test="${item.activeFlag=='N'}">不可用</c:if>
                                            </td>
                                            <td>
                                                ${item.remark}
                                            </td>
                                            <td width="100"><a href="${ctx}/organize/update/${item.id}" title="修改">修改</a>
                                                <a href="javascript:void(0)" data-id="${item.id}" class="op-delete" title="删除">删除</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                            <div class="data-page"><tags:pagination page="${organizes}" paginationSize="5"/></div>
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
        $('#btn-search').on('click', function () {
            $('form').submit();
        });
        $('#search_LIKE_groupName').on('keyup', function (e) {
            if (e.keyCode == 13) {
                $('form').submit();
            }
        });

        $('.op-delete').on('click',function(){
            var _id =$(this).attr('data-id');
            var _modalStr = "";
            _modalStr += '<div class="modal-header">';
            _modalStr += '<h4 class="modal-title">删除记录</h4>';
            _modalStr += '</div>';
            _modalStr += '<div class="modal-body">确定要删除么？</div>';
            _modalStr += '<div class="modal-footer">';
            _modalStr += '<a type="button" id="btn_submit" href="${ctx}/organize/delete/ ';
            _modalStr += _id + '"class="btn">删除</a>';
            _modalStr += '<button type="button" class="btn" data-dismiss="modal">取消</button>';
            _modalStr += '</div>';
            $('#modal').find('.modal-content').html(_modalStr);
            $('#modal').modal('show');

        });
        
        var initSrotType = function(){
        	var _startSortType = $('#sortType').val();
        	$('#btn-sortType-groupName').find('i').removeClass('glyphicon-arrow-down');
        	$('#btn-sortType-createdDate').find('i').removeClass('glyphicon-arrow-down');
        	
        	if(_startSortType == 'groupName'){
        		$('#btn-sortType-groupName').find('i').addClass('glyphicon-arrow-down');
        	}
        	if(_startSortType == 'createdDate'){
        		$('#btn-sortType-createdDate').find('i').addClass('glyphicon-arrow-down');
        	}
        	$('#btn-sortType-groupName').on('click',function(){
        		$('#sortType').val('groupName');
        		$('form').submit();
        	});
        	$('#btn-sortType-createdDate').on('click',function(){
        		$('#sortType').val('createdDate');
        		$('form').submit();
        	});
        }
        initSrotType();
    });
</script>

</body>
</html>
