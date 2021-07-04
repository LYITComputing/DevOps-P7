package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;


    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }


    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }


    public void addFunds(double funds) {
        this.balance += funds;
    }
    public void removeFunds(double funds) {
        this.balance -= funds;
    }
    public boolean transferFunds(double amount, PaymentService service)
    {
        if ((service.getBalance() - amount) < 0)
            return false;
        else
        {
            addFunds(amount);
            service.removeFunds(amount);
            return true;
        }
    }

    public Currency getAccCurrency() {
        return accCurrency;
    }
}
