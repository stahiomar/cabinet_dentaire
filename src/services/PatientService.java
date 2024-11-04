package services;

import Database.dao.PatientDao;
import models.Patient;

import java.util.ArrayList;

public class PatientService {

    private PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public void addPatient(Patient patient) {
        patientDao.save(patient);
    }

    public Patient getPatientById(String patientId) {
        return patientDao.findById(patientId);
    }

    public ArrayList<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    public void updatePatient(Patient updatedPatient) {
        patientDao.update(updatedPatient);
    }

    public void deletePatient(String patientId) {
        patientDao.delete(patientId);
    }
}
