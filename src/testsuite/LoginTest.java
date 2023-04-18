package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.UUID;

public class LoginTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    //Opening browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    /*1. userShouldNavigateToLoginPageSuccessfully
    * click on the ‘Login’ link
    * Verify the text ‘Welcome, Please Sign
    In!’
    */
    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //click on login link
        WebElement loginLink = driver.findElement(By.className("ico-login"));
        loginLink.click();


        //expected results
        String expectedMessage = "Welcome, Please Sign In!";
        //Find the welcome text element and get the text
        WebElement actualTextElement = driver.findElement(By.className("page-title"));
        String actualMessage = actualTextElement.getText();
        //Validation of actual and expected results
        Assert.assertEquals("Not navigate to login page", expectedMessage, actualMessage);

    }

    /*
     * 2. userShouldLoginSuccessfullyWithValidCredentials
     * click on the ‘Login’ link
     * Enter valid username
     * Enter valid password
     * Click on ‘LOGIN’ button
     * Verify the ‘Log out’ text is display
     */

    public String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {

        //find the login link and click on Login Link
        WebElement loginLink = driver.findElement(By.xpath("//div[@class='header-links']//a[text()='Log in']"));
        loginLink.click();
        //Find the username Field Element and send username on username field
        driver.findElement(By.id("Email")).sendKeys("georgesmith@gmail.com");
        //Find the Password Field Element and send password on password field
        driver.findElement(By.name("Password")).sendKeys("123456");
        //Find the Login btn Element and click
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        //Verify the ‘Log out’ text is display
        String expectedText = "Log out";
        WebElement actualText = driver.findElement(By.linkText("Log out"));
        String textShown = actualText.getText();
        Assert.assertEquals("Text not displayed", expectedText, textShown);
    }

    /*3. verifyTheErrorMessage
     * click on the ‘Login’ link
     * Enter invalid username
     * Enter invalid password
     * Click on Login button
     * Verify the error message ‘Login was unsuccessful'. */
    @Test
    public void verifyTheErrorMessage() {
        //click on the login link
        WebElement loginLink = driver.findElement(By.className("ico-login"));
        loginLink.click();

        //Find email element
        driver.findElement(By.id("Email")).sendKeys("magnusjohn12@gmail.com");

        // Find password element
        driver.findElement(By.id("Password")).sendKeys("1234567");

        //click on login button
        WebElement loginButton = driver.findElement(By.cssSelector("button.button-1.login-button"));
        loginButton.click();

        //Expected results
        String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        //Verify the error message
        WebElement actualTextElement = driver.findElement(By.cssSelector("div.message-error.validation-summary-errors"));
        String actualMessage = actualTextElement.getText();
        //Validation of actual and expected results
        Assert.assertEquals("Error message", expectedMessage, actualMessage);

    }
    //Close browser
    @After
    public void tearDown() {
        //closeBrowser();
    }
}
