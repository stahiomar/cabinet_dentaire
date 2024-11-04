package UI.dentistDashboard.panels;

import Database.dao.*;
import Static.Themes;
import UI.CustomFontRenderer;
import models.Dentiste;
import models.InterventionMedecin;
import models.Mutuelle;
import models.Patient;
import models.acte.Acte;
import models.acte.CategorieActe;
import models.antecedantClasses.AntecedantMedical;
import models.antecedantClasses.CategorieAntecedentMedicaux;
import models.antecedantClasses.DossierMedical;
import models.antecedantClasses.Risque;
import models.consultation.Consultation;
import models.consultation.TypeConsultation;
import models.finance.Facture;
import models.finance.StatutPaiement;
import models.finance.TypePaiement;
import services.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ContentPanel extends JPanel {
    PatientDao patientDao;
    PatientService patientService;
    DossierDao dossierDao;
    FactureDao factureDao;
    DossierMedicalService dossierMedicalService;
    ConsultationDao consultationDao;
    ConsultationService consultationService;
    FactureService factureService;
    private Patient newPatient;
    private Consultation consultation = new Consultation();
    private DossierMedical dossierMedical = new DossierMedical();
    private Dentiste dentiste = new Dentiste();

    public ContentPanel(PatientDao patientDao, DossierDao dossierDao, ConsultationDao consultationDao, FactureDao factureDao) {
        setLayout(null);
        this.patientDao = patientDao;
        this.dossierDao = new DossierDao();
        this.consultationDao = new ConsultationDao();
        this.factureDao = new FactureDao();
        this.patientService = new PatientService(patientDao);
        this.dossierMedicalService = new DossierMedicalService(dossierDao);
        this.consultationService = new ConsultationService(consultationDao);
        this.factureService = new FactureService(factureDao);
        profileContent();
    }

    public void profileContent() {
        removeAll();
        setLayout(null);
        //icons
        JLabel fullName = new JLabel(resizeIcon(new ImageIcon("src/Static/icons/dentiste.png"), 50, 50));
        JLabel adresse = new JLabel(new ImageIcon("src/Static/icons/adresse.png"));
        JLabel phone = new JLabel(new ImageIcon("src/Static/icons/phone.png"));
        JLabel email = new JLabel(new ImageIcon("src/Static/icons/email.png"));
        JLabel specialite = new JLabel(resizeIcon(new ImageIcon("src/Static/icons/specialite.png"), 50, 50));

        //labels
        JLabel nameLabel = new JLabel(dentiste.getNom() + " " + dentiste.getPrenom());
        JLabel adresseLabel = new JLabel(dentiste.getAdresse());
        JLabel phoneLabel = new JLabel(dentiste.getTelephone());
        JLabel emailLabel = new JLabel(dentiste.getEmail());
        JLabel specialiteLabel = new JLabel(dentiste.getSpecialite().toString());


        //style
        nameLabel.setFont(Themes.DEFAULTFONT);
        adresseLabel.setFont(Themes.DEFAULTFONT);
        phoneLabel.setFont(Themes.DEFAULTFONT);
        emailLabel.setFont(Themes.DEFAULTFONT);
        fullName.setBounds(10, 50, 150, 50);
        nameLabel.setBounds(120, 50, 150, 50);
        adresse.setBounds(70, 180, 50, 50);
        adresseLabel.setBounds(140, 180, 150, 50);
        phone.setBounds(70, 280, 50, 50);
        phoneLabel.setBounds(140, 280, 150, 50);
        email.setBounds(70, 380, 50, 50);
        emailLabel.setBounds(140, 380, 250, 50);
        specialite.setBounds(70, 480, 50, 50);
        specialiteLabel.setBounds(140, 480, 250, 50);
        add(fullName);
        add(nameLabel);
        add(adresse);
        add(adresseLabel);
        add(phone);
        add(phoneLabel);
        add(email);
        add(emailLabel);
        add(specialite);
        add(specialiteLabel);
        revalidate();
        repaint();
    }

    public void patientContent() {
        removeAll();
        setLayout(new GridLayout(2,1));
        JPanel patientPanel = new JPanel(new GridLayout(1, 1));

        // Form
        JPanel formPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(Themes.DEFAULTFONT);
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameLabel.setFont(Themes.DEFAULTFONT);
        JTextField lastNameField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(Themes.DEFAULTFONT);
        JTextField addressField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(Themes.DEFAULTFONT);
        JTextField phoneField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(Themes.DEFAULTFONT);
        JTextField emailField = new JTextField();

        JLabel cinLabel = new JLabel("CIN:");
        cinLabel.setFont(Themes.DEFAULTFONT);
        JTextField cinField = new JTextField();

        JLabel birthLabel = new JLabel("Birth (YYYY-MM-DD):");
        birthLabel.setFont(Themes.DEFAULTFONT);
        JTextField birthField = new JTextField();

        JLabel mutuelleLabel = new JLabel("Mutual:");
        mutuelleLabel.setFont(Themes.DEFAULTFONT);

        JLabel antecedantLabel = new JLabel("Antecedant medical:");
        antecedantLabel.setFont(Themes.DEFAULTFONT);

        JLabel risqueLabel = new JLabel("Risk:");
        risqueLabel.setFont(Themes.DEFAULTFONT);

        Mutuelle[] mutuelleItems = {Mutuelle.CIMR, Mutuelle.CNAM, Mutuelle.CNOPS, Mutuelle.CNSS};
        CategorieAntecedentMedicaux[] categorieAntecedentMedicauxesItems = {CategorieAntecedentMedicaux.ALLERGIE,
                CategorieAntecedentMedicaux.CONTRE_INDICATION,
                CategorieAntecedentMedicaux.MALADIE_CHRONIQUE,
                CategorieAntecedentMedicaux.MALADIE_HEREDITAIRE,
                CategorieAntecedentMedicaux.AUTRE
        };
        Risque[] risqueItems = {Risque.LOW, Risque.AVERAGE, Risque.HIGH, Risque.UNKNOWN};
        JComboBox mutuelleField = new JComboBox(mutuelleItems);
        JComboBox antecedantField = new JComboBox(categorieAntecedentMedicauxesItems);
        JComboBox risqueField = new JComboBox(risqueItems);


        JLabel hint = new JLabel("click on a row");
        hint.setIcon(resizeIcon(new ImageIcon("src/Static/icons/hint.png"), 40, 40));
        JButton submitButton = new JButton("<html><font color='white'>Submit</font></html>", resizeIcon(new ImageIcon("src/Static/icons/submit.png"), 30, 30));
        submitButton.setFont(Themes.DEFAULTFONT);
        submitButton.setBackground(Themes.BUTTONCOLOR);

        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(cinLabel);
        formPanel.add(cinField);
        formPanel.add(birthLabel);
        formPanel.add(birthField);
        formPanel.add(mutuelleLabel);
        formPanel.add(mutuelleField);
        formPanel.add(antecedantLabel);
        formPanel.add(antecedantField);
        formPanel.add(risqueLabel);
        formPanel.add(risqueField);
        formPanel.add(new JLabel()); // Empty label for spacing
        formPanel.add(submitButton);
        formPanel.add(hint);

        patientPanel.add(formPanel);

        add(patientPanel, BorderLayout.CENTER);
        submitButton.addActionListener(e -> {
            String fname = firstNameField.getText();
            String lname = lastNameField.getText();
            String birth = birthField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String cin = cinField.getText();
            Mutuelle selectedMutuelle = (Mutuelle) mutuelleField.getSelectedItem();
            CategorieAntecedentMedicaux selectedAntecedant = (CategorieAntecedentMedicaux) antecedantField.getSelectedItem();
            Risque selectedRisque = (Risque) risqueField.getSelectedItem();

            AntecedantMedical antecedant = new AntecedantMedical(selectedAntecedant);
            selectedAntecedant.setRisqueAssocie(selectedRisque);
            try {
                dossierMedical = new DossierMedical(new ArrayList<>(), LocalDate.now(), new Patient());
                newPatient = new Patient(fname, lname, address, phone, email, cin, LocalDate.parse(birth), selectedMutuelle, antecedant, dossierMedical);
                if (fname.isEmpty() || lname.isEmpty() || birth.isEmpty() || address.isEmpty() ||
                        phone.isEmpty() || email.isEmpty() || cin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Fields warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (patientService != null) {
                    dossierMedical.setPatient(newPatient);
                    newPatient.setDossierMedical(dossierMedical);
                    patientService.addPatient(newPatient);
                    dossierMedicalService.addDossier(dossierMedical);
                    System.out.println(dossierMedicalService.getAllDossiers());

                    System.out.println("Patient added: " + newPatient);
                    refreshContent();

                } else {
                    System.out.println("PatientService not initialized.");
                }
            } catch (DateTimeParseException exp){
                JOptionPane.showMessageDialog(null, "Please fill the date of birth field with the correct format", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (StackOverflowError ignored){
                refreshContent();

            }
        });
        displayPatients();
        revalidate();
        repaint();
    }

    public void caisseContent() {
        removeAll();
        setLayout(new BorderLayout());

        ArrayList<Facture> factures = factureService.getAllFactures();
        String[] columnNames = {"ID facture", "Date", "Type de paiement","Statut de paiment", "Montant"};
        Object[][] data = new Object[factures.size()][columnNames.length];

        for (int i = 0; i < factures.size(); i++) {
            Facture facture = factures.get(i);
            data[i][0] = facture.getIdFacture();
            data[i][1] = facture.getDateFactureation();
            data[i][2] = facture.getTypePaiement();
            data[i][3] = facture.getStatutPaiement();
            data[i][4] = facture.getMontantTotal();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable factureTable = new JTable(tableModel);
        JTableHeader header = factureTable.getTableHeader();
        factureTable.setRowHeight(50);
        factureTable.setDefaultRenderer(Object.class, new CustomFontRenderer());
        header.setFont(Themes.DEFAULTFONT);
        header.setBackground(Themes.BUTTONCOLOR);
        header.setForeground(Color.WHITE);

        JScrollPane tableScrollPane = new JScrollPane(factureTable);
        add(tableScrollPane, BorderLayout.CENTER);

        ListSelectionModel selectionModel = factureTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Double recetteDeJour = 0.0;
        Double recetteDeMois = 0.0;
        Double recetteDeAnnee = 0.0;
        LocalDate currentDate = LocalDate.now();

        for (Facture facture : factures) {
            if (facture.getDateFactureation().equals(currentDate) && facture.getStatutPaiement() == StatutPaiement.PAID)
                recetteDeJour += facture.getMontantPaye();
            if (facture.getDateFactureation().getMonth() == currentDate.getMonth() && facture.getDateFactureation().getYear() == currentDate.getYear() && facture.getStatutPaiement() == StatutPaiement.PAID)
                recetteDeMois += facture.getMontantPaye();
            if (facture.getDateFactureation().getYear() == currentDate.getYear() && facture.getStatutPaiement() == StatutPaiement.PAID)
                recetteDeAnnee += facture.getMontantPaye();
        }

        JLabel recetteJourLabel = new JLabel("Recipe of the day : " + recetteDeJour + "                ");
        JLabel recetteMoisLabel = new JLabel("Recipe of the month : " + recetteDeMois + "                ");
        JLabel recetteAnneeLabel = new JLabel("Recipe of the year : " + recetteDeAnnee);

        JPanel recettePanel = new JPanel();
        recettePanel.setLayout(new FlowLayout());
        recettePanel.add(recetteJourLabel);
        recettePanel.add(recetteMoisLabel);
        recettePanel.add(recetteAnneeLabel);
        JLabel hint = new JLabel("Hint : click on a row to update it");
        hint.setIcon(resizeIcon(new ImageIcon("src/Static/icons/hint.png"), 40, 40));
        JPanel hintPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hintPanel.add(hint);

        add(hintPanel, BorderLayout.NORTH);
        add(recettePanel, BorderLayout.SOUTH);

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = factureTable.getSelectedRow();

                if (selectedRow != -1) {
                    Object factureId = factureTable.getValueAt(selectedRow, 0);

                    Facture selectedFacture = factureService.getFactureById(factureId.toString());

                    updateFactureContent(selectedFacture);
                }
            }
        });
        revalidate();
        repaint();
    }

    public void displayPatients() {

        ArrayList<Patient> patients = patientService.getAllPatients();

        String[] columnNames = {"ID", "Full Name", "Phone", "Address", "Birth"};

        Object[][] data = new Object[patients.size()][columnNames.length];

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            data[i][0] = patient.getId();
            data[i][1] = patient.getNom() + " " + patient.getPrenom();
            data[i][2] = patient.getTelephone();
            data[i][3] = patient.getAdresse();
            data[i][4] = patient.getDateNaissance();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable patientTable = new JTable(tableModel);
        JTableHeader header = patientTable.getTableHeader();
        header.setBackground(Themes.BUTTONCOLOR);
        header.setForeground(Color.WHITE);
        JScrollPane tableScrollPane = new JScrollPane(patientTable);
        patientTable.setRowHeight(50);
        patientTable.setDefaultRenderer(Object.class, new CustomFontRenderer());
        header.setFont(Themes.DEFAULTFONT);
        add(tableScrollPane, BorderLayout.CENTER);
        ListSelectionModel selectionModel = patientTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = patientTable.getSelectedRow();

                if (selectedRow != -1) {
                    Object patientId = patientTable.getValueAt(selectedRow, 0);
                    dossierMedicalContent((String)patientId);
                }
            }
        });
    }

    public void dossierMedicalContent(String patientId) {
        removeAll();
        JPanel topPanel = new JPanel(new GridLayout(1, 1));
        JPanel bottomPanel = new JPanel(new GridLayout(0, 4, 5, 40));
        topPanel.setBackground(Color.WHITE);
        ImageIcon imageIcon = new ImageIcon("src/Static/images/dents.png");
        JLabel imageLabel = new JLabel(imageIcon);
        topPanel.add(imageLabel);

        // Form
        JLabel dentLabel = new JLabel("N-tooth:");
        dentLabel.setFont(Themes.DEFAULTFONT);
        JTextField dentField = new JTextField();

        JLabel prixBaseLabel = new JLabel("Starting price:");
        prixBaseLabel.setFont(Themes.DEFAULTFONT);
        JTextField prixBaseField = new JTextField();

        JLabel prixPatientLabel = new JLabel("patient price:");
        prixPatientLabel.setFont(Themes.DEFAULTFONT);
        JTextField prixPatientField = new JTextField();

        JLabel noteLabel = new JLabel("Note:");
        noteLabel.setFont(Themes.DEFAULTFONT);
        JTextArea noteField = new JTextArea();

        JLabel acteLabel = new JLabel("Act:");
        acteLabel.setFont(Themes.DEFAULTFONT);

        CategorieActe[] acteItems = {
                CategorieActe.CHIRURGIE,
                CategorieActe.DIAGNOSTIC,
                CategorieActe.IMPLANTOLOGIE,
                CategorieActe.ORTHODONTIE,
                CategorieActe.PREVENTION,
                CategorieActe.PROTHESE_DENTAIRE,
                CategorieActe.SOINS_DES_CARIES,
                CategorieActe.SOINS_DES_TISSUS_MOUS
        };
        JComboBox acteField = new JComboBox(acteItems);

        JLabel typeConsultationLabel = new JLabel("Type de consultation:");
        typeConsultationLabel.setFont(Themes.DEFAULTFONT);

        TypeConsultation[] typeConsultationItems = {
                TypeConsultation.GENERAL_CONSULTATION,
                TypeConsultation.FOLLOW_UP,
                TypeConsultation.EMERGENCY
        };
        JComboBox typeConsultationField = new JComboBox(typeConsultationItems);


        JButton submitButton = new JButton("<html><font color='white'>Submit</font></html>", resizeIcon(new ImageIcon("src/Static/icons/submit.png"), 30, 30));
        submitButton.setFont(Themes.DEFAULTFONT);
        submitButton.setBackground(Themes.BUTTONCOLOR);
        JButton factureButton = new JButton("<html><font color='white'>Billing</font></html>", resizeIcon(new ImageIcon("src/Static/icons/facture.png"), 30, 30));
        factureButton.setFont(Themes.DEFAULTFONT);
        factureButton.setBackground(Themes.BUTTONCOLOR);
        JLabel warning = new JLabel("submit consultation then");
        JLabel warningRest = new JLabel("click on facture button");

        warning.setIcon(resizeIcon(new ImageIcon("src/Static/icons/warning.png"), 30, 30));


        bottomPanel.add(acteLabel);
        bottomPanel.add(acteField);
        bottomPanel.add(dentLabel);
        bottomPanel.add(dentField);
        bottomPanel.add(prixBaseLabel);
        bottomPanel.add(prixBaseField);
        bottomPanel.add(prixPatientLabel);
        bottomPanel.add(prixPatientField);
        bottomPanel.add(typeConsultationLabel);
        bottomPanel.add(typeConsultationField);
        bottomPanel.add(noteLabel);
        bottomPanel.add(noteField);
        bottomPanel.add(new JLabel());
        bottomPanel.add(submitButton);
        bottomPanel.add(warning);
        bottomPanel.add(warningRest);
        bottomPanel.add(new JLabel());
        bottomPanel.add(factureButton);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        submitButton.addActionListener(e -> {
            try {
                CategorieActe categorieActe = (CategorieActe) acteField.getSelectedItem();
                Long dent = Long.parseLong(dentField.getText());
                Double prixBase = Double.parseDouble(prixBaseField.getText());
                Double prixPatient = Double.parseDouble(prixPatientField.getText());
                TypeConsultation typeConsultation = (TypeConsultation) typeConsultationField.getSelectedItem();
                String note = noteField.getText();

                //update dossier medical de patient
                Patient p = patientService.getPatientById(patientId);
                DossierMedical dossier = p.getDossierMedical();

                Acte acte = new Acte(new ArrayList<>(), prixBase, categorieActe);
                InterventionMedecin inter = new InterventionMedecin(note, prixPatient, dent, acte, new Consultation());
                acte.addIntervention(inter);
                consultation = new Consultation(new ArrayList<>(), dossier, LocalDate.now(), typeConsultation, new ArrayList<>());
                inter.setConsultation(consultation);
                consultation.addIntervention(inter);
                dossier.addConsultation(consultation);
                p.setDossierMedical(dossier);
                patientService.updatePatient(p);
                dossierMedicalService.updateDossier(dossier);
                consultationService.addConsultation(consultation);
            } catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(null, "Please fill the fields with valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        factureButton.addActionListener(e -> {
            Double prixPatient = Double.parseDouble(prixPatientField.getText());
            String consultationId = consultation.getIdConsultation();
            factureContent(patientId, consultationId, prixPatient);
        });
        revalidate();
        repaint();
    }

    public void factureContent(String patientId, String consultationId, Double prixPatient){
        removeAll();
        setLayout(new GridLayout(2,1));
        JPanel facturePanel = new JPanel(new GridLayout(1, 1));

        JPanel formPanel = new JPanel(new GridLayout(0, 4, 15, 90));
        JLabel montantPayeLabel = new JLabel("Amount paid:");
        montantPayeLabel.setFont(Themes.DEFAULTFONT);
        JTextField montantPayeField = new JTextField();

        JLabel resteAPayeLabel = new JLabel("Stay paid:");
        resteAPayeLabel.setFont(Themes.DEFAULTFONT);
        JTextField resteAPayeField = new JTextField();

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(Themes.DEFAULTFONT);
        JLabel totalLabelValue = new JLabel(prixPatient.toString());
        totalLabel.setFont(Themes.DEFAULTFONT);

        JLabel statutDeFactureLabel = new JLabel("Payment status:");
        statutDeFactureLabel.setFont(Themes.DEFAULTFONT);

        JLabel typePayementLabel = new JLabel("Payment type:");
        typePayementLabel.setFont(Themes.DEFAULTFONT);

        StatutPaiement[] statutItems = {StatutPaiement.UNPAID, StatutPaiement.WAITING, StatutPaiement.PAID};
        JComboBox statutField = new JComboBox(statutItems);

        TypePaiement[] typePayementItems = {TypePaiement.CREDIT_CARD, TypePaiement.CHECK, TypePaiement.CASH, TypePaiement.TRANSFER, TypePaiement.OTHER};
        JComboBox typePayementField = new JComboBox(typePayementItems);

        JButton submitButton = new JButton("<html><font color='white'>Submit</font></html>", resizeIcon(new ImageIcon("src/Static/icons/submit.png"), 30, 30));
        submitButton.setFont(Themes.DEFAULTFONT);
        submitButton.setBackground(Themes.BUTTONCOLOR);

        formPanel.add(statutDeFactureLabel);
        formPanel.add(statutField);
        formPanel.add(typePayementLabel);
        formPanel.add(typePayementField);
        formPanel.add(montantPayeLabel);
        formPanel.add(montantPayeField);
        formPanel.add(resteAPayeLabel);
        formPanel.add(resteAPayeField);
        formPanel.add(totalLabel);
        formPanel.add(totalLabelValue);
        formPanel.add(new JLabel());
        formPanel.add(submitButton);

        facturePanel.add(formPanel);

        add(facturePanel, BorderLayout.CENTER);
        submitButton.addActionListener(e -> {
            StatutPaiement selectedStatut = (StatutPaiement) statutField.getSelectedItem();
            TypePaiement selectedType = (TypePaiement) typePayementField.getSelectedItem();
            Double montantPaye = Double.parseDouble(montantPayeField.getText());
            Double resteAPaye = Double.parseDouble(resteAPayeField.getText());
            Double total = prixPatient;

            try {
                if (montantPaye.isNaN() || resteAPaye.isNaN() || total.isNaN()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Fields warning", JOptionPane.WARNING_MESSAGE);
                } else if (prixPatient != (montantPaye + resteAPaye)) {
                    JOptionPane.showMessageDialog(null, "The sum of amount paid and remains to be paid should equal to total", "Fields warning", JOptionPane.WARNING_MESSAGE);
                } else{
                    Consultation consultation1 = consultationService.getConsultationById(consultationId);
                    Facture facture = new Facture(selectedStatut, resteAPaye, montantPaye, LocalDate.now(), total, new Consultation(), selectedType);
                    consultation1.addFacture(facture);
                    facture.setConsultation(consultation1);
                    consultationService.updateConsultation(consultation1);
                    Patient patient = patientService.getPatientById(patientId);
                    DossierMedical dossier = patient.getDossierMedical();
                    //save facture to file
                    factureService.addFacture(facture);
                    dossierMedicalService.updateDossier(dossier);
            }
            } catch (StackOverflowError ignored){
            }
        });
        ArrayList<Facture> factures = factureService.getAllFactures();
        String[] columnNames = {"ID facture", "Date", "Type de paiement","Statut de paiment", "Montant"};
        Object[][] data = new Object[factures.size()][columnNames.length];

        for (int i = 0; i < factures.size(); i++) {
            Facture facture = factures.get(i);
            data[i][0] = facture.getIdFacture();
            data[i][1] = facture.getDateFactureation();
            data[i][2] = facture.getTypePaiement();
            data[i][3] = facture.getStatutPaiement();
            data[i][4] = facture.getMontantTotal();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable factureTable = new JTable(tableModel);
        JTableHeader header = factureTable.getTableHeader();
        header.setBackground(Themes.BUTTONCOLOR);
        header.setForeground(Color.WHITE);

        JScrollPane tableScrollPane = new JScrollPane(factureTable);
        add(tableScrollPane, BorderLayout.CENTER);

        ListSelectionModel selectionModel = factureTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        revalidate();
        repaint();
    }

    private void updateFactureContent(Facture facture) {
        removeAll();
        setLayout(new GridLayout(1, 1));

        JPanel updatePanel = new JPanel(new GridLayout(1, 1));
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 50));

        JLabel montantPayeLabel = new JLabel("Amount paid:");
        montantPayeLabel.setFont(Themes.DEFAULTFONT);
        JTextField montantPayeField = new JTextField();

        JLabel resteAPayeLabel = new JLabel("Stay paid:");
        resteAPayeLabel.setFont(Themes.DEFAULTFONT);
        JTextField resteAPayeField = new JTextField();

        JLabel totalLabel = new JLabel("Total:");
        totalLabel.setFont(Themes.DEFAULTFONT);
        JLabel totalField = new JLabel();

        JLabel statutLabel = new JLabel("Payment status:");
        statutLabel.setFont(Themes.DEFAULTFONT);
        JComboBox<StatutPaiement> statutComboBox = new JComboBox<>(StatutPaiement.values());

        montantPayeField.setText(facture.getMontantPaye().toString());
        resteAPayeField.setText(facture.getMontantRestant().toString());
        totalField.setText(facture.getMontantTotal().toString());

        statutComboBox.setSelectedItem(facture.getStatutPaiement());
        JButton updateButton = new JButton("<html><font color='white'>Update</font></html>", resizeIcon(new ImageIcon("src/Static/icons/update.png"), 30, 30));
        updateButton.setFont(Themes.DEFAULTFONT);
        updateButton.setBackground(Themes.BUTTONCOLOR);

        formPanel.add(new JLabel());
        formPanel.add(new JLabel());
        formPanel.add(montantPayeLabel);
        formPanel.add(montantPayeField);
        formPanel.add(resteAPayeLabel);
        formPanel.add(resteAPayeField);
        formPanel.add(totalLabel);
        formPanel.add(totalField);
        formPanel.add(statutLabel);
        formPanel.add(statutComboBox);
        formPanel.add(new JLabel());
        formPanel.add(updateButton);
        formPanel.add(new JLabel());
        formPanel.add(new JLabel());

        updatePanel.add(formPanel);

        add(updatePanel, BorderLayout.CENTER);

        updateButton.addActionListener(e -> {
            Double updatedMontantPaye = Double.parseDouble(montantPayeField.getText());
            Double updatedResteAPaye = Double.parseDouble(resteAPayeField.getText());

            facture.setMontantPaye(updatedMontantPaye);
            facture.setMontantRestant(updatedResteAPaye);
            facture.setStatutPaiement((StatutPaiement) statutComboBox.getSelectedItem());
            factureService.updateFacture(facture);
            caisseContent();
        });

        revalidate();
        repaint();
    }

    public void refreshContent() {
        removeAll();
        patientContent();
        revalidate();
        repaint();
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}