# Mini Hospital Emergency Management System

A Java-based hospital emergency and patient record management system designed using fundamental custom data structures without relying on built-in Java collection libraries.

## Implemented Data Structures & Purpose

1. **Patient Records - Binary Search Tree (BST)**
   - **Key:** Patient ID
   - **Features:** Efficient insert, key-based lookup, deletion (handling leaf nodes, single child, and two-child nodes using an in-order successor), and in-order traversal to list patients in ascending order.
   
2. **Emergency Patient Queue - Circular Queue**
   - **Principle:** First-In, First-Out (FIFO)
   - **Features:** Enqueue incoming emergency patients, dequeue the next patient for treatment, and display waiting patients with circular array wrap-around handling.

3. **Treatment History - Stack**
   - **Principle:** Last-In, First-Out (LIFO)
   - **Features:** Push completed treatment records onto the stack, pop the most recent record for rollback/review, and display records in reverse chronological order.

4. **Patient Visit History - Singly Linked List**
   - **Principle:** Dynamic sequential chain
   - **Features:** Linked list stored inside each patient object to log previous clinic visits, delete specific visits, and traverse full consultation histories.

## How to Compile & Run

Open a terminal in the root `HospitalEMS` directory:

```bash
# Compile all source files into bin directory
javac -d bin src/model/*.java src/datastructures/*.java src/Main.java

# Run the program
java -cp bin Main
