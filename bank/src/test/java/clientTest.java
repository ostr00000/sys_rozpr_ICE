import bankModule.ClientConnection;
import gAccounts.AccountInfo;
import gAccounts.PremiumAccountPrx;
import gAccounts.StandardAccountPrx;
import gBank.ClientKey;
import gBank.PersonalData;
import gExceptions.BankException;
import gExceptions.CreateAccountException;
import gExceptions.GetAccountException;
import gExceptions.TakeLoanException;
import gLoan.Loan;
import gLoan.LoanConfirmation;
import bankModule.DateCast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class clientTest {
    private ClientConnection clientConnection;
    final private long tinyIncome = 4;
    final private long hugeIncome = (long) 1e10;

    private String name = "a";
    private String surname = "b";
    private long income = 4;
    private String lastPesel;

    final private String baseCurrency = "PLN";
    private String loanCurrency = "USD";
    private double loanMoney = 10000;
    private int day = 10;
    private int month = 10;
    private int year = 2020;

    @Before
    public void init() {
        this.clientConnection = new ClientConnection();
    }

    @After
    public void close() {
        this.clientConnection.close();
    }

    private PersonalData getDefaultPersonalData() {
        this.lastPesel = UUID.randomUUID().toString();
        return new PersonalData(this.name, this.surname, this.lastPesel, this.income);
    }

    private ClientKey createAccount() throws CreateAccountException {
        return this.clientConnection.getBank().createAccount(getDefaultPersonalData());
    }

    @Test
    public void createSimpleAccount() throws CreateAccountException {
        createAccount();
    }

    @Test(expected = CreateAccountException.class)
    public void duplicatedPesel() throws CreateAccountException {
        PersonalData personalData = getDefaultPersonalData();
        this.clientConnection.getBank().createAccount(personalData);
        this.clientConnection.getBank().createAccount(personalData);
    }

    @Test(expected = CreateAccountException.class)
    public void negativeIncome() throws CreateAccountException {
        PersonalData personalData = getDefaultPersonalData();
        personalData.income = -1;
        this.clientConnection.getBank().createAccount(personalData);
    }

    private StandardAccountPrx getAccount(ClientKey key) throws GetAccountException {
        return this.clientConnection.getBank().getAccount(key);
    }

    @Test
    public void isStandard() throws BankException {
        this.income = this.tinyIncome;
        ClientKey key = createAccount();
        StandardAccountPrx standard = StandardAccountPrx.checkedCast(getAccount(key));
        PremiumAccountPrx premium = PremiumAccountPrx.checkedCast(getAccount(key));

        assertNotNull(standard);
        assertNull(premium);

    }

    @Test
    public void isPremium() throws BankException {
        this.income = this.hugeIncome;
        ClientKey key = createAccount();
        StandardAccountPrx standard = StandardAccountPrx.checkedCast(getAccount(key));
        PremiumAccountPrx premium = PremiumAccountPrx.checkedCast(getAccount(key));

        assertNotNull(standard);
        assertNotNull(premium);
    }

    private void compare(AccountInfo info) {
        assertEquals(this.name, info.personalData.name);
        assertEquals(this.surname, info.personalData.surname);
        assertEquals(this.lastPesel, info.personalData.pesel);
        assertEquals(this.income, info.personalData.income, 1e-9);
        assertEquals(0, info.money, 1e-9);
    }

    @Test
    public void getStandardAccountInfo() throws BankException {
        this.income = this.tinyIncome;
        ClientKey key = createAccount();
        compare(getAccount(key).getInfo());
    }

    @Test
    public void getPremiumAccountInfo() throws BankException {
        this.income = this.hugeIncome;
        ClientKey key = createAccount();
        compare(getAccount(key).getInfo());
    }

    @Test(expected = GetAccountException.class)
    public void wrongKey() throws BankException {
        getAccount(new ClientKey("12345"));
    }

    private Loan getDefaultLoan() {
        return new Loan(new DateCast<>(this.year, this.month, this.day), this.loanCurrency, this.loanMoney);
    }

    private void compare(LoanConfirmation loanConfirmation) {
        assertEquals(this.day, loanConfirmation.repaymentDate.day);
        assertEquals(this.day, loanConfirmation.repaymentDate.day);
        assertEquals(this.month, loanConfirmation.repaymentDate.month);

        assertEquals(this.baseCurrency, loanConfirmation.baseCurrencyCode);
        assertEquals(this.loanCurrency, loanConfirmation.otherCurrencyCode);

        assertTrue(this.loanMoney <= loanConfirmation.inBaseCurrency);
    }


    @Test
    public void takeLoanBaseCurrency() throws BankException {
        this.income = this.hugeIncome;
        this.loanCurrency = this.baseCurrency;

        ClientKey key = createAccount();
        PremiumAccountPrx prx = PremiumAccountPrx.checkedCast(getAccount(key));
        LoanConfirmation confirmation = prx.takeLoan(getDefaultLoan());

        compare(confirmation);
    }

    @Test
    public void takeLoanOtherCurrency() throws BankException {
        this.income = this.hugeIncome;
        this.loanCurrency = "USD";

        ClientKey key = createAccount();
        PremiumAccountPrx prx = PremiumAccountPrx.checkedCast(getAccount(key));
        LoanConfirmation confirmation = prx.takeLoan(getDefaultLoan());

        compare(confirmation);
    }

    @Test(expected = TakeLoanException.class)
    public void negativeMoney() throws BankException{
        this.income = this.hugeIncome;
        double old = this.loanMoney;
        this.loanMoney = -1;
        Loan loan = getDefaultLoan();
        this.loanMoney = old;

        ClientKey key = createAccount();
        PremiumAccountPrx prx = PremiumAccountPrx.checkedCast(getAccount(key));
        LoanConfirmation confirmation = prx.takeLoan(loan);
        compare(confirmation);
    }

    @Test(expected = TakeLoanException.class)
    public void unsupportedCurrency() throws BankException{
        this.income = this.hugeIncome;
        String old = this.loanCurrency;
        this.loanCurrency = "awfgj";
        Loan loan = getDefaultLoan();
        this.loanCurrency = old;

        ClientKey key = createAccount();
        PremiumAccountPrx prx = PremiumAccountPrx.checkedCast(getAccount(key));
        LoanConfirmation confirmation = prx.takeLoan(loan);
        compare(confirmation);
    }

    @Test(expected = TakeLoanException.class)
    public void invalidDate() throws BankException{
        this.income = this.hugeIncome;
        int old = this.year;
        this.year = 1410;
        Loan loan = getDefaultLoan();
        this.year = old;

        ClientKey key = createAccount();
        PremiumAccountPrx prx = PremiumAccountPrx.checkedCast(getAccount(key));
        LoanConfirmation confirmation = prx.takeLoan(loan);
        compare(confirmation);
    }


}
