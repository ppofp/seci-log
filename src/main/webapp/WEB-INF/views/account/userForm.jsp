<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				${error}
                <div class="layout" style="width: 100%">
                    <div class="header">
                    <span>修改账号</span>
                    </div>
                    <div class="body">
                        <div class="layout-inner">
                            <div class="row">
                                <div class="col-xs-8 col-xs-offset-2">
                                    <form id="inputForm" action="${ctx}/account/user/add-update" method="post"
                                          class="form-horizontal" style="width: 100%; margin-bottom: 30px;">
                                        <input type="hidden" name="id" id="userId" value="${user.id}"/>
                                        <fieldset>
                                            <div class="control-group col-xs-6">
                                                <label class="control-label">登录名:(<span style="color:red">必填</span>)</label>

                                                <div class="controls">
                                                    <input type="text" id="userCode" name="userCode" value="${user.userCode}"
                                                           class="input-large  form-control required"/>
                                                </div>
                                            </div>
                                            <div class="control-group col-xs-6">
                                                <label class="control-label">用户名:(<span style="color:red">必填</span>)</label>

                                                <div class="controls">
                                                    <input type="text" id="userName" name="userName" value="${user.userName}"
                                                           class="input-large  form-control required"/>
                                                </div>
                                            </div> 
                                            <div class="control-group col-xs-12">
                                                <label class="control-label">所在部门</label>
                                                <div class="controls">
                                                    <div class="dropdown ">
                                                        <input type="hidden" id="parentOrganizationId" name="smOrganization.id" value="${user.smOrganization.id}">
                                                        <button class="btn btn-default dropdown-toggle btn-block"
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
                                            ${role}
                                            <div class="form-actions  col-xs-12" style="margin-top: 35px;">
                                                <div class="row">
                                                    <input id="submit_btn" class="btn btn-primary " style="width: 120px; float: left; margin-left: 15px" type="button" title="提交" value="提交"/>&nbsp;
                                                    <input id="cancel_btn" class="btn btn-primary btn-block"  style="width: 120px; float:left; margin-left: 15px"  type="button" title="返回" value="返回"
                                                           onclick="history.back()"/>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#loginName").focus();
		//为inputForm注册validate函数
		var _userId = $('#userId').val();
		
		if(_userId && _userId != ""){
			
		}else{
			$("#inputForm").validate({
				rules: {
					userCode: {
						remote: "${ctx}/account/user/checkLoginName"
					}
				},
				messages: {
					userCode: {
						remote: "用户登录名已存在"
					}
				}
			});			
		}
	});

	// 选择部门是哦户的支持的事件
    function onClick(event, treeId, treeNode, clickFlag){
        $('#parentOrganizationId').val(treeNode.id);
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
            }
        })

    })(jQuery);
    // 
    $(function(){
    	var initUserRole = function(){
    		var _userRole = ${userRole};
    		if(_userRole && _userRole.length > 0){
    			var count = _userRole.length;
    			for(var i = 0; i < count; i++){
    				$("#role_"+_userRole[i].id).find('input[type="checkbox"]')[0].checked = true;
    			}
    		}
    	};
    	var initSubmit = function(){
    		$('#submit_btn').on('click',function(){
    			var _roleList = $('form').find('.checkbox');
    			var roleStr = '';
    			
    			if(_roleList && _roleList.length > 0){
    				for(var i = 0; i < _roleList.length; i++){

    					var isChecked =  $(_roleList[i]).find('input[type="checkbox"]')[0].checked;
    					
    					if(isChecked){
        					if(i != 0){
        						roleStr += ',';
        					}
        					roleStr += $($(_roleList[i]).find('label')).attr('data-roleid');
    					}
    					
    				}
    			}
    			$('#userRole').val(roleStr);
    			$('form').submit();
    		});
    	};
    	
    	initUserRole();
    	initSubmit();
    });
</script>
</body>
</html>
