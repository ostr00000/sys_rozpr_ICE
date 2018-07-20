package bankModule;

import currency.CurrencyMap;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

import java.util.Arrays;
import java.util.List;

public class BankData {
    public static final CurrencyMap currencyMap ;
    public static final String baseCurrency;
    public static final List<String> otherCurrencies;


    static {
        baseCurrency = "PLN";
        otherCurrencies = Arrays.asList("EUR", "USD", "GBP", "CAD");
        currencyMap = new CurrencyMap(baseCurrency, otherCurrencies);
        connectToCurrencyService();
    }

    private static void connectToCurrencyService() {
        try {
            currencyMap.init();
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.UNAVAILABLE) {
                System.err.println("Cannot connect to currency service");
            } else {
                System.err.println(e.toString());
            }
            System.exit(1);
        }
    }

}
