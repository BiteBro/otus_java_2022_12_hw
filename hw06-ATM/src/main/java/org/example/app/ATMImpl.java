package org.example.app;

import org.example.controller.Manager;
import org.example.exception.MoneyRequestExecutionException;
import org.example.model.*;

import java.util.Set;

public class ATMImpl implements ATM{
    private final Manager cashManager;

    public ATMImpl(Manager manager) {
        this.cashManager = manager;
    }

    public void putMoneyOnBanknotes(Money nominal, int billCount) {
        for (Money ms : Money.values()) {
            if (billCount > 0 & ms.getNominal() == nominal.getNominal()) {
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
