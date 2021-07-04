package revolut;

public class PaymentService {
    private String serviceName;
    private double balance;

    public PaymentService(String name){
        this.serviceName = name;
        this.balance = 0.0;
    }
    public PaymentService (String name, double balance)
    {
        this.serviceName = name;
        this.balance = balance;
    }


    public String getType() {
        return serviceName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void removeFunds(double amount)
    {
        this.balance -= amount;
    }



}
