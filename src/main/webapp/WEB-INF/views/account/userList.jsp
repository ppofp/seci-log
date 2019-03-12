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
                        <div class="header"><span>用户管理</span></div>
                        <div class="body">
                            <div class="layout-inner row">
                                <div class="data-ctrl">
                                    <div class="btn-group">
                                        <a class="btn btn-default" href="${ctx}/account/user/add" title="增加">增加</a>
                                        <a class="btn btn-default" href="${ctx}/account/user/upload" title="导入用户">导入用户</a>
                                    </div>
                                    <form class="col-xs-5">
                                        <div class="input-group">
                                            <div class="control-group col-xs-6" style="height:34px;">
                                                <div class="controls">
                                                    <div class="dropdown ">
                                                        <input type="hidden" id="search_EQ_smOrganization1groupName" name="search_EQ_smOrganization.groupName" 
                                                        value="<%if(request.getParameter("search_EQ_smOrganization.groupName")!= null){ 
                                                        	//el表达式目前的测试不支持对象，只能用原生jsp
														out.print(request.getParameter("search_EQ_smOrganization.groupName").toString());}%>">
                                                        <button class="btn btn-default form-control dropdown-toggle btn-block drop-btn"
                                                                type="button" id="dropdownMenu1" data-toggle="dropdown">
                                                            <span class="pull-left">
                                                             	<c:if test="${user.smOrganization == null}">
																  请选择部门
																</c:if>
																<c:if test="${user.smOrganization != null }">
																  ${user.smOrganization.groupName}
																</c:if>
																
															</span>
                                                            <i class="caret pull-right"></i>
                                                        </button>
                                                        <ul id="treeDemo" class="ztree dropdown-menu" role="menu"></ul>
                                                    </div>
                                                </div>
                                            </div>
                                             <div class="control-group col-xs-6"  style="height:34px; padding-right:0px;">
                                             	<div class="controls">
                                             	<input type="text" placeholder="登录名" name="search_LIKE_userCode" id="search_LIKE_userCode" class="form-control" value="${param.search_LIKE_userCode}"/>
                                             	</div>
                                             </div>
                                        	<input type="hidden" name="sortType" id="sortType" value="${sortType}"/>
                                            <%--<input type="submit" value="查询"/>--%>
                                            <span class="input-group-addon" id="btnSearch" title="搜索"><i class="glyphicon glyphicon-search"></i></span>
                                        </div>
                                    </form>
                                </div>

                                <div class="data-body">
                                	
                                    <table width="100%" class="table table-bordered table-striped table-hover">
                                        <thead><tr>
                                        	<th width="150"><a id="btn-sortType-userCode" href="javascript:void(0)" title="按登录名进行排序">登录名<i class="glyphicon glyphicon-arrow-down"></i></a></th>
                                        	<th width="150"><a id="btn-sortType-userName" href="javascript:void(0)" title="按用户名进行排序">用户名<i class="glyphicon glyphicon-arrow-down"></i></a></th>
                                        	<th width="100">部门</th>
                                        	<th width="250">注册时间</th>
                                        	<th width="80">状态</th>
                                        	<th width="230">管理</th>
                                        </tr></thead>
                                        <tbody>
                                        <tbody>
                                        <c:forEach items="${objects.content}" var="user">
                                            <tr>
                                                <td><a href="${ctx}/account/user/update/${user.id}">${user.userCode}</a></td>
                                                <td>${user.userName}</td>
                                                <td>${user.smOrganization.groupName}</td>
                                                <td>
                                                    <fmt:formatDate value="${user.createdDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                                </td>
                                                <td>
                                                	<c:if test="${user.state=='active'}">有效</c:if>
                                                	<c:if test="${user.state=='lock'}">锁定</c:if>
                                                	<c:if test="${user.state=='apply'}">申请</c:if>
                                                </td>
                                                <td><a href="${ctx}/account/user/update/${user.id}" title="修改">修改</a>
                                                	<a href="javascript:void(0)"data-id="${user.id}" class="op-delete" title="删除">删除</a>
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
            $('#btnSearch').on('click',function(){
                $('form').submit();
            });
            $('#search_LIKE_userCode').on('keyup',function(e){
                if(e.keyCode == 13){
                    $('form').submit();
                };
            });
        });

        $(function(){
            $('.op-delete').on('click',function(){
                var _id =$(this).attr('data-id');
                var _modalStr = "";
                _modalStr += '<div class="modal-header" style="top:200px;">';
                _modalStr += '<h4 class="modal-title">删除记录</h4>';
                _modalStr += '</div>';
                _modalStr += '<div class="modal-body">确定要删除么？</div>';
                _modalStr += '<div class="modal-footer">';
                _modalStr += '<a type="button" id="btn_submit" href="${ctx}/account/user/delete/ ';
                _modalStr += _id + '"class="btn">删除</a>';
                _modalStr += '<button type="button" class="btn" data-dismiss="modal">取消</button>';
                _modalStr += '</div>';
                $('#modal').find('.modal-content').html(_modalStr);
                $('#modal').modal('show');
            });
        });
        
    	// 选择部门是哦户的支持的事件
        function onClick(event, treeId, treeNode, clickFlag){
            $('#search_EQ_smOrganization1groupName').val(treeNode.name);
            $('#dropdownMenu1').find('span').text(treeNode.name);
        }

        var setting = {
            data: {
                key: {
                    title:"name"
                },
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick,
            }
        };
        var zNodes =[];
        var InitZeeTree = (function($){
            var _url = "${ctx}/api/sso/org";

            $.ajax({
                url:_url,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data && data.length>0){

                        for(var i = 0; i < data.length; i++){
                            var _node = {id:data[i].id,pId:data[i].parentOrganizationId || 0,name:data[i].groupName,open:true,tId:data[i].id};
                            zNodes.push(_node);
                        }
                        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                    }
                },
                error:function(){
                    $('#modal').find('.modal-content').html('<div id="message" class="alert alert-danger" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>${message}</div>');
                    $('#modal').modal('show');
                    setTimeout(function(){$('#modal').modal('hide')},2000)
                }
            })

        })(jQuery);
        
        $(function(){
        	var _organizeName = $('#dropdownMenu1').children('span').text();
        });
        
        $(function(){
        	
        	var _sortType = $('#sortType').val();
        	$('#btn-sortType-userCode').find('i').removeClass('glyphicon-arrow-down');
        	$('#btn-sortType-userName').find('i').removeClass('glyphicon-arrow-down');
        	
        	if(_sortType == 'userCode'){
        		$('#btn-sortType-userCode').find('i').addClass('glyphicon-arrow-down');	
        	}
        	if(_sortType == 'userName'){
        		$('#btn-sortType-userName').find('i').addClass('glyphicon-arrow-down');
        	}
        	
        	var _groupName = $('#search_EQ_smOrganization1groupName').val();
        	
        	if(_groupName == '' || $.trim(_groupName) == ''){
        		$('#dropdownMenu1').children('span').html('部门');
        	}else{
        		$('#dropdownMenu1').children('span').html(_groupName );
        	}

        	$('#btn-sortType-userCode').on('click',function(){
        		if(_sortType = 'userCode'){
        			$('#sortType').val('userCode');
        			$('form').submit();
        		}
        	});
        	$('#btn-sortType-userName').on('click',function(){
        		if(_sortType != 'userName'){
        			$('#sortType').val('userName');
        			$('form').submit();
        		}
        	});

        })
    </script>
</body>
</html>
