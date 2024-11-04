package UI.secretaireDashboard.panels;

import Database.dao.DossierDao;
import Database.dao.FactureDao;
import Database.dao.PatientDao;
import Static.Themes;
import UI.CustomFontRenderer;
import models.Mutuelle;
import models.Patient;
import models.Secretaire;
import models.antecedantClasses.AntecedantMedical;
import models.antecedantClasses.CategorieAntecedentMedicaux;
import models.antecedantClasses.DossierMedical;
import models.antecedantClasses.Risque;
import models.finance.Facture;
import models.finance.StatutPaiement;
import services.DossierMedicalService;
import services.FactureService;
import services.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ContentPanelS extends JPanel {
    PatientDao patientDao;
    PatientService patientService;
    DossierDao dossierDao;
    DossierMedicalService dossierMedicalService;
    FactureDao factureDao;
    FactureService factureService;
    private Patient newPatient;
    private DossierMedical dossierMedical = new DossierMedical();
    private Secretaire secretaire = new Secretaire();

    public ContentPanelS(PatientDao patientDao, DossierDao dossierDao, FactureDao factureDao) {
        setLayout(null);
        this.patientDao = patientDao;
        this.dossierDao = new DossierDao();
        this.factureDao = new FactureDao();
        this.patientService = new PatientService(patientDao);
        this.dossierMedicalService = new DossierMedicalService(dossierDao);
        this.factureService = new FactureService(factureDao);
        profileContent();
    }

    public void profileContent() {
        removeAll();
        setLayout(null);
        //icons
        JLabel fullName = new JLabel(resizeIcon(new ImageIcon("src/Static/icons/secretary.png"), 50, 50));
        JLabel adresse = new JLabel(new ImageIcon("src/Static/icons/adresse.png"));
        JLabel phone = new JLabel(new ImageIcon("src/Static/icons/phone.png"));
        JLabel email = new JLabel(new ImageIcon("src/Static/icons/email.png"));

        //labels
        JLabel nameLabel = new JLabel(secretaire.getNom() + " " + secretaire.getPrenom());
        JLabel adresseLabel = new JLabel(secretaire.getAdresse());
        JLabel phoneLabel = new JLabel(secretaire.getTelephone());
        JLabel emailLabel = new JLabel(secretaire.getEmail());


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
        add(fullName);
        add(nameLabel);
        add(adresse);
        add(adresseLabel);
        add(phone);
        add(phoneLabel);
        add(email);
        add(emailLabel);
        revalidate();
        repaint();
    }


    public void patientContent() {
        removeAll();
        setLayout(new GridLayout(2,1));
        // Create a panel to hold the components
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



        JButton submitButton = new JButton("<html><font color='white'>Submit</font></html>", resizeIcon(new ImageIcon("src/Static/icons/submit.png"), 30, 30));
        submitButton.setFont(Themes.DEFAULTFONT);
        submitButton.setBackground(Themes.BUTTONCOLOR);

        JLabel hint = new JLabel("click on a row");
        hint.setIcon(resizeIcon(new ImageIcon("src/Static/icons/hint.png"), 40, 40));

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
        formPanel.add(new JLabel());
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

            dossierMedical = new DossierMedical(new ArrayList<>(), LocalDate.now(), new Patient());

            AntecedantMedical antecedant = new AntecedantMedical(selectedAntecedant);
            selectedAntecedant.setRisqueAssocie(selectedRisque);
            try {
                newPatient = new Patient(fname, lname, address, phone, email, cin, LocalDate.parse(birth), selectedMutuelle, antecedant, dossierMedical);
                if (fname.isEmpty() || lname.isEmpty() || birth.isEmpty() || address.isEmpty() ||
                        phone.isEmpty() || email.isEmpty() || cin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Fields warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (patientService != null) {
                    dossierMedical.setPatient(newPatient);
                    patientService.addPatient(newPatient);
                    dossierMedicalService.addDossier(dossierMedical);
                    System.out.println("Patient added: " + newPatient);
                    refreshContent();
                } else {
                    System.out.println("PatientService not initialized.");
                }
            } catch (DateTimeParseException exp){
                System.out.println("ghjkl");
                JOptionPane.showMessageDialog(null, "Please fill the date of birth field with the correct format", "Error", JOptionPane.ERROR_MESSAGE);
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
                    Patient selectedPatient = patients.get(selectedRow);

                    updatePatientContent(selectedPatient);
                }
            }
        });
    }


    private void updatePatientContent(Patient patient) {
            removeAll();
            setLayout(new GridLayout(1, 1));
            JPanel updatePanel = new JPanel(new GridLayout(1, 1));

            // Form
            JPanel formPanel = new JPanel(new GridLayout(0, 4, 60, 60));

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

            firstNameField.setText(patient.getNom());
            lastNameField.setText(patient.getPrenom());
            addressField.setText(patient.getAdresse());
            phoneField.setText(patient.getTelephone());
            emailField.setText(patient.getEmail());
            cinField.setText(patient.getCin());
            JButton updateButton = new JButton("<html><font color='white'>Update</font></html>", resizeIcon(new ImageIcon("src/Static/icons/update.png"), 30, 30));
            updateButton.setFont(Themes.DEFAULTFONT);
            updateButton.setBackground(Themes.BUTTONCOLOR);
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
            formPanel.add(new JLabel());
            formPanel.add(updateButton);
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());
            formPanel.add(new JLabel());



            updatePanel.add(formPanel);

            add(updatePanel, BorderLayout.CENTER);

            updateButton.addActionListener(e -> {
                // Get the updated information
                String updatedFirstName = firstNameField.getText();
                String updatedLastName = lastNameField.getText();
                String updatedAdresse = addressField.getText();
                String updatedPhone = phoneField.getText();
                String updatedEmail = emailField.getText();
                String updatedCIN = cinField.getText();
                patient.setNom(updatedFirstName);
                patient.setPrenom(updatedLastName);
                patient.setAdresse(updatedAdresse);
                patient.setTelephone(updatedPhone);
                patient.setEmail(updatedEmail);
                patient.setCin(updatedCIN);
                patientService.updatePatient(patient);
                refreshContent();
            });

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

    private Icon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}