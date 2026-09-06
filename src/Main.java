import datastructures.EmergencyQueue;
import datastructures.PatientBST;
import datastructures.TreatmentStack;
import model.Patient;
import model.TreatmentRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientBST patientBST = new PatientBST();
        EmergencyQueue emergencyQueue = new EmergencyQueue(50);
        TreatmentStack treatmentStack = new TreatmentStack(50);

        int visitCounter = 1000;

        while (true) {
            System.out.println("\n==================================================");
            System.out.println("  MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
            System.out.println("==================================================");
            System.out.println("1. Register New Patient");
            System.out.println("2. Search Patient Record ");
            System.out.println("3. Delete Patient Record");
            System.out.println("4. Display All Registered Patients");
            System.out.println("5. Add Patient to Emergency Queue");
            System.out.println("6. Treat Next Emergency Patient");
            System.out.println("7. View Emergency Waiting Queue");
            System.out.println("8. View Treatment History");
            System.out.println("9. Undo / Pop Last Completed Treatment");
            System.out.println("10. Add Visit History to Patient");
            System.out.println("11. View Patient Visit History");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID (Integer): ");
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("Enter Contact Number: ");
                    String contact = scanner.nextLine().trim();
                    System.out.print("Enter Medical Condition: ");
                    String cond = scanner.nextLine().trim();

                    patientBST.insert(id, name, age, contact, cond);
                    System.out.println("Patient registration completed.");
                    break;

                case 2:
                    System.out.print("Enter Patient ID to search: ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                    Patient found = patientBST.find(id);
                    if (found != null) {
                        System.out.println("\n--- Patient Found ---");
                        found.displayPatient();
                    } else {
                        System.out.println("Patient with ID " + id + " not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Patient ID to delete: ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                    if (patientBST.delete(id)) {
                        System.out.println("Patient record " + id + " deleted successfully from BST.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- All Registered Patients (Sorted by ID) ---");
                    patientBST.inOrderDisplay();
                    break;

                case 5:
                    System.out.print("Enter Registered Patient ID for Emergency: ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                    Patient pQueue = patientBST.find(id);
                    if (pQueue != null) {
                        emergencyQueue.enqueue(pQueue);
                    } else {
                        System.out.println("Patient ID not registered. Please register the patient first.");
                    }
                    break;

                case 6:
                    Patient treatedPatient = emergencyQueue.dequeue();
                    if (treatedPatient != null) {
                        System.out.println("\nNow Treating Patient: " + treatedPatient.name + " (ID: " + treatedPatient.patientId + ")");
                        System.out.print("Enter Attending Doctor Name: ");
                        String doc = scanner.nextLine().trim();
                        System.out.print("Enter Treatment Given: ");
                        String tx = scanner.nextLine().trim();

                        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        
                        TreatmentRecord rec = new TreatmentRecord(treatedPatient.patientId, treatedPatient.name, doc, tx, timestamp);
                        treatmentStack.push(rec);

                        
                        treatedPatient.visitHistory.addVisit(++visitCounter, timestamp, doc, treatedPatient.medicalCondition, tx);
                        System.out.println("Treatment finished and saved to stack and visit history.");
                    }
                    break;

                case 7:
                    emergencyQueue.displayQueue();
                    break;

                case 8:
                    treatmentStack.displayStack();
                    break;

                case 9:
                    TreatmentRecord popped = treatmentStack.pop();
                    if (popped != null) {
                        System.out.println("\nPopped most recent record from stack:");
                        popped.display();
                    }
                    break;

                case 10:
                    System.out.print("Enter Patient ID: ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                    Patient pHist = patientBST.find(id);
                    if (pHist != null) {
                        System.out.print("Enter Visit Date (e.g. 2026-09-03): ");
                        String d = scanner.nextLine().trim();
                        System.out.print("Enter Doctor: ");
                        String doc = scanner.nextLine().trim();
                        System.out.print("Enter Diagnosis: ");
                        String diag = scanner.nextLine().trim();
                        System.out.print("Enter Treatment: ");
                        String tx = scanner.nextLine().trim();
                        pHist.visitHistory.addVisit(++visitCounter, d, doc, diag, tx);
                        System.out.println("Visit added successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 11:
                    System.out.print("Enter Patient ID: ");
                    id = Integer.parseInt(scanner.nextLine().trim());
                    Patient pView = patientBST.find(id);
                    if (pView != null) {
                        System.out.println("\n--- Visit History for " + pView.name + " (ID: " + pView.patientId + ") ---");
                        pView.visitHistory.displayHistory();
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please enter a valid number (0-11).");
            }
        }
    }
}
