import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class TestAddressBook {
    ChromeDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void TestAddressBook() {
        
        openAddressbook();

        login();
        wd.findElement(By.name("user")).click();

        password();
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        wd.findElement(By.linkText("groups")).click();
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            wd.findElement(By.name("selected[]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/span[2]/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/span[2]/input")).click();
        }
        wd.findElement(By.name("delete")).click();

        goToGroupPage();
        fillGroupForm();
        submitGroupCreations();

        wd.findElement(By.linkText("group page")).click();
    }

    private void password() {
        wd.findElement(By.name("pass")).sendKeys("secret");
    }

    private void openAddressbook() {
        wd.get("http://localhost/addressbook/");
    }

    private void submitGroupCreations() {
        wd.findElement(By.name("submit")).click();
    }

    private void fillGroupForm() {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys("GroupName");
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys("GroupHeader");
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys("GroupFooter");
    }

    private void goToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
        wd.findElement(By.name("new")).click();
    }

    private void login() {
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
