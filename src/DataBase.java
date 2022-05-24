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
        int command;
        while (true) {
            System.out.println("Студенттің ID енгізу үшін 1 басыңыз");
            System.out.println("Шығу үшін 0 басыңыз ");
            Scanner sc = new Scanner(System.in);
            command = sc.nextInt();
            if(command==1){
                PreparedStatement ps = connect.prepareStatement("SELECT * FROM students WHERE id=?");
                System.out.println("Студенттің ID енгізіңіз: ");
                int studentID = sc.nextInt();
                ps.setInt(1,studentID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " " + rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name") + " " + rs.getInt("age"));
                }
                ps.close();
            }

            if(command==0){
                System.exit(0);
            }
        }
    }
}


