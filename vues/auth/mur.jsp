<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../header.jsp"/>

<ul>
  <li>${personne.nom}</li>
  <li>${personne.prenom}</li>
  <li>${personne.email}</li>
  <li>${personne.date_naissance}</li>
</ul>

<jsp:include page="../footer.jsp"/>
