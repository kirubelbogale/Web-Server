package ca.cmpt213.asn5.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client extends Application {

    public static void main(String[] args)
    {
        //Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Name: ");
        nameLabel.setPrefWidth(100);
        nameLabel.setPadding(new Insets(0, 0, 0, 30));
        Label strengthLabel = new Label("Strength: ");
        strengthLabel.setPadding(new Insets(0, 0, 0, 30));
        TextField nameField = new TextField();
        TextField strengthField = new TextField();
        Button submit = new Button("POST Req");
        Button submit2 = new Button("GET req");
        GridPane gridpane = new GridPane();
        gridpane.add(nameLabel, 0, 0);
        gridpane.add(strengthLabel, 0, 1);
        gridpane.add(nameField, 1, 0);
        gridpane.add(strengthField, 1, 1);
        gridpane.add(submit, 1, 2);
        gridpane.add(submit2, 1, 3);

        submit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    URL url = new URL("http://localhost:8080/tokimon");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    System.out.println(connection.getResponseCode());
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }

                    connection.disconnect();
                } catch (IOException e) {

                }
            }
        });

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    URL url = new URL("http://localhost:8080/tokimon");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    // get textbox values

                    // add them in request body...
                    wr.write("{\"name\":\"bobby\",\"strength\":75}");

                    wr.flush();
                    wr.close();
                    connection.connect();
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();
                } catch (IOException e) {

                }

            }
        });

        Scene scene = new Scene(gridpane, 300, 100); // (parent, horizontal, vertical)

        primaryStage.setScene(scene);
        primaryStage.setTitle("New Tokimon");
        primaryStage.show();
    }
}
