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
	
    <div class="center-body">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xs-12">
                    <div class="layout">
                        <div class="header"><span>角色新增</span></div>
                        <div class="body">
                            <div class="layout-inner row">
                            	<div class="col-xs-4 col-xs-offset-4">
                            	<form action="${ctx}/role/add-update" method="post">
                            		<input type="hidden" name="id" id="roleId" value="${role.id}"/>
	                                <div class="control-group" style="height:90px;">
	                                    <label class="control-label">角色类型:</label>
	                                    <div class="controls">
	                                        <input type="text" id="roleCode" name="roleCode" value="${role.roleCode}"
	                                               class="input-large  form-control required"/>
	                                    </div>
	                                </div>                                                        
	                            
	                                <div class="control-group" style="height:90px;">
	                                    <label class="control-label">角色名称:</label>
	                                    <div class="controls">
	                                        <input type="text" id="roleName" name="roleName" value="${role.roleName}"
	                                               class="input-large  form-control required"/>
	                                    </div>
	                                </div>
                                    <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                           <div class="row">
                                               <input id="submit_btn" class="btn btn-primary " style="width: 120px; float: left; margin-left: 15px" type="submit" title="提交" value="提交"/>&nbsp;
                                               <input id="cancel_btn" class="btn btn-primary btn-block"  style="width: 120px; float:left; margin-left: 15px"  type="button" title="返回" value="返回"
                                                      onclick="history.back()"/>
                                           </div>
                                     </div>
                                </form>
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
</body>
</html>
