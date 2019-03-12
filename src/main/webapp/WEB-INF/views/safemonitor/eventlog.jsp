<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>安全日志</title>
</head>

<body>
${menutitle}
<div class="center-body">
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <div class="layout">
                    <div class="header"><span>安全事件</span></div>
                    <div class="body">
                        <div class="layout-inner row">
                            <div class="data-ctrl">
                                  <div class="btn-group">
                                        <a class="btn btn-default" id="alertCreate">生成告警</a>
                                    </div>
                                <form class="col-xs-5">
                                    <div class="input-group">
                                    	<div class="control-group col-xs-6" style="height:34px;">
                                    		<div class="controls">
                                    			<input type="text" placeholder="目标IP" name="search_LIKE_targetIp" id="search_LIKE_targetIp" class="form-control  col-xl-5" value="${param.search_LIKE_targetIp}"/>
                                    		</div>
                                    	</div>
                                    	<div class="control-group col-xs-6" style="padding-right:0px; height:34px;">
                                    		<div class="controls">
	                                   			<input type="text" placeholder="内容" name="search_LIKE_eventDesc" id="search_LIKE_eventDesc" class="form-control col-xl-5" value="${param.search_LIKE_eventDesc}"/>
	                                   		</div>
	                                   	</div>
                                        <span class="input-group-addon" id="btnSearch" title="搜索"><i class="glyphicon glyphicon-search"></i></span>
                                    </div>
                               </form>
                            </div>

                            <div class="data-body">
                                <table width="100%" class="table table-bordered table-striped table-hover">
                                    <thead><tr><th width="5%"><label title="全选"><input type="checkbox" name="checkAll" id="checkAll">  选择</label></th>
                                    		   <th width="14%">日志时间</th><th width="8%">事件类型</th>
                                    	       <th width="5%">级别</th><th width="6%">源Ip</th><th width="4%">源端口</th><th width="6%">目标Ip</th>
                                    	       <th width="5%">目标端口</th><th width="4%">数量</th><th width="30%">日志</th></tr></thead>
                                    <tbody>
                                    <tbody>
                                    <c:forEach items="${objects.content}" var="object">
                                        <tr>
                                        	<td><input type="checkbox" name="eventlogList" value="${object.id}"></td>
                                            <td>
                                                <fmt:formatDate value="${object.eventDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                                            </td>
                                            <td>${object.eventType}</td>
                                            <td>${object.level}</td>
                                            <td>${object.sourceIp}</td>
                                            <td>${object.sourcePort}</td>
                                            <td>${object.targetIp}</td>
                                            <td>${object.targetPort}</td>
                                            <td>${object.eventCount}</td>
                                            <td>${object.eventDesc}</td>
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
<script type="text/javascript">
    $(function(){
        $('#btnSearch').on('click',function(){
        	$('form').attr('action',"${ctx}/safemonitor/eventlog");
            $('form').submit();
        });
        
    	$('#checkAll').on('change',function(){
    		var _checkBox = $('input[name="eventlogList"]');
    		var _count = _checkBox.length;
    		if($(this).is(":checked")){
    			for(var i = 0; i < _count; i++){
    				 _checkBox[i].checked = true;
    			}
    		}else{
    			for(var i = 0; i < _count; i++){
   				 _checkBox[i].checked = false;
	   			}
    		}
    	});
    	
        $('#alertCreate').on('click',function(){
        	var str="";  
			
        	$('input[name="eventlogList"]:checked').each(function(){    
        		str+="eventlogList="+$(this).val()+"&";
            });
        	if (str.length == 0){
        		alert("请选择日志！");
        		return;
        	}
        	str = str.substr(0,str.length-1);
        	$("#alertCreate").attr("href","${ctx}/safemonitor/eventlog/createAlert?"+str);
        	$("#alertCreate").click();
        });
    	
    	<c:if test="${not empty message}">
    	$(function(){
    	    var _code = ${code};
    	    $('#modal').find('.modal-content').html('<div id="message" class="alert ' + (function(){if(_code == 100)return 'alert-success';else return 'alert-danger';})() + '" style="margin-bottom:0px;"><button data-dismiss="modal" class="close">×</button>${message}</div>');
    	    $('#modal').modal('toggle');
    	    setTimeout(function(){$('#modal').modal('hide')},2000)
    	});
    	</c:if>
    });
</script>
</body>
</html>
