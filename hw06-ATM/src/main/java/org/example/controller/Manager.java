package org.example.controller;

import org.example.model.Cash;
import org.example.model.Storage;

import java.util.Set;

public interface Manager {
    void putCash(Cash obj);
    Set<Cash> popCash(int cash);
    Set<Cash> popAllCash();
    Storage<Cash> getCashStorage();
}
