package model;

public class VisitRecord {
    public int visitId;
    public String visitDate;
    public String doctorName;
    public String diagnosis;
    public String treatment;
    public VisitRecord next;

    public VisitRecord(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    public void display() {
        System.out.println("  [Visit ID: " + visitId + " | Date: " + visitDate + " | Doctor: " + doctorName 
                + " | Diagnosis: " + diagnosis + " | Treatment: " + treatment + "]");
    }
}