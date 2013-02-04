<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"/>

<script type="text/javascript" charset="utf-8">
  $(document).ready(function() {
    $('#disabled').click(function(e) {
      e.preventDefault();
    });
    $('#disabled').text("Ajouté");
  });
</script>

<ul>
  <li>${ami.nom}</li>
  <li>${ami.prenom}</li>
  <li>${ami.email}</li>
  <li>${ami.date_naissance}</li>
</ul>

<c:if test="${ami.id_personne != personne.id_personne}">

  <c:forEach items="${amis}" var="ami_moi">
    <c:if test="${ami_moi.id_personne == ami.id_personne}">
      <c:set var="deja_ami" value="1" />
    </c:if>
  </c:forEach>

  <a href="${pageContext.request.contextPath}/index.html?id=ajouter&new=${ami.id_personne}&reload=1"
    class="${empty deja_ami == true ? '' : 'disabled'} btn btn-primary"
    id="${empty deja_ami == true ? '' : 'disabled'}">Ajouter aux amis</a>

</c:if>

<c:if test="${ami.id_personne == personne.id_personne}">

  <form action="${pageContext.request.contextPath}/index.html"
    method="get" accept-charset="utf-8">
    <input type="hidden" name="id" value="publication" />
    <textarea class="input-xxlarge" rows="3" name="contenu" placeholder="Quoi de neuf ?"></textarea><br />
    <input class="btn btn-primary" type="submit" id="publier" value="Publier" /><br />
  </form>

</c:if>

<hr />

<c:forEach items="${actualitees}" var="actualite">
<ul class="media-list">
  <li class="media">
  <a class="pull-left" href="#">
    <img class= "media-object" src="http://fakeimg.pl/64x64/">
  </a>
  <div class="media-body">
    <h4 class="media-heading" style="text-transform:capitalize;">
      <a href="${pageContext.request.contextPath}/index.html?id=${actualite.id_personne}">
        <c:out value="${actualite.nom}"/> <c:out value="${actualite.prenom}"/>
      </a>
    </h4>
    <div class="media">
      <c:choose>
        <c:when test="${actualite.type_actu == 'amis'}">
          <c:out value="${actualite.contenu}" escapeXml="false" />
        </c:when>
        <c:otherwise>
          <c:out value="${actualite.contenu}" />
        </c:otherwise>
      </c:choose>
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
