<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 

DEFAULT LAYOUT<br>

<h1><tiles:getAsString name="title"/></h1>

<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="body" />

<tiles:insertAttribute name="footer" />

