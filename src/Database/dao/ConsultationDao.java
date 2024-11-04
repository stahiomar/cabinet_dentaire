package Database.dao;

import models.consultation.Consultation;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements IDao<Consultation>{
    private static final String CONSULTATIONS_FILE = "src/Database/files/consultations.txt";

    public void save(Consultation consultation) {
        List<Consultation> consultations = findAll();
        consultations.add(consultation);
        saveConsultationsToFile(consultations);
    }

    public Consultation findById(String consultationId) {
        List<Consultation> consultations = findAll();
        for (Consultation consultation : consultations) {
            if (consultation.getIdConsultation().equals(consultationId)) {
                return consultation;
            }
        }
        return null;
    }

    public ArrayList<Consultation> findAll() {
        ArrayList<Consultation> consultations = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONSULTATIONS_FILE))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Consultation) {
                        consultations.add((Consultation) obj);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    public void update(Consultation updatedConsultation) {
        List<Consultation> consultations = findAll();
        for (int i = 0; i < consultations.size(); i++) {
            if (consultations.get(i).getIdConsultation().equals(updatedConsultation.getIdConsultation())) {
                consultations.set(i, updatedConsultation);
                break;
            }
        }
        saveConsultationsToFile(consultations);
    }

    private void saveConsultationsToFile(List<Consultation> consultations) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONSULTATIONS_FILE))) {
            for (Consultation consultation : consultations) {
                oos.writeObject(consultation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
