import java.util.Scanner;

/* PATIENT NODE (Linked List) */
class Patient {
    int id;
    String name;
    String phone;
    String email;
    Patient next;

    Patient(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.next = null;
    }
}

/* HASH TABLE USING SEPARATE CHAINING */

class PatientHashTable {

    int size = 10;
    Patient table[] = new Patient[size];

    int hashFunction(int id) {
        return id % size;
    }

    void insert(Patient p) {

        int index = hashFunction(p.id);

        if (table[index] == null) {
            table[index] = p;
        } else {

            Patient temp = table[index];

            while (temp.next != null)
                temp = temp.next;

            temp.next = p;
        }
    }

    void displayHash() {

        for (int i = 0; i < size; i++) {

            System.out.print("Index " + i + ": ");

            Patient temp = table[i];

            while (temp != null) {
                System.out.print(temp.name + " -> ");
                temp = temp.next;
            }

            System.out.println("null");
        }
    }
}

/* DOCTOR CLASS */

class Doctor {
    int id;
    String name;
    String specialization;
    int experience;

    Doctor(int id, String name, String specialization, int experience) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }
}

/* QUEUE FOR TOKEN */

class TokenQueue {

    int front = -1;
    int rear = -1;
    int size = 100;
    int queue[] = new int[size];

    void enqueue(int token) {

        if (rear == size - 1) {
            System.out.println("Queue Full");
            return;
        }

        if (front == -1)
            front = 0;

        queue[++rear] = token;
    }

    void display() {

        if (front == -1) {
            System.out.println("No Tokens");
            return;
        }

        for (int i = front; i <= rear; i++) {
            System.out.println("Token: " + queue[i]);
        }
    }
}

/* STACK FOR HISTORY */

class ActionStack {

    int top = -1;
    String stack[] = new String[100];

    void push(String action) {

        if (top == 99)
            return;

        stack[++top] = action;
    }

    void display() {

        if (top == -1) {
            System.out.println("No History Available");
            return;
        }

        for (int i = top; i >= 0; i--)
            System.out.println(stack[i]);
    }
}

public class HealHubDSA {

    static Scanner sc = new Scanner(System.in);

    static Patient head = null;

    static PatientHashTable hashTable = new PatientHashTable();

    static Doctor doctors[] = new Doctor[50];
    static int doctorCount = 0;

    static int patientId = 1;
    static int tokenNumber = 1;

    static TokenQueue tokenQueue = new TokenQueue();
    static ActionStack history = new ActionStack();

    /* PATIENT REGISTRATION */

    static void registerPatient() {

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Phone: ");
        String phone = sc.next();

        System.out.print("Email: ");
        String email = sc.next();

        Patient newPatient = new Patient(patientId++, name, phone, email);

        hashTable.insert(newPatient);

        if (head == null)
            head = newPatient;
        else {

            Patient temp = head;

            while (temp.next != null)
                temp = temp.next;

            temp.next = newPatient;
        }

        System.out.println("Patient Registered!");
        history.push("Patient Registered: " + name);
    }

    /* DISPLAY PATIENTS */

    static void displayPatients() {

        if (head == null) {
            System.out.println("No Patients");
            return;
        }

        Patient temp = head;

        while (temp != null) {

            System.out.println(
                    temp.id + " " +
                    temp.name + " " +
                    temp.phone + " " +
                    temp.email
            );

            temp = temp.next;
        }
    }

    /* SEARCH PATIENT (LINEAR SEARCH) */

    static void searchPatient() {

        System.out.print("Enter Name to Search: ");
        String name = sc.next();

        Patient temp = head;

        while (temp != null) {

            if (temp.name.equalsIgnoreCase(name)) {

                System.out.println("Patient Found");
                System.out.println("ID: " + temp.id);
                System.out.println("Phone: " + temp.phone);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Patient Not Found");
    }

    /* ADD DOCTOR */

    static void addDoctor() {

        System.out.print("Doctor Name: ");
        String name = sc.next();

        System.out.print("Specialization: ");
        String spec = sc.next();

        System.out.print("Experience (years): ");
        int exp = sc.nextInt();

        doctors[doctorCount] = new Doctor(doctorCount + 1, name, spec, exp);

        doctorCount++;

        history.push("Doctor Added: " + name);

        System.out.println("Doctor Added!");
    }

    /* BUBBLE SORT DOCTORS */

    static void sortDoctors() {

        for (int i = 0; i < doctorCount - 1; i++) {

            for (int j = 0; j < doctorCount - i - 1; j++) {

                if (doctors[j].name.compareToIgnoreCase(doctors[j + 1].name) > 0) {

                    Doctor temp = doctors[j];
                    doctors[j] = doctors[j + 1];
                    doctors[j + 1] = temp;

                }
            }
        }

        System.out.println("Doctors Sorted!");
    }

    /* DISPLAY DOCTORS */

    static void displayDoctors() {

        for (int i = 0; i < doctorCount; i++) {

            System.out.println(
                    doctors[i].id + " " +
                    doctors[i].name + " " +
                    doctors[i].specialization + " " +
                    doctors[i].experience + " years"
            );
        }
    }

    /* TOKEN GENERATION */

    static void generateToken() {

        int token = tokenNumber++;

        tokenQueue.enqueue(token);

        System.out.println("Your Token Number: " + token);

        history.push("Token Generated: " + token);
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== HEALHUB MENU =====");

            System.out.println("1 Register Patient");
            System.out.println("2 Display Patients");
            System.out.println("3 Search Patient");
            System.out.println("4 Add Doctor");
            System.out.println("5 Sort Doctors");
            System.out.println("6 Display Doctors");
            System.out.println("7 Generate Token");
            System.out.println("8 Show History");
            System.out.println("9 Show Hash Table");
            System.out.println("10 Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    displayPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    addDoctor();
                    break;

                case 5:
                    sortDoctors();
                    break;

                case 6:
                    displayDoctors();
                    break;

                case 7:
                    generateToken();
                    break;

                case 8:
                    history.display();
                    break;

                case 9:
                    hashTable.displayHash();
                    break;

                case 10:
                    System.out.println("Exiting System...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 10);
    }
}
