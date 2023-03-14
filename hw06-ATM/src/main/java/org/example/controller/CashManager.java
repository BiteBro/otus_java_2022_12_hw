package org.example.controller;

import org.example.exception.MoneyRequestExecutionException;
import org.example.model.*;

import java.util.*;

public class CashManager implements Manager {
    private final Storage<Cash> storage;

    public CashManager(Storage<Cash> storage) {
        this.storage = storage;
    }

    @Override
    public void putCash(Cash cash) {
        if (cash.getQuantity() < 0) {
            throw new MoneyRequestExecutionException();
        }
        for (Cash c : storage.getSet()) {
            if (c.equals(cash)) {
                int quantity = c.getQuantity();
                quantity = quantity + cash.getQuantity();
                c.setQuantity(quantity);
                return;
            }
        }
        storage.getSet().add(cash);
    }

    @Override
    public Set<Cash> popCash(int amount) {
        if (amount > 0 && amount <= getQuantityCash(storage.getSet())) {
            Set<Cash> set = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());
            for (Cash c : storage.getSet()) {
                Cash ch = popKitBanknotes(c, amount);
                set.add(ch);
                amount = amount - ch.getTotal();
                if (amount == 0) {
                    removeBanknotes(set);
                    return set;
                }
            }
        }
        throw new MoneyRequestExecutionException();
    }

    @Override
    public Set<Cash> popAllCash() {
        return popCash(storage.getSet().stream().mapToInt(Cash::getTotal).sum());
    }

    private void removeBanknotes(Set<Cash> removeSet) {
        for (Cash c : storage.getSet()) {
            for (Cash rc : removeSet) {
                if (c.equals(rc)) {
                    c.setQuantity(c.getQuantity() - rc.getQuantity());
                    break;
                }
            }
        }
    }

    private Cash popKitBanknotes(Cash c, int amount) {
        if (c.getTotal() > amount) {
            return c.createInstance(c.getMoney(), amount / c.getNominal());
        } else
        {
            return c.createInstance(c.getMoney(), c.getQuantity());
        }
    }

    private int getQuantityCash(Set<Cash> cashSet) {
        return cashSet.stream().mapToInt(a -> a.getQuantity() * a.getNominal()).sum();
    }

    @Override
    public Storage<Cash> getCashStorage() {
        return storage;
    }
}
