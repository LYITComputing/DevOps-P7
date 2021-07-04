package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Account;
import revolut.Person;

import java.util.Currency;

public class CurrencyExchangeStepDefinitions {

    Person lorraine;
    double convertAmount;
    double exchangeRate;

    @Before(value = "@CurrEx")//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        lorraine = new Person("Lorraine");

    }
    @Given("Lorraine has {double} euro in her euro Revolut account")
    public void lorraine_has_euro_in_her_euro_revolut_account(double startingBalance)  throws Exception {
        // Write code here that turns the phrase above into concrete actions
        lorraine.setAccountBalance("EUR",startingBalance);
    }
    @Given("Lorraine selects {double} euro to convert")
    public void lorraine_selects_euro_to_convert(double convertAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.convertAmount = convertAmount;
    }
    @Given("Lorraine selects USD as her exchange currency")
    public void lorraine_selects_usd_as_her_exchange_currency() {
        // Write code here that turns the phrase above into concrete actions
        Currency accCurrency = Currency.getInstance("USD");
        Account exchangeAccount = new Account(accCurrency, 0);
        lorraine.addAccount(exchangeAccount);
    }
    @When("Lorraine exchanges at a rate of {double}")
    public void lorraine_exchanges_at_a_rate_of(Double exchangeRate) {
        // Write code here that turns the phrase above into concrete actions
        this.exchangeRate = exchangeRate;

    }
    @Then("The new balance of her euro account should now be {double}")
    public void the_new_balance_of_her_euro_account_should_now_be(double euroAmount) {
        // Write code here that turns the phrase above into concrete actions

        //Arrange
        double expectedResult = euroAmount;
        //Act
        lorraine.convertCurrency("EUR","USD", convertAmount, exchangeRate);
        double actualResult = lorraine.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new Euro balance is: " + actualResult);
    }
    @Then("a new USD account should have balance of {double}")
    public void a_new_usd_account_should_have_balance_of(double usdAmount) {
        // Write code here that turns the phrase above into concrete actions
        //Arrange
        double expectedResult = usdAmount;
        //Act
        double actualResult = lorraine.getAccount("USD").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new USD balance is: " + actualResult);
    }
    @Given("Lorraine has {double} USD in her USD Revolut account")
    public void lorraine_has_usd_in_her_usd_revolut_account(double usdAmount) throws  Exception{
        // Write code here that turns the phrase above into concrete actions
        lorraine.setAccountBalance("USD", usdAmount);
    }
}
