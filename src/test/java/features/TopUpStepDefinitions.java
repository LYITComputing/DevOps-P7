package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class TopUpStepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;

    @Before (value="@TopUp")//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) throws Exception{
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance("EUR",startingBalance);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        //throw new io.cucumber.java.PendingException();
    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")
    //public void danny_selects_his_debit_card_as_his_top_up_method(String topUpSource) {
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
        topUpMethod.setBalance(5000);
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").transferFunds(topUpAmount, topUpMethod);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }
    @Given("Danny has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(double startingBalance) throws  Exception{
        danny.setAccountBalance("EUR",startingBalance);
        // Write code here that turns the phrase above into concrete actions

    }
    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").transferFunds(topUpAmount, topUpMethod);
    }
    @Then("The balance in his euro account should be {double}")
    public void the_balance_in_his_euro_account_should_be(double accAmount) {
        // Write code here that turns the phrase above into concrete actions
        //Arrange
        double expectedResult = accAmount;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }
    @Given("this DebitCard has balance of {double}")
    public void this_debit_card_has_balance_of(double startingBalance) {
        // Write code here that turns the phrase above into concrete actions
        topUpMethod.setBalance(startingBalance);
    }

    @Then("Payment Service transfer should reject")
    public void payment_service_transfer_should_reject() {
        // Write code here that turns the phrase above into concrete actions
        //Arrange
        boolean expectedResult = false;
        //Act
        boolean actualResult = danny.getAccount("EUR").transferFunds(topUpAmount, topUpMethod);
        //Assert
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("The new final balance is: " + danny.getAccount("EUR").getBalance());

    }

    @Then("Payment Service transfer should accept")
    public void payment_service_transfer_should_accept() {
        // Write code here that turns the phrase above into concrete actions
        //Arrange
        boolean expectedResult = true;
        //Act
        boolean actualResult = danny.getAccount("EUR").transferFunds(topUpAmount, topUpMethod);
        //Assert
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("The new final balance is: " + danny.getAccount("EUR").getBalance());
    }

}
