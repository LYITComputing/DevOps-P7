package revolut;

public class FX {
    // This page will help to understand why I didnt implement a more elegent solution https://images.app.goo.gl/o2XjLkqHFJ6dEScB9
    private final double USD_to_EUR = 0.82;
    private final double USD_to_GBP = 0.71;
    private final double EUR_to_USD = 1.21;
    private final double EUR_to_GBP = 0.86;
    private final double GBP_to_EUR = 1.17;
    private final double GBP_to_USD = 1.42;

    public FX() {
    }

    // This method will take in two currency strings and convert the amount passed from one string to the other.
    public double currencyConvesion(String startCurrency, String finishCurrency, double amountToBeConverted){
        switch(startCurrency) {
            case "EUR":
                if(finishCurrency.equalsIgnoreCase("USD")){
                    return amountToBeConverted*EUR_to_USD;
                }
                else{
                    return amountToBeConverted*EUR_to_GBP;
                }
            case "USD":
                if(finishCurrency.equalsIgnoreCase("EUR")){
                    return amountToBeConverted*USD_to_EUR;
                }
                else{
                    return amountToBeConverted*USD_to_GBP;
                }
            case "GBP":
                if(finishCurrency.equalsIgnoreCase("EUR")){
                    return amountToBeConverted*GBP_to_EUR;
                }
                else{
                    return amountToBeConverted*GBP_to_USD;
                }
                default:
                 return 0;
        }
    }
}
