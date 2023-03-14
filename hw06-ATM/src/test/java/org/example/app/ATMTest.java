package org.example.app;

import org.example.controller.CashManager;
import org.example.model.CashStorage;
import org.example.model.Money;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    private CashManager manager;
    private ATMImpl atm;
    public ATMTest() {
    }

    @BeforeEach
    void setUp() {
        this.manager = new CashManager(new CashStorage());
        this.atm = new ATMImpl(manager);
    }

    @AfterEach
    void tearDown() {
        this.manager = null;
        this.atm = null;
    }

    @Test
    public void testPutMoneyOnBanknotes() {
        System.out.println("putMoneyOnBanknotes");
        Money nominal = Money.FIVE_HUNDRED;
        int wrongBillCount = -3;

        try {
            atm.putMoneyOnBanknotes(nominal, wrongBillCount);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
        }
    }

}
