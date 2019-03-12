<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="webroot" value="${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

<html>
<head>
    <title>资产修改</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">

                <div class="layout" style="width: 100%">
                    <div class="header"><span>扫描资产</span></div>
                    <div class="body">
                        <div class="layout-inner">
                            <div class="row">
                                <div class="col-xs-4 col-xs-offset-4">
                                    <form id="inputForm" action="${ctx}/safeasset/asset/scanadd" method="post"
                                          class="form-horizontal" style="width: 100%; margin-bottom: 30px;">
                                        <fieldset>
                                            <div class="control-group col-xs-12">
											<label id="iplabel" class="control-label" for="groupName">IP段:</label>
                                            <div class="controls">
                                               <input type="text" id="ips" name="ips" value="${ips}"
                                                  class="input-large  form-control"/>
                                               </div>
                                               <label id="iplabel" class="control-label" for="groupName">格式：192.168.1.1-100</label>
                                            </div>
                                            
                                            <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                                <div class="row">
                                                </div>
                                            </div>
                                            <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                                <div class="row">
                                                	<input id="submit_btn" class="btn btn-primary "
                                                           style="width: 120px; float: left; margin-left: 15px"
                                                           type="button" value="提交" title="提交"/>&nbsp;
                                                    <input id="cancel_btn" class="btn btn-primary btn-block"
                                                           style="width: 120px; float:left; margin-left: 15px"
                                                           type="button" value="返回" title="返回"/>
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

	$(document).ready(function() {
		var isStart = ${isStart}
		if ( isStart == true ){
			$("#iplabel").html("正在扫描IP段，请等候");
			$("#ips").attr("readonly", "readonly");
			$("#submit_btn").attr("disabled", true);
		}
		
		$("#submit_btn").click(function() {
			$("#inputForm").submit();
		});
		
		$("#cancel_btn").click(function() {
			 location.href = "http://${webroot}/safeasset/asset"
		});
	});

	function myrefresh() {
		$.ajax({
			type : "get",
			async : true,
			dataType : "json",
			url : "http://${webroot}/safeasset/asset/getstatus",
			contentType : "application/json; charset=utf-8",
			success : function(data, textStatus) {
				if (data == true) {
					$("#iplabel").html("正在扫描IP段，请等候");
					$("#ips").attr("readonly", "readonly");
					$("#submit_btn").attr("disabled", true);
				} else if (data == false) {
					var isStart = ${isStart}
					if ( isStart == true ){
						$("#iplabel").html("扫描完成，可以返回查看资产！");
					} else if ( isStart == false ){
						$("#iplabel").html("IP段：");
					}
					
					$("#ips").removeAttr("readonly");
					$("#submit_btn").removeAttr("disabled");
				}
			},
			error : function(data) {
				console.log(data.status + " " + data.statusText);
			}
		});
	}
	
	setInterval('myrefresh()', 2000);
</script>
</body>
</html>
