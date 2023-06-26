package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private String direction = "up";
    private String flag = "second";
    private int count;

    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        Thread t1 = new Thread(() -> m.go("first"));
        Thread t2 = new Thread(() -> m.go("second"));
        t1.setName("First ");
        t2.setName("Second");

        t2.start();
        t1.start();

        t1.join();
        t2.join();
    }

    private synchronized void go(String threadName) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (threadName.equals(flag)) {
                    this.wait();
                }
                if (flag.equals("second")) {
                    if (direction.equals("up")) {
                        if (count >= 10) {
                            direction = "down";
                            continue;
                        }
                        count++;
                    }
                    if (direction.equals("down")) {
                        count--;
                        if (count <= 1) {
                            direction = "up";
                        }
                    }
                }
                flag = threadName;
                logger.info("Thread {}, count: {}", Thread.currentThread().getName(), count);
                sleep();
                notifyAll();
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}