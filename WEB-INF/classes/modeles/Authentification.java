package modeles;

public class Authentification {

  private int id_authentification;
  private String login;
  private String password;
  private String role;

  public Authentification(String login, String password, String role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }

  /*
   * Setter
   */
  public void setId_authentification(int id_authentification) {
    this.id_authentification = id_authentification;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(String role) {
    this.role = role;
  }

  /*
   * Getter
   */
  public int getId_authentification() {
    return this.id_authentification;
  }

  public String getLogin() {
    return this.login;
  }

  public String getPassword() {
    return this.password;
  }

  public String getRole() {
    return this.role;
  }
}
