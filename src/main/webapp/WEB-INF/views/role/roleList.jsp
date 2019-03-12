<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
	<link rel="stylesheet" href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.exedit-3.5.js"></script>
</head>

<body>
	${menutitle}
    <div class="center-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <div class="layout">
                        <div class="header"><span>角色列表</span></div>
                        <div class="body">
                            <div class="layout-inner row">
                                <div class="data-ctrl">
                                    <div class="btn-group">
                                        <a class="btn btn-default" href="${ctx}/role/add" title="增加">增加</a>

                                    </div>
                                    
                                </div>

                                <div class="data-body">
                                	
                                    <table width="100%" class="table table-bordered table-striped table-hover">
                                        <thead><tr>
                                        	<th width="20%">角色类型</th>
                                        	<th width="20%">角色名称</th>
                                        	<th width="25%">添加时间</th>
                                        	<th width="10%">状态</th>
                                        	<th width="25%">管理</th>
                                        </tr></thead>
                                        <tbody>
                                        <c:forEach items="${objects}" var="role">
                                            <tr>
                                                <td><a href="${ctx}/account/user/update/${role.id}">${role.roleCode}</a></td>
                                                <td>${role.roleName}</td>
                                                <td>
                                                    <fmt:formatDate value="${role.createdDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                                </td>
                                                <td>
                                                <c:if test="${role.activeFlag=='Y'}">有效</c:if>
                                                <c:if test="${role.activeFlag=='N'}">无效</c:if>
                                                </td>
                                                <td>
                                                	<a href="${ctx}/role/rolemenu/${role.id}" title="角色授权">角色授权</a>
                                                	<a href="${ctx}/role/updata/${role.id}" title="修改">修改</a>
                                                	<a href="javascript:void(0)"data-id="${role.id}" class="op-delete" title="删除">删除</a>
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
 	<c:if test="${not empty message}">
        <script type="text/javascript">
            $(function(){
                var _code = ${code};
                $('#modal').find('.modal-content').html('<div id="message" class="alert ' + (function(){if(_code == 100)return 'alert-success';else return 'alert-danger';})() + '" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>${message}</div>');
                $('#modal').modal('toggle');
                setTimeout(function(){$('#modal').modal('hide')},2000)
            })


        </script>
	</c:if>
 	<script type="text/javascript">

    $(function(){
        $('.op-delete').on('click',function(){
            var _id =$(this).attr('data-id');
            var _modalStr = "";
            _modalStr += '<div class="modal-header" style="top:200px;">';
            _modalStr += '<h4 class="modal-title">删除记录</h4>';
            _modalStr += '</div>';
            _modalStr += '<div class="modal-body">确定要删除么？</div>';
            _modalStr += '<div class="modal-footer">';
            _modalStr += '<a type="button" id="btn_submit" href="${ctx}/role/delete/ ';
            _modalStr += _id + '"class="btn">删除</a>';
            _modalStr += '<button type="button" class="btn" data-dismiss="modal">取消</button>';
            _modalStr += '</div>';
            $('#modal').find('.modal-content').html(_modalStr);
            $('#modal').modal('show');
        });
    });
    
 	</script>
</body>
</html>
