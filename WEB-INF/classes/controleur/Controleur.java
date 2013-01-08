package tweetbook.controleur;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controleur extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    RequestDispatcher rd = this.getServletContext().getRequestDispatcher( "/WEB-INF/vue/connexion.jsp" );
    rd.forward(request, response);
	}
}
