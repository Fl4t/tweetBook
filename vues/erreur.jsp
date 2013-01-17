<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="header.jsp"/>

<h1>Holy shit !</h1>

<div class="alert">
  Un probleme de type "${param.message}" est survenu.
</div>

<% if (exception != null) { %>
  <code><%= exception.getMessage() %></code>
<% } %>

<jsp:include page="footer.jsp"/>
