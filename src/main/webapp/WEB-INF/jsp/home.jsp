<%@page session="false"%>
<%@taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn"    uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sec"   uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<tiles:insertDefinition name="home">

	<tiles:putAttribute name="menu">
		<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />
		<li><a href="<c:url value='/projectNew'/>"><span class="fa fa-plus fa-2x" title="new project"></span></a></li>
		<c:if test="${isAdmin}">
			<li><a href="<c:url value='/admin'/>"><span class="fa fa-users fa-2x" title="admin"></span></a></li>
		</c:if>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">

		<div class="titlebar">
			<div class="titlebar-item titlebar-primary">
				<div class="titlebar-title">welcome !</div>
			</div>
 		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>