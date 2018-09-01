<!--
国际化界面演示
@author chenhx
@version index.jsp, v 0.1 2018-09-01 下午 5:28
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1><spring:message code="welcome"></spring:message></h1>
<spring:message code="name"></spring:message>
<a href="<c:url value='/i18n/index?localeName=zh' />">中文</a>
<a href="<c:url value='/i18n/index?localeName=en' />">英文</a>
</body>
</html>