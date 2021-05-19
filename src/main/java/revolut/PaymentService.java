package revolut;

public class PaymentService {
    private String serviceName;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public boolean Accepted(Person accountHolder, double topupAmount,String currencyType, boolean successfulPayment)
    {
        if(successfulPayment) {
            // Add this in when moving topups to the payment service
            //accountHolder.getAccount(currencyType).addFunds(topupAmount);
            return true;
        }
        return false;
    }
}

