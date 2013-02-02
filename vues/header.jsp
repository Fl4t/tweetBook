<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="modeles.Personne, modeles.Actualite, bdd.BDDTools, java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="fr">
  <head>
    <meta charset="UTF-8">
    <title>tweetBook</title>
    <link href="${pageContext.request.contextPath}/ressources/css/bootstrap.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/ressources/css/datepicker.css" rel="stylesheet" media="screen">
  </head>
  <body>

    <%
    // pour la première connexion
      if (request.getRemoteUser() != null && session.getAttribute("personne") == null) {
        BDDTools tools = new BDDTools();
        Personne p = tools.fetch(request.getRemoteUser());
        ArrayList<Actualite> actus = tools.fetchActualitees(p);
        session.setAttribute("actualitees", actus);
        session.setAttribute("personne", p);
      }
    %>

    <a href="https://github.com/Fl4t/tweetbook">
      <img style="position: absolute; top: 41px; right: 0; border: 0;"
      src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png"
      alt="Fork me on GitHub">
    </a>
    <div class="navbar">
      <div class="navbar-inner">
        <a class="brand" href="${pageContext.request.contextPath}/index.html">TweetBook</a>
        <ul class="nav">
          <li><a href="${pageContext.request.contextPath}/index.html">Fil d'actualitées</a></li>
          <li><a href="${pageContext.request.contextPath}/index.html?id=${personne.id_personne}">Mur</a></li>
          <li><a href="${pageContext.request.contextPath}/index.html?id=amis">Amis</a></li>
        </ul>
        <c:if test="${!empty sessionScope.personne}">
        <ul class="nav pull-right">
          <li class="divider-vertical"></li>
          <li><a href="${pageContext.request.contextPath}/index.html?id=admin">Administration</a></li>
          <li><a href="${pageContext.request.contextPath}/index.html?id=deconnexion">Deconnexion</a></li>
        </ul>
        </c:if>
      </div>
    </div>
    <div class="container">
      <script src="${pageContext.request.contextPath}/ressources/js/bootstrap.js"></script>
      <script src="${pageContext.request.contextPath}/ressources/js/jquery.js"></script>
      <script src="${pageContext.request.contextPath}/ressources/js/bootstrap-datepicker.js"></script>
      <script src="${pageContext.request.contextPath}/ressources/js/locales/bootstrap-datepicker.fr.js"></script>
      <div>
