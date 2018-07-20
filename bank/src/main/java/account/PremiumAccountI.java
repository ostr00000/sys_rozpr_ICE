package account;

import bankModule.BankData;
import com.zeroc.Ice.Current;
import gAccounts.PremiumAccount;
import gBank.PersonalData;
import gExceptions.TakeLoanErrorType;
import gExceptions.TakeLoanException;
import gLoan.Loan;
import gLoan.LoanConfirmation;

import java.time.DateTimeException;
import java.time.LocalDate;

public class PremiumAccountI extends Account implements PremiumAccount {

    PremiumAccountI(PersonalData personalData) {
        super(personalData);
    }

    @Override
    public String getType() {
        return "premium";
    }

    @Override
    public LoanConfirmation takeLoan(Loan loan, Current current) throws TakeLoanException {
        System.out.println("takeLoan " + loan.toString());
        checkLoan(loan);

        double ratio = getRatio(loan);
        double totalBaseCost = getTotalLoanCostInBaseCurrency(loan);
        double totalOtherCost = totalBaseCost * ratio;

        return new LoanConfirmation(loan.repaymentDate,
                BankData.baseCurrency, totalBaseCost,
                loan.CurrencyCode, totalOtherCost);
    }

    private void checkLoan(Loan loan) throws TakeLoanException {
        if (!isSupportedCurrency(loan)) {
            throw new TakeLoanException("Bank doesn't support this currency", TakeLoanErrorType.UnsupportedCurrency);
        }
        if (loan.money <= 0) {
            throw new TakeLoanException("Money must be positive number", TakeLoanErrorType.NegativeMoney);
        }

        if (convertTimeFrom(loan).isBefore(LocalDate.now())) {
            throw new TakeLoanException("Date to repay loan was in past", TakeLoanErrorType.InvalidDate);
        }
    }

    private boolean isSupportedCurrency(Loan loan) {
        return BankData.otherCurrencies.contains(loan.CurrencyCode) || BankData.baseCurrency.equals(loan.CurrencyCode);
    }

    private LocalDate convertTimeFrom(Loan loan) throws TakeLoanException {
        try {
            return LocalDate.of(loan.repaymentDate.year, loan.repaymentDate.month, loan.repaymentDate.day);
        } catch (DateTimeException e) {
            throw new TakeLoanException(e.getMessage(), TakeLoanErrorType.InvalidDate);
        }
    }

    private Double getRatio(Loan loan) throws TakeLoanException {
        Double ratio = BankData.currencyMap.getRatio(BankData.baseCurrency, loan.CurrencyCode);
        if (ratio == null) {
            throw new TakeLoanException(
                    "Bank cannot support this currency at this moment",
                    TakeLoanErrorType.UnsupportedCurrency
            );
        }
        return ratio;
    }

    private double getTotalLoanCostInBaseCurrency(Loan loan) {
        return loan.money * 1.1;
    }

}
