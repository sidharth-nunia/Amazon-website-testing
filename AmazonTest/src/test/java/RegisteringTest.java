import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisteringTest {
      
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    
    @BeforeClass
    public static void before() {
        extent = new ExtentReports("reports/RegisteringTest.html", true);
        System.setProperty("webdriver.chrome.driver","//Users//sidharthnunia//Downloads//Selenium//chromedriver");
        driver = new ChromeDriver();
    }
    
    @Test
    public void passwordMismatchTest() throws InterruptedException, IOException { 
        test = extent.startTest("Password Mismatch Test");
        test.log(LogStatus.INFO, "open Amazon account page");
        driver.get("https://www.amazon.com/ap/register?showRememberMe=true&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3F_encoding%3DUTF8%26opf_redir%3D1%26ref_%3Dnav_ya_signin&prevRID=FNHS1R347ZRBWZ5MQTBE&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=usflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        test.log(LogStatus.PASS, "successfully open the page");
        driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys("name");
        driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("email@email.com");
        driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@id='ap_password_check']")).sendKeys("passwordcheck");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
        Thread.sleep(1000);
//        Assert.assertEquals("Passwords must match", driver.findElement(By.xpath("//div[@id='auth-password-mismatch-alert']")).getText());
        String actual = driver.findElement(By.xpath("//div[@id='auth-password-mismatch-alert']")).getText();
        String expected = "Passwords must match";
        test.log(LogStatus.INFO, "Expected Value: '" + expected + "'");
        test.log(LogStatus.INFO, "Actual Value: '" + actual + "'");
        if(actual.equals(expected)) {
            test.log(LogStatus.PASS, "expected value equals actual value.");
        } else {
            test.log(LogStatus.FAIL, "expected value doesn't equal actual value.");
        }
        screenshot("passwordMismatchTest");
        extent.endTest(test);
        extent.flush();
    }
    
    @Test
    public void emptyNameTest() throws InterruptedException, IOException {
        test = extent.startTest("Empty Name Test");
        test.log(LogStatus.INFO, "open Amazon account page");
        driver.get("https://www.amazon.com/ap/register?showRememberMe=true&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2F%3F_encoding%3DUTF8%26opf_redir%3D1%26ref_%3Dnav_ya_signin&prevRID=FNHS1R347ZRBWZ5MQTBE&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=usflex&openid.mode=checkid_setup&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=usflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys(" ");
        driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("email@email.com");
        driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@id='ap_password_check']")).sendKeys("password");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
        Thread.sleep(1000);
//        Assert.assertEquals("Enter your name", driver.findElement(By.xpath("//div[@id='auth-customerName-missing-alert']")).getText());
        String actual = driver.findElement(By.xpath("//div[@id='auth-customerName-missing-alert']")).getText();
        String expected = "Enter your name";
        test.log(LogStatus.INFO, "Expected Value: '" + expected + "'");
        test.log(LogStatus.INFO, "Actual Value: '" + actual + "'");
        if(actual.equals(expected)) {
            test.log(LogStatus.PASS, "expected value equals actual value.");
        } else {
            test.log(LogStatus.FAIL, "expected value doesn't equal actual value.");
        }
        screenshot("emptyNameTest");
        extent.endTest(test);
        extent.flush();
    }
    
    public void screenshot(String testName) throws IOException {
        File screentshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String savePath = "screenshot/RegisteringTest/" + testName + ".png";
        FileUtils.copyFile(screentshot, new File(savePath));
    }
    
    @AfterClass
    public static void after() {
        driver.quit();
        extent.close();
    }
}
