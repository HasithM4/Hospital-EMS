package datastructures;

import model.TreatmentRecord;

public class TreatmentStack {
    private int maxSize;
    private TreatmentRecord[] stackArray;
    private int top;

    public TreatmentStack(int size) {
        this.maxSize = size;
        this.stackArray = new TreatmentRecord[maxSize];
        this.top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    
    public void push(TreatmentRecord record) {
        if (isFull()) {
            System.out.println("Treatment History Stack is full!");
            return;
        }
        stackArray[++top] = record;
        System.out.println("Treatment record successfully archived in history stack.");
    }

    
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment History Stack is empty!");
            return null;
        }
        return stackArray[top--];
    }

    
    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return stackArray[top];
    }

    
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records found in the history stack.");
            return;
        }
        System.out.println("\n--- Completed Treatments History (LIFO - Most Recent First) ---");
        for (int i = top; i >= 0; i--) {
            stackArray[i].display();
        }
    }
}
