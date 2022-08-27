package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
    static boolean answer;

    public static boolean askMessage(String title , String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setResizable(false);

        window.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Info_icon-72a7cf.svg/1024px-Info_icon-72a7cf.svg.png"));

        Label label = new Label();
        label.setText(message);
        label.setPadding(new Insets(20));

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        okButton.setPadding(new Insets(10));
        okButton.setMinWidth(75);

        cancelButton.setPadding(new Insets(10));
        cancelButton.setMinWidth(75);

        okButton.setOnAction(e->{
            answer = true;
            window.close();
        });

        cancelButton.setOnAction(e->{
            answer = false;
            window.close();
        });

        BorderPane buttonPane = new BorderPane();
        buttonPane.setLeft(cancelButton);
        buttonPane.setRight(okButton);
        buttonPane.setPadding(new Insets(10));

        VBox layout = new VBox(label , buttonPane);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);

        //=======================CSS STYLING===========================================

        label.setStyle("-fx-text-fill : black");
        layout.setStyle("-fx-background-color: white");
        cancelButton.setStyle("-fx-background-color : white ; -fx-background-radius : 80 ; -fx-text-fill : black");
        cancelButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                cancelButton.setStyle("-fx-background-color : #d2d8e0 ; -fx-background-radius : 80 ; -fx-text-fill : black");
            }else{
                cancelButton.setStyle("-fx-background-color : white ; -fx-background-radius : 80 ; -fx-text-fill : black");
            }
        });

        okButton.setStyle("-fx-background-color :  white ; -fx-background-radius : 80 ; -fx-text-fill : black");
        okButton.hoverProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue){
                okButton.setStyle("-fx-background-color :  #d2d8e0 ; -fx-background-radius : 80 ; -fx-text-fill : black");
            }else{
                okButton.setStyle("-fx-background-color :  white; -fx-background-radius : 80 ; -fx-text-fill : black");
            }
        });


        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static void displayMessage(String message , String title)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setResizable(false);
        window.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Info_icon-72a7cf.svg/1024px-Info_icon-72a7cf.svg.png"));


        Button okButton = new Button("OK");
        okButton.setPadding(new Insets(10));
        okButton.setMinWidth(50);
        okButton.setOnAction(e->window.close());

        Label text = new Label(message);

        VBox layout = new VBox(text , okButton);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(20);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout);

        //=========================CSS STYLING=======================================

        text.setStyle("-fx-text-fill : black");
        layout.setStyle("-fx-background-color: white");

        okButton.setStyle("-fx-background-color :  #2a5796 ; -fx-background-radius : 0; -fx-text-fill : white");
        okButton.hoverProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue){
                okButton.setStyle("-fx-background-color : #254e87 ; -fx-background-radius : 0 ; -fx-text-fill : white");
            }else{
                okButton.setStyle("-fx-background-color : #2a5796  ; -fx-background-radius : 0; -fx-text-fill : white");
            }
        });


        window.setScene(scene);
        window.showAndWait();

    }


}
