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
                        <div class="header"><span>${role.roleName}-角色授权</span></div>
                        <div class="body">
                            <div class="layout-inner row">
                            	<div class="col-xs-10 col-xs-offset-1">
                            	<form action="${ctx}/role/rolemenu/add/${role.id}" method="get">
                            		<input type="hidden" name="menuids" id="menuids" value=""/>
									${menuList}
                                    <div class="form-actions  col-xs-12" style="margin-top: 15px;">
                                          <div class="row">
                                              <input id="submit_btn" class="btn btn-primary " style="width: 120px; float: left; margin-left: 15px" type="button" title="提交" value="提交"/>&nbsp;
                                              <input id="cancel_btn" class="btn btn-primary btn-block"  style="width: 120px; float:left; margin-left: 15px"  type="button" title="返回" value="返回"/>
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
 	
 	<script type="text/javascript">
 	$(function(){
 		var initRoleMenu = function(){
 	 		var _roleMenu = ${roleMenu};
 	 		if(_roleMenu && _roleMenu.length > 0){
 	 			for(var i = 0; i < _roleMenu.length; i++){
 	 				$('#menu_'+_roleMenu[i].id).find('input[type="checkbox"]')[0].checked = true;
 	 			}
 	 		}
 		}
        var initMenuCheck = function(){
            var _menuListBox = $('.menu-list');
            if(_menuListBox && _menuListBox.length > 0){
                _menuListBox.each(function(){
                    var menuList = $(this).find('.checkbox');
                    var menuCheck = $(menuList).find('input[type="checkbox"]')
                    $(menuList[0]).on('click',function(){
                        if($(this).find('input[type="checkbox"]')[0].checked){
                            for(var i = 1; i < menuCheck.length; i++){
                                menuCheck[i].checked = true;
                            }
                        }else{
                            for(var i = 1; i < menuCheck.length; i++){
                                menuCheck[i].checked = false;
                            }
                        }
                    });
                });
            }
        }
 		var initButton = function(){
 			$('#cancel_btn').on('click',function(){
 				document.location.href= "${ctx}/role";
 			});
 			$('#submit_btn').on('click',function(){
 				var _menuBox = $('.menu-list').find('.checkbox'),
 					_menuSize = _menuBox.length,
 					_menuids = '';
 				
 				for(var i = 0;i < _menuSize; i++){
 					if($(_menuBox[i]).find('input[type="checkbox"]')[0].checked){
 	 					if(i != 0){
 	 						_menuids += ',';
 	 					}
 	 					_menuids += $(_menuBox[i]).find('label').attr('data-menuid');
 					}
 				}
 				$('#menuids').val(_menuids);
 				$('form').submit();
 			});
 		}
 		
 		var pageInit = function(){
 			initRoleMenu();
 			initMenuCheck();
 			initButton();
 		}
 		
 		
 		pageInit();
 		
 		
 	});
 	
 	</script>
</body>
</html>
