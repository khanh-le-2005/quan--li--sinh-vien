import java.util.LinkedList;
import java.util.Scanner;
public class studentManager {
    private LinkedList<student> students = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);
    public void addStudent() {
        int id = getValidStudentId();
        String name = getValidStudentName();
        double marks = getValidMarks();
        students.add(new student(id, name, marks));
        System.out.println("Student added successfully!");
    }
    public void editStudent() {
        int id = getValidStudentId();
        student student = findStudentById(id);
        if (student != null) {
            System.out.println("Editing student: " + student);
            student.setName(getValidStudentName());
            student.setMarks(getValidMarks());
            student.updateRank();
            System.out.println("Student updated: " + student);
        } else {
            System.out.println("Student not found!");
        }
    }
    public void deleteStudent() {
        int id = getValidStudentId();
        student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted!");
        } else {
            System.out.println("Student not found!");
        }
    }
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            students.forEach(System.out::println);
        }
    }
    public void sortstudentsByMarks() {
        students.sort((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));
        System.out.println("Students sorted by marks:");
        displayStudents();
    }
    private student findStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }
    private int getValidStudentId() {
        while (true) {
            System.out.print("Enter Student ID: ");
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } else {
                System.out.println("Invalid input! Please enter a numeric ID.");
                scanner.nextLine();
            }
        }
    }
    private String getValidStudentName() {
        while (true) {
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine().trim();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("Invalid input! Name must contain only letters and spaces.");
            }
        }
    }
    private double getValidMarks() {
        while (true) {
            System.out.print("Enter Student Marks (0-10): ");
            if (scanner.hasNextDouble()) {
                double marks = scanner.nextDouble();
                scanner.nextLine();
                if (marks >= 0 && marks <= 10) {
                    return marks;
                } else {
                    System.out.println("Invalid input! Marks should be between 0 and 10.");
                }
            } else {
                System.out.println("Invalid input! Please enter numeric values for Marks.");
                scanner.nextLine();
            }
        }
    }
    public void searchstudentById() {
        int id = getValidStudentId();
        student foundStudent = findStudentById(id);

        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }
    public void searchstudentByName() {
        System.out.print("Enter Student Name to search: ");
        String name = scanner.nextLine().trim();
        boolean found = false;

        for (student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student found: " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with the name: " + name);
        }
    }
}

