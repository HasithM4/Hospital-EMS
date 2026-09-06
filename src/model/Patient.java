package model;

import datastructures.VisitHistoryList;

public class Patient {
    public int patientId;
    public String name;
    public int age;
    public String contactNumber;
    public String medicalCondition;
    public VisitHistoryList visitHistory;

    public Patient leftChild;
    public Patient rightChild;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistoryList();
        this.leftChild = null;
        this.rightChild = null;
    }

    public void displayPatient() {
        System.out.println("ID: " + patientId + " | Name: " + name + " | Age: " + age 
                + " | Contact: " + contactNumber + " | Condition: " + medicalCondition);
    }
}