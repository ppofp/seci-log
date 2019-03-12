<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>工单确认</title>
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
                                    <form id="inputForm" action="${ctx}/safemonitor/ticket/saveTicket" method="post"
                                          class="form-horizontal" style="width: 100%; margin-bottom: 30px;">
                                        <input type="hidden" name="id" id="itemId" value="${alert.id}" />
                                        <c:forEach items="${tickets}" var="objects" varStatus="status">
                                        <input type="hidden" name="tickets" id="tickets[${ status.index}]" value="${objects}"/>
                                        </c:forEach>
                                        <fieldset>
                                            <div class="control-group col-xs-12">
                                                <label class="control-label" for="groupName">确认方式</label>
                                                <div class="controls">
	                                                <select id="processType" name="processType">
													  <option value ="process">处理中</option>
													  <option value ="close">处理完毕</option>
													</select>
                                                </div>
                                            </div>
                                            <div class="control-group col-xs-12" style="height:250px;">
                                                <label class="control-label" for="groupName">处理建议</label>
                                                <div class="controls">
                                                    <textarea name="memo" class="input-large  form-control" id="memo" cols="30" rows="10">${object.processRemark}</textarea>
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
