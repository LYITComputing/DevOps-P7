package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    private String fromCurrencyCode;
    PaymentService topUpMethod;
    private boolean resultFromPaymentService = true;
    Person danny;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} {word} in his Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance, String currencyCode) {
        //System.out.println("Danny has starting account balance of: " + String.valueOf(startingBalance));
        danny.setAccountBalance(startingBalance, currencyCode);
        //double newAccountBalance = danny.getAccountBalance("EUR");
        //System.out.println("Danny's new account balance if: " + String.valueOf(newAccountBalance));
    }

    @Given("Danny selects {double} EUR as the topUp amount")
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
        if(resultFromPaymentService) {
            danny.getAccount("EUR").addFunds(topUpAmount);
        }
        //throw new io.cucumber.java.PendingException();
    }



    @Given("Danny has a starting balance of {int}")
    public void DannyHasAStartingBalanceOf(Integer balance) {
        // Write code here that turns the phrase above into concrete actions
        danny.setAccountBalance(balance, "EUR");
    }
    @When("Danny now tops up by {int}")
    public void DannyNowTopsUpBy(Integer topupAmount) {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topupAmount);
    }
    @Then("The balance in his euro account should be {int}")
    public void TheBalanceInHisEuroAccountShouldBe(Integer newBalance) {
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

    @And("The {paymentService} Rejects the request")
    public void thePaymentServiceRejectsTheRequest(PaymentService service) {
        resultFromPaymentService = service.Accepted(danny,100,"EUR",false);
    }

    @And("The {paymentService} Accepts the request")
    public void theDebitCardAcceptsTheRequest(PaymentService service) {
        resultFromPaymentService = service.Accepted(danny,100,"EUR",true);
    }




    @And("Danny selects his {word} {paymentService} as his Buy method")
    public void dannySelectsHisEuroAccountAsHisBuyMethod(String fromCurrencyCode,PaymentService service) {
        topUpMethod = service;
        this.fromCurrencyCode = fromCurrencyCode;

    }

    @When("Danny purchases {word} with {int} {word}")
    public void dannyPurchasesBitcoinWithEuro(String cryptoCode, int amount, String fromCurrency) {
        if(topUpMethod.getType().equals("Account"))
        {
            danny.transferBetweenAccounts(fromCurrency,cryptoCode,(double)amount);
            System.out.println("Transferring "+ fromCurrency +"" +amount +" to " +cryptoCode );
        }
    }

    @Then("The new balance of his {word} account should now be {double}")
    public void theNewBalanceOfHisBTCAccountShouldNowBe(String cryptoCurrency, double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount(cryptoCurrency).getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("New " + cryptoCurrency + " balanace is: " + actualResult);
    }


    @And("Danny selects {double} {word} to Sell back to {word}")
    public void dannySelectsBTCToSellBackToEUR(Double amount, String cryptoCurrency, String toCurrencyCode) {
        danny.transferBetweenAccounts(cryptoCurrency,toCurrencyCode,amount);
    }

    @When("Danny Sells Bitcoin")
    public void dannySellsBitcoin() {

    }
}
