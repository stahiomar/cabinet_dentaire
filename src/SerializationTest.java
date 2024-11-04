import models.Mutuelle;
import models.Patient;
import models.antecedantClasses.AntecedantMedical;
import models.antecedantClasses.CategorieAntecedentMedicaux;
import models.antecedantClasses.DossierMedical;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SerializationTest {
    public static void main(String[] args) {
        Patient patient = new Patient("John", "Doe", "Address", "123456789", "john@example.com", "ABC123", LocalDate.now(), Mutuelle.CIMR, new AntecedantMedical(CategorieAntecedentMedicaux.AUTRE), new DossierMedical());

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.ser"))) {
            oos.writeObject(patient);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"))) {
            Patient deserializedPatient = (Patient) ois.readObject();
            System.out.println("Deserialized Patient: " + deserializedPatient);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}