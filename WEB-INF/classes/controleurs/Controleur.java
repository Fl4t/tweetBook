package controleurs;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bdd.*;

public class Controleur extends HttpServlet {

  private static final String VUE_ACTUALITE = "/vues/auth/actualitees.jsp";
  private static final String VUE_ADMIN = "/vues/auth/admin.jsp";
  private static final String VUE_MUR = "/vues/auth/mur.jsp";
  private static final String VUE_AMIS = "/vues/auth/amis.jsp";
  private static final String VUE_PROFIL = "/vues/auth/profil.jsp";

  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {

    HttpSession session = request.getSession(true);
    String redirection = request.getParameter("id");

    /*
     *Connexion
     */
    if (redirection == null)  {
      response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      /*
       *Actualitées
       */
    } else if (redirection.equals("actualitees")) {
      ModeleActualite actualitees = new ModeleActualite();
      actualitees.initialize();
      actualitees.execute();
      session.setAttribute("actualitees", actualitees.getListe());
      response.sendRedirect(request.getContextPath() + VUE_ACTUALITE);

      /*
       *Mur
       */
    } else if (redirection.equals("mur")) {
      ModeleActualite actualitees = new ModeleActualite();
      actualitees.initialize();
      actualitees.execute();
      session.setAttribute("actualitees", actualitees.getListe());
      response.sendRedirect(request.getContextPath() + VUE_MUR);

      /*
       *Si l'utilisateur accede à sa page d'amis
       */
    } else if (redirection.equals("amis")) {
      ModeleAmi amis = new ModeleAmi();
      amis.initialize();
      amis.execute();
      session.setAttribute("amis", amis.getListe());
      response.sendRedirect(request.getContextPath() + VUE_AMIS);

      /*
       *Si l'utilisateur accede à sa page d'admin
       */
    } else if (redirection.equals("profil")) {
      response.sendRedirect(request.getContextPath() + VUE_PROFIL);
    }
  }
}
