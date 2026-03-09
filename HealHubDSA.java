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

/* DOCTOR CLASS */
class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
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

        System.out.println("\n--- Action History ---");

        for (int i = top; i >= 0; i--)
            System.out.println(stack[i]);
    }
}

public class HealHubDSA {

    static Scanner sc = new Scanner(System.in);

    /* LINKED LIST HEAD */
    static Patient head = null;

    /* DOCTOR ARRAY */
    static Doctor doctors[] = new Doctor[50];
    static int doctorCount = 0;

    static int patientId = 1;
    static int tokenNumber = 1;

    static TokenQueue tokenQueue = new TokenQueue();
    static ActionStack history = new ActionStack();

    /* PATIENT REGISTRATION (Linked List) */
    static void registerPatient() {

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Phone: ");
        String phone = sc.next();

        System.out.print("Email: ");
        String email = sc.next();

        Patient newPatient = new Patient(patientId++, name, phone, email);

        if (head == null)
            head = newPatient;
        else {

            Patient temp = head;

            while (temp.next != null)
                temp = temp.next;

            temp.next = newPatient;
        }

        System.out.println("Patient Registered Successfully!");
        history.push("Registered Patient: " + name);
    }

    /* DISPLAY PATIENTS */
    static void displayPatients() {

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

    /* SEARCH PATIENT (Linear Search) */
    static void searchPatient() {

        System.out.print("Enter Patient Name: ");
        String name = sc.next();

        Patient temp = head;

        while (temp != null) {

            if (temp.name.equals(name)) {

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

        doctors[doctorCount] = new Doctor(doctorCount + 1, name, spec);

        doctorCount++;

        history.push("Doctor Added: " + name);

        System.out.println("Doctor Added!");
    }

    /* SORT DOCTORS (Bubble Sort) */
    static void sortDoctors() {

        for (int i = 0; i < doctorCount - 1; i++) {

            for (int j = 0; j < doctorCount - i - 1; j++) {

                if (doctors[j].name.compareTo(doctors[j + 1].name) > 0) {

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
                    doctors[i].specialization
            );
        }
    }

    /* GENERATE TOKEN (Queue) */
    static void generateToken() {

        int token = tokenNumber++;

        tokenQueue.enqueue(token);

        System.out.println("\n--- Collect Your Token ---");
        System.out.println("Token Number: " + token);
        System.out.println("Please pay at reception.");
        System.out.println("Thank You 😊");

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
            System.out.println("9 Exit");

         

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
            }

        } while (choice != 0);
    }
}