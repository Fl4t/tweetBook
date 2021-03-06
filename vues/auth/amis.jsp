<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"/>

<script type="text/javascript" charset="utf-8">

$(document).ready(function() {
  $("#nom").keyup(function() {

    var champ_de_recherche = $("#nom").val();
    if (champ_de_recherche != "") {
      var request = $.ajax({
        url: "${pageContext.request.contextPath}/recherche.html",
        type: "GET",
        data: {la_recherche : champ_de_recherche},
        dataType: "html"
        });

      request.done(function(data) {
        $("#listeNom").html(data);
      });

    } else {
      $("#listeNom").text("");
    }
  });
});

</script>

    <div class="row">
      <div class="span6">

        <h1>Liste des amis</h1>

        <ul class="unstyled">
        <c:forEach items="${amis}" var="ami">
          <li>
          <a href="${pageContext.request.contextPath}/index.html?id=${ami.id_personne}">
            <c:out value="${ami.nom}"/> <c:out value="${ami.prenom}"/>
          </a>
          </li>
        </c:forEach>
        </ul>

      </div>
      <div class="span6">

        <h1>Ajouter des amis</h1>
        <form action="#" method="get" accept-charset="utf-8">
          <label for="nom">Recherche d'une personne :</label>
          <input type="text" name="nom" id="nom" value="" /><br />
        </form>

        <div id="listeNom"></div>

      </div>
    </div>

<jsp:include page="../footer.jsp"/>
