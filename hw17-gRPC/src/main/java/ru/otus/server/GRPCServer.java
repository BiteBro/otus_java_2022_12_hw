package ru.otus.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.server.service.NumbersServiceImpl;

import java.io.IOException;

public class GRPCServer {

    private static final Logger logger = LoggerFactory.getLogger(GRPCServer.class);
    public static final int PORT = 8190;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT).addService(new NumbersServiceImpl()).build();

        server.start();
        logger.info("Server is started : \n");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.shutdown();
            logger.info("Server is shutdown...");
        }));

        server.awaitTermination();
    }
}
