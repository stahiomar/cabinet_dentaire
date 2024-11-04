package services;

import Database.dao.ConsultationDao;
import models.consultation.Consultation;
import java.util.ArrayList;

public class ConsultationService {
    private ConsultationDao consultationDao;

    public ConsultationService(ConsultationDao consultationDao) {
        this.consultationDao = consultationDao;
    }

    public void addConsultation(Consultation consultation) {
        consultationDao.save(consultation);
    }

    public Consultation getConsultationById(String consultationId) {
        return consultationDao.findById(consultationId);
    }

    public ArrayList<Consultation> getAllConsultations() {
        return consultationDao.findAll();
    }

    public void updateConsultation(Consultation updatedConsultation) {
        consultationDao.update(updatedConsultation);
    }
}
