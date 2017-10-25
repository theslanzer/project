import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by shjoshi on 8/31/2017.
 */
public class Connector {
public static Connection getConnection(){
    Connection connection =null;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","automation");
    } catch (Exception e){
        e.printStackTrace();
    }
    return connection;
}
}
