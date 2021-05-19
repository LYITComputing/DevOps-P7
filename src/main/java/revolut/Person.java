package revolut;

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
        revolut.Currency accCurrency = new Currency("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        userAccounts.put("EUR", euroAccount);
    }

    public void setAccountBalance(double startingBalance, String currencyType) {
        if(userAccounts.containsKey(currencyType)) {
            userAccounts.get(currencyType).setBalance(startingBalance);
        }else{
            revolut.Currency accCurrency = new Currency(currencyType);
            userAccounts.put(currencyType,new Account(accCurrency, startingBalance));
        }
    }

    public void transferBetweenAccounts(String fromCurrencyCode, String toCurrencyCode, Double amount)
    {
        //Ensure the person has the Currency within their account.
        if(!userAccounts.containsKey(toCurrencyCode)) {
            this.setAccountBalance(0,toCurrencyCode);
        }

        //Does the Person have the Currency in their account?
        if(userAccounts.containsKey(fromCurrencyCode)) {
            double currentBalance = userAccounts.get(fromCurrencyCode).getBalance();
            Currency fromCurrency = userAccounts.get(fromCurrencyCode).getCurrency();
            // Are there enough funds
            if (currentBalance >= amount) {
                //Take money from "fromCurrency"
                userAccounts.get(fromCurrencyCode).setBalance(currentBalance - amount);
                //Holding the amount of currency in the amount field.
                //need to convert it to the new currency
                userAccounts.get(toCurrencyCode).setBalance(fromCurrency.GetConvertedValue(toCurrencyCode, amount));
            } else {
                // Not enough funds
                throw new IllegalStateException("Cannot use a currency where the balance is below the amount needed to transfer");
            }
        }else{
            throw new IllegalStateException("No " + fromCurrencyCode + " found within the account to use for transfer");
        }

    }

    public double getAccountBalance(String code) {
        return userAccounts.get(code).getBalance();
    }

    public Account getAccount(String account) {
        return userAccounts.get(account);
    }
}
