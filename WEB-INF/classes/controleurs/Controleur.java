package controleurs;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controleur extends HttpServlet {

  public static final String VUE_ACTUALITE = "/vues/auth/actualites.jsp";
  public static final String VUE_CONNEXION = "/vues/connexion.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_CONNEXION);
    rd.forward(request, response);
	}

  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    // Si l'utilisateur tente de s'authentifier
    if (request.getParameter("id").equals("login")) {
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_ACTUALITE);
      rd.forward(request, response);
    } else {
      RequestDispatcher rd = this.getServletContext().getRequestDispatcher(VUE_CONNEXION);
      rd.forward(request, response);
    }
  }
}
