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
import modeles.Actualite;

public class ModeleActualite {

  protected ResultSet rs;
  protected Connection con = null;

  private void initialize() {
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

  public ArrayList<Actualite> fetchAll() {
    this.initialize();
    ArrayList<Actualite> actualitees = new ArrayList<Actualite>();
    try {
      PreparedStatement prep = this.con.prepareStatement("SELECT * FROM actualitees");
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Actualite a = new Actualite();
        a.setId_actualite(rs.getInt("id_actualite"));
        a.setContenu(rs.getString("contenu"));
        a.setDate_ajout(rs.getDate("date_ajout"));
        actualitees.add(a);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return actualitees;
  }
}
