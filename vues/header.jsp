<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="fr">
  <head>
    <meta charset="UTF-8">
    <title>tweetBook</title>
    <link href="${pageContext.request.contextPath}/ressources/stylesheets/bootstrap.css" rel="stylesheet" media="screen">
  </head>
  <body>
    <a href="https://github.com/Fl4t/tweetbook">
      <img style="position: absolute; top: 0; right: 0; border: 0;"
      src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png"
      alt="Fork me on GitHub">
    </a>
    <div class="container">
        <script src="${pageContext.request.contextPath}/ressources/javascripts/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/ressources/javascripts/jquery.js"></script>
        <div class="navbar">
          <div class="navbar-inner">
            <a class="brand" href="${pageContext.request.contextPath}/index.html?id=actualitees">TweetBook</a>
            <ul class="nav">
              <li><a href="${pageContext.request.contextPath}/index.html?id=actualitees">Fil d'actualitées</a></li>
              <li><a href="${pageContext.request.contextPath}/index.html?id=mur">Mur</a></li>
              <li><a href="${pageContext.request.contextPath}/index.html?id=amis">Amis</a></li>
              <li><a href="${pageContext.request.contextPath}/index.html?id=profil">Profil</a></li>
            </ul>
          </div>
        </div>
        <div>
