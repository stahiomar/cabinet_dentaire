package models;

import java.time.LocalDate;

public class Dentiste extends User{
    private Specialite specialite;

    public Dentiste(String nom, String prenom, String adresse, String telephone, String email, String cin, String username, String password) {
        super(nom, prenom, adresse, telephone, email, cin, username, password);
    }


    public Dentiste(){
        setNom("Khaled");
        setPrenom("Jalal");
        setAdresse("Sala, Diyar");
        setTelephone("0600129565");
        setEmail("dentiste@gmail.com");
        setUsername("doctor");
        setPassword("doctor");
        setSpecialite(Specialite.CHIRURGIE_DENTAIRE);
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
}
