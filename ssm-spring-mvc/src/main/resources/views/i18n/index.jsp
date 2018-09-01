<!--
@author chenhx
@version index.jsp, v 0.1 2018-09-01 下午 5:28
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1><spring:message code="welcome" scope="session"></spring:message></h1>
<spring:message code="name" scope="session"></spring:message>
<a href="?locale=zh">中文</a> <a href="?locale=en">英文</a>
</body>
</html>