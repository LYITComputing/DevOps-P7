package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.FX;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    private String startingCurrency;
    private String finishingCurrency;
    PaymentService topUpMethod;
    Person danny;
    FX ForeignExchange;


    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        ForeignExchange = new FX();
        this.topUpAmount = 0;
        this.startingCurrency = "";
        this.finishingCurrency = "";
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance,"EUR");
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        if(topUpAmount>0){
            this.topUpAmount = topUpAmount;
        }else{
            System.out.println("Invalid Top up amount");
        }
    }

    //@Given("Danny selects his {word},{int} as his topUp method")
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
    @Given("Danny has a starting balance of {int}")
    public void danny_has_a_starting_balance_of(Integer int1) {
        danny.setAccountBalance(int1,"EUR");
    }
    @When("Danny now tops up by {int}")
    public void danny_now_tops_up_by(Integer int1) throws IllegalArgumentException {
        if(int1>0) {
            danny.setAccountBalance(
                    danny.getAccountBalance("EUR") + int1,"EUR"
            );
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    @Then("The balance in his euro account should be {int}")
    public void the_balance_in_his_euro_account_should_be(Integer newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("Danny has a starting balance of {int} in his {string}")
    public void danny_has_a_starting_balance_of_in_his(Integer startingAmount, String startingCurrency) {
        danny.setAccountBalance(startingAmount,startingCurrency);
        this.startingCurrency = startingCurrency;
        this.topUpAmount = startingAmount;
    }
    @Given("Danny selects his {string} as the currency to convert to")
    public void danny_selects_his_as_the_currency_to_convert_to(String FinishingCurrency) {
        this.finishingCurrency = FinishingCurrency;
    }
    @When("Danny now converts his money")
    public void danny_now_converts_his_money() {
        danny.setAccountBalance(ForeignExchange.currencyConvesion(this.startingCurrency,this.finishingCurrency,topUpAmount),finishingCurrency);
    }

    @Then("The balance in his {string} account should be {double}")
    public void balanceShouldBe(String finishingCurrency, double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount(finishingCurrency).getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }
}
