package org.example.model;

import org.example.exception.MoneyRequestExecutionException;

import java.util.Objects;

// класс количества денег относительно номинала и колличества купюр
public class CashImpl implements Cash {
    private final Money money;
    private int quantity;

    public CashImpl(Money money, int quantity) {
        this.money = money;
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getTotal() {
        return money.asNominal() * quantity;
    }

    @Override
    public int getNominal() {
        return money.asNominal();
    }

    @Override
    public Money getMoney() {
        return money;
    }

    @Override
    public Cash createInstance(Money money, int quantity) {
        return new CashImpl(money, quantity);
    }

    @Override
    public String toString() {
        return "Cash{" +
                "nominal=" + money.asNominal() +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashImpl cashImpl = (CashImpl) o;
        return money == cashImpl.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

}
