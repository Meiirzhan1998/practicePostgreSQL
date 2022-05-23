import java.io.*;
import java.sql.SQLException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        FileReader fileReader = new FileReader("students.json");
        Students[] students = mapper.readValue(fileReader, Students[].class);
//        for (Students student : students) {
//            System.out.println(student);
//        }
        DataBase db = new DataBase();
        db.connectToDB();
        for(int i = 0 ; i<students.length;i++){
            db.addStudent(students[i]);
        }
    }
}
