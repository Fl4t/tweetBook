<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../erreur.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"/>

<h1>Liste des actualitées</h1>

<p>
  <c:forEach items="${actualitees}" var="actualite">
    <c:out value="${actualite.date_ajout}"/>
    <c:out value="${actualite.contenu}"/>
  </c:forEach>
</p>


<jsp:include page="../footer.jsp"/>
