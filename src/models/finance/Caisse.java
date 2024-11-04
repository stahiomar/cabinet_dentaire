package models.finance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Caisse {
    private Double recetteDuJours;
    private Double RecetteDuMois;
    private Double RecetteDuLAnnee;
    private  Long idCaisse;

    public Caisse() {
    }

    public Caisse(Double recetteDuJours, Double recetteDuMois, Double recetteDuLAnnee, Long idCaisse) {
        this.recetteDuJours = recetteDuJours;
        RecetteDuMois = recetteDuMois;
        RecetteDuLAnnee = recetteDuLAnnee;
        this.idCaisse = idCaisse;
    }



    public Double getRecetteDuJours() {
        return recetteDuJours;
    }

    public void setRecetteDuJours(Double recetteDuJours) {
        this.recetteDuJours = recetteDuJours;
    }

    public Double getRecetteDuMois() {
        return RecetteDuMois;
    }

    public void setRecetteDuMois(Double recetteDuMois) {
        RecetteDuMois = recetteDuMois;
    }

    public Double getRecetteDuLAnnee() {
        return RecetteDuLAnnee;
    }

    public void setRecetteDuLAnnee(Double recetteDuLAnnee) {
        RecetteDuLAnnee = recetteDuLAnnee;
    }

    public Long getIdCaisse() {
        return idCaisse;
    }

    public void setIdCaisse(Long idCaisse) {
        this.idCaisse = idCaisse;
    }

    @Override
    public String toString() {
        return "Caisse{" +
                ", recetteDuJours=" + recetteDuJours +
                ", RecetteDuMois=" + RecetteDuMois +
                ", RecetteDuLAnnee=" + RecetteDuLAnnee +
                ", idCaisse=" + idCaisse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caisse caisse = (Caisse) o;
        return Objects.equals(recetteDuJours, caisse.recetteDuJours) && Objects.equals(RecetteDuMois, caisse.RecetteDuMois) && Objects.equals(RecetteDuLAnnee, caisse.RecetteDuLAnnee) && Objects.equals(idCaisse, caisse.idCaisse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recetteDuJours, RecetteDuMois, RecetteDuLAnnee, idCaisse);
    }
}
