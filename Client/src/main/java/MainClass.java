import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.text.html.ListView;
import java.awt.*;

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
    }
}
