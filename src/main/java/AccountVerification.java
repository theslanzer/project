import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountVerification {

    public String accountTest(int id, WebDriver driver,String test) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        String a=null;
        CompareAccount compareAccount = new CompareAccount();
        try {
            Connection con = Connector.getConnection();
            String sql = "select * from test.login WHERE id=?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) if (rs.getString("status").equals("Logged In!")) {
                if (test.equals("Active")) {
                    driver.findElement(By.xpath("//*[@id=\"top\"]/div/nav/div[4]/ul[1]/li[2]/a")).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"trAccountDetails\"]/td[4]/a/span")));
                    int dataCount = compareAccount.dataTable(driver);
                    driver.findElement(By.xpath("//*[@id=\"top\"]/div/nav/div[4]/ul[1]/li[5]/a")).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"activeAccountManagement\"]/div[2]/div/span")));
                    int count = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"activeAccountManagement\"]/div[2]/div/span")).getText());
                    System.out.println("Application tab count: " + count + "\nAdmin tab Count: " + dataCount);
                    if (dataCount == count) {
                        a = dataCount+" Application verified for active accounts";
                    } else {
                        a = "Application verification Failed for termed accounts!";
                    }
                    System.out.println("--**Comparing Complete**--");
                }
                else{
                    driver.findElement(By.xpath("//*[@id=\"top\"]/div/nav/div[4]/ul[1]/li[2]/a")).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ng-app\"]/div[2]/div[3]/ul/li[2]/a"))).click();
                    Thread.sleep(2000);
                    int dataCount = compareAccount.dataTable(driver);
                    driver.findElement(By.xpath("//*[@id=\"top\"]/div/nav/div[4]/ul[1]/li[5]/a")).click();
                    Thread.sleep(2000);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ng-app\"]/div[2]/div[1]/div[4]/div/ul/li[2]/a"))).click();
                    int count = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"termedAccountManagement\"]/div[2]/div/span")).getText());
                    System.out.println("Application tab count: " + count + "\nAdmin tab Count: " + dataCount);
                    if (dataCount == count) {
                        a = dataCount+" Application number verified for termed accounts";
                    } else {
                        a = "Application verification Failed for termed accounts!";
                    }
                    System.out.println("--**Comparing Complete**--");
                }
            }
        else{
                    a = "Not logged-in!!";
                    }
            }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a;
    }
}