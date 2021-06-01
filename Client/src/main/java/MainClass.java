import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import protobuf.ChatServiceGrpc;
import protobuf.ChatServiceOuterClass;

public class MainClass extends Application {
    private final ObservableList<String> messages = FXCollections.observableArrayList();
    private final ListView<String> messagesView = new ListView<>();
    private final TextField name = new TextField("name");
    private final TextField message = new TextField();
    private final Button send = new Button();
    private final ObservableList<String> loggers = FXCollections.observableArrayList();
    private final ListView<String> loggersView = new ListView<>();

    public static void main(String[] args) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        messagesView.setItems(messages);

        send.setText("Send");

        BorderPane pane = new BorderPane();
        pane.setLeft(name);
        pane.setCenter(message);
        pane.setRight(send);

        loggersView.setItems(loggers);

        BorderPane root = new BorderPane();
        root.setCenter(messagesView);
        root.setBottom(pane);
        root.setLeft(loggersView);

        primaryStage.setTitle("gRPC Chat");
        primaryStage.setScene(new Scene(root, 720, 600));

        primaryStage.show();

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        ChatServiceGrpc.ChatServiceStub chatService = ChatServiceGrpc.newStub(channel);

        StreamObserver<ChatServiceOuterClass.ChatMessage> chat = chatService.chat(new StreamObserver<ChatServiceOuterClass.ChatMessageFromServer>() {
            @Override
            public void onNext(ChatServiceOuterClass.ChatMessageFromServer value) {
                Platform.runLater(() -> {
                    if (value.getMessage().getMessage().equals("disconnected") || value.getMessage().getMessage().equals("new user connected")) {
                        loggers.add(value.getMessage().getFrom() + " " + value.getMessage().getMessage());
                        loggersView.scrollTo(loggers.size());
                    }
                    else {
                        messages.add(value.getMessage().getFrom() + ": " + value.getMessage().getMessage());
                        messagesView.scrollTo(messages.size());
                    }
                });
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
