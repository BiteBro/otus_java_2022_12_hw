package ru.otus.client;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.ServerResponse;

public class ClientStreamObserver implements StreamObserver<ServerResponse> {
    private static Logger logger = LoggerFactory.getLogger(ClientStreamObserver.class);
    private int value = 0;

    @Override
    public void onNext(ServerResponse value) {
        logger.info("server sent a value : {}", value.getNumber());
        setLastValue(value.getNumber());
    }

    @Override
    public void onError(Throwable t) {
        logger.info("Error!!!");
    }

    @Override
    public void onCompleted() {
        logger.info("completed");
    }

    public synchronized int getLastValueAndReset() {
        int lastValue = this.value;
        this.value = 0;
        return lastValue;
    }

    private synchronized void setLastValue(int value) {
        this.value = value;
    }
}
