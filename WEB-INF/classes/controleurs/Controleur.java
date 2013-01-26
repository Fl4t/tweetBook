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

    Principal principal = request.getUserPrincipal();
    //Enregistrement du visiteur dans la session si possible
    if (principal != null){
      String name = principal.getName();
      ModelePersonne modPers = new ModelePersonne();
      Personne p = modPers.fetch(name);
      session.setAttribute("personne", p);
    }

    if (session.getAttribute("personne") != null) {

      /*
       * Connexion & Actualitées
       */
      if (redirection == null || redirection.equals("actualitees")) {
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

        /*
         *Mur
         */
      } else if (redirection.equals("mur")) {
        response.sendRedirect(request.getContextPath() + VUE_MUR);

        /*
         *Si l'utilisateur accede à sa page d'amis
         */
      } else if (redirection.equals("amis")) {
        Personne p = (Personne) session.getAttribute("personne");
        ModelePersonne modPers = new ModelePersonne();
        ArrayList<Personne> amis = modPers.fetchAmis(p);
        session.setAttribute("amis", amis);
        response.sendRedirect(request.getContextPath() + VUE_AMIS);

        /*
         *Si l'utilisateur accede à sa page d'admin
         */

        /*
         * Nouvel utilisateur
         */
      } else if (redirection.equals("nouveau")) {
        response.sendRedirect(request.getContextPath() + VUE_NOUVEAU);

        /*
         *Enregistrement du nouvel utilisateur
         */
      } else if (redirection.equals("enregistrer")) {
        Personne p = new Personne(
            request.getParameter("inputNom"),
            request.getParameter("inputPrenom"),
            request.getParameter("inputDate_naissance"),
            request.getParameter("inputEmail"), "tous");
        Authentification a = new Authentification(
            request.getParameter("inputLogin"),
            request.getParameter("inputPassword"), "role1");
        ModelePersonne personne = new ModelePersonne();
        personne.inscription(p, a);
        ModeleActualite modAct = new ModeleActualite();
        ArrayList<Actualite> actualitees = modAct.fetchAll();
        session.setAttribute("actualitees", actualitees);
        response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);
      }
    } else {
      response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);
    }
  }
}
