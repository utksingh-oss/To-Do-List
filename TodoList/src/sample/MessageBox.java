package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {
    public static String[] show()
    {
        Stage stage = new Stage();
        stage.setTitle("Add Details");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);

        TextArea textArea = new TextArea();
        textArea.setPromptText("Enter the description here! ");
        DatePicker datePicker = new DatePicker();
        Button okay= new Button("OK");

        VBox vBox = new VBox(textArea , datePicker , okay);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox , 300 , 200);

        String[] arr = new String[2];

        okay.setOnAction(e->{
            arr[0] = textArea.getText();
            arr[1] = datePicker.getValue().toString();
            stage.close();
        });

        stage.setScene(scene);
        stage.showAndWait();
        return arr;
    }

}
