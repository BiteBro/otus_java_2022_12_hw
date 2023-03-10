package org.example.model;

import java.util.List;

public enum MoneyRUB implements Money {
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    FIVE_THOUSAND(5000);

    private final int nominal;

    MoneyRUB(int nominal) {
        this.nominal = nominal;
    }

    @Override
    public int asNominal() {
        return this.nominal;
    }

}
