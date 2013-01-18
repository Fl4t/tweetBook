<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="../erreur.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"/>

<h1>Liste des amis</h1>
<p>
  <c:forEach items="${amis}" var="ami">
    <c:out value="${ami.id_personne1}"/>
    <c:out value="${ami.id_personne2}"/>
    <c:out value="${ami.date_ajout}"/>
  </c:forEach>
</p>

<jsp:include page="../footer.jsp"/>
