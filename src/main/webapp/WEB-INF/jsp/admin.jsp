<%@page session="true"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertDefinition name="admin">

	<tiles:putAttribute name="menu">
		<li><a href="<c:url value='/projectNew'/>"><span class="fa fa-plus fa-2x" title="new project"></span></a></li>
	</tiles:putAttribute>
 
	<tiles:putAttribute name="body">

		<div class="titlebar">
			<div class="titlebar-item titlebar-primary">
				<div class="titlebar-title">${title}</div>
			</div>
		</div>

		<div class="row">
			<a href="http://ironsummitmedia.github.io/startbootstrap-sb-admin-2/pages/index.html">sb-admin-2</a>
		</div>

	</tiles:putAttribute>

</tiles:insertDefinition>