<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctx}/static/zTree/css/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.core-3.5.min.js"></script>
    <%--<script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.excheck-3.5.js"></script>--%>
    <script type="text/javascript" src="${ctx}/static/zTree/js/jquery.ztree.exedit-3.5.js"></script>



</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">

                <div class="layout" style="width: 100%">
                    <div class="header">
                    </div>
                    <div class="body">
                        <div class="layout-inner">
                            <div class="row">
                                <div class="col-xs-4 col-xs-offset-4">
                                    <form id="inputForm" action="${ctx}/organize/add-update" method="post"
                                          class="form-horizontal" style="width: 100%; margin-bottom: 30px;">
                                        <input type="hidden" name="id" id="itemId" value="${organize.id}"/>
                                        <fieldset>
                                            <div class="control-group col-xs-12">
                                                <label class="control-label" for="groupName">组织名称</label>
                                                <div class="controls">
                                                    <input type="text" id="groupName" name="groupName" value="${organize.groupName}"
                                                           class="input-large  form-control"/>
                                                </div>
                                            </div>
                                            <div class="control-group col-xs-12">
                                                <label class="control-label">上级部门</label>
                                                <div class="controls">
                                                    <div class="dropdown ">
                                                        <input type="hidden" id="parentOrganizationId" name="parentOrganizationId" value="${organize.parentOrganizationId}">
                                                        <button class="btn btn-default dropdown-toggle btn-block"
                                                                type="button" id="dropdownMenu1" data-toggle="dropdown" title="选择部门">
                                                            <span class="pull-left">选择父部门</span>
                                                            <i class="caret pull-right"></i>
                                                        </button>
                                                        <ul id="treeDemo" class="ztree dropdown-menu" role="menu"></ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="control-group col-xs-12" style="height:250px;">
                                                <label class="control-label">组织介绍</label>
                                                <div class="controls">
                                                    <textarea name="remark" class="input-large  form-control" id="remark" cols="30" rows="10">${organize.remark}</textarea>
                                                </div>
                                            </div>
                                            <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                                <div class="row">
                                                </div>
                                            </div>
                                            <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                                <div class="row">
                                                    <input id="submit_btn" class="btn btn-primary "
                                                           style="width: 120px; float: left; margin-left: 15px"
                                                           type="submit" value="提交" title="提交"/>&nbsp;
                                                    <input id="cancel_btn" class="btn btn-primary btn-block"
                                                           style="width: 120px; float:left; margin-left: 15px"
                                                           type="button" value="返回"
                                                           onclick="history.back()" title="返回"/>
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


<div class="modal fade" id="modal">
    <div class="modal-dialog error-box">
        <div class="modal-content">
        </div>
    </div>
</div>

<script type="text/javascript">

    var getZtreeNode = function(id,treeNode){
    	
    	if(id == null || id == ''){
    		$("#treeDemo").prev().children('span').text(treeNode.name );
    		$("#parentOrganizationId").val(treeNode.id);
    		return;
    	}
    	
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getSelectedNodes(),
                curSrcNode = zTree.getNodeByParam("id",id,null),
                targetNode = nodes.length>0? nodes[0]:null;
        targetNode = zTree.moveNode(targetNode, curSrcNode, "inner");

        if (!targetNode) {

        }else{
            $("#treeDemo").prev().children('span').text(treeNode.name );
            $("#parentOrganizationId").val(treeNode.id);

        }
        targetNode = curSrcNode;
        delete targetNode.isCur;
        zTree.selectNode(targetNode);
    };
    function onClick(event, treeId, treeNode, clickFlag){
        var _itemId = $("#itemId").val();
        var _id = treeNode.id;

        getZtreeNode(_itemId,treeNode);
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
            onClick: onClick
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

	var init = function(){
		var _initOrganizeId = $('#parentOrganizationId').val();
	}
	init();
</script>
</body>
</html>
