import java.io.*;
import java.util.*;

public class EmployeeRecordSystem {
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "employees.csv";
    static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployees();

        while (true) {
            System.out.println("\nEmployee Record System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addEmployee();
                case "2" -> viewEmployees();
                case "3" -> {
                    saveEmployees();
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    static class Employee {
        String name, email, department;
        Employee(String name, String email, String department) {
            this.name = name; this.email = email; this.department = department;
        }
    }

    static void addEmployee() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter department: ");
        String dept = scanner.nextLine();

        employees.add(new Employee(name, email, dept));
        System.out.println("Employee added!");
    }

    static void viewEmployees() {
        System.out.println("\nAll Employees:");
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            System.out.printf("%d. %s | %s | %s%n", i+1, e.name, e.email, e.department);
        }
    }

    static void loadEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    employees.add(new Employee(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing employee file found, starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }

    static void saveEmployees() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Employee e : employees) {
                pw.println(e.name + "," + e.email + "," + e.department);
            }
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }
    }
}
