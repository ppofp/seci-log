<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>安全配置维护</title>
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
                                ${dicMsg}
                                    <form id="inputForm" action="${ctx}/config/safeconfig/detail/add-update" method="post"
                                          class="form-horizontal" style="width: 100%; margin-bottom: 30px;">
                                        <input type="hidden" name="id" id="id" value="${object.id}" />
                                        <input type="hidden" name="baseDicId" id="baseDicId" value="${object.baseDicId}" />
                                        <fieldset>
                                            <div class="control-group col-xs-12" >
                                                <label class="control-label" for="groupName">规则名称</label>
                                                <div class="controls">
                                                 	<input type="text" id="name" name="name" value="${object.name}"
                                                           class="input-large  form-control required"/>
                                                </div>
                                            </div>
                                            <div class="control-group col-xs-12" >
                                                <label class="control-label" for="groupName">规则值</label>
                                                <div class="controls">
                                                	<input type="text" id="code" name="code" value="${object.code}"
                                                    	class="input-large  form-control required"/>
                                                </div>
                                            </div>
                                            <div class="control-group col-xs-12" style="height:250px;">
                                                <label class="control-label" for="groupName">备注</label>
                                                <div class="controls">
                                                    <textarea name="remark" class="input-large  form-control" id="remark" cols="30" rows="10">${object.remark}</textarea>
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
</body>
</html>
