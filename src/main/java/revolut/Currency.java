package revolut;

import java.util.ArrayList;
import java.util.HashMap;

public class Currency {

    private String currencyCode;

    private HashMap<String,Double> ConversionMap;
    public Currency(String code)
    {
        currencyCode = code;
        ConversionMap = new HashMap<String,Double>();
        GetCurrencyConversionRates();
    }

    private void GetCurrencyConversionRates()
    {
        switch(currencyCode) {
            case "EUR":
                ConversionMap.put("BTC", 0.000034 );
                ConversionMap.put("USD", 1.22 );
                ConversionMap.put("ETH", 0.00046 );
                ConversionMap.put("XRP", 0.84 );
                break;
            case "BTC":
                ConversionMap.put("EUR", 31900.53 );
                ConversionMap.put("USD", 38822.60 );
                ConversionMap.put("ETH", 14.78 );
                ConversionMap.put("XRP", 191113.63);
                break;
            case "USD":
                ConversionMap.put("BTC", 0.000026);
                ConversionMap.put("EUR", 0.82 );
                ConversionMap.put("ETH", 0.00038 );
                ConversionMap.put("XRP", 0.70);
                break;
            case "ETH":
                ConversionMap.put("BTC", 0.079 );
                ConversionMap.put("USD", 2616.42 );
                ConversionMap.put("EUR", 2149.63);
                ConversionMap.put("XRP", 0.00040324 );
            case "XRP":
                ConversionMap.put("BTC", 0.000037 );
                ConversionMap.put("USD", 1.1625 );
                ConversionMap.put("ETH", 0.000433 );
                ConversionMap.put("EUR", 0.95);
            default:
                ConversionMap.put("BTC", 1.0 );
                ConversionMap.put("USD", 1.0 );
                ConversionMap.put("ETH",1.0 );
                ConversionMap.put("EUR", 1.0);
        }
    }

    public double GetConvertedValue(String toCurrency, Double amountToConvert)
    {
        if(ConversionMap.containsKey(toCurrency)) {
            return ConversionMap.get(toCurrency).doubleValue() * amountToConvert;
        }
        else {
            throw new IllegalArgumentException("No Conversion Rate Set for " + toCurrency);
        }
    }


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return this.currencyCode;
    }
}
