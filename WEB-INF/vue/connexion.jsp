<!DOCTYPE HTML>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>tweetBook</title>
</head>
<body>

  <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <jsp:useBean id="user" class="modele.Users"/>
  <jsp:setProperty name="user" property="*" >

  <form action="ex3.jsp" method="post" accept-charset="utf-8">
    <label for="login">Login : </label>
    <input type="text" name="login" id="login" value="<%= user.login %>" />
    <label for="password">Mot de passe : </label>
    <input type="password" name="password" id="password" value="<%= user.password %>" />
    <input type="submit" name="valider" id="valider" value="Valider" />
  </form>

</body>
</html>
