package bdd;

import java.util.ArrayList;
import javax.naming.NamingException;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.naming.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import modeles.Personne;
import modeles.Authentification;

public class ModelePersonne {

  protected ResultSet rs;
  protected Connection con = null;

  private void initialize() {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource) envCtx.lookup("auth");
      this.con = ds.getConnection();
    } catch(NamingException e) {
      e.getStackTrace();
    } catch(SQLException e2) {
      e2.getStackTrace();
    }
  }

  public ArrayList<Personne> fetchAll() {
    this.initialize();
    ArrayList<Personne> personnes = new ArrayList<Personne>();
    try {
      PreparedStatement prep = this.con.prepareStatement("SELECT * FROM personnes");
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Personne p = new Personne();
        p.setId_personne(rs.getInt("id_personne"));
        p.setNom(rs.getString("nom"));
        p.setPrenom(rs.getString("prenom"));
        p.setDate_naissance(rs.getString("date_naissance"));
        p.setEmail(rs.getString("email"));
        p.setVisibilite(rs.getString("visibilite"));
        personnes.add(p);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return personnes;
  }

  public Personne fetch(String login) {
    this.initialize();
    Personne p = new Personne();
    try {
      PreparedStatement prep = this.con.prepareStatement(
          "SELECT * FROM personnes " +
          " where id_personne = (" +
                                "SELECT id_authentification from authentification " +
                                "where login = ?"+
                                ")");
      prep.setString(1, login);
      this.rs = prep.executeQuery();
      while (rs.next()) {
        p.setId_personne(rs.getInt("id_personne"));
        p.setNom(rs.getString("nom"));
        p.setPrenom(rs.getString("prenom"));
        p.setDate_naissance(rs.getString("date_naissance"));
        p.setEmail(rs.getString("email"));
        p.setVisibilite(rs.getString("visibilite"));
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return p;
  }

  public void inscription(Personne p, Authentification a) {
    this.initialize();
    try {
      PreparedStatement prep1 = this.con.prepareStatement(
          "insert into personnes values (null, ?, ?, ?, ?, ?)");
      prep1.setString(1, p.getNom());
      prep1.setString(2, p.getPrenom());
      prep1.setString(3, p.getDate_naissance());
      prep1.setString(4, p.getEmail());
      prep1.setString(5, p.getVisibilite());
      prep1.executeUpdate();
      PreparedStatement prep2 = this.con.prepareStatement(
          "insert into authentification values (null, ?, ?, ?)");
      prep2.setString(1, a.getLogin());
      prep2.setString(2, a.getPassword());
      prep2.setString(3, a.getRole());
      prep2.executeUpdate();
      con.close();
      System.out.println("inscription");
    } catch(Exception e) {
      System.out.println("inscriptionException");
      System.out.println(e.getMessage());
    }
  }
}
