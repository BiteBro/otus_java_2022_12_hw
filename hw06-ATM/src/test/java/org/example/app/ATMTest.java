package org.example.app;

import org.example.controller.CashManager;
import org.example.model.CashStorage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    private CashManager manager;
    private ATM atm;
    public ATMTest() {
    }

    @BeforeEach
    void setUp() {
        this.manager = new CashManager(new CashStorage());
        this.atm = new ATM(manager);
    }

    @AfterEach
    void tearDown() {
        this.manager = null;
        this.atm = null;
    }

    @Test
    public void testPutMoneyOnBanknotes() {
        System.out.println("putMoneyOnBanknotes");
        int wrongNominal = 300;
        int billCount = 3;

        try {
            atm.putMoneyOnBanknotes(wrongNominal, billCount);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
        }

        int nominal = 500;
        int wrongBillCount = -3;

        try {
            atm.putMoneyOnBanknotes(nominal, wrongBillCount);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
        }
    }

}
