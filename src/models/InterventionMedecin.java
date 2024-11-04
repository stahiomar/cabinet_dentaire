package models;

import models.acte.Acte;
import models.consultation.Consultation;
import java.io.Serializable;
import java.util.UUID;

public class InterventionMedecin implements Serializable {
    private String idIntervention;
//    private static final long serialVersionUID = 1L;
    private String noteMedecin;
    private Double prixPatient;
    private Long dent;
    private Acte acte;
    private Consultation consultation;

    public String getIdIntervention() {
        return idIntervention;
    }

    public void setIdIntervention() {
        this.idIntervention = UUID.randomUUID().toString();
    }

    public String getNoteMedecin() {
        return noteMedecin;
    }

    public void setNoteMedecin(String noteMedecin) {
        this.noteMedecin = noteMedecin;
    }

    public Double getPrixPatient() {
        return prixPatient;
    }

    public void setPrixPatient(Double prixPatient) {
        this.prixPatient = prixPatient;
    }

    public Long getDent() {
        return dent;
    }

    public void setDent(Long dent) {
        this.dent = dent;
    }

    public Acte getActe() {
        return acte;
    }

    public InterventionMedecin(String noteMedecin, Double prixPatient, Long dent, Acte acte, Consultation consultation) {
        setIdIntervention();
        this.noteMedecin = noteMedecin;
        this.prixPatient = prixPatient;
        this.dent = dent;
        this.acte = acte;
        this.consultation = consultation;
    }

    public void setActe(Acte acte) {
        this.acte = acte;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    @Override
    public String toString() {
        return "InterventionMedecin{" +
                "noteMedecin='" + noteMedecin + '\'' +
                ", prixPatient=" + prixPatient +
                ", dent=" + dent +
                ", acte=" + acte +
                ", consultation=" + consultation +
                '}';
    }
}
