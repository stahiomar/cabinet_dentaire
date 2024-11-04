package models;

public class User extends Personne{
    private String username, password;

    public User(){

    }

    public User(String nom, String prenom, String adresse, String telephone, String email, String cin, String username, String password) {
        super(nom, prenom, adresse, telephone, email, cin);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
