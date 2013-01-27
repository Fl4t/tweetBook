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

  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {

    HttpSession session = request.getSession(true);
    String redirection = request.getParameter("id");
    BDDTools modPers = new BDDTools();

    if (request.getUserPrincipal() != null) {
      this.enregistrerUserDansSession(request, session);
    }

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
        modPers.inscription(p, a);
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else {
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);
      }

    //////////////////////////////
    //// Personne authentifié ////
    //////////////////////////////

    } else {

      if (redirection == null) {
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else if (redirection.equals("mur")) {
        response.sendRedirect(request.getContextPath() + VUE_MUR);

      } else if (redirection.equals("amis")) {
        Personne p = (Personne) session.getAttribute("personne");
        ArrayList<Personne> amis = modPers.fetchAmis(p);
        session.setAttribute("amis", amis);
        response.sendRedirect(request.getContextPath() + VUE_AMIS);

      } else if (redirection.equals("admin")) {
        response.sendRedirect(request.getContextPath() + VUE_ADMIN);

      } else if (redirection.equals("deconnexion")) {
        session.invalidate();
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      } else {
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);
      }
    }
  }

  private void enregistrerUserDansSession(HttpServletRequest request, HttpSession session) {
    Principal principal = request.getUserPrincipal();
    String name = principal.getName();
    Personne p = this.tools.fetch(name);
    session.setAttribute("personne", p);
  }
}
