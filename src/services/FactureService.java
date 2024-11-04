package services;

import Database.dao.FactureDao;
import models.consultation.Consultation;
import models.finance.Facture;

import java.util.ArrayList;

public class FactureService {
    private FactureDao factureDao;

    public FactureService(FactureDao factureDao) {
        this.factureDao = factureDao;
    }

    public void addFacture(Facture facture) {
        factureDao.save(facture);
    }

    public Facture getFactureById(String factureId) {
        return factureDao.findById(factureId);
    }

    public ArrayList<Facture> getAllFactures() {
        return factureDao.findAll();
    }

    public void updateFacture(Facture updatedFacture) {
        factureDao.update(updatedFacture);
    }
}
