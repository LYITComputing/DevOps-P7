package revolut;

import java.util.Currency;
import java.util.HashMap;

public class Person {

    private String name;

    private HashMap<String, Account> userAccounts = new HashMap<String, Account>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        userAccounts.put("EUR", euroAccount);
        //Create a default dollar account and add it the our userAccounts container
        accCurrency = Currency.getInstance("USD");
        Account dollarAccount = new Account(accCurrency, 0);
        userAccounts.put("USD", dollarAccount);
        //Create a default pound account and add it the our userAccounts container
        accCurrency = Currency.getInstance("GBP");
        Account poundAccount = new Account(accCurrency, 0);
        userAccounts.put("GBP", poundAccount);
    }

    public void setAccountBalance(double startingBalance,String accountToBeCredited) {
        userAccounts.get(accountToBeCredited).setBalance(startingBalance);
    }

    public double getAccountBalance(String eur) {
        return userAccounts.get("EUR").getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }
}
