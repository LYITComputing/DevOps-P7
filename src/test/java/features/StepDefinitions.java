package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Bill;
import revolut.DebitCard;
import revolut.PaymentService;
import revolut.Person;
import java.util.ArrayList;

public class StepDefinitions {

    private double topUpAmount;
    //private String topUpMethod;
    PaymentService topUpMethod;
    Person danny;
    Person mark;

    //my extra variables
    Bill bill;
    ArrayList<Person> friends = new ArrayList<>();
    DebitCard dannysDebitCard = new DebitCard();

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        mark = new Person("Mark");

        friends.add(danny);
        friends.add(mark);

        bill = new Bill();

    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance);
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
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
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

    //Danny tops up his account
    //*******************************************************
    @Given("Danny has a starting balance of {double}")
    public void danny_has_a_starting_balance_of(double startingBalance) {
        // Write code here that turns the phrase above into concrete actions

        danny.setAccountBalance(startingBalance);

    }

    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions

        danny.topupAccount(topUpAmount, dannysDebitCard, topUpMethod);
    }

    @Then("The balance in his euro account should be {double}")
    public void the_balance_in_his_euro_account_should_be(double balance) {
        // Write code here that turns the phrase above into concrete actions

        double actualBalance = danny.getAccountBalance("EUR");

        Assert.assertEquals(balance, actualBalance, 0.01);
    }

    //payment service denies/approves payment topup request
    //*******************************************************
    @Given("Danny has a starting balance of {double} euro")
    public void danny_has_a_starting_balance_of_euro(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }

    @Given("Danny's debit card has a balance of {double} euro")
    public void danny_s_debit_card_has_a_balance_of_euro(double debitCardBalance) {
        dannysDebitCard.setBalance(debitCardBalance);
    }

    //Splitting a bill between friends
    //*******************************************************
    @Given("The total bill is {double} euro")
    public void the_total_bill_is_euro(double billAmount) {
        bill.setBillAmount(billAmount);
    }
    @Given("Danny has a start balance of {double}")
    public void danny_has_a_start_balance_of(double dannyAccountBalance) {
        danny.setAccountBalance(dannyAccountBalance);
    }
    @Given("Mark has a start balance of {double}")
    public void mark_has_a_start_balance_of(double markAccountBalance) {
        mark.setAccountBalance(markAccountBalance);
    }
    @When("Danny and Mark split the bill")
    public void danny_and_mark_split_the_bill() {
        bill.splitBill(friends);
    }
    @Then("Danny's new balance should be {double}")
    public void danny_s_new_balance_should_be(double dannysNewBalance) {
        //Arrange
        double expectedBalance = dannysNewBalance;
        //Act
        double dannysActualBalance = danny.getAccountBalance("EUR");
        //Assert
        Assert.assertEquals(expectedBalance, dannysActualBalance, 0.01);
    }
    @Then("Mark's new balance should be {double}")
    public void mark_s_new_balance_should_be(double marksNewBalance) {
        //Arrange
        double expectedBalance = marksNewBalance;
        //Act
        double marksActualBalance = mark.getAccountBalance("EUR");
        //Assert
        Assert.assertEquals(expectedBalance, marksActualBalance, 0.01);
    }

    //One of the friends has insufficient funds
    //*******************************************************
    @Given("Danny has a start balance of {double} euro")
    public void danny_has_a_start_balance_of_euro(double dannysStartBalance) {
        danny.setAccountBalance(dannysStartBalance);
    }

    @Given("Mark has a start balance of {double} euro")
    public void mark_has_a_start_balance_of_euro(double marksStartBalance) {
        mark.setAccountBalance(marksStartBalance);
    }

    @Then("The transaction should be cancelled")
    public void the_transaction_should_be_cancelled() {
        //Arrange
        String expectedResult = "Transaction Cancelled: Insufficient Funds";

        //Act
        String actualResult = bill.splitBill(friends);

        //Assert
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Then("Danny's balance should be {double} euro")
    public void danny_s_balance_should_be_euro(double finalBalance) {
        //Arrange
        double expectedResult = finalBalance;

        //Act
        double actualResult = danny.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0.01);
    }
    @Then("Mark's balance should be {double} euro")
    public void mark_s_balance_should_be_euro(double finalBalance) {
        //Arrange
        double expectedResult = finalBalance;

        //Act
        double actualResult = mark.getAccountBalance("EUR");

        //Assert
        Assert.assertEquals(expectedResult, actualResult, 0.01);
    }


}
