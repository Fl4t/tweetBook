<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"/>

<script type="text/javascript">
// vérifier le mot de passe
</script>

    <legend>Changer de mot de passe</legend>
    <form action="${pageContext.request.contextPath}/index.html"
      method="get" accept-charset="utf-8">
      <input type="hidden" name="id" value="password" />
      <label for="nouveau">Nouveau mot de passe :</label>
      <input type="password" name="motDePasse1" id="nouveau" placeholder="Mot de passe"/><br />
      <label for="confirmation">Confirmation du mot de passe :</label>
      <input type="password" name="motDePasse2" id="confirmation" placeholder="Mot de passe"/><br />
      <input class="btn btn-primary" type="submit" name="valider" id="valider" value="Confirmer" /><br />
    </form>

    <legend>Changer la visibilité du mur</legend>
    <form action="${pageContext.request.contextPath}/index.html"
      method="get" accept-charset="utf-8">
      <input type="hidden" name="id" value="visibilite" />
      <label class="radio">
        <input type="radio" name="visibilite" value="tous" id="tous"
        <c:if test="${personne.visibilite == 'tous'}">
          checked
        </c:if>
        />
        Tous : Tout le monde a accès à vos actualitées.
      </label>
      <label class="radio">
        <input type="radio" name="visibilite" value="amis" id="amis"
        <c:if test="${personne.visibilite == 'amis'}">
          checked
        </c:if>
        />
        Amis : Seul les amis peuvent voir vos publications.
      </label>
      <label class="radio">
        <input type="radio" name="visibilite" value="aucune" id="aucune"
        <c:if test="${personne.visibilite == 'aucune'}">
          checked
        </c:if>
        />
        Aucune : Vous êtes le seul à voir votre contenu publié.
      </label>
      <input class="btn btn-primary" type="submit" id="valider" value="Confirmer" /><br />
    </form>

<jsp:include page="../footer.jsp"/>
