package datastructures;

import model.VisitRecord;

public class VisitHistoryList {
    private VisitRecord first;

    public VisitHistoryList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    
    public void addVisit(int visitId, String date, String doctor, String diagnosis, String treatment) {
        VisitRecord newVisit = new VisitRecord(visitId, date, doctor, diagnosis, treatment);
        newVisit.next = first;
        first = newVisit;
    }

   
    public VisitRecord findVisit(int visitId) {
        VisitRecord current = first;
        while (current != null) {
            if (current.visitId == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    
    public boolean deleteVisit(int visitId) {
        if (isEmpty()) return false;

        VisitRecord current = first;
        VisitRecord previous = first;

        while (current != null && current.visitId != visitId) {
            previous = current;
            current = current.next;
        }

        if (current == null) return false;

        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return true;
    }

    
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("  No prior visit history.");
            return;
        }
        VisitRecord current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }
}