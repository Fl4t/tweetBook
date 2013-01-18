<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="header.jsp"/>

<h1>Holy shit !</h1>

<c:if test="${not empty param.message}">
<div class="alert">
  Un probleme de type "${param.message}" est survenu.
</div>
</c:if>

<% if (exception != null) { %>
  <%= exception.getMessage() %>
<% } %>

<jsp:include page="footer.jsp"/>
