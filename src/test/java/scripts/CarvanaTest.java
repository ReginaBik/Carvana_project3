package scripts;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

public class CarvanaTest extends Base {

    /*Test Case 1: Test name = Validate Carvana home page title and url
    Test priority = 1
    Given user is on "https://www.carvana.com/"
    Then validate title equals to "Carvana | Buy & Finance Used Cars Online | At Home Delivery"
    And validate url equals to "https://www.carvana.com/"*/

    @Test(priority = 1, description = "TEST CASE :  Validate Carvana home page title and url")
    public void testCarvanaUrlAndTitle() {
        driver.get("https://www.carvana.com/");
        Assert.assertEquals(driver.getTitle(), "Carvana | Buy & Finance Used Cars Online | At Home Delivery");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/");
    }

    /*Test Case 2: Test name = Validate the Carvana logo
    Test priority = 2
    Given user is on "https://www.carvana.com/"
    Then validate the logo of the “Carvana” is displayed*/

    @Test(priority = 2, description = "TEST CASE 2 :  Validate the Carvana logo")
    public void testLogo() {
        driver.get("https://www.carvana.com/");
        Waiter.pause(2);
        Assert.assertTrue(carvanaMainPage.carVaNaLogo.isDisplayed());
    }

    /*Test Case 3: Test name = Validate the main navigation section items
    Test priority = 3
    Given user is on "https://www.carvana.com/"
    Then validate the navigation section items below are displayed
    |HOW IT WORKS      |
    |ABOUT CARVANA     |
    |SUPPORT & CONTACT */

    @Test(priority = 3, description = "Test case 3:Validate the main navigation section items")
    public void testNavigationSection() {
        driver.get("https://www.carvana.com/");
        String[] navigationSectionItemsTexts = {"HOW IT WORKS", "ABOUT CARVANA", "SUPPORT & CONTACT"};
        for (int i = 0; i < carvanaMainPage.navigationSectionItems.size(); i++) {
            Assert.assertTrue(carvanaMainPage.navigationSectionItems.get(i).isDisplayed());
            Assert.assertEquals(carvanaMainPage.navigationSectionItems.get(i).getText(), navigationSectionItemsTexts[i]);
        }
    }

    /*Test Case 4: Test name = Validate the sign in error message
    Test priority = 4
    Given user is on "https://www.carvana.com/"
    When user clicks on “SIGN IN” button
    Then user should be navigated to “Sign in” modal
    When user enters email as “johndoe@gmail.com”
    And user enters password as "abcd1234"
    And user clicks on "SIGN IN" button
    Then user should see error message as "Email address and/or password combination is incorrect
    Please try again or reset your password."*/

    @Test(priority = 4, description = "TEST CASE 4 :  Validate the sign in error message")
    public void testSignInErrorMessage() {
        driver.get("https://www.carvana.com/");
        carvanaMainPage.signInLink.click();
        Assert.assertTrue(carvanaMainPage.signInModel.isDisplayed());
        carvanaMainPage.userNameInputBox.sendKeys("reginabiktimirova1@gmail.com");
        carvanaMainPage.passwordInputBox.sendKeys("II454647"); //1 incorrect character
        carvanaMainPage.signInButton.click();
        Assert.assertTrue(carvanaMainPage.userNameLabel.isDisplayed());
        Assert.assertTrue(carvanaMainPage.userNameInputBox.isEnabled());
        Assert.assertTrue(carvanaMainPage.passwordLabel.isDisplayed());
        Assert.assertTrue(carvanaMainPage.passwordInputBox.isEnabled());
        Assert.assertEquals(carvanaMainPage.errorMessage.getText(), "Email address and/or password combination is incorrect\n" +
                "Please try again or reset your password.");
    }

    /*Test Case 5: Test name = Validate the search filter options and search button
    Test priority = 5
    Given user is on "https://www.carvana.com/"
    When user clicks on "SEARCH CARS" link
    Then user should be routed to "https://www.carvana.com/cars"
    And user should see filter options
    |PAYMENT & PRICE      |
    |MAKE & MODEL      |
    |BODY TYPE |
    |YEAR & MILEAGE      |
    |FEATURES      |
    |MORE FILTERS*/

    @Test(priority = 5, description = "TestCase 5 : Validate the search filter options and search button")
    public void TestSearchCars() {
        driver.get("https://www.carvana.com/");
        carvanaMainPage.searchCarButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars");

        String[] filterOptionsTexts = {"PAYMENT & PRICE", "MAKE & MODEL", "BODY TYPE", "YEAR & MILEAGE", "FEATURES", "MORE FILTERS"};
        for (int i = 0; i < carvanaCarSearchPage.filterOptions.size(); i++) {
            Assert.assertTrue(carvanaCarSearchPage.filterOptions.get(i).isDisplayed());
            Assert.assertTrue(carvanaCarSearchPage.filterOptions.get(i).isEnabled());
            Assert.assertEquals(carvanaCarSearchPage.filterOptions.get(i).getText(), filterOptionsTexts[i]);


        }
    }

