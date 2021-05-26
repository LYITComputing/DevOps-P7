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

    public void send(Person person, Double sendMoney) {

        if(this.getAccount("EUR").getBalance() >= sendMoney){

            person.getAccount("EUR").receiveTransfer(sendMoney);
            this.getAccount("EUR").setBalance(this.getAccount("EUR").getBalance() - sendMoney );

        }
    }

    public void splitBill(Person person, Double bill) {

        bill = bill / 2.0;

        if((this.getAccount("EUR").getBalance() > bill && person.getAccount("EUR").getBalance() > bill) ) {

            this.setAccountBalance(this.getAccount("EUR").getBalance() - bill);
            person.setAccountBalance(person.getAccount("EUR").getBalance() - bill);

        }

    }
}
