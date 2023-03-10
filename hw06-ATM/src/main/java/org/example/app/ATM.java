package org.example.app;

import org.example.controller.Manager;
import org.example.exception.MoneyRequestExecutionException;
import org.example.model.*;

import java.util.Set;

public class ATM {
    private final Manager cashManager;

    public ATM(Manager manager) {
        this.cashManager = manager;
    }

    public void putMoneyOnBanknotes(int nominal, int billCount) {
        for (Money ms : MoneyRUB.values()) {
            if (billCount > 0 & ms.asNominal() == nominal) {
                cashManager.putCash(new CashImpl(ms, billCount));
                return;
            }
        }
        throw new MoneyRequestExecutionException();
    }

    public Set<Cash> popCash(int cash) {
        return cashManager.popCash(cash);
    }

    public Set<Cash> popAllCash() {
        return cashManager.popAllCash();
    }

    public Storage<Cash> getStorage() {
        return cashManager.getCashStorage();
    }
}
