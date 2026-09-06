package datastructures;

import model.Patient;

public class EmergencyQueue {
    private int maxSize;
    private Patient[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public EmergencyQueue(int size) {
        this.maxSize = size;
        this.queArray = new Patient[maxSize];
        this.front = 0;
        this.rear = -1;
        this.nItems = 0;
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

   
    public void enqueue(Patient p) {
        if (isFull()) {
            System.out.println("Emergency Queue is full! Cannot admit more patients.");
            return;
        }
        if (rear == maxSize - 1) {
            rear = -1; 
        }
        queArray[++rear] = p;
        nItems++;
        System.out.println("Patient " + p.name + " (ID: " + p.patientId + ") added to emergency queue.");
    }

    
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency Queue is empty! No patients waiting.");
            return null;
        }
        Patient temp = queArray[front++];
        if (front == maxSize) {
            front = 0; 
        }
        nItems--;
        return temp;
    }

   
    public Patient peekFront() {
        if (isEmpty()) {
            return null;
        }
        return queArray[front];
    }

    
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        System.out.println("\n--- Emergency Waiting Queue (FIFO) ---");
        int count = 0;
        int i = front;
        while (count < nItems) {
            Patient p = queArray[i];
            System.out.println((count + 1) + ". ID: " + p.patientId + " | Name: " + p.name 
                    + " | Condition: " + p.medicalCondition);
            i = (i + 1) % maxSize;
            count++;
        }
    }
}