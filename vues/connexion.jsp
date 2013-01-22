<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>

<div class="row">
  <div class="span9">

    <div class="hero-unit">

      <h1>Bienvenue sur TweetBook !</h1>
      <p>Tweetbook est un réseau social spécialement développé pour l'entreprise</p>
      <p>
      <a href="https://github.com/Fl4t/tweetBook"
        class="btn btn-primary btn-large">
        Code source du projet
      </a>
      </p>


    </div>
  </div>

  <div class="span3">

    <legend>Connectez-vous</legend>
    <form action="j_security_check" method="post" accept-charset="utf-8">
      <label for="login">Identifiant :</label>
      <input type="text" name="j_username" id="login" placeholder="Identifiant"/><br />
      <label for="password">Mot de passe :</label>
      <input type="password" name="j_password" id="password" placeholder="Mot de passe"/><br />
      <input class="btn btn-primary" type="submit" name="valider" id="valider" value="Connexion" /><br />
    </form>

    <legend>Créez un compte</legend>
    <p>
    <a href="${pageContext.request.contextPath}/index.html?id=nouveau"
      class="btn btn-primary">Inscription</a>
    </p>

  </div>
</div>


<jsp:include page="footer.jsp"/>
