package org.example.controller;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.example.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CashManagerTest {
    private CashManager manager;
    public CashManagerTest() {
    }

    @BeforeEach
    public void setUp() {
        this.manager = new CashManager(new CashStorage());
    }

    @AfterEach
    void tearDown() {
        this.manager = null;
    }

    @Test   // Проверка на отрицательное число с выбросом исключения
    public void testPutCashWrongValue() {
        System.out.println("********** putCashWrongValue() ");
        Cash wrongValue = new CashImpl(MoneyRUB.FIFTY, -11);
        try {
            manager.putCash(wrongValue);
            fail();
        } catch (Exception ex) {
        }
    }

    @Test   // Проверка на отсутствие дублирования номинала валют в упорядоченном массиве по убыванию
    public void testPutCashNoDuplicate() {
        System.out.println("********** putCashNoDuplicate() ");
        Set<Cash> expResult = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

        expResult.add(new CashImpl(MoneyRUB.FIFTY, 7));
        expResult.add(new CashImpl(MoneyRUB.FIFTY, 4));
        expResult.add(new CashImpl(MoneyRUB.FIVE_HUNDRED, 7));
        expResult.add(new CashImpl(MoneyRUB.ONE_HUNDRED, 3));

        manager.putCash(new CashImpl(MoneyRUB.FIFTY, 8));
        manager.putCash(new CashImpl(MoneyRUB.FIVE_HUNDRED, 3));
        manager.putCash(new CashImpl(MoneyRUB.FIVE_HUNDRED, 4));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 3));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 3));

        Set<Cash> result = manager.popAllCash();

        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    @Test   // Проверка на валидность внесения
    public void testPutCashValidateQuantity() {
        System.out.println("********** putCashValidateQuantity() ");
        Set<Cash> expResult = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

        expResult.add(new CashImpl(MoneyRUB.FIFTY, 17));
        expResult.add(new CashImpl(MoneyRUB.ONE_HUNDRED, 13));

        manager.putCash(new CashImpl(MoneyRUB.FIFTY, 4));
        manager.putCash(new CashImpl(MoneyRUB.FIFTY, 5));
        manager.putCash(new CashImpl(MoneyRUB.FIFTY, 8));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 3));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 4));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 6));

        var expResultInt = expResult.stream().mapToInt(Cash::getQuantity).toArray();
        var resultInt = manager.popAllCash().stream().mapToInt(Cash::getQuantity).toArray();

        assertArrayEquals(expResultInt, resultInt);
    }

    @Test   //  Проверка на отрицательную сумму денег
    public void testPopCashWrongValue() {
        System.out.println("********** popCash() ");
        int wrongValue = -250;
        manager.putCash(new CashImpl(MoneyRUB.FIFTY, 11));

        try {
            manager.popCash(wrongValue);
            fail();
        } catch (Exception ex) {

        }
    }

    @Test   //  Проверка на получение суммы денег большей чем в банкомате
    public void testPopCashLargeValue() {
        System.out.println("********** popCash() ");
        int largeValue = 503350;
        Cash firstCash = new CashImpl(MoneyRUB.FIFTY, 11);

        manager.putCash(firstCash);

        try {
            manager.popCash(largeValue);
            fail();
        } catch (Exception ex) {
        }

    }
    @Test   //  Проверка на получение суммы денег с отсутствующим в банкомате номиналом
    public void testPopCashNoDenomination() {
        System.out.println("********** popCash() ");
        int wrongValue = 3350;
        manager.putCash(new CashImpl(MoneyRUB.FIVE_THOUSAND, 11));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 11));

        try {
            manager.popCash(wrongValue);
            fail();
        } catch (Exception ex) {
        }
    }

    @Test   //  Проверка на получение суммы денег минимальным набором купюр
    public void testPopCash() {
        System.out.println("********** popCash() ");
        int expIntResult = 3300;
        Set<Cash> expResult = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

        manager.putCash(new CashImpl(MoneyRUB.FIFTY, 11));
        manager.putCash(new CashImpl(MoneyRUB.FIVE_HUNDRED, 7));
        manager.putCash(new CashImpl(MoneyRUB.ONE_HUNDRED, 3));

        Set<Cash> resultCashSet = manager.popCash(expIntResult);
        int resultCash = resultCashSet.stream().mapToInt(Cash::getTotal).sum();
        assertEquals(expIntResult, resultCash);

        expResult.add(new CashImpl(MoneyRUB.FIVE_HUNDRED, 6));
        expResult.add(new CashImpl(MoneyRUB.ONE_HUNDRED, 3));

        assertEquals(expResult.toString(), resultCashSet.toString());
    }
}
