package models.antecedantClasses;

public enum CategorieAntecedentMedicaux{
    MALADIE_CHRONIQUE,
    CONTRE_INDICATION,
    AUTRE,
    MALADIE_HEREDITAIRE,
    ALLERGIE;
    private Risque risqueAssocie;

    public Risque getRisqueAssocie() {
        return risqueAssocie;
    }

    public void setRisqueAssocie(Risque risqueAssocie) {
        this.risqueAssocie = risqueAssocie;
    }

}
