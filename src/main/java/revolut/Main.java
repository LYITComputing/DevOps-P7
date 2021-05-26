package revolut;

public class Main {
    public static void main(String[] args) {

        Person john = new Person("John");
        Person mary = new Person("Mary");

        john.setAccountBalance(200.0);
        mary.setAccountBalance(1000.0);

        john.splitBill(mary,500.0);


        System.out.println("John : " + john.getAccountBalance("EUR"));
        System.out.println("MAry : " + mary.getAccountBalance("EUR"));


    }
}
