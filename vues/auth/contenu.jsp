<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:forEach items="${actualitees}" var="actu">
<ul class="media-list">
  <li class="media">
  <a class="pull-left" href="${pageContext.request.contextPath}/index.html?id=${actu.id_personne}">
    <img class= "media-object" src="http://fakeimg.pl/64x64/">
  </a>
  <div class="media-body">
    <h4 class="media-heading" style="text-transform:capitalize;">
      <a href="${pageContext.request.contextPath}/index.html?id=${actu.id_personne}">
        <c:out value="${actu.nom}"/> <c:out value="${actu.prenom}"/>
      </a>
    </h4>
    <div class="media">
      <c:choose>
        <c:when test="${actu.type_actu == 'amis'}">
          <c:out value="${actu.contenu}" escapeXml="false" />
        </c:when>
        <c:otherwise>
          <c:out value="${actu.contenu}" />
        </c:otherwise>
      </c:choose>
    </div>
    <small class="muted">
      le <fmt:formatDate pattern="dd/MM/yyy 'à' HH:mm:ss" value="${actu.date_ajout}"/>
    </small>
  </div>
  <c:choose>
    <c:when test="${actu.type_actu == 'message'}">
    <div class="btn-toolbar">
      <div class="btn-group">
        <a class="btn ${actu.notation == 1 ? 'active' : ''}"
          href="${pageContext.request.contextPath}/index.html?id=like&personne=${actu.id_personne}&actu=${actu.id_actualite}&notation=${actu.notation == 1 ? '0' : '1'}">
          <i class="icon-thumbs-up"></i>${actu.nbLike}
        </a>
        <a class="btn ${actu.notation == -1 ? 'active' : ''}"
          href="${pageContext.request.contextPath}/index.html?id=unlike&personne=${actu.id_personne}&actu=${actu.id_actualite}&notation=${actu.notation == -1 ? '0' : '-1'}">
          <i class="icon-thumbs-down"></i>${actu.nbUnlike}
        </a>
      </div>
    </div>
    </c:when>
  </c:choose>
  </li>
</ul>
<hr />
</c:forEach>
