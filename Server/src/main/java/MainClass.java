import io.grpc.Server;
import io.grpc.ServerBuilder;
import services.ChatService;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder
                .forPort(9090)
                .addService(new ChatService())
                .build();

        server.start();
        server.awaitTermination();
    }
}