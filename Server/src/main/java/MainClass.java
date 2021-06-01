import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder
                .forPort(9090)
                .build();

        server.start();
        server.awaitTermination();
    }
}