package models.antecedantClasses;

import models.Patient;
import models.consultation.Consultation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DossierMedical implements Serializable {
    private ArrayList <Consultation> Consultations = new ArrayList<>();
    private LocalDate dateCreation ;
    private Patient patient;
    private String numeroDossier;

    public DossierMedical() {
    }

    public DossierMedical(ArrayList<Consultation> consultations, LocalDate dateCreation, Patient patient) {
        setNumeroDossier();
        Consultations = consultations;
        this.dateCreation = dateCreation;
        this.patient = patient;
        this.numeroDossier = numeroDossier;
    }

    public ArrayList<Consultation> getConsultations() {
        return Consultations;
    }

    public void setConsultations(ArrayList<Consultation> consultations) {
        Consultations = consultations;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier() {
        this.numeroDossier = UUID.randomUUID().toString();
    }

    public void addConsultation(Consultation consultation) {
        Consultations.add(consultation);
    }

    @Override
    public String toString() {
        return "DossierMedical{" +
                "Consultations=" + Consultations +
         //       ", dateCreation=" + dateCreation +
                ", patient=" + patient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DossierMedical that = (DossierMedical) o;
        return Objects.equals(Consultations, that.Consultations) && Objects.equals(dateCreation, that.dateCreation) && Objects.equals(patient, that.patient) && Objects.equals(numeroDossier, that.numeroDossier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Consultations, dateCreation, patient, numeroDossier);
    }
}
