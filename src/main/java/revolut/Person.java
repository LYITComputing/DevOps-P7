package revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalance) {
        userAccounts.get("EUR").setBalance(startingBalance);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }

    public void topupAccount(double topupAmount, DebitCard card, PaymentService topupRequest){
        if(topupRequest.approveRequest(card, topupAmount)) {
            double newBalance = userAccounts.get("EUR").getBalance() + topupAmount;
            this.setAccountBalance(newBalance);
        }
        else{
            System.out.println("Topup Request Not Approved");
        }
    }

    public void pay(double amount){
        double newBalance = userAccounts.get("EUR").getBalance() - amount;
        this.setAccountBalance(newBalance);
    }
}
