import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/students_repository";

    private Connection connect;

    public DataBase()  {
    }

    public void connectToDB() throws SQLException {
        connect = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public ArrayList<Students> getAllStudents() throws SQLException {
        ArrayList<Students> students = new ArrayList<>();
        PreparedStatement pt = connect.prepareStatement("SELECT * FROM students");
        ResultSet rs = pt.executeQuery();
        while (rs.next()){
            Long id = rs.getLong("id");
            String firstName = rs.getString("first_name");
            String middleName = rs.getString("middle_name");
            String lastName = rs.getString("last_name");
            int age = rs.getInt("age");
            students.add(new Students(id, firstName,middleName,lastName,age));

        }
        return students;
    }

    public void addStudent(Students s){
        PreparedStatement pt = null;
        try {
            pt = connect.prepareStatement("INSERT INTO students(first_name, middle_name, last_name, age) values (?,?,?,?)");
            pt.setString(1,s.getFirstName());
            pt.setString(2,s.getMiddleName());
            pt.setString(3,s.getLastName());
            pt.setInt(4,s.getAge());
            pt.executeUpdate();
            pt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
