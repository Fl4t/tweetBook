package controleurs;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import bdd.*;
import modeles.*;

public class Controleur extends HttpServlet {

  private static final String VUE_NOUVEAU = "/vues/nouveau.jsp";

  private static final String VUE_ACTUALITE = "/vues/auth/actualitees.jsp";
  private static final String VUE_ADMIN = "/vues/auth/admin.jsp";
  private static final String VUE_MUR = "/vues/auth/mur.jsp";
  private static final String VUE_AMIS = "/vues/auth/amis.jsp";

  private BDDTools tools = new BDDTools();

  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {

    HttpSession session = request.getSession(true);
    String redirection = request.getParameter("id");

    //////////////////////////////////
    //// Personne non-authentifié ////
    //////////////////////////////////

    if (session.getAttribute("personne") == null) {

      if (redirection == null) {
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else if (redirection.equals("nouveau")) {
        response.sendRedirect(request.getContextPath() + VUE_NOUVEAU);

      } else if (redirection.equals("enregistrer")) {
        Personne p = new Personne
          (
            request.getParameter("inputNom"),
            request.getParameter("inputPrenom"),
            request.getParameter("inputDate_naissance"),
            request.getParameter("inputEmail"), "tous"
          );
        Authentification a = new Authentification
          (
            request.getParameter("inputLogin"),
            request.getParameter("inputPassword"),
            "role1"
          );
        this.tools.inscription(p, a);
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else {
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);
      }

    //////////////////////////////
    //// Personne authentifié ////
    //////////////////////////////

    } else {

      Personne p = this.fetchUserDansSession(session);

      if (redirection == null) {
        ArrayList<Actualite> actus = this.tools.fetchActualitees(p);
        session.setAttribute("actualitees", actus);
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else if (redirection.equals("publication")) {
        ArrayList<Actualite> actus = this.tools.fetchActualitees(p);
        this.tools.insertActualite(request.getParameter("contenu"), p.getId_personne());
        session.setAttribute("actualitees", actus);
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else if (redirection.equals("amis")) {
        ArrayList<Personne> amis = this.tools.fetchAmis(p);
        session.setAttribute("amis", amis);
        response.sendRedirect(request.getContextPath() + VUE_AMIS);

      } else if (redirection.equals("admin")) {
        response.sendRedirect(request.getContextPath() + VUE_ADMIN);

      } else if (redirection.matches("\\d{1,}")) {
        ArrayList<Personne> amis = this.tools.fetchAmis(p);
        Personne amisVisite = this.tools.fetchById(Integer.parseInt(redirection));
        ArrayList<Actualite> actus = this.tools.fetchActuMur(amisVisite);
        session.setAttribute("ami", amisVisite);
        session.setAttribute("amis", amis);
        session.setAttribute("actualitees", actus);
        response.sendRedirect(request.getContextPath() + VUE_MUR);

      } else if (redirection.equals("ajouter")) {
        Personne nouvelAmi = this.tools.fetchById(Integer.parseInt(request.getParameter("new")));
        this.tools.ajouterAmi(p, nouvelAmi);
        ArrayList<Personne> amis = this.tools.fetchAmis(p);
        ArrayList<Actualite> actus = this.tools.fetchActuMur(nouvelAmi);
        session.setAttribute("ami", nouvelAmi);
        session.setAttribute("amis", amis);
        session.setAttribute("actualitees", actus);
        if (request.getParameter("reload").equals("1"))
          response.sendRedirect(request.getContextPath() + VUE_MUR);
        else
          response.sendRedirect(request.getContextPath() + VUE_AMIS);

      } else if (redirection.equals("visibilite")) {
        this.tools.changerVisibilite(p, request.getParameter("visibilite"));
        this.remoteUserDansSession(request, session);
        response.sendRedirect(request.getContextPath() + VUE_ADMIN);

      } else if (redirection.equals("password")) {
        this.tools.changerMotDePasse(p, request.getParameter("motDePasse2"));
        this.remoteUserDansSession(request, session);
        response.sendRedirect(request.getContextPath() + VUE_ADMIN);

      } else if (redirection.equals("deconnexion")) {
        session.invalidate();
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else {
        ArrayList<Actualite> actus = this.tools.fetchActualitees(p);
        session.setAttribute("actualitees", actus);
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);
      }
    }
  }

  private void remoteUserDansSession(HttpServletRequest request, HttpSession session) {
    Personne p = this.tools.fetch(request.getRemoteUser());
    session.setAttribute("personne", p);
  }

  private Personne fetchUserDansSession(HttpSession session) {
    return (Personne) session.getAttribute("personne");
  }
}
