<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <div class="layout" style="width: 100%">
                    <div class="header">
                    </div>
                    <div class="body">
                        <div class="layout-inner">
                            <div class="row">
                                <div class="col-xs-6 col-xs-offset-3">
                                    <form id="inputForm" action="${ctx}/account/user/dochangepwd/${user.id}" method="get"
                                          class="form-horizontal" style="width: 100%; margin-bottom: 30px;">
                                        <input type="hidden" name="id" id="userId" value="${user.id}"/>
                                        <fieldset>
                                          <div class="control-group col-xs-12">
                                               <label class="control-label">用户密码:</label>

                                               <div class="controls">
                                                   <input type="password" id="oldPassword" name="oldPassword" value=""
                                                          class="input-large  form-control required"/>
                                               </div>
                                           </div>
                                           <div class="control-group col-xs-12">
                                               <label class="control-label">用户新密码:</label>

                                               <div class="controls">
                                                   <input type="password" id="newPassword" name="newPassword" value=""
                                                          class="input-large  form-control required"/>
                                               </div>
                                           </div> 
                                           <div class="control-group col-xs-12">
                                               <label class="control-label">重复密码:</label>
                                               <div class="controls">
                                                   <input type="password" id="confirmPassWord" name="confirmPassWord" value=""
                                                          class="input-large  form-control required"/>
                                               </div>
                                           </div>

                                           <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                               <div class="row">
                                               </div>
                                           </div>
                                           <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                               <div class="row">
                                                   <input id="submit_btn" class="btn btn-primary " style="width: 120px; float: left; margin-left: 15px" type="submit" value="提交" title="提交"/>&nbsp;
                                                   <input id="cancel_btn" class="btn btn-primary btn-block"  style="width: 120px; float:left; margin-left: 15px"  type="button" value="返回"
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
       <div class="modal-content" style="margin:200px auto 0px; width:600px;">
        </div>
    </div>
</div>
<c:if test="${not empty result}">
<script type="text/javascript">
	var _result = ${result};
</script>
</c:if>
<script type="text/javascript" src="${ctx}/static/skin/js/changepwd.js"></script>
</body>
</html>
