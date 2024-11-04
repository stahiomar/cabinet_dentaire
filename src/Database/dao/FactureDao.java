package Database.dao;

import models.antecedantClasses.DossierMedical;
import models.finance.Facture;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FactureDao implements IDao<Facture>{
    private static final String FACTURES_FILE = "src/Database/files/factures.txt";


    public void save(Facture facture) {
        List<Facture> factures = findAll();
        factures.add(facture);
        saveFacturesToFile(factures);
    }

    public Facture findById(String factureId) {
        List<Facture> factures = findAll();
        for (Facture facture : factures) {
            if (facture.getIdFacture().equals(factureId)) {
                return facture;
            }
        }
        return null;
    }

    public ArrayList<Facture> findAll() {
        ArrayList<Facture> factures = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FACTURES_FILE))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Facture) {
                        factures.add((Facture) obj);
                    }
                } catch (EOFException e) {
                    break; // Fin du fichier
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return factures;
    }

    public void update(Facture updatedFacture) {
        List<Facture> factures = findAll();
        for (int i = 0; i < factures.size(); i++) {
            if (factures.get(i).getIdFacture().equals(updatedFacture.getIdFacture())) {
                factures.set(i, updatedFacture);
                break;
            }
        }
        saveFacturesToFile(factures);
    }

    private void saveFacturesToFile(List<Facture> factures) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FACTURES_FILE))) {
            for (Facture facture : factures) {
                oos.writeObject(facture);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
