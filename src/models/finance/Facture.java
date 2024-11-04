package models.finance;

import models.consultation.Consultation;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Facture implements Serializable {
    private Double montantRestant;
    private Double montantPaye;
    private String idFacture;
    private StatutPaiement statutPaiement;
    private LocalDate dateFactureation;
    private Double MontantTotal ;
    private Consultation consultation;
    private TypePaiement typePaiement;

    public Facture() {
    }

    public Facture(StatutPaiement statutPaiement, Double montantRestant, Double montantPaye, LocalDate dateFactureation, Double montantTotal, Consultation consultation, TypePaiement typePaiement) {
        this.montantRestant = montantRestant;
        this.montantPaye = montantPaye;
        this.idFacture = UUID.randomUUID().toString();
        this.dateFactureation = dateFactureation;
        this.MontantTotal = montantTotal;
        this.consultation = consultation;
        this.typePaiement = typePaiement;
        this.statutPaiement = statutPaiement;
    }

    public Double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public Double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public LocalDate getDateFactureation() {
        return dateFactureation;
    }

    public void setDateFactureation(LocalDate dateFactureation) {
        this.dateFactureation = dateFactureation;
    }

    public Double getMontantTotal() {
        return MontantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        MontantTotal = montantTotal;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public TypePaiement getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(TypePaiement typePaiement) {
        this.typePaiement = typePaiement;
    }

    public StatutPaiement getStatutPaiement() {
        return statutPaiement;
    }

    public void setStatutPaiement(StatutPaiement statutPaiement) {
        this.statutPaiement = statutPaiement;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "montantRestant=" + montantRestant +
                //", situationFinanciere=" + situationFinanciere +
                ", montantPaye=" + montantPaye +
                //", idFacture=" + idFacture +
                ", dateFactureation=" + dateFactureation +
                ", MontantTotal=" + MontantTotal +
                //", consultation=" + consultation +
                ", typePaiement=" + typePaiement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return Objects.equals(montantRestant, facture.montantRestant) && Objects.equals(montantPaye, facture.montantPaye) && Objects.equals(idFacture, facture.idFacture) && Objects.equals(dateFactureation, facture.dateFactureation) && Objects.equals(MontantTotal, facture.MontantTotal) && Objects.equals(consultation, facture.consultation) && typePaiement == facture.typePaiement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(montantRestant, montantPaye, idFacture, dateFactureation, MontantTotal, consultation, typePaiement);
    }
}
