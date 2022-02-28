package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ListView<BorderPane> listView = new ListView<>();
        Label label = new Label("To Do List");
        listView.setPrefWidth(400);
        listView.setStyle("-fx-control-inner-background : green");

        TextField textField = new TextField();
        textField.setMinWidth(350);
        textField.setPromptText("Enter the task here");
        Button button = new Button("create");


        HBox hBox1 = new HBox(listView);


        HBox hBox = new HBox(textField, button);


        DatePicker datePicker = new DatePicker();
        datePicker.setStyle(" -fx-control-inner-text :white ; -fx-background-radius : 10");


        VBox vBox = new VBox(label,hBox1,datePicker,hBox);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox , 400 , 300);
        button.setOnAction(e->createEvent(textField , listView , hBox1 ));

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void createEvent(TextField textField, ListView<BorderPane> listView , HBox hBox1) {
        RadioButton radioButton = new RadioButton();
        Label label = new Label(textField.getText());

        TextArea textArea = new TextArea();
        textArea.setPromptText("HELLO");
        textArea.setMaxWidth(30);
        label.setStyle("-fx-background-color : lightgreen ; -fx-text-fill : darkGreen");

        boolean[] added = {false};
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if(button==MouseButton.PRIMARY) {
                    if(!added[0]){
                        hBox1.getChildren().add(textArea);
                        added[0] = true;
                    }else{
                        hBox1.getChildren().remove(textArea);
                        added[0] = false;
                    }

                }
            }
        });


        ContextMenu menu = new ContextMenu();
        MenuItem editEvent = new MenuItem("Edit Event");
        MenuItem dueDate = new MenuItem("Add A Due Date");
        label.setContextMenu(menu);

        menu.getItems().addAll(editEvent , dueDate);

        HBox hBox = new HBox(radioButton , label);
        hBox.setSpacing(5);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(hBox);
        borderPane.setStyle("-fx-background-color : lightgreen ; -fx-background-radius : 10");
        borderPane.setPadding(new Insets(5));

        listView.getItems().add(borderPane);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
