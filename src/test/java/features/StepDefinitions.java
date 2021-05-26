package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    PaymentService topUpMethod;
    Person danny;
    Person mary;
    boolean transactionState;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){

        danny = new Person("Danny");
    }

    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {

        danny.setAccountBalance(startingBalance);

    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {

        this.topUpAmount = topUpAmount;

    }

    //@Given("Danny selects his {word} as his topUp method")
    @Given("Danny selects his {paymentService} as his topUp method")

    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {

        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {

        danny.getAccount("EUR").addFunds(topUpAmount,topUpMethod);

    }

    @Then("The new balance of his euro account should now be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {

        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(Double startingBalance) {

        danny.setAccountBalance(startingBalance);
    }
    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(Double topUpAmount) {

       transactionState = danny.getAccount("EUR").addFunds(topUpAmount, topUpMethod);
    }

    @Given("Danny has {double} euro in this DebitCard")
    public void danny_has_euro_in_this_debit_card(Double accountBalance) {

        topUpMethod.setBalance(accountBalance);
    }

    @Given("a person named Mary")
    public void a_person_named_mary() {

        mary = new Person("Mary");
    }

    @When("Danny sends Mary {double} euros")
    public void danny_sends_mary_euros(Double sendMoney) {

       danny.send(mary,sendMoney);
    }

    @Then("The balance in Marys euro account should be {double}")
    public void the_balance_in_marys_euro_account_should_be(Double newBalance) {

        double expectedResult = newBalance;
        //Act
        double actualResult = mary.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny's starting balance is {double}")
    public void danny_s_starting_balance_is(Double startingBalance) {

        danny.setAccountBalance(startingBalance);
    }

    @Given("Mary has a starting balance of {double}")
    public void mary_has_a_starting_balance_of(Double startingBalance) {

        mary.setAccountBalance(startingBalance);
    }

    @When("A bill of {double} euros is Split")
    public void a_bill_of_euros_is_split(Double bill) {

        danny.splitBill(mary,bill);

    }

    @Then("The transaction should be returned as {}")
    public void the_transaction_should_be_returned_as_false(boolean expectedTransactionState) {
        //Arrange
        boolean expectedResult = expectedTransactionState;
        //Act
        boolean actualResult = transactionState;
        //Assert
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("The new final balance is: " + actualResult);

    }



}
