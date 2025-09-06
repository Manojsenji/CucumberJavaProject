package StepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountLoginStepDefinitions {

    public WebDriver driver;


    @Given("Open the browser")
    public void open_the_browser() {
        //System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @When("Enter the URL {string}")
    public void enter_the_url(String string) {
        driver.get("https://dev.ashurityhealth.com/login");
    }

    @When("Enter registered username and password")
    public void enter_registered_username_and_password() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='Enter Your Username']")))
                .sendKeys("admin");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='Enter Your Password']")))
                .sendKeys("admin");
    }
    @When("Click on login button")
    public void click_on_login_button() {
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
    }
    @Then("User must successfully login to the web page")
    public void user_must_successfully_login_to_the_web_page() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait until the Admin Dashboard heading is visible
        String capText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Admin Dashboard']"))).getText();

        System.out.println(capText);
        Assert.assertTrue("Admin Dashboard heading not found!", capText.contains("Admin Dashboard"));
    }

}
