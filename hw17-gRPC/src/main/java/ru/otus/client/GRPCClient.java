package ru.otus.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.ClientRequest;
import ru.otus.NumbersServiceGrpc;


public class GRPCClient {
    private static final Logger logger = LoggerFactory.getLogger(GRPCClient.class);
    private static final String HOST = "localhost";
    private static final int PORT = 8190;
    int value = 0;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext().build();

        var stub = NumbersServiceGrpc.newStub(channel);

        new GRPCClient().action(stub);

        logger.info("Client completed his work ");
    }

    private void action(NumbersServiceGrpc.NumbersServiceStub stub) {
        ClientStreamObserver clientStreamObserver = new ClientStreamObserver();
        stub.getNumber(clientRequest(), clientStreamObserver);
        for (int i = 0; i <= 50; i++) {
            logger.info("answer : {}", getNextValue(clientStreamObserver));
            sleep();
        }
    }

    private int getNextValue(ClientStreamObserver clientStreamObserver) {
        value = value + clientStreamObserver.getLastValueAndReset() + 1;
        return value;
    }

    private ClientRequest clientRequest() {
        return ClientRequest.newBuilder()
                .setBeginValue(0)
                .setEndValue(30)
                .build();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
