<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
	<title>文件上传</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

</head>
 <body>
${menutitle}
 <div class="center-body">
     <div class="container-fluid">
         <div class="row">
             <div class="col-xs-12">

                 <div class="layout" style="width: 100%">
                     <div class="header">
                         <span>用户导入</span>
                     </div>
                     <div class="body">
                         <div class="layout-inner">
                             <div class="row">
                                 <div class="col-xs-12">
                                     请先下载模板，然后根据模板内容填写资料。
                                     <br>注：格式只能是excel格式，文件名不能修改。文件不得大于2M。
                                     <br><font color="#FF0000">${message}</font>
                                     <a href="${ctx}/account/user/download"  class="btn btn-primary btn-block" style="width: 120px; margin-bottom: 15px; margin-left: 15px; margin-top: 15px;" title="下载模板">下载模板</a>
                                     <form id="frmupload" method="post" action="${ctx}/account/user/upload" enctype="multipart/form-data">
                                         <input id="file" name="file" type="file" value=""/>
                                         <input type="submit" id="btn-submit" class="btn btn-primary btn-block" style="width: 120px; float:left; margin-left: 15px; margin-top: 15px;" title="导入数据" value="导入数据" />
                                         <input id="cancel_btn" class="btn btn-primary btn-block"  style="width: 120px; float:left; margin-left: 15px;margin-top: 15px;"  type="button" title="返回" value="返回"
                                                onclick="history.back()"/>
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
    $('#btnOpendailg').on('click',function(){
        $('#file').click();
    })
</script>
<script type="text/javascript">  
$(function() {  
	  
	function isvalidatefile(obj) {  
	    var extend = obj.substring(obj.lastIndexOf(".") + 1);  
	    //alert(obj);
	    if (extend == "") {
	    	return  false;
	    } else {  
	        if (!(extend == "xls" )) {  
	            alert("请上传后缀名为xls(Excel2003)的文件!");  
	            return false;  
	        }  
	    }  
	    return true;  
	}  
	
    $('#frmupload').submit(function() { 
        if ($('#file').val() == '') {  
            alert('请选择上传导入文件!');  
            $('#file').focus();  
            return false;  
        }else{
        	
        	return isvalidatefile($('#file').val());
        }  
    }); 
});  

</script>
</body>
</html>