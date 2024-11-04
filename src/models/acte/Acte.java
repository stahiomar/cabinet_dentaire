package models.acte;

import models.InterventionMedecin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Acte implements Serializable {
    private String idActe;
//    private static final long serialVersionUID = 1L;
    private ArrayList<InterventionMedecin> interventions = new ArrayList<>();
    private Double prixDeBase;
    private CategorieActe categorie;

    public String getIdActe() {
        return idActe;
    }

    public void setIdActe() {
        this.idActe = UUID.randomUUID().toString();
    }

    public ArrayList<InterventionMedecin> getInterventions() {
        return interventions;
    }

    public void setInterventions(ArrayList<InterventionMedecin> interventions) {
        this.interventions = interventions;
    }

    public Acte() {}

    public Acte(ArrayList<InterventionMedecin>interventions, Double prixDeBase, CategorieActe categorie) {
        setIdActe();
        this.interventions = interventions;
        this.prixDeBase = prixDeBase;
        this.categorie = categorie;
    }

    public Double getPrixDeBase() {
        return prixDeBase;
    }

    public void setPrixDeBase(Double prixDeBase) {
        this.prixDeBase = prixDeBase;
    }

    public CategorieActe getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieActe categorie) {
        this.categorie = categorie;
    }

    public void addIntervention(InterventionMedecin interventionMedecin){
        interventions.add(interventionMedecin);
    }

    @Override
    public String toString() {
        return "Acte{" +
                "idActe='" + idActe + '\'' +
                ", interventions=" + interventions +
                ", prixDeBase=" + prixDeBase +
                ", categorie=" + categorie +
                '}';
    }
}
