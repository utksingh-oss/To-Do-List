package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import java.util.Date;


public class TodoList {
    static String backgroundColor = "#34578e";
    static Label label;
//    public static  ListView<HBox> doneEvents;
//    public static  ListView<HBox> listView;
    public static BorderPane createList(String text)
    {


        label = new Label(text);
        label.setStyle("-fx-text-fill : white");
        label.setFont(Font.font("Abadi Extra Light" , 28));
        Label labelDate = new Label();
        labelDate.setStyle("-fx-text-fill : " + backgroundColor + " ; -fx-background-color : white");

        if(text.equals("My Day")){
            Date date = new Date();
            String dateString = date.toString();
            labelDate.setText(dateString);
        }

        MenuBar  menuBar = new MenuBar();
        Menu menu = new Menu("...");
        menu.setStyle("-fx-background-color : #63665d ; -fx-background-radius : 2 ;  -fx-control-inner-background : white");

        MenuItem red = new MenuItem("                   ");
        red.setStyle("-fx-background-color : red ; -fx-border-color : black ;");
        MenuItem blue = new MenuItem("                   ");
        blue.setStyle("-fx-background-color : blue ;-fx-border-color : black ;");
        MenuItem purple = new MenuItem("                   ");
        purple.setStyle("-fx-background-color : purple ;-fx-border-color : black;");
        MenuItem pink = new MenuItem("                   ");
        pink.setStyle("-fx-background-color : pink ; -fx-border-color : black  ;  -fx-control-inner-background : #63665d ");
        MenuItem green = new MenuItem("                   ");
        green.setStyle("-fx-background-color : green ; -fx-border-color : black ;  -fx-control-inner-background : #63665d ");

        menuBar.getMenus().add(menu);;
        menuBar.setStyle("-fx-background-color :" + backgroundColor);
        menu.getItems().addAll(red , blue , purple ,pink , green );
        BorderPane borderPane1 = new BorderPane();
        borderPane1.setLeft(label);
        borderPane1.setRight(menuBar);
        borderPane1.setPadding(new Insets(20));
        VBox heading = new VBox(borderPane1 , labelDate);


        ListView<HBox> listView = new ListView<>();
        listView.setPadding(new Insets(10));
        listView.setStyle("-fx-control-inner-background: " +backgroundColor+  " ; -fx-background-color : transparent");

        Label completedLabel = new Label("Completed");
        completedLabel.setStyle("-fx-background-color : white ; -fx-text-fill : #34578e ; -fx-background-radius : 5");
        completedLabel.setVisible(false);
        completedLabel.setPadding(new Insets(5));


        ListView<HBox> doneEvents = new ListView<>();
        doneEvents.setPadding(new Insets(10));
        doneEvents.setStyle("-fx-control-inner-background:" + backgroundColor + " ; -fx-background-color : transparent");

        VBox vBox = new VBox();
        vBox.getChildren().add(listView);
        vBox.getChildren().add(completedLabel);
        vBox.getChildren().add(doneEvents);
        doneEvents.setMaxHeight(0);


        TextField textField = new TextField();
        textField.setMinWidth(950);
        textField.setPromptText("Add a task");
        textField.setMinHeight(35);
        textField.setStyle("-fx-background-radius : 0 ; -fx-background-color : #302f2e ; -fx-text-fill : white ; -fx-prompt-text-fill: white");
        Button create = new Button("+");
        create.setMinWidth(50);
        create.setMinHeight(35);
        create.setStyle("-fx-background-radius : 0 ;-fx-background-color : #302f2e ; -fx-text-fill : white ;");
        create.setFont(Font.font(15));
        HBox hbox = new HBox(textField , create);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(heading);
        borderPane.setCenter(vBox);
        borderPane.setBottom(hbox);
        borderPane.setLayoutX(10);
        borderPane.setLayoutY(10);
        borderPane.setPadding(new Insets(3));
        borderPane.setStyle("-fx-background-color : " + backgroundColor);




        red.setOnAction(event -> {
            backgroundColor = "#c13f51";
            borderPane.setStyle("-fx-background-color : " + backgroundColor);
            menuBar.setStyle("-fx-background-color :" + backgroundColor);
            doneEvents.setStyle("-fx-control-inner-background:" + backgroundColor + " ; -fx-background-color : transparent");
            listView.setStyle("-fx-control-inner-background: " +backgroundColor + " ; -fx-background-color : transparent");
        });

        blue.setOnAction(event -> {
            backgroundColor = "#34578e";
            borderPane.setStyle("-fx-background-color : " + backgroundColor);
            menuBar.setStyle("-fx-background-color :" + backgroundColor);
            doneEvents.setStyle("-fx-control-inner-background:" + backgroundColor + " ; -fx-background-color : transparent");
            listView.setStyle("-fx-control-inner-background: " +backgroundColor + " ; -fx-background-color : transparent");
        });

        purple.setOnAction(event -> {
            backgroundColor = "#9e5add";
            borderPane.setStyle("-fx-background-color : " + backgroundColor);
            menuBar.setStyle("-fx-background-color :" + backgroundColor);
            doneEvents.setStyle("-fx-control-inner-background:" + backgroundColor + " ; -fx-background-color : transparent");
            listView.setStyle("-fx-control-inner-background: " +backgroundColor + " ; -fx-background-color : transparent");
        });

        pink.setOnAction(event -> {
            backgroundColor = "#e547ae";
            borderPane.setStyle("-fx-background-color : " + backgroundColor);
            menuBar.setStyle("-fx-background-color :" + backgroundColor);
            doneEvents.setStyle("-fx-control-inner-background:" + backgroundColor + " ; -fx-background-color : transparent");
            listView.setStyle("-fx-control-inner-background: " +backgroundColor + " ; -fx-background-color : transparent");
        });

        green.setOnAction(event -> {
            backgroundColor = "#4ca035";
            borderPane.setStyle("-fx-background-color : " + backgroundColor);
            menuBar.setStyle("-fx-background-color :" + backgroundColor);
            doneEvents.setStyle("-fx-control-inner-background:" + backgroundColor + " ; -fx-background-color : transparent");
            listView.setStyle("-fx-control-inner-background: " +backgroundColor +  " ; -fx-background-color : transparent");
        });

        create.setOnAction(event -> createFunction(textField , listView , doneEvents , completedLabel, borderPane));
        textField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                createFunction(textField , listView , doneEvents , completedLabel, borderPane);
            }
        });

        return borderPane;

    }




    private static void createFunction(TextField textField, ListView<HBox> listView, ListView<HBox> doneEvents , Label completedLabel , BorderPane borderPane) {
        if(textField.getText().strip().equals("")) return;
        HBox hBox = new HBox();
        Text text = new Text(textField.getText());
        text.setFont(Font.font("Helvetica" , FontPosture.REGULAR , 16 ));
        text.setFill(Color.WHITE);


        VBox vBox2 = new VBox();
        vBox2.getChildren().add(text);
        Text text1 = new Text();
        text1.setText("");
        text1.setFill(Color.WHITE);
        vBox2.getChildren().add(text1);
        text1.setFont(Font.font(10));


        RadioButton radioButton = new RadioButton();
        hBox.getChildren().add(radioButton);
        hBox.getChildren().add(vBox2);
        hBox.setSpacing(5);
        hBox.setStyle("-fx-background-color : #899968 ; -fx-border-fill: black");
        hBox.setPadding(new Insets(10));
        listView.getItems().add(hBox);
        radioButton.setOnAction(e->{
            if(radioButton.isSelected()){
                listView.getItems().remove(hBox);
                doneEvents.getItems().add(hBox);
                text.setStrikethrough(true);
                if(doneEvents.getItems().size()!=0){
                    completedLabel.setVisible(true);
                    doneEvents.setMaxHeight(200);
                }
            }else{
                doneEvents.getItems().remove(hBox);
                listView.getItems().add(hBox);
                text.setStrikethrough(false);
                if(doneEvents.getItems().size()==0){
                    completedLabel.setVisible(false);
                }
            }
        });

        textField.clear();
        Button details = new Button("Add/View Details");
        Button dueDate = new Button("Add Due Date");
        Button deleteTask = new Button("Delete Task");
        Button close = new Button("  X  ");
        close.setFont(Font.font(15));
        close.setPadding(new Insets(3));
        details.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 0 ; -fx-border-color : white");
        details.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                details.setStyle("-fx-background-color : white ; -fx-text-fill:#302f2e; -fx-background-radius: 0 ; -fx-border-color : white");
            }else{
                details.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 0 ; -fx-border-color : white");
            }
        });

        dueDate.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 0 ;-fx-border-color : white");
        dueDate.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                dueDate.setStyle("-fx-background-color : white ; -fx-text-fill: #302f2e ; -fx-background-radius: 0 ; -fx-border-color : white");
            }else{
                dueDate.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 0 ; -fx-border-color : white");
            }
        });

        deleteTask.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 0 ;-fx-border-color : white");
        deleteTask.setMinWidth(175);
        deleteTask.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                deleteTask.setStyle("-fx-background-color : white ; -fx-text-fill:#302f2e ; -fx-background-radius: 0 ; -fx-border-color : white");
            }else{
                deleteTask.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 0 ;  -fx-border-color : white");
            }
        });


        Tasks tasks = new Tasks();
        tasks.setTask(textField.getText());

        int minWidth = 175;
        details.setMinWidth(minWidth);
        dueDate.setMinWidth(minWidth);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(details);
        vBox.getChildren().add(dueDate);
        vBox.getChildren().add(deleteTask);

        details.setPadding(new Insets(10));
        dueDate.setPadding(new Insets(10));
        deleteTask.setPadding(new Insets(10));

        vBox.setPadding(new Insets(10));
        vBox.setStyle("-fx-background-color : #302f2e ; -fx-border-color : white");
        final boolean[] visible = {false};

        hBox.setOnMouseClicked(event -> {
            borderPane.getChildren().remove(borderPane.getRight());
            if(event.getButton() == MouseButton.PRIMARY){
                if(!visible[0]){
                    borderPane.setRight(vBox);
                    visible[0] = true;
                }else{
                    borderPane.getChildren().remove(vBox);
                    visible[0] = false;
                }
            }
        });
        details.setOnAction(event -> {
            int index = vBox.getChildren().indexOf(details);
            TextArea textArea = new TextArea();
            textArea.setMaxWidth(175);
            textArea.setStyle("-fx-control-inner-background: #302f2e ; -fx-text-fill : white; -fx-background-radius : 0 ");
            ObservableList observableList =  vBox.getChildren();
            for(Object o : observableList){
                if(o.getClass().equals(textArea.getClass())){
                    String string = ((TextArea) o).getText();
                    vBox.getChildren().remove(o);
                    tasks.setTaskDescription(string);
                    return;
                }
            }
            vBox.getChildren().add(index + 1 , textArea);
            textArea.setText(tasks.getTaskDescription());
            textArea.setWrapText(true);

        });

        dueDate.setOnAction(event -> {
            int index = vBox.getChildren().indexOf(dueDate);
            DatePicker datePicker = new DatePicker();
            datePicker.setPromptText(tasks.getDueDate());
            ObservableList observableList =  vBox.getChildren();
            for(Object o : observableList){
                if(o.getClass().equals(datePicker.getClass())){
                    if(((DatePicker) o).getValue()!=null) {
                        String string = ((DatePicker) o).getValue().toString();
                        tasks.setDueDate(string);
                        text1.setText("Due Date : " + tasks.getDueDate());
                    }
                    vBox.getChildren().remove(o);
                    return;
                }
            }
            vBox.getChildren().add(index + 1 , datePicker);
        });

        for(int i = 0 ; i < 10; i++){
            Button button = new Button();
            button.setStyle("-fx-background-color : #302f2e");
            vBox.getChildren().add(button);
        }
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(close);
        close.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 100 ; ");
        close.setPadding(new Insets(10));
        close.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                close.setStyle("-fx-background-color : white ; -fx-text-fill: #302f2e ; -fx-background-radius: 100;");
            }else{
                close.setStyle("-fx-background-color : #302f2e ; -fx-text-fill: white ; -fx-background-radius: 100 ;");
            }
        });

        close.setOnAction(event -> {
            borderPane.getChildren().remove(vBox);
        });

        deleteTask.setOnAction(event -> {
            listView.getItems().remove(hBox);
            doneEvents.getItems().remove(hBox);
            borderPane.getChildren().remove(vBox);
        });


    }

    public static void setTitle(String text){
        label.setText(text);
    }

}
