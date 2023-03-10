package org.example.model;

public interface Cash {
    int getQuantity();
    int getNominal();
    void setQuantity(int quantity);
    int getTotal();
    Cash createInstance(Money money, int cash);
    Money getMoney();
}
