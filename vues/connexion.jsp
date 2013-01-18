<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="erreur.jsp" %>
<jsp:include page="header.jsp"/>


  <form action="index.html?id=login" method="post" accept-charset="utf-8">
    <label for="login">Login :</label>
    <input type="text" name="login" id="login" value="${login}" /><br />
    <label for="password">Mot de passe :</label>
    <input type="password" name="password" id="password" value="${password}" /><br />
    <input type="submit" name="valider" id="valider" value="Valider" /><br />
  </form>

  <p>Nouveau ? <a href="index.html?id=nouveau">Créez un compte.</a></p>
<jsp:include page="footer.jsp"/>
