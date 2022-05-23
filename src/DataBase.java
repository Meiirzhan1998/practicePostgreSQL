import java.sql.*;
import java.util.Scanner;

public class DataBase {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/students_repository";

    private Connection connect;

    public DataBase() {
    }

    public void connectToDB() throws SQLException {
        connect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void addStudent(Students s) {
        PreparedStatement pt = null;
        try {
            pt = connect.prepareStatement("INSERT INTO students(first_name, middle_name, last_name, age) values (?,?,?,?)");
            pt.setString(1, s.getFirstName());
            pt.setString(2, s.getMiddleName());
            pt.setString(3, s.getLastName());
            pt.setInt(4, s.getAge());
            pt.executeUpdate();
            pt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void findById() throws SQLException {
        while (true) {
            System.out.println("Студенттің ID енгізіңіз: ");
            System.out.println("Шығу үшін 0 басыңыз ");
            Scanner sc = new Scanner(System.in);
            int command = sc.nextInt();

            if (command == 1) {
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students WHERE id=1");

                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name") + " " + rs.getInt("age"));
                }
            } else if (command == 2) {
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students WHERE id=2");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name") + " " + rs.getInt("age"));
                }
            } else if (command == 0) {
                System.exit(0);

            } else {
                System.err.println("Команда не распознана");
            }
        }
    }
}

