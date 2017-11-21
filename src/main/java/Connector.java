import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    public static Connection getConnection(){
        Connection connection =null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false","root","automation");
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getQcdb(){
        Connection connection= null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://rds-om-qc.c3uzu7me4rud.us-east-1.rds.amazonaws.com:3306/om_qc_new?autoReconnect=true&useSSL=false","shjoshi","f-PRE2eCruc!");
        } catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