    /*Test Case 6: Test name = Validate the search result tiles
    Test priority = 6
    Given user is on "https://www.carvana.com/"
    When user clicks on "SEARCH CARS" link
    Then user should be routed to "https://www.carvana.com/cars"
    When user enters "mercedes-benz" to the search input box
    And user clicks on "GO" button in the search input box
    Then user should see "mercedes-benz" in the url
    And validate each result tile
    VALIDATION OF EACH TILE INCLUDES BELOW
    Make sure each result tile is displayed with below information
    1. an image
    2. add to favorite button
    3. tile body
    ALSO VALIDATE EACH TILE BODY:
    Make sure each tile body has below information
    1. Inventory type - text should be displayed and should not be null
    2. Year-Make-Model information - text should be displayed and should not be null
    3. Trim-Mileage information - text should be displayed and should not be null
    4. Price - Make sure that each price is more than zero
    5. Monthly Payment information - text should be displayed and should not be null
    6. Down Payment information - text should be displayed and should not be null
    7. Delivery chip must be displayed as “Free Shipping”
    NOTE: After completing all scripts, create an xml file called “test.xml” and make sure that you can run all
    scripts using “mvn test -PRegression”
    NOTE: Push your code to GitHub or GitLab and submit the link of the repo on 06/20/2022, */
    @Test(priority = 6, description = "TEST CASE 6 :Validate the search result tiles")
    public void TestSearchResult() {

        driver.get("https://www.carvana.com/");
        carvanaMainPage.searchCarButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars");
        carvanaCarSearchPage.searchInputBox.sendKeys("mercedes-benz");
        carvanaCarSearchPage.goButton.click();

       Waiter.waitUntilURLIs(driver, 5, "https://www.carvana.com/cars/mercedes-benz?email-capture=");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars/mercedes-benz?email-capture=");

        Waiter.waitUntilTitleIs(driver, 5, "Used Mercedes-Benz For Sale Online | Carvana");
        Assert.assertEquals(driver.getTitle(), "Used Mercedes-Benz For Sale Online | Carvana");

        for (WebElement element : carvanaCarSearchPage.resultImages) {
            if (element.getAttribute("data-test") != null) {
                Assert.assertTrue(element.isDisplayed());
            }
        }
        for (WebElement element : carvanaCarSearchPage.resultImages) {
            if (element.getAttribute("fill-rule") != null) {
                Assert.assertTrue(element.isDisplayed());
            }
        }

        for (int i = 0; i < carvanaCarSearchPage.topFrameElements.size(); i++) {
            Assert.assertTrue(carvanaCarSearchPage.topFrameElements.get(i).isDisplayed());
        }

        for (int i = 0; i < carvanaCarSearchPage.middleFrameElements.size(); i++) {
            if (carvanaCarSearchPage.middleFrameElements.get(i).getText() != null) {
                Assert.assertTrue(carvanaCarSearchPage.middleFrameElements.get(i).isDisplayed());
            }

            if (carvanaCarSearchPage.year_ModelTexts.get(i).getText() != null) {
                Assert.assertTrue(carvanaCarSearchPage.year_ModelTexts.get(i).isDisplayed());
            }
            if (carvanaCarSearchPage.trimMileageInformation.get(i).getText() != null) {
                Assert.assertTrue(carvanaCarSearchPage.trimMileageInformation.get(i).isDisplayed());
            }
            String price = carvanaCarSearchPage.prices.get(i).getText().substring(1).replace(",", "");
            Assert.assertTrue(Integer.parseInt(price) > 0);

            if (carvanaCarSearchPage.monthlyInformation.get(i).getText() != null) {
                Assert.assertTrue(carvanaCarSearchPage.monthlyInformation.get(i).isDisplayed());
            }

            if (carvanaCarSearchPage.downPayments.get(i).getText() != null) {
                Assert.assertTrue(carvanaCarSearchPage.downPayments.get(i).isDisplayed());
            }
        }

        for (int i = 0; i < carvanaCarSearchPage.bottomFrameElements.size(); i++) {
            if (carvanaCarSearchPage.bottomFrameElements.get(i).getText() != null) {
                Assert.assertTrue(carvanaCarSearchPage.bottomFrameElements.get(i).isDisplayed());
            }
            Waiter.pause(2);
            if (carvanaCarSearchPage.deliveryChip.get(i).getAttribute("data-test") != null) {
                Assert.assertTrue(carvanaCarSearchPage.deliveryChip.get(i).isDisplayed());
            }

        }

    }
}