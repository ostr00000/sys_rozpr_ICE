package account;

import gBank.PersonalData;

public class StandardAccountI extends Account {

    StandardAccountI(PersonalData personalData) {
        super(personalData);
    }

    @Override
    public String getType() {
        return "standard";
    }

}
