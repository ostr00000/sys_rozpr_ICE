package currency;

import java.util.Objects;

public class Relation {
    private String baseCurrency;
    private String otherCurrency;

    Relation(String baseCurrency, String otherCurrency) {
        this.baseCurrency = baseCurrency;
        this.otherCurrency = otherCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return Objects.equals(baseCurrency, relation.baseCurrency) &&
                Objects.equals(otherCurrency, relation.otherCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseCurrency, otherCurrency);
    }

    @Override
    public String toString() {
        return baseCurrency + "->" + otherCurrency;
    }
}
