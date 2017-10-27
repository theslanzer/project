import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.lang.System.*;
import static org.openqa.selenium.By.xpath;

public class Automation {
    private WebDriver driver;
    private JFrame j =new JFrame();

    @Test
    public void login() throws SQLException {
        JTextField usrname = new JTextField(5);
        JPasswordField passwrd = new JPasswordField(5);
        String[] type = { "Active", "Termed" };
        JComboBox choices =new JComboBox(type);
        JPanel myPanel = new JPanel(new GridLayout(0, 1, 2, 2));
        myPanel.add(new JLabel("Username:"));
        myPanel.add(usrname);
        myPanel.add(new JLabel("Pasword:"));
        myPanel.add(passwrd);
        myPanel.add(new JLabel("Account to be tested:"));
        myPanel.add(choices);
        int option= JOptionPane.showConfirmDialog(null, myPanel,"Please Enter Username and Password", JOptionPane.OK_CANCEL_OPTION);

        if(option==0) {
            File file = new File("D:\\\\SW\\\\chromedriver_win32\\\\chromedriver.exe");
            setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            driver = new ChromeDriver();
            String status;
                Connection con = Connector.getConnection();
                try {
                    driver.manage().window().maximize();
                    driver.get("https://om.qc.deerwalk.com/");
                    driver.findElement(By.id("username")).sendKeys(usrname.getText());
                    char[] passw = passwrd.getPassword();
                    driver.findElement(By.id("password")).sendKeys(new String(passw));
                    driver.findElement(By.id("submitLogin")).click();
                    String test = (String) choices.getSelectedItem();
                    System.out.println(test);
                    if (driver.findElements(xpath("//*[@id=\"top\"]/div/nav/div[1]")).size() > 0) {
                        status = "Logged In!";
                        Login val = new Login();
                        int id = val.validation(usrname.getText(), status);
                        if (id > 0) {
                            if (verifyPage()) {
                                AccountVerification acc = new AccountVerification();
                                String stat = acc.accountTest(id, driver,test);
                                String sql1 = "UPDATE test.login SET test =? where id=?;";
                                PreparedStatement pst1 = con.prepareStatement(sql1);
                                pst1.setString(1, stat);
                                pst1.setInt(2, id);
                                pst1.execute();
                                driver.close();
                            }
                        } else {
                            status = "Dashboard verification failed!";
                            String sql1 = "UPDATE test.login SET test =? where id=?;";
                            PreparedStatement pst1 = con.prepareStatement(sql1);
                            pst1.setString(1, status);
                            pst1.setInt(2, id);
                            pst1.execute();
                            driver.close();
                        }
                    } else {
                        status = "Login failed!";
                        Login val = new Login();
                        val.validation(usrname.getText(), status);
                        driver.close();
                        JOptionPane.showMessageDialog(j,"   Login Failed","Alert",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    driver.close();
                }
                con.close();
        }
        else{
            JOptionPane.showMessageDialog(j,"   Application closed!","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean verifyPage() throws InterruptedException {
        boolean flag;
        try {
            Thread.sleep(5000);
            if (driver.findElement(By.className("clearfix")).isDisplayed()
                    && driver.findElement(By.className("ng-scope")).isDisplayed()
                    && driver.findElement(By.xpath("//*[@id=\"processingcount\"]")).isDisplayed()
                    && driver.findElement(By.xpath("//*[@id=\"processingcount\"]/div[1]")).isDisplayed()
                    && driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div/div[10]")).isDisplayed()
                    && driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div/div[11]")).isDisplayed()) {
                flag=true;
            }
            else {
                flag=false;
                JOptionPane.showMessageDialog(j,"   Dashboard verification failed!","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }catch (Exception e){
            flag =false;
            e.printStackTrace();
        }
        return flag;
    }
}