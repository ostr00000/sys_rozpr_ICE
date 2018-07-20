import bankModule.ClientConnection;
import bankModule.DateCast;
import gAccounts.AccountInfo;
import gAccounts.PremiumAccountPrx;
import gAccounts.StandardAccountPrx;
import gBank.ClientKey;
import gBank.PersonalData;
import gExceptions.CreateAccountException;
import gExceptions.GetAccountException;
import gExceptions.TakeLoanException;
import gLoan.Loan;
import gLoan.LoanConfirmation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClient {
    private final Scanner scanner;
    private final ClientConnection clientConnection;
    private final Map<String, Runnable> actions;

    private StandardAccountPrx accountPrx;

    public static void main(String[] args) {
        MainClient main = new MainClient();
        main.mainLoop();
    }

    private MainClient() {
        this.scanner = new Scanner(System.in);
        this.clientConnection = new ClientConnection("tcp -h localhost -p 10001");

        this.actions = new HashMap<>();
        this.actions.put("e", this::stop);
        this.actions.put("c", this::createAccount);
        this.actions.put("l", this::logIn);
        this.actions.put("s", this::getAccountInfo);
        this.actions.put("t", this::takeLoan);
    }


    @SuppressWarnings("InfiniteLoopStatement")
    private void mainLoop() {
        while (true) {
            String action = read("Actions:\n" +
                    "'e' - exit program,\n" +
                    "'c' - create new account,\n" +
                    "'l' - log in into existing account,\n" +
                    "'s' - show account info,\n" +
                    "'t' - take loan,\n"
            );
            System.out.println("Next action: " + action);
            runAction(action);
        }
    }

    private String read(String toPrint) {
        System.out.println(toPrint);
        return this.scanner.next();
    }

    private void runAction(String action) {
        if (this.actions.containsKey(action)) {
            this.actions.get(action).run();
        } else {
            System.out.println("Wrong action");
        }
    }

    private void stop() {
        System.exit(0);
    }

    private void createAccount() {
        try {
            PersonalData personalData = createPersonalData();
            ClientKey clientKey = this.clientConnection.getBank().createAccount(personalData);
            System.out.println("Client id=" + clientKey.key);
            this.accountPrx = this.clientConnection.getBank().getAccount(clientKey);
        } catch (NumberFormatException e) {
            System.out.println("Wrong numbers");
        } catch (CreateAccountException e) {
            System.out.println("Cannot create account: " + e.toString());
        } catch (GetAccountException e) {
            System.out.println("Cannot login to account: " + e.toString());
        }
    }

    private PersonalData createPersonalData() {
        String name = read("Get name");
        String surname = read("Get surname");
        String pesel = read("Get PESEL");
        String incomeString = read("Get your income");
        long income = Long.valueOf(incomeString);
        return new PersonalData(name, surname, pesel, income);
    }

    private void logIn() {
        try {
            String key = read("Get account id");
            this.accountPrx = this.clientConnection.getBank().getAccount(new ClientKey(key));
            System.out.println("Logged successful");
        } catch (GetAccountException e) {
            System.out.println("Cannot login to account: " + e.toString());
        }
    }

    private void getAccountInfo() {
        if (isLogged()) {
            AccountInfo info = this.accountPrx.getInfo();
            printAccountInfo(info);
        }
    }

    private void printAccountInfo(AccountInfo info) {
        System.out.println("Account type: " + info.accountType
                + "\nname: " + info.personalData.name
                + "\nsurname: " + info.personalData.surname
                + "\nPESEL: " + info.personalData.pesel
                + "\nincome: " + info.personalData.income
                + "\nmoney: " + info.money
        );
    }


    private boolean isLogged() {
        if (this.accountPrx != null) {
            return true;
        } else {
            System.out.println("You must login first");
            return false;
        }
    }

    private void takeLoan() {
        if (isLogged()) {
            try {
                Loan loan = createLoan();
                PremiumAccountPrx premium = PremiumAccountPrx.checkedCast(this.accountPrx);
                LoanConfirmation conf = premium.takeLoan(loan);
                printLoanConfirmation(conf);
            } catch (NumberFormatException e) {
                System.out.println("Wrong numbers");
            } catch (TakeLoanException e) {
                System.out.println("Cannot take loan: " + e.toString());
            } catch (NullPointerException e) {
                System.out.println("Type of account isn't premium - you can't take loan");
            }
        }
    }

    private Loan createLoan() throws NumberFormatException {
        String currencyCode = read("Get currency code of the loan");
        String moneyString = read("Get number money in base currency");

        System.out.println("Get loan repayment date: ");
        String year = read( "Get year");
        String month = read("Get month");
        String day = read( "Get day");

        return new Loan(new DateCast(year, month, day), currencyCode, Double.valueOf(moneyString));
    }

    private void printLoanConfirmation(LoanConfirmation conf) {
        System.out.println("Base currency: " + conf.baseCurrencyCode
                + "\nMoney to repay in base currency: " + conf.inBaseCurrency
                + "\nOther currency: " + conf.otherCurrencyCode
                + "\nMoney to repay in other currency: " + conf.inOtherCurrency
                + "\nRepayment date: " + conf.repaymentDate.year
                + "." + conf.repaymentDate.month
                + "." + conf.repaymentDate.day
        );
    }


}