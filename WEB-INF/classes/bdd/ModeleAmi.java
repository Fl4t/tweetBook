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
import modeles.Ami;

public class ModeleAmi {

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

  public ArrayList<Ami> fetchAll() {
    this.initialize();
    ArrayList<Ami> amis = new ArrayList<Ami>();
    try {
      PreparedStatement prep = this.con.prepareStatement("SELECT * FROM amis");
      this.rs = prep.executeQuery();
      while (rs.next()) {
        Ami a = new Ami();
        a.setId_personne1(rs.getInt("id_personne1"));
        a.setId_personne2(rs.getInt("id_personne2"));
        a.setDate_ajout(rs.getDate("date_ajout"));
        amis.add(a);
      }
      con.close();
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    return amis;
  }
}
