import java.sql.*;
import java.util.*;

public class EmployeePayrollSystem {

    static final String DB_URL = "jdbc:mysql://localhost:3306/payroll_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Payroll Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    deleteEmployee(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    public static void addEmployee(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Basic Salary: ");
            double basicSalary = scanner.nextDouble();

            double totalSalary = basicSalary + (0.1 * basicSalary);

            String sql = "INSERT INTO employees (id, name, basic_salary, total_salary) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setDouble(3, basicSalary);
            stmt.setDouble(4, totalSalary);

            stmt.executeUpdate();
            System.out.println("Employee added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewEmployees() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\nID | Name | Basic Salary | Total Salary");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " +
                        rs.getDouble("basic_salary") + " | " + rs.getDouble("total_salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.print("Enter Employee ID to delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM employees WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
