//import org.openqa.selenium.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class FetchTable {
//    String accountName;
//    String applicationNo;
//
//public boolean dataTable(WebDriver driver) throws SQLException {
//        String accountName;
//        String applicationNo;
//        boolean bool =false;
//        Connection con = Connector.getConnection();
//        int rowCount = driver.findElements(By.id("trAccountDetails")).size();
//        System.out.println("Row count is: " +rowCount);
//
//        try {
//        String sql = "DELETE FROM test.account;";
//        PreparedStatement pst = con.prepareStatement(sql);
//        pst.execute();
//
//        String sql1 = "ALTER TABLE test.account AUTO_INCREMENT = 1;";
//        PreparedStatement pst1 = con.prepareStatement(sql1);
//        pst1.execute();
//        }catch (SQLException e){
//        System.out.println("Error while clearing 'account' table!! \n");
//        e.printStackTrace();
//        }
//
//        for(int i=1; i<=rowCount; i++){
//        accountName= driver.findElement(By.xpath("(//*[@id=\"trAccountDetails\"]/td[3])["+i+"]")).getText();
//        applicationNo= driver.findElement(By.xpath("(//*[@id=\"trAccountDetails\"]/td[4])["+i+"]")).getText();
//        try {
//
//        String sql2 = "INSERT INTO test.account(Account,Application_no) VALUES(?,?);";
//        PreparedStatement pst2 = con.prepareStatement(sql2);
//        pst2.setString(1, accountName);
//        pst2.setInt(2, Integer.parseInt(applicationNo));
//        pst2.execute();
//        bool= true;
//        }
//        catch (SQLException e){
//        System.out.println("Error while inserting into 'account' table!! \n");
//        bool=false;
//        e.printStackTrace();
//        }
//        }
//        return bool;
//        }
//        }