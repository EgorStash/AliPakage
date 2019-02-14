package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.MainPage;

import java.util.List;
import java.util.Map;

public class AliStepDefs {

    @Given("I opened belpost site")
    public void iOpenedBelpostSite() {
        new MainPage().openBrowserPage();
    }


    @When("^I search ([\\w ]+)$")
    public void iSearch(String trackNumber) {
        new MainPage().searchItem(trackNumber);
    }

    @Then("^I read item info and make decision what info i should collect$")
    public void iReadItemInfoAndMakeDecisionWhatInfoIShouldCollect() {
        List<Map<String, String>> collectedData = new MainPage().decisionWhatToCollect();
        new MainPage().readTableInfo(collectedData);
        Assert.assertNotNull(collectedData);
    }


}
