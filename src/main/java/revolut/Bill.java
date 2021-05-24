package revolut;

import java.util.ArrayList;

public class Bill {
    private double amount;

    public void setBillAmount(double billAmount){
        this.amount = billAmount;
    }

    public String splitBill(ArrayList<Person> friends){

        double amountToPay = this.amount / friends.size();
        boolean sufficientFunds = true;

        for (Person person : friends) {
            if ((person.getAccountBalance("EUR") - amountToPay) < 0) {
                sufficientFunds = false;
                break;
            }
        }
        if(sufficientFunds) {
            for (Person friend : friends) {
                friend.pay(amountToPay);
            }
            return "Transaction Approved";
        }
        else{
            return "Transaction Cancelled: Insufficient Funds";
        }
    }
}
