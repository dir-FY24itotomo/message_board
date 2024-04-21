<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    	<c:choose>
    		<c:when test="${message != null}">
    			<h2>id : ${message.id}　Edition</h2>

       			<form method="POST" action="${pageContext.request.contextPath}/update">
            		<c:import url="_form.jsp" />
        		</form>

        		<p><a href="${pageContext.request.contextPath}/index">Go back to view</a></p>

        		<p><a href="#" onclick="confirmDestroy();">Delete message</a></p>
        		<form method="POST" action="${pageContext.request.contextPath}/destroy">
            		<input type="hidden" name="_token" value="${_token}" />
        		</form>
        		<script>
        			function confirmDestroy() {
            			if(confirm("Do you sure to delete?")) {
                			document.forms[1].submit();
           				}
        			}
        		</script>
    		</c:when>
    		<c:otherwise>
    			<h2>404 Not found</h2>
    		</c:otherwise>
    	</c:choose>

    </c:param>
</c:import>