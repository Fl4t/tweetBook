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

public class ModelePersonne {

  protected ArrayList<Personne> personnes = new ArrayList<Personne>();
  protected ResultSet rs;
  protected Connection con = null;

  public void initialize() {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource) envCtx.lookup("auth");
      this.con = ds.getConnection();
    } catch(NamingException e) {
      System.out.println(e.getMessage());
    } catch(SQLException e2) {
      System.out.println(e2.getMessage());
    }
  }

  public void execute() {
    try {
      PreparedStatement prep = this.con.prepareStatement("SELECT * FROM personnes");
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Personne p = new Personne();
        p.setId_personne(rs.getInt("id_personne"));
        p.setNom(rs.getString("nom"));
        p.setPrenom(rs.getString("prenom"));
        p.setDate_naissance(rs.getDate("date_naissance"));
        p.setEmail(rs.getString("email"));
        p.setVisibilite(rs.getString("visibilite"));
        personnes.add(p);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public ArrayList<Personne> getListe() {
    return this.personnes;
  }
}
