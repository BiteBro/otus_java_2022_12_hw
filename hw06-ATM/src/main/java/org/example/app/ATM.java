package org.example.app;

import org.example.model.Cash;
import org.example.model.Money;
import org.example.model.Storage;

import java.util.Set;

public interface ATM {
    void putMoneyOnBanknotes(Money nominal, int billCount);
    Set<Cash> popCash(int cash);
    Set<Cash> popAllCash();
    Storage<Cash> getStorage();
}
