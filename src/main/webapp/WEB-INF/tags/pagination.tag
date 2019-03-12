<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
int total =  Integer.parseInt((Long.toString(page.getTotalElements())));

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("total", total);
%>
<nav>
	<ul class="pagination">
		 <% if (page.hasPreviousPage()){%>
               	<li><a href="?page=1&sortType=${sortType}&${searchParams}">首页</a></li>
                <li><a href="?page=${current-1}&sortType=${sortType}&${searchParams}">上一页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">首页</a></li>
                <li class="disabled"><a href="#">上一页</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?page=${i}&sortType=${sortType}&${searchParams}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<li><a href="?page=${current+1}&sortType=${sortType}&${searchParams}">下一页</a></li>
                <li><a href="?page=${page.totalPages}&sortType=${sortType}&${searchParams}">尾页</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">尾页</a></li>
         <%} %>
         <li><a>共${total}条记录</a></li>
	</ul>
<nav>
