import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareAccount {

    public int dataTable(WebDriver driver, String test) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        String applicationNo;
        int appCount=0;
        String table;
        if(test.equals("Active")){
            table="ActiveCollection";
        }
        else{
            table="TermedCollection";
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@st-safe-src='"+table+"']//*[@id=\"trAccountDetails\"]")));
        int rowCount = driver.findElements(By.xpath("//table[@st-safe-src='"+table+"']//*[@id=\"trAccountDetails\"]")).size();
        System.out.println("Row count is: " +rowCount);
        for(int i=1; i<=rowCount; i++){
            applicationNo= driver.findElement(By.xpath("(//table[@st-safe-src='"+table+"']//*[@id=\"trAccountDetails\"]/td[4])["+i+"]")).getText();
            appCount+= Integer.parseInt(applicationNo);
        }
        return appCount;

    }
}