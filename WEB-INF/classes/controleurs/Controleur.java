package controleurs;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
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
    String redirection = request.getParameter("id");

    /*
     *Connexion
     */
    if (redirection == null)  {
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_ACTUALITE);
      rd.forward(request, response);

      /*
       *Actualitées
       */
    } else if (redirection.equals("actualitees")) {
      ModeleActualite actualitees = new ModeleActualite();
      actualitees.initialize();
      actualitees.execute();
      request.setAttribute("actualitees", actualitees.getListe());
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_ACTUALITE);
      rd.forward(request, response);

      /*
       *Mur
       */
    } else if (redirection.equals("mur")) {
      ModeleActualite actualitees = new ModeleActualite();
      actualitees.initialize();
      actualitees.execute();
      request.setAttribute("actualitees", actualitees.getListe());
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_MUR);
      rd.forward(request, response);

      /*
       *Si l'utilisateur accede à sa page d'amis
       */
    } else if (redirection.equals("amis")) {
      ModeleAmi amis = new ModeleAmi();
      amis.initialize();
      amis.execute();
      request.setAttribute("amis", amis.getListe());
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_AMIS);
      rd.forward(request, response);

      /*
       *Si l'utilisateur accede à sa page d'admin
       */
    } else if (redirection.equals("profil")) {
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_PROFIL);
      rd.forward(request, response);
    }
  }
}
