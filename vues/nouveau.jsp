<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<p>
<h1>Inscription</h1>
</p>

<script type="text/javascript" charset="utf-8">
  $(document).ready(function() {
    $('#datepicker').datepicker({language: 'fr'});
    $('#datepicker').datepicker('setStartDate', '01-01-1920');
    $('#datepicker').datepicker('setEndDate', '01-01-2000');
    $('#datepicker').datepicker().on('changeDate', function() {
      $('#datepicker').datepicker('hide');
      $('#inputEmail').focus();
    })
  });
</script>

    <p>

    <form class="form-horizontal" method="get"
      action="${pageContext.request.contextPath}/index.html">
      <input type="hidden" name="id" value="enregistrer" />
      <div class="control-group">
        <label class="control-label" for="inputLogin">Identifiant</label>
        <div class="controls">
          <input name="inputLogin" type="text" id="inputLogin" placeholder="Identifiant" required>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="inputNom">Nom</label>
        <div class="controls">
          <input name="inputNom" type="text" id="inputNom" placeholder="Nom" required>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="inputPrenom">Prenom</label>
        <div class="controls">
          <input name="inputPrenom" type="text" id="inputPrenom" placeholder="Prenom" required>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="datepicker">Date de naissance</label>
        <div class="controls">
          <div class="input-append date" id="datepicker">
            <input name="inputDate_naissance" id="inputDate_naissance" class="span2" type="date" required>
            <span class="add-on"><i class="icon-calendar"></i></span>
          </div>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls">
          <div class="input-prepend">
            <span class="add-on"><i class="icon-envelope"></i></span>
            <input name="inputEmail" class="span2" id="inputEmail" type="text" placeholder="Email" required>
          </div>
        </div>
      </div>

      <div class="control-group">
        <label class="control-label" for="inputPassword">Mot de passe</label>
        <div class="controls">
          <input name="inputPassword" type="password" id="inputPassword" placeholder="Password" required>
        </div>
      </div>
      <div class="control-group">
        <div class="controls">
          <button type="submit" class="btn btn-primary">Créez le compte</button>
        </div>
      </div>
    </form>

    </p>

    <jsp:include page="footer.jsp"/>
