package bdd;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import javax.naming.NamingException;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.naming.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modeles.*;

public class BDDTools {

  protected ResultSet rs;
  protected Connection con = null;

  private void initialize() {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource) envCtx.lookup("auth");
      this.con = ds.getConnection();
      PreparedStatement pragma = this.con.prepareStatement("PRAGMA foreign_keys = ON");
      pragma.executeQuery();
    } catch(NamingException e) {
      e.getStackTrace();
    } catch(SQLException e2) {
      e2.getStackTrace();
    }
  }

  public ArrayList<Actualite> fetchActuMur(Personne p) {
    this.initialize();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dateMod = null;
    ArrayList<Actualite> actus = new ArrayList<Actualite>();
    try {
      PreparedStatement prep = this.con.prepareStatement(

          "select distinct ac.id_actualite, " +

          "(select ai.notation " +
          "  from aimes ai " +
          "  where ai.id_personne = p.id_personne " +
          "  and ai.id_actualite = ac.id_actualite) as notation, " +

          "(select count(*) " +
          "  from aimes ai " +
          "  where ai.id_personne = p.id_personne " +
          "  and ai.id_actualite = ac.id_actualite " +
          "and ai.notation = ?) as nbLike, " +

          "(select count(*) " +
          "  from aimes ai " +
          "  where ai.id_personne = p.id_personne " +
          "  and ai.id_actualite = ac.id_actualite " +
          "and ai.notation = ?) as nbUnlike, " +

          "p.id_personne, " +
          "ac.type_actu, p.nom, " +
          "p.prenom, " +
          "ac.date_ajout, " +
          "ac.contenu " +

          "from actualitees ac " +
          "join personnes p on p.id_personne = ac.id_personne " +


          "where ac.id_personne = ? " +

          "and ((p.visibilite = 'tous' or p.visibilite = 'amis') or p.id_personne = ?) " +

          "order by ac.date_ajout desc "

          );
      prep.setInt(1, 1);
      prep.setInt(2, -1);
      prep.setInt(3, p.getId_personne());
      prep.setInt(4, p.getId_personne());
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Actualite actu = new Actualite();
        actu.setId_actualite(rs.getInt("id_actualite"));
        actu.setId_personne(rs.getInt("id_personne"));
        actu.setNbLike(rs.getInt("nbLike"));
        actu.setNbUnlike(rs.getInt("nbUnlike"));
        actu.setNotation(rs.getInt("notation"));
        actu.setType_actu(rs.getString("type_actu"));
        actu.setNom(rs.getString("nom"));
        actu.setPrenom(rs.getString("prenom"));
        try {
          dateMod = df.parse(rs.getString("date_ajout"));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        actu.setDate_ajout(dateMod);
        actu.setContenu(rs.getString("contenu"));
        actus.add(actu);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return actus;
  }

  public ArrayList<Actualite> fetchActualitees(Personne p) {
    this.initialize();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dateMod = null;
    ArrayList<Actualite> actus = new ArrayList<Actualite>();
    try {
      PreparedStatement prep = this.con.prepareStatement(

          "select distinct ac.id_actualite, " +

          "(select ai.notation " +
          "  from aimes ai " +
          "  where ai.id_personne = p.id_personne " +
          "  and ai.id_actualite = ac.id_actualite) as notation, " +

          "(select count(*) " +
          "  from aimes ai " +
          "  where ai.id_personne = p.id_personne " +
          "  and ai.id_actualite = ac.id_actualite " +
          "and ai.notation = ?) as nbLike, " +

          "(select count(*) " +
          "  from aimes ai " +
          "  where ai.id_personne = p.id_personne " +
          "  and ai.id_actualite = ac.id_actualite " +
          "and ai.notation = ?) as nbUnlike, " +

          "p.id_personne, " +
          "ac.type_actu, p.nom, " +
          "p.prenom, " +
          "ac.date_ajout, " +
          "ac.contenu " +

          "from actualitees ac " +
          "join personnes p on p.id_personne = ac.id_personne " +


          "where ac.id_personne in ( " +
          "                        select id_personne2 " +
          "                        from amis " +
          "                        where id_personne1 = ? or id_personne2 = ? " +
          "                      ) " +

          "and ac.date_ajout >= ( " +
          "                      select am.date_ajout " +
          "                      from amis am " +
          "                      where am.id_personne1 = ac.id_personne or am.id_personne2 = ac.id_personne " +
          "                    ) " +

          "and ((p.visibilite = 'tous' or p.visibilite = 'amis') or p.id_personne = ?) " +

          "order by ac.date_ajout desc "

          );

      prep.setInt(1, 1);
      prep.setInt(2, -1);
      prep.setInt(3, p.getId_personne());
      prep.setInt(4, p.getId_personne());
      prep.setInt(5, p.getId_personne());
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Actualite actu = new Actualite();
        actu.setId_actualite(rs.getInt("id_actualite"));
        actu.setId_personne(rs.getInt("id_personne"));
        actu.setNbLike(rs.getInt("nbLike"));
        actu.setNbUnlike(rs.getInt("nbUnlike"));
        actu.setNotation(rs.getInt("notation"));
        actu.setType_actu(rs.getString("type_actu"));
        actu.setNom(rs.getString("nom"));
        actu.setPrenom(rs.getString("prenom"));
        try {
          dateMod = df.parse(rs.getString("date_ajout"));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        actu.setDate_ajout(dateMod);
        actu.setContenu(rs.getString("contenu"));
        actus.add(actu);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return actus;
  }

  public void changerVisibilite(Personne p, String visibilite) {
    this.initialize();
    try {
      PreparedStatement prep = this.con.prepareStatement(
          "update personnes " +
          "set visibilite = ? " +
          "where id_personne = ?"
          );
      prep.setString(1, visibilite);
      prep.setInt(2, p.getId_personne());
      prep.executeUpdate();
      con.close();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void changerMotDePasse(Personne p, String motDePasse) {
    this.initialize();
    try {
      PreparedStatement prep = this.con.prepareStatement(
          "update authentification " +
          "set password = ? " +
          "where id_authentification = ?"
          );
      prep.setString(1, motDePasse);
      prep.setInt(2, p.getId_personne());
      prep.executeUpdate();
      con.close();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public ArrayList<Personne> fetchAmis(Personne p) {
    this.initialize();
    ArrayList<Personne> amis = new ArrayList<Personne>();
    try {
      PreparedStatement prep = this.con.prepareStatement(

          "SELECT * FROM personnes " +
          "where id_personne IN (" +
          "select id_personne2 from amis where id_personne1 = ? or id_personne2 = ?" +
          ") and id_personne != ?"

          );
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

  public Personne fetchById(int id) {
    this.initialize();
    Personne p = new Personne();
    try {
      PreparedStatement prep = this.con.prepareStatement(
          "SELECT * FROM personnes " +
          " where id_personne = ?");
      prep.setInt(1, id);
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

  private String capitalize(String line) {
    return Character.toUpperCase(line.charAt(0)) + line.substring(1);
  }

  public void ajouterAmi(Personne moi, Personne nouvelAmi, HttpServletRequest request) {
    this.insertActualite(
        "amis",
        "<a href=\"" + request.getContextPath() + "/index.html?id=" + moi.getId_personne() + "\">" +
        this.capitalize(moi.getNom()) + " " + this.capitalize(moi.getPrenom()) + "</a>" +
        " est " + "maintenant amis avec <a href=\"" + request.getContextPath() + "/index.html?id=" +
        nouvelAmi.getId_personne() + "\">" + this.capitalize(nouvelAmi.getNom()) +
        " " + this.capitalize(nouvelAmi.getPrenom()) + "</a>", moi.getId_personne()
        );
    this.insertActualite(
        "amis",
        "<a href=\"" + request.getContextPath() + "/index.html?id=" + nouvelAmi.getId_personne() + "\">" +
        this.capitalize(nouvelAmi.getNom()) + " " + this.capitalize(nouvelAmi.getPrenom()) + "</a>" +
        " est " + "maintenant amis avec <a href=\"" + request.getContextPath() + "/index.html?id=" +
        moi.getId_personne() + "\">" + this.capitalize(moi.getNom()) +
        " " + this.capitalize(moi.getPrenom()) + "</a>", nouvelAmi.getId_personne()
        );
    this.initialize();
    try {
      PreparedStatement prep = this.con.prepareStatement(
          "insert into amis values (?, ?, datetime('now'))");
      prep.setInt(1, moi.getId_personne());
      prep.setInt(2, nouvelAmi.getId_personne());
      prep.executeUpdate();
      PreparedStatement prep2 = this.con.prepareStatement(
          "insert into amis values (?, ?, datetime('now'))");
      prep2.setInt(1, nouvelAmi.getId_personne());
      prep2.setInt(2, moi.getId_personne());
      prep2.executeUpdate();
      con.close();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void insertActualite(String type_actu, String contenu, int id_personne) {
    this.initialize();
    try {
      PreparedStatement prep = this.con.prepareStatement(
          "insert into actualitees values (null, ?, ?, datetime(), ?)");
      prep.setString(1, type_actu);
      prep.setString(2, contenu);
      prep.setInt(3, id_personne);
      prep.executeUpdate();
      con.close();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
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

  public void updateLike(String pers, String actu, String notation) {
    this.initialize();
    if (notation.equals("1")) {

      try {
        PreparedStatement prep = this.con.prepareStatement(
            "insert into aimes values (?, ?, ?)"
            );
        prep.setInt(1, Integer.parseInt(notation));
        prep.setInt(2, Integer.parseInt(actu));
        prep.setInt(3, Integer.parseInt(pers));
        prep.executeUpdate();
        con.close();
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }

    } else {

      try {
        PreparedStatement prep = this.con.prepareStatement(
            "delete from aimes where id_personne = ? and id_actualite = ?"
            );
        prep.setInt(1, Integer.parseInt(pers));
        prep.setInt(2, Integer.parseInt(actu));
        prep.executeUpdate();
        con.close();
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }

    }
  }

  public void updateUnlike(String pers, String actu, String notation) {
    this.initialize();
    if (notation.equals("-1")) {

      try {
        PreparedStatement prep = this.con.prepareStatement(
            "insert into aimes values (?, ?, ?)"
            );
        prep.setInt(1, Integer.parseInt(notation));
        prep.setInt(2, Integer.parseInt(actu));
        prep.setInt(3, Integer.parseInt(pers));
        prep.executeUpdate();
        con.close();
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }

    } else {

      try {
        PreparedStatement prep = this.con.prepareStatement(
            "delete from aimes where id_personne = ? and id_actualite = ?"
            );
        prep.setInt(1, Integer.parseInt(pers));
        prep.setInt(2, Integer.parseInt(actu));
        prep.executeUpdate();
        con.close();
      } catch(Exception e) {
        System.out.println(e.getMessage());
      }

    }
  }
}
