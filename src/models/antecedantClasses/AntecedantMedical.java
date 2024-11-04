package models.antecedantClasses;

import models.Patient;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class AntecedantMedical implements Serializable {
    //@Serial
//    private static final long serialVersionUID = 1L;

    private String idAntecedant;

    public String getIdAntecedant() {
        return idAntecedant;
    }

    public void setIdAntecedant() {
        this.idAntecedant = UUID.randomUUID().toString();
    }

    public ArrayList<Patient> getPatientAvecCeAntecedantMedacal() {
        return patientAvecCeAntecedantMedacal;
    }

    public void setPatientAvecCeAntecedantMedacal(ArrayList<Patient> patientAvecCeAntecedantMedacal) {
        this.patientAvecCeAntecedantMedacal = patientAvecCeAntecedantMedacal;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    private ArrayList<Patient> patientAvecCeAntecedantMedacal = new ArrayList<>();
    private String libelle;
    private CategorieAntecedentMedicaux categorie;

    public AntecedantMedical(CategorieAntecedentMedicaux categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "AntecedantMedical{" +
                "categorie=" + categorie +
                '}';
    }
}
