package models.acte;

public enum CategorieActe {
    PREVENTION,
    CHIRURGIE,
    ORTHODONTIE,
    IMPLANTOLOGIE,
    SOINS_DES_CARIES,
    PROTHESE_DENTAIRE,
    DIAGNOSTIC,
    SOINS_DES_TISSUS_MOUS;
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
