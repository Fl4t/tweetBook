<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"/>

<form action="${pageContext.request.contextPath}/index.html"
  method="get" accept-charset="utf-8">
  <input type="hidden" name="id" value="publication" />
  <input type="hidden" name="endroit" value="actualitees" />
  <textarea class="input-xxlarge" rows="3" name="contenu" placeholder="Quoi de neuf ?"></textarea><br />
  <input class="btn btn-primary" type="submit" id="publier" value="Publier" /><br />
</form>

<hr />

<c:forEach items="${actualitees}" var="actualite">
<ul class="media-list">
  <li class="media">
  <a class="pull-left" href="${pageContext.request.contextPath}/index.html?id=${actualite.id_personne}">
    <img class= "media-object" src="http://fakeimg.pl/64x64/">
  </a>
  <div class="media-body">
    <h4 class="media-heading" style="text-transform:capitalize;">
      <a href="${pageContext.request.contextPath}/index.html?id=${actualite.id_personne}">
        <c:out value="${actualite.nom}"/> <c:out value="${actualite.prenom}"/>
      </a>
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
