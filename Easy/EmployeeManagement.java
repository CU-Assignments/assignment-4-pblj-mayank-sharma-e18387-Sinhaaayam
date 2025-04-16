import java.util.ArrayList;
import java.util.Scanner;

// Employee class with encapsulation
class Employee {
    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setSalary(double salary) { this.salary = salary; }

    // Display employee info
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

public class EmployeeManagement {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Add employee
    public static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully.\n");
    }

    // Update employee
    public static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new salary: ");
                double newSalary = scanner.nextDouble();

                e.setName(newName);
                e.setSalary(newSalary);
                System.out.println("Employee updated successfully.\n");
                return;
            }
        }
        System.out.println("Employee not found.\n");
    }

    // Remove employee
    public static void removeEmployee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = scanner.nextInt();

        for (Employee e : employees) {
            if (e.getId() == id) {
                employees.remove(e);
                System.out.println("Employee removed successfully.\n");
                return;
            }
        }
        System.out.println("Employee not found.\n");
    }

    // Search employee
    public static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();

        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println("Employee Found:");
                e.display();
                System.out.println();
                return;
            }
        }
        System.out.println("Employee not found.\n");
    }

    // Main menu
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> updateEmployee();
                case 3 -> removeEmployee();
                case 4 -> searchEmployee();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.\n");
            }
        } while (choice != 5);

        scanner.close();
    }
}

