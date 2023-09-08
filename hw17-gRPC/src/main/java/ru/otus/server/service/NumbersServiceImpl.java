package ru.otus.server.service;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.ClientRequest;
import ru.otus.NumbersServiceGrpc;
import ru.otus.ServerResponse;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NumbersServiceImpl extends NumbersServiceGrpc.NumbersServiceImplBase {
    public static final Logger logger = LoggerFactory.getLogger(NumbersServiceImpl.class);

    @Override
    public void getNumber(ClientRequest request, StreamObserver<ServerResponse> responseObserver) {
        var executor = Executors.newScheduledThreadPool(1);
        var beginValue = new AtomicInteger(request.getBeginValue());

        Runnable task = () -> {
            int value = beginValue.incrementAndGet();
            responseObserver.onNext(ServerResponse.newBuilder().setNumber(value).build());
            if (value == request.getEndValue()) {
                executor.shutdown();
                responseObserver.onCompleted();
                logger.info("Sending the sequence of numbers is completed.");
            }
        };

        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }
}
