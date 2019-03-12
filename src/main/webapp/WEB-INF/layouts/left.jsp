<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="menuId" value="${selectmenu}"/>
<div class="left-side">
    <ul id="menuUl">${allmenu}
    </ul>
</div>

<script type="text/javascript">


    $(function(){
        var _menuId = $('#menuId').val();
        if(_menuId && _menuId != "") {
            $("#" + _menuId).parents('li').addClass('active').parent('ul').show();


            $('#' + _menuId).parents('ul').each(function(){
                var _tempI = $(this).prev('a').find('.glyphicon-chevron-down');
                _tempI.removeClass('glyphicon-chevron-down');

                _tempI.addClass('glyphicon-chevron-up');
            });
        }
    });

</script>