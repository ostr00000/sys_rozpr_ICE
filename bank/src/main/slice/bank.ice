
module gBank{
    struct PersonalData{
        string name;
        string surname;
        string pesel;
        double income;
    };

    struct ClientKey {
        string key;
    };
};

module gExceptions{

    exception BankException {
        string reason;
    };

    enum CreateErrorType {
        PeselAlreadyUsed,
        WrongIncome
    };

    exception CreateAccountException extends BankException {
        CreateErrorType type;
    };

    exception GetAccountException extends BankException {};

    enum TakeLoanErrorType {
        InvalidDate,
        UnsupportedCurrency,
        NegativeMoney
    };

    exception TakeLoanException extends BankException {
        TakeLoanErrorType type;
    };

};

module gLoan {

    struct Date{
        short year;
        short month;
        short day;
    };

    struct Loan{
        Date repaymentDate;
        string CurrencyCode;
        double money;
    };

    struct LoanConfirmation{
        Date repaymentDate;
        string baseCurrencyCode;
        double inBaseCurrency;
        string otherCurrencyCode;
        double inOtherCurrency;
    };
};

module gAccounts {

    struct AccountInfo{
        gBank::PersonalData personalData;
        double money;
        string accountType;
    };

    interface StandardAccount{
        idempotent AccountInfo getInfo();
    };

    interface PremiumAccount extends StandardAccount{
        gLoan::LoanConfirmation takeLoan(gLoan::Loan loan) throws gExceptions::TakeLoanException;
    };
};

module gBank {

    interface Bank{
        ClientKey createAccount(PersonalData personalData) throws gExceptions::CreateAccountException;
        idempotent gAccounts::StandardAccount* getAccount(ClientKey key) throws gExceptions::GetAccountException;
    };
};