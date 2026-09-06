package model;

public class TreatmentRecord {
    public int patientId;
    public String patientName;
    public String doctorName;
    public String treatmentDetails;
    public String timestamp;

    public TreatmentRecord(int patientId, String patientName, String doctorName, String treatmentDetails, String timestamp) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.treatmentDetails = treatmentDetails;
        this.timestamp = timestamp;
    }

    public void display() {
        System.out.println("Patient: " + patientName + " (ID: " + patientId + ") | Doctor: " + doctorName 
                + " | Treatment: " + treatmentDetails + " | Time: " + timestamp);
    }
}