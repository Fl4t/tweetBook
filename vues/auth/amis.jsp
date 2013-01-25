<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"/>

<h1>Liste des amis</h1>

<c:forEach items="${amis}" var="ami">
<ul>
  <li><c:out value="${ami.nom}"/></li>
  <li><c:out value="${ami.prenom}"/></li>
</ul>
</c:forEach>

<jsp:include page="../footer.jsp"/>
