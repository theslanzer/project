import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLException;
import java.util.List;

public class CompareAccount {

    public int dataTable(WebDriver driver) throws SQLException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        String applicationNo;
        int appCount=0;
        Thread.sleep(2000);
        int rowCount = driver.findElements(By.xpath("//table[@st-table='displayedTermedCollection']/tbody/tr[@class='animate ng-scope']")).size();
        System.out.println("Row count is: " +rowCount);
        for(int i=1; i<=rowCount; i++){
            applicationNo= driver.findElement(By.xpath("(//*[@id=\"trAccountDetails\"]/td[4])["+i+"]")).getText();
            appCount+= Integer.parseInt(applicationNo);
        }
        return appCount;
    }
}