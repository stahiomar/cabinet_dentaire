package Database.dao;

import models.Patient;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IDao<Patient>{
    private static final String PATIENTS_FILE = "src/Database/files/patients.txt";

    // Méthode pour ajouter un patient
    public void save(Patient patient) {
        List<Patient> patients = findAll();
        patients.add(patient);
        savePatientsToFile(patients);
    }

    // Méthode pour récupérer un patient par ID
    public Patient findById(String patientId) {
        List<Patient> patients = findAll();
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    // Méthode pour récupérer tous les patients
    public ArrayList<Patient> findAll() {
        ArrayList<Patient> patients = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATIENTS_FILE))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Patient) {
                        patients.add((Patient) obj);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return patients;
    }

    // Méthode pour mettre à jour un patient
    public void update(Patient updatedPatient) {
        List<Patient> patients = findAll();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(updatedPatient.getId())) {
                patients.set(i, updatedPatient);
                break;
            }
        }
        savePatientsToFile(patients);
    }

    // Méthode pour supprimer un patient
    public void delete(String patientId) {
        List<Patient> patients = findAll();
        patients.removeIf(patient -> patient.getId().equals(patientId));
        savePatientsToFile(patients);
    }

    // Méthode privée pour sauvegarder les patients dans le fichier
    private void savePatientsToFile(List<Patient> patients) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATIENTS_FILE))) {
            for (Patient patient : patients) {
                oos.writeObject(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}