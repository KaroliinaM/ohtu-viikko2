package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Stepdefs {
    
    //WebDriver driver = new ChromeDriver();
 //   WebDriver driver = new HtmlUnitDriver();
            File pathBinary = new File("/home/kape/firefox/firefox");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
    String baseUrl = "http://localhost:4567";
    
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        System.out.println("Ekan testin alku");
        driver.get(baseUrl);
        System.out.println(driver.getPageSource());
        sleep(2);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();
        System.out.println(driver.getPageSource());
        sleep(2);
    }
    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        newUserSelected();

        
    }
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_succesfully_created(String username, String password) throws Throwable {
        newUserSelected();
        signUpWith(username, password, password);
        pageHasContent("Welcome to Ohtu Application!");
        goViaLink("continue to application mainpage");
        goViaLink("logout");
    }
    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_creation_failed(String username, String password) throws Throwable {
        newUserSelected();
        signUpWith(username, password, password);
        goViaLink("back to home");
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @When("^incorrect username \"([^\"]*)\" and password \"([^\"]*)\" are given") 
    public void incorrect_username_and_password_are_given(String username, String password) throws Throwable{
        logInWith(username, password);
    }
    
    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered")
    public void valid_username_and_password_and_matching_confirmation_are_given(String username, String password) throws Throwable {
        signUpWith(username, password, password);
    }
    @When("^an invalid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void invalid_username_and_valid_password_and_valid_confirmation_are_given(String username, String password) throws Throwable {
        signUpWith(username, password, password);
    }
    
    @When("^a correct username \"([^\"]*)\" and too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_short_password_and_valid_confirmation_are_given(String username, String password) throws Throwable {
        signUpWith(username, password, password);
        sleep(2);
    }
    
    @When("^a correct username \"([^\"]*)\" and password \"([^\"]*)\" and unmatching password confirmation are entered$")
    public void valid_username_and_password_and_invalid_comfirmation(String username, String password) throws Throwable {
        signUpWith(username, password, "salasana2");
        sleep(2);
    }
        
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("^a new user is created$")
    public void new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_not_crated_and_error_reported(String message) throws Throwable {
        pageHasContent(message);
        pageHasContent("Create username and give password");
    }            
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
        private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private void signUpWith(String username, String password, String passwordConf) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element=driver.findElement(By.name("username"));
        element.sendKeys(username);
        element=driver.findElement(By.name("password"));
        element.sendKeys(password);
        element=driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConf);
        element=driver.findElement(By.name("signup"));
        element.submit();
        sleep(1);
    }

    private void newUserSelected() {
       driver.get(baseUrl);
       sleep(1);
       WebElement element=driver.findElement(By.linkText("register new user"));
       element.click();
       sleep(1);
    }
    private void goViaLink(String linkText) {
        WebElement element=driver.findElement(By.linkText(linkText));
        element.click();
        sleep(1);
    }
}
