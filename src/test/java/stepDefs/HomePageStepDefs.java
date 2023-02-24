package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utilities.BrowserUtils;
import utilities.Config;
import utilities.Driver;

public class HomePageStepDefs {
    HomePage homePage = new HomePage();

    @Given("the user is on the home page")
    public void the_user_is_on_the_login_page(){
        Driver.getDriver().get(Config.getProperty("url"));
    }
    @When("the user click on search on home page")
    public void the_user_click_on_search_on_home_page(){
        BrowserUtils.clickToElement(homePage.searchBox);
    }
    @When("enters {string} to search box on home page")
    public void enters_to_search_box_on_home_page(String text){
        BrowserUtils.inputToTextBox(homePage.searchInput,text);
        BrowserUtils.clickEnterOnInput(homePage.searchInput);
    }
    @When("the user scrolls down to see {string} on home page")
    public void the_user_scrolls_down_to_see_on_home_page(String text) {
        BrowserUtils.scrollToElement(homePage.servicesLabel);
    }


    @Then("the user should see  {string} label")
    public void the_user_should_see_label(String string) {
        BrowserUtils.verifyElementDisplayed(homePage.servicesLabel);
        BrowserUtils.wait(10);
    }
}
