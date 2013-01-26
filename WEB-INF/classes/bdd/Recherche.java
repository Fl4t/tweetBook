package bdd;

import java.io.PrintWriter;
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

public class Recherche extends HttpServlet {

  public void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    Personne p = (Personne) session.getAttribute("personne");
    PrintWriter out = response.getWriter();
    ModelePersonne modPers = new ModelePersonne();
    ArrayList<Personne> amisPossibles = modPers.rechercheAmis(p, request.getParameter("la_recherche"));
    out.println(this.HTMLiser(amisPossibles));
  }

  public String HTMLiser(ArrayList<Personne> amisPossibles) {
    String ret = "";
    ret += "<ul>";
    for (Personne p : amisPossibles) {
      ret += "<li>" + p.getNom() + " " + p.getPrenom() + "</li>";
    }
    ret += "</ul>";
    return ret;
  }
}
