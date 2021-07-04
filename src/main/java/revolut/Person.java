package revolut;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;

public class Person {

    private String name;
    //Accounts currency & balance
    // EUR 30
    // USD 50
    // STG 30
    private List<Account> accounts = new ArrayList<>();

    public Person(String name){
        this.name = name;
        //Create a default euro account and add it the our userAccounts container
        Currency accCurrency = Currency.getInstance("EUR");
        Account euroAccount = new Account(accCurrency, 0);
        accounts.add(euroAccount);
    }
    public void addAccount(Account acc)
    {
        accounts.add(acc);
    }

    public void setAccountBalance(String accName, double startingBlanace) throws Exception {
        Account acc = getAccount(accName);
        if (acc == null)
        {
            addAccount(new Account(Currency.getInstance(accName),startingBlanace));
        }
        else {
            acc.setBalance(startingBlanace);
        }
    }

    public double getAccountBalance(String acc) throws Exception {

        return getAccount(acc).getBalance();
    }

    public Account getAccount(String account) {
        for (Account acc:
                accounts) {
            if (acc.getAccCurrency().getCurrencyCode().equals(account))
                return acc;
        }
        return null;
    }
    public void convertCurrency(String originatingAccount,  String destinationAccount,
                                double conversionAmount, double exchangeRate)
    {
        getAccount(originatingAccount).removeFunds(conversionAmount);
        double newCurrencyAmount = conversionAmount *  exchangeRate;
        getAccount(destinationAccount).addFunds(newCurrencyAmount);
    }



}
