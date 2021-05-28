package revolut;

public class PaymentService {
    private final String serviceName;
    private int pinCode;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public static boolean canTransferFromAccount(String AccountName){
        switch(AccountName) {
            case "BankAccount":
                return true;
            case "LoanAccount":
                return false;
            case "SavingAccount":
                return false;
            case "DebitCard":
                return true;
                default:
                    return false;
        }
    }

    public String getType() {
        return serviceName;
    }
}
