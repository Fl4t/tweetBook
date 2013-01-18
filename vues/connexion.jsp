<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="erreur.jsp" %>
<jsp:include page="header.jsp"/>

  <form action="j_security_check" method="post" accept-charset="utf-8">

    <label for="login">Login :</label>
    <input type="text" name="j_username" id="login" value="" /><br />
    <label for="password">Mot de passe :</label>
    <input type="password" name="j_password" id="password" value="" /><br />
    <input type="submit" name="valider" id="valider" value="Valider" /><br />
  </form>

  <p>Nouveau ? <a href="index.html?id=nouveau">Créez un compte.</a></p>
<jsp:include page="footer.jsp"/>
