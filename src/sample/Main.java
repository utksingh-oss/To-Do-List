package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.sql.*;

public class Main extends Application {
    Button signUp;
    Button logIn;
    Stage stage;

    String uName;
    String password;
    String emailID ;
    String fullName;

    String url = "jdbc:mysql://localhost:3306/abc";
    String uname = "root";
    String pass = "858684Arsh123!";


    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("TO-DO List");
        stage.setResizable(false);



        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url , uname , pass);
        Statement st = con.createStatement();

        //==============Scene 1======================

        signUp = new Button("Sign Up");
        signUp.setMinWidth(100);
        signUp.setPadding(new Insets(10));

        logIn = new Button("LogIn");
        logIn.setMinWidth(100);
        logIn.setPadding(new Insets(10));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(signUp, logIn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        Scene scene1 = new Scene(borderPane, 400, 400);

        stage.setScene(scene1);
        stage.getIcons().add(new Image("https://findicons.com/files/icons/1061/sleek_xp_software/300/notepad.png"));
        stage.show();

        //==============Scene 2==========================

        //For name
        TextField fname = new TextField();
        TextField lname = new TextField();
        fname.setPromptText("First Name");
        lname.setPromptText("Last Name");
        fname.setPadding(new Insets(10));
        lname.setPadding(new Insets(10));
        fname.setMinWidth(170);
        lname.setMinWidth(170);

        HBox nameBox = new HBox(fname, lname);
        nameBox.setSpacing(5);
        //nameBox.setAllignment(Pos.CENTER);
        nameBox.setPadding(new Insets(2));
        nameBox.setMaxWidth(350);

        //For email
        TextField email = new TextField();
        email.setPromptText("Email address");
        email.setPadding(new Insets(10));
        email.setMaxWidth(350);

        TextField userName = new TextField();
        userName.setPromptText("Username");
        userName.setPadding(new Insets(10));
        userName.setMaxWidth(350);


        //Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPadding(new Insets(10));
        passwordField.setMaxWidth(350);

        //Confirm Password
        PasswordField confirmPass = new PasswordField();
        confirmPass.setPromptText("Confirm Password");
        confirmPass.setPadding(new Insets(10));
        confirmPass.setMaxWidth(350);

        //Back and Next Button
        Button back = new Button("Back");
        back.setPadding(new Insets(15));
        back.setMinWidth(75);
        Button next = new Button("Next");
        next.setPadding(new Insets(15));
        next.setMinWidth(75);

        back.setOnAction(e -> stage.setScene(scene1));

        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(back);
        bottomPane.setRight(next);
        bottomPane.setPadding(new Insets(20));

        Label errorLabel = new Label();

        VBox vBox2 = new VBox(nameBox, email, userName, passwordField, confirmPass, errorLabel);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(10);

        BorderPane bPane = new BorderPane();
        bPane.setCenter(vBox2);
        bPane.setBottom(bottomPane);
        bPane.setPadding(new Insets(15));


        Scene scene2 = new Scene(bPane, 400, 400);



        //=========Scene 3=======================

        ComboBox questions = new ComboBox<>();
        questions.getItems().addAll("What is the name of your favourite movie?" ,"What is the name of your home town?",
        "Which is you favourite novel?");
        questions.setMaxWidth(300);
        questions.setPromptText("Select a security question!");

        TextField answer = new TextField();
        answer.setMaxWidth(300);

        Label errorLabel2 = new Label();


        Button createAccount = new Button("Create Account");
        createAccount.setPadding(new Insets(10));
        createAccount.setMinWidth(50);

        createAccount.setOnAction(e -> {
            try {
                createAccount(scene1, questions, answer , errorLabel2 , st);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        VBox otpBox = new VBox(questions, answer ,errorLabel2,createAccount);
        otpBox.setAlignment(Pos.CENTER);
        otpBox.setPadding(new Insets(20));
        otpBox.setSpacing(10);

        BorderPane otpPane = new BorderPane();
        otpPane.setCenter(otpBox);

        Scene scene3 = new Scene(otpPane, 400, 400);

        signUp.setOnAction(e->stage.setScene(scene2));
        next.setOnAction(e -> {
            try {
                confirmationScreen(fname, lname, email, userName, passwordField, confirmPass, errorLabel , scene3 , st);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        //CSS Styling

        questions.setStyle("-fx-background-color : white ; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
        questions.setMinHeight(30);
        answer.setStyle("-fx-background-color : white ; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
        answer.setMinHeight(30);
        otpBox.setStyle("-fx-background-color : white ;");
        createAccount.setStyle("-fx-background-color : #2a5796 ; -fx-background-radius : 0; -fx-text-fill : white ");
        createAccount.hoverProperty().addListener(((observable, oldValue, newValue) ->{
            if(newValue){
                createAccount.setStyle("-fx-background-color :#1f4170; -fx-background-radius : 0 ; -fx-text-fill : white");
            }else{
                createAccount.setStyle("-fx-background-color : #2a5796 ; -fx-background-radius : 0 ; -fx-text-fill : white");
            }

        }));

        //===================================================SCENE 4 login screen====================================================
        Label labelLogin = new Label("Login");
        labelLogin.setFont(Font.font("Calibri (Body)", 50));
        TextField userName2 = new TextField();
        userName2.setMaxWidth(350);
        PasswordField password2 = new PasswordField();
        password2.setMaxWidth(350);

        Label errorLabel3 = new Label();

        userName2.setPromptText("USERNAME / EMAIL ID");
        password2.setPromptText("PASSWORD");

        int minWidth = 100;
        Button enter = new Button("Enter");
        enter.setMinWidth(minWidth);
        enter.setPadding(new Insets(10));
        Button goBack = new Button("Back");
        goBack.setPadding(new Insets(10));
        goBack.setMinWidth(minWidth);
        Button forgot = new Button("Forgot Password");
        forgot.setPadding(new Insets(10));
        forgot.setMinWidth(minWidth);

        VBox vBox3 = new VBox(labelLogin , userName2 , password2 ,errorLabel3, enter);
        vBox3.setSpacing(10);
        vBox3.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        HBox hBox = new HBox(goBack , forgot);
        hBox.setSpacing(140);
        hBox.setPadding(new Insets(20));

        BorderPane borderPane2 = new BorderPane();
        borderPane2.setCenter(vBox3);
        borderPane2.setBottom(hBox);

        Scene scene4 = new Scene(borderPane2 , 400 , 400);

        goBack.setOnAction(e->stage.setScene(scene1));
        enter.setOnAction(e-> {
            try {
                LogIn(userName2 , password2 ,st , errorLabel3);
            } catch (SQLException | ClassNotFoundException | FileNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        //============================================CSS STYLING=======================================================


        signUp.setStyle("-fx-background-color : #2a5796 ; -fx-background-radius : 0; -fx-text-fill : white ");
        logIn.setStyle("-fx-background-color : #2a5796 ; -fx-background-radius :  0 ; -fx-text-fill : white ");
        vBox.setStyle("-fx-background-color : white");

        signUp.hoverProperty().addListener(((observable, oldValue, newValue) ->{
            if(newValue){
                signUp.setStyle("-fx-background-color :#1f4170; -fx-background-radius : 0 ; -fx-text-fill : white");
            }else{
                signUp.setStyle("-fx-background-color : #2a5796 ; -fx-background-radius : 0 ; -fx-text-fill : white");
            }

        }));

        logIn.hoverProperty().addListener((((observable, oldValue, newValue) -> {
            if(newValue){
                logIn.setStyle("-fx-background-color : #1f4170; -fx-background-radius : 0 ; -fx-text-fill : white");
            }else{
                logIn.setStyle("-fx-background-color :  #2a5796 ; -fx-background-radius : 0 ; -fx-text-fill : white");
            }
        })));


       fname.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       lname.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       email.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       userName.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       passwordField.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       confirmPass.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       bPane.setStyle("-fx-background-color : white ; ");

       Image img1 = new Image("https://cdn3.iconfinder.com/data/icons/glyph/227/Button-Next-512.png");
       ImageView view1= new ImageView(img1);
       view1.setFitHeight(30);
       view1.setPreserveRatio(true);


        Image img2 = new Image("https://cdn3.iconfinder.com/data/icons/glyph/227/Button-Back-2-512.png");
        ImageView view2= new ImageView(img2);
        view2.setFitHeight(30);
        view2.setPreserveRatio(true);

       back.setStyle("-fx-background-color : white ; -fx-background-radius : 100 ; -fx-text-fill : black");
       back.setPadding(new Insets(5));
       back.setMinWidth(0);
       back.setText("");
       back.setGraphic(view2);


       back.hoverProperty().addListener(((observable, oldValue, newValue) ->{
           if(newValue){
               back.setStyle("-fx-background-color :#99a3aa ; -fx-background-radius : 100 ; -fx-text-fill : white");
           }else{
               back.setStyle("-fx-background-color : white ; -fx-background-radius : 100 ; -fx-text-fill : white");
           }

       }));



       next.setStyle("-fx-background-color : white; -fx-background-radius : 100 ; -fx-text-fill : black");
       next.setGraphic(view1);
       next.setMinWidth(0);
       next.setPadding(new Insets(5));
       next.setText("");

       next.hoverProperty().addListener((((observable, oldValue, newValue) -> {
           if(newValue){
               next.setStyle("-fx-background-color :  #99a3aa ; -fx-background-radius : 100 ; -fx-text-fill : white");
           }else{
               next.setStyle("-fx-background-color : white ; -fx-background-radius : 100 ; -fx-text-fill : white");
           }
       })));


       labelLogin.setStyle("-fx-text-fill : black");
       userName2.setPadding(new Insets(10));
       userName2.setStyle("-fx-background-color : white ; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       password2.setStyle("-fx-background-color : white ; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
       password2.setPadding(new Insets(10));
       errorLabel.setStyle("-fx-text-fill : black");
       enter.setStyle("-fx-background-color : #2a5796 ; -fx-text-fill : white ; -fx-background-radius : 0");
       goBack.setStyle("-fx-background-color :  white ; -fx-background-radius : 0; -fx-text-fill : #2a5796");
       forgot.setStyle("-fx-background-color :  white ; -fx-background-radius : 0 ; -fx-text-fill : #2a5796");
       borderPane2.setStyle("-fx-background-color : white");


       forgot.hoverProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue){
               forgot.setStyle("-fx-background-color :  white  ; -fx-background-radius : 0 ; -fx-text-fill : black");
           }else{
               forgot.setStyle("-fx-background-color :  white   ; -fx-background-radius : 0 ; -fx-text-fill : #2a5796");
           }
       });

       enter.hoverProperty().addListener((observable, oldValue, newValue) ->{
           if(newValue){
               enter.setStyle("-fx-background-color :  #254e87 ; -fx-background-radius : 0 ; -fx-text-fill : white");
           }else{
               enter.setStyle("-fx-background-color :  #2a5796  ; -fx-background-radius : 0 ; -fx-text-fill : white");
           }
        });

        goBack.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                goBack.setStyle("-fx-background-color :white ; -fx-background-radius : 0 ; -fx-text-fill : black");
            }else{
                goBack.setStyle("-fx-background-color :white ; -fx-background-radius : 0 ; -fx-text-fill : #2a5796");
            }
        });



        errorLabel2.setStyle("-fx-text-fill : black");



        //============================================FORGOT PASSWORD===================================================

        int width = 300;
        TextField textField1 = new TextField();
        textField1.setPromptText("Enter the username / email-id");
        textField1. setMaxWidth(width);

        ComboBox questions1 = new ComboBox<>();
        questions1.getItems().addAll("What is the name of your favourite movie?" ,"What is the name of your home town?",
                "Which is you favourite novel?");
        questions1.setPromptText("Select a security question");
        questions1.setPrefWidth(width);

        Label errorLabel4 = new Label();

        Label forgotLabel = new Label("Forgot Passoword");
        forgotLabel.setFont(Font.font("Calibri (Body)", 40));

        TextField answer1 = new TextField();
        answer1.setPromptText("Answer the security question");
        answer1.setMaxWidth(width);

        VBox newVBox = new VBox(forgotLabel , textField1 , questions1 , answer1 , errorLabel4);
        newVBox.setAlignment(Pos.CENTER);
        newVBox.setSpacing(10);
        newVBox.setPadding(new Insets(10));


        Button back1 = new Button("");
        Button next1 = new Button("Next");
        back1.setPadding(new Insets(10));
        Image img = new Image("https://www.searchpng.com/wp-content/uploads/2019/02/Back-Arrow-Icon-PNG-1024x1024.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(30);
        view.setPreserveRatio(true);
        back1.setGraphic(view);
        next1.setPadding(new Insets(10));

        HBox newHBox = new HBox(back1 , next1);
        newHBox.setSpacing(240);
        newHBox.setPadding(new Insets(10));

        int buttonWidth = 90;

        back1.setMinWidth(20);
        next1.setMinWidth(buttonWidth);

        BorderPane borderPane1 = new BorderPane();
        borderPane1.setCenter(newVBox);
        borderPane1.setBottom(newHBox);



        Scene forgotPasswordScene = new Scene(borderPane1 , 400 , 400);


        logIn.setOnAction(e->stage.setScene(scene4));
        forgot.setOnAction(e->stage.setScene(forgotPasswordScene));
        back1.setOnAction(e->stage.setScene(scene4));
        next1.setOnAction(e-> {
            try {
                showPassword(questions1, answer1 , textField1 , st ,errorLabel4 , scene4);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        //CSS STYLING
        textField1.setStyle("-fx-background-color :white ; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
        textField1.setMinHeight(30);
        answer1.setStyle("-fx-background-color : white; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
        answer1.setMinHeight(30);
        questions1.setStyle("-fx-background-color : white ; -fx-text-fill : black ; -fx-background-radius : 0 ; -fx-border-color : white white black white");
        questions1.setMinHeight(30);

        borderPane1.setStyle("-fx-background-color : white");

        back1.setStyle("-fx-background-color : white ; -fx-background-radius : 50 ; -fx-text-fill : black");
        back1.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                back1.setStyle("-fx-background-color : #d2d8e0; -fx-background-radius : 50; -fx-text-fill : white");
            }else{
                back1.setStyle("-fx-background-color : white ; -fx-background-radius : 0; -fx-text-fill : black");
            }
        });

        next1.setStyle("-fx-background-color :  white ; -fx-background-radius :0 ; -fx-text-fill :blue");
        next1.hoverProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue){
                next1.setStyle("-fx-background-color :  white ; -fx-background-radius : 0; -fx-text-fill : black");
            }else{
                next1.setStyle("-fx-background-color :  white ; -fx-background-radius : 0 ; -fx-text-fill :  #2a5796");
            }
        });



    }

    private void showPassword(ComboBox questions, TextField answer1, TextField textField1, Statement st, Label errorLabel4, Scene scene4) throws SQLException {
        String query = "Select username , pass , question1 , question2 , question3 FROM users";
        ResultSet rs = st.executeQuery(query);
        boolean correct = false;
        String password = null ;

        while (rs.next()) {
            String username = rs.getString("username");
            if (textField1.getText().equals(username)){
                if(questions.getValue().equals("What is the name of your favourite movie?")){
                    String answer = rs.getString("question1");
                    if(answer.equals(answer1.getText().strip())){
                        correct = true;
                        password = rs.getString("pass");
                    }
                }
                else if(questions.getValue().equals("What is the name of your home town?")){
                    String answer = rs.getString("question2");
                    if(answer.equals(answer1.getText().strip())){
                        correct = true;
                        password = rs.getString("pass");
                    }
                }
                else if(questions.getValue().equals("Which is you favourite novel?")){
                    String answer = rs.getString("question3");
                    if(answer.equals(answer1.getText().strip())){
                        correct = true;
                        password = rs.getString("pass");
                    }
                }
            }
        }

        if(correct){
            answer1.clear();
            textField1.clear();
            MessageBox.displayMessage("Your Password is "+password ,"Password" );
            stage.setScene(scene4);
        }else{
            errorLabel4.setText("Check your answer or account name and try again");
            return;
        }
    }

    public void confirmationScreen(TextField fname,TextField lname,TextField email, TextField userName, PasswordField passwordField, PasswordField confirmPass,Label errorLabel , Scene scene ,Statement st) throws SQLException {
        if(fname.getText().strip().equals("") || lname.getText().strip().equals("")|| email.getText().strip().equals("") || userName.getText().strip().equals("")||
        passwordField.getText().strip().equals("")||confirmPass.getText().strip().equals("")){
            errorLabel.setText("All the Fields are required");
            errorLabel.setAlignment(Pos.CENTER);
            return;
        }
        if(passwordField.getText().length() < 8 || passwordField.getText().length() > 30){
            errorLabel.setText("Password length should be in between 8 to 30 characters!");
            errorLabel.setAlignment(Pos.CENTER);
            return;
        }

        if(!passwordField.getText().equals(confirmPass.getText())){
            errorLabel.setText("Password and Confirm Password do not match!");
            errorLabel.setAlignment(Pos.CENTER);
            return;
        }

        String query = "Select username FROM users";
        ResultSet rs = st.executeQuery(query);

        boolean found = false;

        while(rs.next())
        {
            String userN = rs.getString("username");
            if(userName.getText().equals(userN)){
                found = true;
                break;
            }
        }
        if(found){
            errorLabel.setText("An account with this username already exists!");
            errorLabel.setAlignment(Pos.CENTER);
            return;
        }

        boolean found2 = false;

        query = "Select emailId From users";
        rs = st.executeQuery(query);
        while(rs.next()){
            String emailID = rs.getString("emailId");
            if(email.getText().equals(emailID)){
                found2 = true;
                break;
            }
        }
        if(found2){
            errorLabel.setText("An account is already registered to this email id");
            errorLabel.setAlignment(Pos.CENTER);
            return;
        }



        boolean present = false;
        emailID = email.getText();
        for(int i = 0 ; i < emailID.length() ; i++)
        {
            if(emailID.charAt(i) == '@') present = true;
        }

        if(!present){
            errorLabel.setText("Invalid Email Address!");
            errorLabel.setAlignment(Pos.CENTER);
            return;
        }



        String message = "Confirm " + email.getText() + " ?";
        boolean ans = MessageBox.askMessage("Confirm EmailID" , message);

        uName = userName.getText();
        password = passwordField.getText();
        fullName = fname.getText() + lname.getText();

        if(ans){
            stage.setScene(scene);
        }else{
            return;
        }


    }

    public void createAccount(Scene scene , ComboBox questions ,TextField answer , Label errorLabel , Statement st) throws SQLException {
        if(questions.getValue() == null || answer.getText().strip().equals("")){
            errorLabel.setText("One or More Fields are Empty!");
            errorLabel.setAlignment(Pos.CENTER);
        }else{
            if(questions.getValue().toString().equals("What is the name of your favourite movie?")){
                String query = "INSERT INTO users "+"VALUES ('" + uName + "','"+ emailID + "','"+password + "','" + fullName + "','" + answer.getText().strip() + "','"+ null + "','"+null +"')";
                int count = st.executeUpdate(query);
                System.out.println(count + "rows affected");
            }else if(questions.getValue().toString().equals("What is the name of your home town?")){
                String query = "INSERT INTO users "+"VALUES ('" + uName + "','"+ emailID + "','"+password + "','" + fullName + "','" + null + "','"+ answer.getText().strip() + "','"+null +"')";
                int count = st.executeUpdate(query);
                System.out.println(count + "rows affected");
            }else if(questions.getValue().toString().equals("Which is you favourite novel?")){
                String query = "INSERT INTO users "+"VALUES ('" + uName + "','"+ emailID + "','"+password + "','" + fullName + "','" + null + "','"+ null + "','"+ answer.getText() +"')";
                int count = st.executeUpdate(query);
                System.out.println(count + "rows affected");
            }

            MessageBox.displayMessage("Account Created","INFO");
            stage.setScene(scene);
        }
    }

    public void LogIn(TextField userName , PasswordField password , Statement st , Label errorLabel) throws SQLException, ClassNotFoundException, FileNotFoundException {
        //"select sname from Student where rollno=2"
        //"SELECT id, first, last, age FROM Registration"
        String query = "SELECT username , emailId , pass FROM users";
        ResultSet rs = st.executeQuery(query);

        boolean found = false;
        String passW=null;

        while(rs.next())
        {
            String userN = rs.getString("username");
            passW = rs.getString("pass");
            String email = rs.getString("emailId");

            if(userName.getText().equals(userN)){
                found = true;
                break;
            }
            else if(userName.getText().equals(email)){
                found = true;
                break;
            }
        }

        if(!found) {
            errorLabel.setText("No account found with this username or email id");
            errorLabel.setAlignment(Pos.CENTER);
        }else{
            if(password.getText().equals(passW)){
                stage.close();
                MainApplication.open(userName.getText() , passW);
            }else{
                errorLabel.setText("Wrong Password!");;
                errorLabel.setAlignment(Pos.CENTER);
            }
        }
    }


}