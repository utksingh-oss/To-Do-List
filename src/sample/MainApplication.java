package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;



public class MainApplication {

    private static String backgroundColor = "white";
    private static String textFill = "black";

    public static void open(String userName, String passW) throws SQLException, ClassNotFoundException, FileNotFoundException {

        Stage stage = new Stage();
        stage.setTitle("To Do List");
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://findicons.com/files/icons/1061/sleek_xp_software/300/notepad.png"));

        //-----------------------Scene Setting--------------------------------------//

        Image image = new Image(new FileInputStream("C:\\Users\\Normie01\\Downloads\\Microsoft_Account.svg.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);

        int width1 = 120;
        Label settingLabel =new Label("Settings");
        settingLabel.setFont(Font.font("Abadi Extra Light" , 30));
        Button manageAccount = new Button("Manage Account");
        manageAccount.setMinWidth(width1);
        manageAccount.setStyle("-fx-background-radius : 0 ; -fx-background-color : #bcb5af ;");
        manageAccount.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                manageAccount.setStyle("-fx-background-radius : 0 ; -fx-background-color : #bcb5af ; -fx-border-color : "+textFill);
            }else{
                manageAccount.setStyle("-fx-background-radius : 0 ; -fx-background-color : #bcb5af ;");
            }
        });
        manageAccount.setOnAction(event -> {
            try {
                boolean close = Account.open(backgroundColor , textFill , passW , userName);
                if(close){
                    stage.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        Button signOut = new Button("Sign out");
        signOut.setStyle("-fx-background-color : #c43539 ; -fx-text-fill : white ; -fx-background-radius : 0");
        signOut.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                signOut.setStyle("-fx-background-color : #c43539 ; -fx-text-fill : white ; -fx-background-radius : 0 ; -fx-border-color : "+textFill);
            }else{
                signOut.setStyle("-fx-background-color : #c43539 ; -fx-text-fill : white ; -fx-background-radius : 0");
            }
        });
        signOut.setMinWidth(width1);
        VBox vBox1 = new VBox(manageAccount , signOut);
        vBox1.setSpacing(10);
        Label labelu = new Label(userName);
        labelu.setFont(Font.font("Abadi Extra Light" , 20));
        labelu.setPadding(new Insets(10));
        HBox hBox = new HBox(imageView , labelu , vBox1);
        hBox.setSpacing(100);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);

        Separator separator = new Separator();
        Separator separator1 = new Separator();

        //Theme
        ToggleGroup theme = new ToggleGroup();
        RadioButton light = new RadioButton("Light Theme");
        RadioButton dark  = new RadioButton("Dark Theme");
        Label themeLabel = new Label("Theme");
        themeLabel.setFont(Font.font("Abadi Extra Light" , 28));

        light.setToggleGroup(theme);
        dark.setToggleGroup(theme);
        light.setSelected(true);
        VBox vBoxToggle = new VBox(themeLabel,light , dark);
        vBoxToggle.setAlignment(Pos.CENTER);
        vBoxToggle.setSpacing(10);

        Label defaultListsLabel = new Label("Default Lists");
        defaultListsLabel.setFont(Font.font("Abadi Extra Light" , 20));

        VBox checkBox = new VBox();

        CheckBox importantCheck = new CheckBox("Important");
        importantCheck.setSelected(true);
        importantCheck.setMinWidth(100);
        CheckBox tasksCheck = new CheckBox("Tasks");
        tasksCheck.setSelected(true);
        tasksCheck.setMinWidth(100);
        CheckBox plannedCheck = new CheckBox("Planned");
        plannedCheck.setSelected(true);
        plannedCheck.setMinWidth(100);
;

        checkBox.getChildren().addAll(importantCheck , tasksCheck , plannedCheck);
        checkBox.setAlignment(Pos.CENTER);
        checkBox.setSpacing(10);

        VBox vBoxCheck = new VBox( defaultListsLabel ,checkBox);
        vBoxCheck.setAlignment(Pos.CENTER);
        vBoxCheck.setSpacing(10);
        Separator separator2 = new Separator();

        VBox completeVBox = new VBox(settingLabel , separator ,hBox ,separator1, vBoxToggle , separator2 , vBoxCheck);
        completeVBox.setAlignment(Pos.TOP_CENTER);
        completeVBox.setSpacing(20);
        completeVBox.setPadding(new Insets(20));
        completeVBox.setStyle("-fx-background-color : " + backgroundColor);



        //The Whole Program

        int width = 200;
        Button account = new Button(userName);
        account.setFont(Font.font("Helvetica",12));
        account.setMinWidth(width);


        Button myDay = new Button("    My Day");
        myDay.setMinWidth(width);
        myDay.setFont(Font.font("Helvetica",12));
        Button important = new Button("    Important");
        important.setFont(Font.font("Helvetica",12));
        important.setMinWidth(width);
        Button planned = new Button("     Planned");
        planned.setFont(Font.font("Helvetica",12));
        planned.setMinWidth(width);
        Button  tasks = new Button("    Tasks");
        tasks.setFont(Font.font("Helvetica",12));
        tasks.setMinWidth(width);


