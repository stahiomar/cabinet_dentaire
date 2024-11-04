package models;

import java.time.LocalDate;
import java.util.Objects;

public class Secretaire extends User{

    private  Double SalaireDeBase;
    private LocalDate dateDeRetoure;
    private Double prime;

    public Secretaire(){
        setNom("Wael");
        setPrenom("Abdullah");
        setAdresse("Sala, Tabrekt");
        setTelephone("0672641935");
        setEmail("secretaire@gmail.com");
        setUsername("sec");
        setPassword("sec");
    }

    public Secretaire(String nom, String prenom, String adresse, String telephone, String email, String cin, String username, String password) {
        super(nom, prenom, adresse, telephone, email, cin, username, password);
    }

    public Secretaire(String nom, String prenom, String adresse, String telephone, String email, String cin, String username, String password, Double salaireDeBase, Double prime) {
        super(nom, prenom, adresse, telephone, email, cin, username, password);
        SalaireDeBase = salaireDeBase;
        this.dateDeRetoure = dateDeRetoure;
        this.prime = prime;
    }

    public Double getSalaireDeBase() {
        return SalaireDeBase;
    }

    public void setSalaireDeBase(Double salaireDeBase) {
        SalaireDeBase = salaireDeBase;
    }

    public LocalDate getDateDeRetoure() {
        return dateDeRetoure;
    }

    public void setDateDeRetoure(LocalDate dateDeRetoure) {
        this.dateDeRetoure = dateDeRetoure;
    }

    public Double getPrime() {
        return prime;
    }

    public void setPrime(Double prime) {
        this.prime = prime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secretaire that = (Secretaire) o;
        return Objects.equals(SalaireDeBase, that.SalaireDeBase) && Objects.equals(dateDeRetoure, that.dateDeRetoure) && Objects.equals(prime, that.prime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SalaireDeBase, dateDeRetoure, prime);
    }
}
