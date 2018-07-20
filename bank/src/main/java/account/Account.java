package account;

import com.zeroc.Ice.Current;
import com.zeroc.Ice.Identity;
import gAccounts.AccountInfo;
import gAccounts.StandardAccount;
import gBank.ClientKey;
import gBank.PersonalData;

import java.util.UUID;

public abstract class Account implements StandardAccount {
    private PersonalData personalData;
    private UUID key;
    @SuppressWarnings("FieldCanBeLocal")
    private double money = 0;

    Account(PersonalData personalData) {
        this.personalData = personalData;
        this.key = UUID.randomUUID();
        System.out.println("create " + getType());
    }

    public ClientKey getClientKey() {
        return new ClientKey(this.key.toString());
    }

    public Identity getIdentity() {
        return new Identity(this.personalData.pesel, getType());
    }

    public abstract String getType();

    @Override
    public AccountInfo getInfo(Current current) {
        return new AccountInfo(this.personalData, this.money, getType());
    }

}