        Button createList = new Button("   New List");
        createList.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
        createList.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                createList.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                createList.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
            }
        });
        createList.setMinWidth(width);
        createList.setFont(Font.font("Helvetica",12));



        VBox vBox = new VBox();

        account.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
        vBox.setStyle("-fx-background-color :" + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0 " );
        myDay.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0" );
        important.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0" );
        planned.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
        tasks.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");

        account.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                account.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                account.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill+ ";-fx-background-radius : 0" );
            }
        });
        myDay.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                myDay.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                myDay.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill+ ";-fx-background-radius : 0" );
            }
        });
        important.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                important.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                important.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill+ ";-fx-background-radius : 0" );
            }
        });
        planned.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                planned.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                planned.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill+ ";-fx-background-radius : 0" );
            }
        });
        tasks.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                tasks.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                tasks.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
            }
        });



        vBox.setAlignment(Pos.TOP_CENTER);
        Label label = new Label("                          ");
        label.setVisible(false);

        vBox.getChildren().addAll(account,label, myDay , important , planned , tasks , separator , createList);
        vBox.setPadding(new Insets(20 ,0 , 20, 0));
        vBox.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background:" +backgroundColor+  "; -fx-border-color: "+ backgroundColor);




        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(scrollPane);

        account.setOnAction(e->borderPane.setCenter(completeVBox));
        dark.setOnAction(event -> {
            backgroundColor = "#2d2a28";
            textFill = "white";
            ObservableList<Node> list = vBox.getChildren();
            vBox.setStyle("-fx-background-color :" + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0 " );
            for(Node o : list){
                if(o.equals(separator1) || o.equals(separator)) continue;
                o.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
                o.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue){
                        o.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
                    }else{
                        o.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
                    }
                });
            }
            completeVBox.setStyle("-fx-background-color : " + backgroundColor);
            settingLabel.setStyle("-fx-text-fill :"+textFill);
            labelu.setStyle("-fx-text-fill :"+textFill);
            separator.setStyle("-fx-text-fill :"+textFill + ";-fx-background-color :" + textFill);
            separator1.setStyle("-fx-text-fill :"+textFill);
            light.setStyle("-fx-text-fill :"+textFill);
            dark.setStyle("-fx-text-fill :"+textFill);
            themeLabel.setStyle("-fx-text-fill :"+textFill);
            scrollPane.setStyle("-fx-background:" +backgroundColor +  "; -fx-border-color: "+ backgroundColor);
            defaultListsLabel.setStyle("-fx-text-fill :"+textFill);
            importantCheck.setStyle("-fx-text-fill :"+textFill);
            tasksCheck.setStyle("-fx-text-fill :"+textFill);
            plannedCheck.setStyle("-fx-text-fill :"+textFill);
        });

        light.setOnAction(event -> {
            backgroundColor = "white";
            textFill = "black";
            vBox.setStyle("-fx-background-color :" + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0 " );
            ObservableList<Node> list = vBox.getChildren();
            for(Node o : list){
                if(o.equals(separator1) || o.equals(separator)) continue;
                o.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
                o.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
                o.hoverProperty().addListener((observable, oldValue, newValue) -> {
                    if(newValue){
                        o.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
                    }else{
                        o.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
                    }
                });
            }
            completeVBox.setStyle("-fx-background-color : " + backgroundColor);
            settingLabel.setStyle("-fx-text-fill :"+textFill);
            labelu.setStyle("-fx-text-fill :"+textFill);
            separator.setStyle("-fx-text-fill :"+textFill);
            separator1.setStyle("-fx-text-fill :"+textFill);
            light.setStyle("-fx-text-fill :"+textFill);
            dark.setStyle("-fx-text-fill :"+textFill);
            themeLabel.setStyle("-fx-text-fill :"+textFill);
            scrollPane.setStyle("-fx-background:" +backgroundColor + "; -fx-border-color: "+ backgroundColor);
            defaultListsLabel.setStyle("-fx-text-fill :"+textFill);
            importantCheck.setStyle("-fx-text-fill :"+textFill);
            tasksCheck.setStyle("-fx-text-fill :"+textFill);
            plannedCheck.setStyle("-fx-text-fill :"+textFill);
        });


        BorderPane tasksPane = TodoList.createList("Tasks");
        tasks.setOnAction(event -> {
            borderPane.setCenter(tasksPane);
        });


        BorderPane myDayBorderPane = TodoList.createList("My Day");
        myDay.setOnAction(event -> {
            borderPane.setCenter(myDayBorderPane);
        });

        borderPane.setCenter(myDayBorderPane);

        BorderPane importantPane = TodoList.createList("Important");
        important.setOnAction(event -> {
            borderPane.setCenter(importantPane);
        });

        BorderPane plannedPane = TodoList.createList("Planned");
        planned.setOnAction(event -> {
            borderPane.setCenter(plannedPane);
        });

        Image img = new Image("http://cdn.onlinewebfonts.com/svg/download_532412.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        myDay.setGraphic(view);

        //https://cdn.pixabay.com/photo/2016/12/21/17/11/signe-1923369_960_720.png
        Image img1 = new Image("https://cdn.pixabay.com/photo/2016/12/21/17/11/signe-1923369_960_720.png");
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(20);
        view1.setPreserveRatio(true);
        createList.setGraphic(view1);

        Image img2 = new Image("https://maxcdn.icons8.com/Share/icon/Messaging/star1600.png");
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(20);
        view2.setPreserveRatio(true);
        important.setGraphic(view2);

        Image img3 = new Image("https://maxcdn.icons8.com/Share/icon/p1em/Very_Basic/home1600.png");
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(20);
        view3.setPreserveRatio(true);
        tasks.setGraphic(view3);

        Image img4 = new Image("https://maxcdn.icons8.com/Share/icon/p1em/Time_And_Date/calendar1600.png");
        ImageView view4 = new ImageView(img4);
        view4.setFitHeight(20);
        view4.setPreserveRatio(true);
        planned.setGraphic(view4);





        importantCheck.setOnAction(event -> {
            if(importantCheck.isSelected()){
                vBox.getChildren().add(3 , important);
            }else{
                vBox.getChildren().remove(important);
            }
        });
        tasksCheck.setOnAction(event -> {
            if(tasksCheck.isSelected()){
                if(vBox.getChildren().indexOf(planned)!=-1){
                    int index = vBox.getChildren().indexOf(planned);
                    vBox.getChildren().add(index , tasks);
                }else{
                    int index = vBox.getChildren().indexOf(separator);
                    vBox.getChildren().add(index , tasks);
                }
            }else{
                vBox.getChildren().remove(tasks);
            }
        });
        plannedCheck.setOnAction(event -> {
            if(plannedCheck.isSelected()){
                int index = vBox.getChildren().indexOf(separator);
                vBox.getChildren().add(index , planned);
            }else{
                vBox.getChildren().remove(planned);
            }
        });

        createList.setOnAction(e->newList(vBox , borderPane  ,tasksPane));

        Scene scene = new Scene(borderPane, 1200, 800);
        stage.setScene(scene);
        stage.show();

        signOut.setOnAction(event ->{
            stage.close();
        });
    }

    private static void newList(VBox vBox,BorderPane borderPane , BorderPane taskpane) {
        Button newList1 = new Button("Untitled List");
        newList1.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
        newList1.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
        newList1.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                newList1.setStyle("-fx-background-color : #e8ded7"+ ";-fx-background-radius : 0");
            }else{
                newList1.setStyle("-fx-background-color : " + backgroundColor + "; -fx-text-fill :" + textFill + ";-fx-background-radius : 0");
            }
        });
        newList1.setMinWidth(200);




        ContextMenu menu = new ContextMenu();
        MenuItem deleteList = new MenuItem("Delete List");
        MenuItem renameList = new MenuItem("Rename List");
        menu.getItems().addAll(deleteList , renameList);
        menu.setStyle("  -fx-control-inner-background : white ;");
        deleteList.setStyle("-fx-font-family :\"Helvetica\" ");
        renameList.setStyle("-fx-font-family :\"Helvetica\" ");

        newList1.setContextMenu(menu);Image img = new Image("https://cdn.icon-icons.com/icons2/916/PNG/512/Menu_icon_icon-icons.com_71858.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        newList1.setGraphic(view);
        newList1.setFont(Font.font("Helvetica",12));

        BorderPane bpane = TodoList.createList("Untitled List");
        newList1.setOnAction(event -> borderPane.setCenter(bpane));
        int index = vBox.getChildren().size()-1;
        vBox.getChildren().add(index , newList1);

        renameList.setOnAction(event -> {
            int index1 = vBox.getChildren().indexOf(newList1);
            vBox.getChildren().remove(index1);
            TextField textField = new TextField();
            vBox.getChildren().add(index1 , textField);
            textField.requestFocus();
            textField.setStyle("-fx-background-color : "+ backgroundColor + ";-fx-text-fill : "+textFill + "; -fx-border-color : "+backgroundColor+" "+backgroundColor+" "+
                    textFill+" "+backgroundColor+" ; -fx-background-radius : 0");
            if(!textField.isFocused()){
                String text = textField.getText();
                newList1.setText(text);
                vBox.getChildren().remove(textField);
                vBox.getChildren().add(index1,newList1);
            }
            textField.setOnKeyPressed( e -> {
                if( e.getCode() == KeyCode.ENTER ) {
                    String text = textField.getText();
                    newList1.setText(text);
                    TodoList.setTitle(text);
                    vBox.getChildren().remove(textField);
                    vBox.getChildren().add(index1,newList1);
                }
            } );
        });

        deleteList.setOnAction(event -> {
            vBox.getChildren().remove(newList1);
        });



    }


}
