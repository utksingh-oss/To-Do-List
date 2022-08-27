package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class Account {
    public static boolean open(String backgroundColor , String textFill , String passw , String ID) throws ClassNotFoundException, SQLException {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Manage Account");
        stage.setResizable(false);

        Button changePassword = new Button("Change Password");
        Button deleteAccount = new Button("Delete Account");
        changePassword.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
        changePassword.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                changePassword.setStyle("-fx-background-color : "+backgroundColor + " ;-fx-text-fill : "+textFill + " ; -fx-background-radius : 0 ; -fx-border-color : "+textFill);
            }else{
                changePassword.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
            }
        });
        deleteAccount.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
        deleteAccount.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                deleteAccount.setStyle("-fx-background-color : "+backgroundColor + " ;-fx-text-fill : "+textFill + " ; -fx-background-radius : 0 ; -fx-border-color : "+textFill);
            }else{
                deleteAccount.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
            }
        });

        int width = 120;
        changePassword.setMinWidth(width);
        deleteAccount.setMinWidth(width);

        VBox vBox = new VBox(changePassword , deleteAccount);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color : " + backgroundColor);

        //======================Finally Just Change The pucking password================
        PasswordField passwordField1 = new PasswordField();
        PasswordField passwordField2 = new PasswordField();
        passwordField1.setPromptText("Enter the new Password");
        passwordField2.setPromptText("Confirm the new Password");
        passwordField1.setMaxWidth(290);
        passwordField2.setMaxWidth(290);

        Label error = new Label("");
        error.setStyle("-fx-text-fill : " + textFill + "; -fx-background-color : "+ backgroundColor);

        passwordField1.setStyle("-fx-background-color : "+ backgroundColor + " ; -fx-text-fill : " + textFill + " ; -fx-background-radius : 0 ; -fx-prompt-text-fill:" + textFill +
                ";-fx-border-color : "+textFill);
        passwordField2.setStyle("-fx-background-color : "+ backgroundColor + " ; -fx-text-fill : " + textFill + " ; -fx-background-radius : 0 ; -fx-prompt-text-fill:" + textFill +
                ";-fx-border-color : "+textFill);

        Button change = new Button("Change Password");
        change.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
        change.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                change.setStyle("-fx-background-color : "+backgroundColor + " ;-fx-text-fill : "+textFill + " ; -fx-background-radius : 0 ; -fx-border-color : "+textFill);
            }else{
                change.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
            }
        });

        VBox vBox2 = new VBox(passwordField1 , passwordField2 ,error, change);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(10);
        vBox2.setStyle("-fx-background-color : " + backgroundColor);

        Scene scene2 = new Scene(vBox2 , 300  , 200);

        change.setOnAction(event -> {
            if(passwordField1.getText().equals(passwordField2.getText())){
                String url = "jdbc:mysql://localhost:3306/abc";
                String uname = "root";
                String pass = "858684Arsh123!";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Connection con = null;
                try {
                    con = DriverManager.getConnection(url , uname , pass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                System.out.println("Connecting to the database ...");
                Statement stmt = null;
                try {
                    stmt = con.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String sql = null;
                if(ID.indexOf('@') == -1) {
                    sql = "update users set pass='" + passwordField1.getText() + "' where username ='" + ID + "'";
                }else{
                    sql = "update users set pass='" + passwordField1.getText() + "' where emailId ='" + ID + "'";
                }
                try {
                    stmt.executeUpdate(sql);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                System.out.println("Data Updated!");
                stage.close();
            }else{
                error.setText("New password and confirm password do not match");
            }
        });



        //======================Change Password Scene=================================
        PasswordField enterPassword = new PasswordField();
        enterPassword.setPromptText("Enter the current password");
        Button next = new Button("Next");
        Label errorLabel = new Label();

        VBox vBox1 = new VBox(enterPassword ,errorLabel ,  next);
        vBox1.setSpacing(10);
        vBox1.setPadding(new Insets(10));
        vBox1.setAlignment(Pos.CENTER);

        vBox1.setStyle("-fx-background-color : "+backgroundColor);

        enterPassword.setStyle("-fx-background-color : "+ backgroundColor + " ; -fx-text-fill : " + textFill + " ; -fx-background-radius : 0 ; -fx-prompt-text-fill:" + textFill +
                ";-fx-border-color : "+textFill);
        next.setStyle("-fx-background-color : "+textFill + " ; -fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
        next.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                next.setStyle("-fx-background-color : "+backgroundColor + " ;-fx-text-fill : "+textFill + " ; -fx-background-radius : 0 ; -fx-border-color : "+textFill);
            }else{
                next.setStyle("-fx-background-color : "+textFill + " ;-fx-text-fill : "+backgroundColor + " ; -fx-background-radius : 0");
            }
        });
        errorLabel.setStyle("-fx-text-fill :" + textFill );

        next.setOnAction(event -> {
            if(enterPassword.getText().strip().equals(passw)){
                stage.setScene(scene2);
            }else{
                errorLabel.setText("Wrong Password!");
            }
        });

        Scene scene1 = new Scene(vBox1  , 300 , 200);


        boolean[] ans1 = {false};


        changePassword.setOnAction(event -> {
            stage.setScene(scene1);
        });

        deleteAccount.setOnAction(event -> {
            boolean ans = MessageBox.askMessage("Confirmation" , "Are you sure you want to delete the account ?");
            if(ans){
                String url = "jdbc:mysql://localhost:3306/abc";
                String uname = "root";
                String pass = "858684Arsh123!";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Connection con = null;
                try {
                    con = DriverManager.getConnection(url ,uname , pass);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                System.out.println("Connecting to the database ...");
                Statement stmt = null;
                try {
                    stmt = con.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String sql;
                if(ID.indexOf('@') == -1) {
                    sql = "DELETE FROM users WHERE username ='" + ID + "'";
                }else{
                    sql = "DELETE FROM users WHERE emailId ='" + ID + "'";
                }
                try {
                    stmt.executeUpdate(sql);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                System.out.println("Data Updated!");
                ans1[0] = true;
            }
        });




        Scene scene = new Scene(vBox  , 300 , 200);
        stage.setScene(scene);
        stage.showAndWait();
        return ans1[0];
    }
}
