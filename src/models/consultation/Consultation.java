package models.consultation;

import models.InterventionMedecin;
import models.antecedantClasses.DossierMedical;
import models.finance.Facture;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Consultation implements Serializable {
    private String idConsultation;
    private ArrayList<InterventionMedecin> interventions = new ArrayList<>();
    private DossierMedical dossierMedical;
    private LocalDate dateConsultation;
    private TypeConsultation typeConsultation;
    private ArrayList<Facture> factures = new ArrayList<>();

    public String getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation() {
        this.idConsultation = UUID.randomUUID().toString();
    }

    public ArrayList<InterventionMedecin> getInterventions() {
        return interventions;
    }

    public void setInterventions(ArrayList<InterventionMedecin> interventions) {
        this.interventions = interventions;
    }

    public DossierMedical getDossierMedical() {
        return dossierMedical;
    }

    public void setDossierMedical(DossierMedical dossierMedical) {
        this.dossierMedical = dossierMedical;
    }

    public LocalDate getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(LocalDate dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public TypeConsultation getTypeConsultation() {
        return typeConsultation;
    }

    public void setTypeConsultation(TypeConsultation typeConsultation) {
        this.typeConsultation = typeConsultation;
    }

    public ArrayList<Facture> getFactures() {
        return factures;
    }

    public void setFactures(ArrayList<Facture> factures) {
        this.factures = factures;
    }

    public Consultation() {
    }

    public Consultation(ArrayList<InterventionMedecin> interventions, DossierMedical dossierMedical, LocalDate dateConsultation, TypeConsultation typeConsultation, ArrayList<Facture> factures) {
        this.interventions = interventions;
        this.dossierMedical = dossierMedical;
        this.dateConsultation = dateConsultation;
        this.typeConsultation = typeConsultation;
        this.factures = factures;
        this.idConsultation = UUID.randomUUID().toString();
    }

    public void addIntervention(InterventionMedecin interventionMedecin){
        interventions.add(interventionMedecin);
    }

    public void addFacture(Facture facture){
        factures.add(facture);
    }

    @Override
    public String toString() {
        return "Consultation{" +
                //"interventions=" + interventions +
                //", dossierMedical=" + dossierMedical +
                ", dateConsultation=" + dateConsultation +
                ", typeConsultation=" + typeConsultation +
                ", factures=" + factures +
                '}';
    }
}
