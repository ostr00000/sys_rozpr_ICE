package account;

import gBank.PersonalData;

public class AccountFactory {
    private double minPremiumIncome;

    public AccountFactory(long minPremiumIncome) {
        this.minPremiumIncome = minPremiumIncome;
    }

    public Account createAccount(PersonalData personalData){
        if (personalData.income > this.minPremiumIncome){
            return new PremiumAccountI(personalData);
        }else {
            return new StandardAccountI(personalData);
        }
    }
}
