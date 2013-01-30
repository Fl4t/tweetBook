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
    BDDTools modPers = new BDDTools();
    ArrayList<Personne> amisPossibles = modPers.rechercheAmis(p, request.getParameter("la_recherche"));
    out.println(this.HTMLiser(request, amisPossibles));
  }

  public String HTMLiser(HttpServletRequest request, ArrayList<Personne> amisPossibles) {
    String ret = "";
    ret += "<ul class=\"unstyled\">";
    for (Personne p : amisPossibles) {
      ret += "<li><a href=\"" + request.getContextPath() + "/index.html?id=" + p.getId_personne() + "\">" +
        p.getNom() + " " + p.getPrenom() + "</a></li>";
    }
    ret += "</ul>";
    return ret;
  }
}
