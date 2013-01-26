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

  public ArrayList<Personne> fetchAmis(Personne p) {
    this.initialize();
    ArrayList<Personne> amis = new ArrayList<Personne>();
    try {
      PreparedStatement prep = this.con.prepareStatement("SELECT * FROM personnes " +
          "where id_personne IN (" +
          "select id_personne2 from amis where id_personne1 = ? or id_personne2 = ?" +
          ") and id_personne != ?");
      prep.setInt(1, p.getId_personne());
      prep.setInt(2, p.getId_personne());
      prep.setInt(3, p.getId_personne());
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Personne copain = new Personne();
        copain.setId_personne(rs.getInt("id_personne"));
        copain.setNom(rs.getString("nom"));
        copain.setPrenom(rs.getString("prenom"));
        copain.setDate_naissance(rs.getString("date_naissance"));
        copain.setEmail(rs.getString("email"));
        copain.setVisibilite(rs.getString("visibilite"));
        amis.add(copain);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return amis;
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

  public ArrayList<Personne> rechercheAmis(Personne client, String login) {
    this.initialize();
    ArrayList<Personne> personnes = new ArrayList<Personne>();
    try {
      PreparedStatement prep = this.con.prepareStatement(

          "SELECT id_personne, nom, prenom, date_naissance, email, visibilite " +
          "from personnes " +
          "where id_personne NOT IN (" +
          "                  select id_personne2 " +
          "                  from amis " +
          "                  where id_personne1 = ? or id_personne2 = ?) " +
          "and id_personne != ? " +
          "and nom != ? " +
          "and prenom != ? " +
          "and (nom like ? or prenom like ?)");

      prep.setInt(1, client.getId_personne());
      prep.setInt(2, client.getId_personne());
      prep.setInt(3, client.getId_personne());
      prep.setString(4, client.getNom());
      prep.setString(5, client.getPrenom());
      prep.setString(6, "%" + login + "%");
      prep.setString(7, "%" + login + "%");
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
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}