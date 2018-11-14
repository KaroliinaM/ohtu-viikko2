package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {

        //ChromeDriverManager.getInstance().setup();
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());
        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        System.out.println(driver.getPageSource());

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        // System.out.println(driver.getPageSource());
        sleep(2);
        element.submit();
        System.out.println(driver.getPageSource());

        sleep(3);

        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource());

        sleep(3);

        element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(3);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println(driver.getPageSource());
        sleep(3);

        loginAttempt("none", "password", driver);

        element = driver.findElement(By.linkText("back to home"));
        element.click();
        System.out.println(driver.getPageSource());
        sleep(3);

        element = driver.findElement(By.linkText("register new user"));
        element.click();
        System.out.println(driver.getPageSource());
        sleep(3);

        element = driver.findElement(By.name("username"));
        Random r = new Random();
        String username="pasi"+r.nextInt(100000);
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys("uusiPassu");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("uusiPassu");

        element = driver.findElement(By.name("signup"));
        element.submit();

        System.out.println(driver.getPageSource());
        sleep(3);
        element=driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        System.out.println(driver.getPageSource());
        sleep(3);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        System.out.println(driver.getPageSource());

        sleep(3);
        
        
        element = driver.findElement(By.linkText("login"));
        element.click();
        System.out.println(driver.getPageSource());
        sleep(3);
        
        loginAttempt(username, "uusiPassu", driver);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void loginAttempt(String username, String password, WebDriver driver) {

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        //System.out.println(driver.getPageSource());
        sleep(2);
        element.submit();
        System.out.println(driver.getPageSource());

        sleep(3);
    }

    
}
