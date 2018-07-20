package bankModule;

import account.Account;
import account.AccountFactory;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectPrx;
import gAccounts.StandardAccountPrx;
import gBank.Bank;
import gBank.ClientKey;
import gBank.PersonalData;
import gExceptions.CreateAccountException;
import gExceptions.CreateErrorType;
import gExceptions.GetAccountException;

import java.util.HashMap;
import java.util.Map;

public class BankI implements Bank {
    private ObjectAdapter adapter;
    private Map<ClientKey, ObjectPrx> accountMap = new HashMap<>();

    BankI(ObjectAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public StandardAccountPrx getAccount(ClientKey key, Current current) throws GetAccountException {
        System.out.println("getAccount with key: " + key.key);
        ObjectPrx prx = this.accountMap.get(key);
        if (prx == null) {
            throw new GetAccountException("Account with key " + key.key + " doesn't exist");
        }
        return StandardAccountPrx.uncheckedCast(prx);
    }

    @Override
    public ClientKey createAccount(PersonalData personalData, Current current) throws CreateAccountException {
        System.out.println("createAccount(pesel=" + personalData.pesel + ", income=" + personalData.income + ")");
        checkIncome(personalData);
        checkPeselUsage(personalData);

        Account account = new AccountFactory(10000).createAccount(personalData);
        ObjectPrx prx = this.adapter.add(account, account.getIdentity());
        this.accountMap.put(account.getClientKey(), prx);

        return account.getClientKey();
    }

    private void checkIncome(PersonalData personalData) throws CreateAccountException {
        if (personalData.income < 0) {
            throw new CreateAccountException("income cannot be negative", CreateErrorType.WrongIncome);
        }
    }

    private void checkPeselUsage(PersonalData data) throws CreateAccountException {
        if (isDataUsedInCategory(data, "premium") || isDataUsedInCategory(data, "standard")) {
            throw new CreateAccountException("pesel was already used", CreateErrorType.PeselAlreadyUsed);
        }
    }

    private boolean isDataUsedInCategory(PersonalData personalData, String category) {
        return null != this.adapter.find(new Identity(personalData.pesel, category));
    }

}
