package org.example.model;

import java.util.*;

public class CashStorage implements Storage<Cash> {
    private final Set<Cash> set = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

    public CashStorage() {
    }

    @Override
    public Set<Cash> getSet() {
        return set;
    }
}
