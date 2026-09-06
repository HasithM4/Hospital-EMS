package datastructures;

import model.Patient;

public class PatientBST {
    private Patient root;

    public PatientBST() {
        this.root = null;
    }

    
    public Patient find(int patientId) {
        Patient current = root;
        while (current != null && current.patientId != patientId) {
            if (patientId < current.patientId) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current;
    }

    
    public void insert(int patientId, String name, int age, String contact, String condition) {
        Patient newPatient = new Patient(patientId, name, age, contact, condition);
        if (root == null) {
            root = newPatient;
            return;
        }

        Patient current = root;
        Patient parent;
        while (true) {
            parent = current;
            if (patientId < current.patientId) {
                current = current.leftChild;
                if (current == null) {
                    parent.leftChild = newPatient;
                    return;
                }
            } else if (patientId > current.patientId) {
                current = current.rightChild;
                if (current == null) {
                    parent.rightChild = newPatient;
                    return;
                }
            } else {
                System.out.println("Patient with ID " + patientId + " already exists!");
                return;
            }
        }
    }

    
    public void inOrderDisplay() {
        inOrder(root);
    }

    private void inOrder(Patient localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            localRoot.displayPatient();
            inOrder(localRoot.rightChild);
        }
    }

   
    public boolean delete(int key) {
        Patient current = root;
        Patient parent = root;
        boolean isLeftChild = true;

        while (current != null && current.patientId != key) {
            parent = current;
            if (key < current.patientId) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
        }

        if (current == null) return false;

        // Case 1: Leaf node
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) root = null;
            else if (isLeftChild) parent.leftChild = null;
            else parent.rightChild = null;
        }
        // Case 2: One child (right)
        else if (current.leftChild == null) {
            if (current == root) root = current.rightChild;
            else if (isLeftChild) parent.leftChild = current.rightChild;
            else parent.rightChild = current.rightChild;
        }
        // Case 2: One child (left)
        else if (current.rightChild == null) {
            if (current == root) root = current.leftChild;
            else if (isLeftChild) parent.leftChild = current.leftChild;
            else parent.rightChild = current.leftChild;
        }
        // Case 3: Two children
        else {
            Patient successor = getSuccessor(current);
            if (current == root) root = successor;
            else if (isLeftChild) parent.leftChild = successor;
            else parent.rightChild = successor;

            successor.leftChild = current.leftChild;
        }
        return true;
    }

    private Patient getSuccessor(Patient delNode) {
        Patient successorParent = delNode;
        Patient successor = delNode;
        Patient current = delNode.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
}
