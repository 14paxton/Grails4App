<%--
  Created by IntelliJ IDEA.
  User: bpaxton
  Date: 9/11/2019
  Time: 7:52 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name="layout" content="${gspLayout ?: 'main'}"/>
    <title><g:message code="springSecurity.denied.title" /></title>
</head>

<body>
<div class="body">
    <div class="errors"><g:message code="springSecurity.denied.message" /></div>
</div>
</body>

</html>
