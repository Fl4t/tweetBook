<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"/>

<ul>
  <li>${personne.nom}</li>
  <li>${personne.prenom}</li>
  <li>${personne.email}</li>
  <li>${personne.date_naissance}</li>
</ul>

<c:forEach items="${actualitees}" var="actualite">
<ul class="media-list">
  <li class="media">
  <a class="pull-left" href="#">
    <img class= "media-object" src="http://fakeimg.pl/64x64/">
  </a>
  <div class="media-body">
    <h4 class="media-heading" style="text-transform:capitalize;">
      <c:out value="${actualite.nom}"/> <c:out value="${actualite.prenom}"/>
    </h4>
    <div class="media">
      <c:out value="${actualite.contenu}"/>
    </div>
    <small class="muted">
      le <fmt:formatDate pattern="dd/MM/yyy 'à' HH:mm:ss" value="${actualite.date_ajout}"/>
    </small>
  </div>
  </li>
</ul>
<hr />
</c:forEach>

<jsp:include page="../footer.jsp"/>
