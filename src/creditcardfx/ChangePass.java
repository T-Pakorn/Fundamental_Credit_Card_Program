/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

/**
 *
 * @author user
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangePass {
    
        static boolean result;
        static BorderPane pane = new BorderPane();
        static BorderPane pane2 = new BorderPane();
        static HBox ly1,ly2,ly3,ly4;
        static VBox layout1,layout2;
        static Stage window,window2;
        static Scene scene1 = new Scene(pane, 600, 450);
        static Scene scene2 = new Scene(pane2,400,300);
        static Button confirmButton;
        static Label old,neww,confirm,error;
        static Label txt;
        static PasswordField oldpass = new PasswordField();
        static PasswordField newpass = new PasswordField();  
        static PasswordField confirmpass = new PasswordField(); 
        
    public  static void display(String adminName){
        //Security sc = new Security();
        
        //windows
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ChangePassword");
        
        //Labels
        old = new Label("Old Password");
        old.setStyle("-fx-font-size: 13px; -fx-text-fill: White");
        neww = new Label("New Password");
        neww.setStyle("-fx-font-size: 13px; -fx-text-fill: White");
        confirm = new Label("Confirm Password");
        confirm.setStyle("-fx-font-size: 13px; -fx-text-fill: White");
        error = new Label();
        error.setStyle("-fx-font-size: 15px; -fx-text-fill: White");
        txt = new Label("Enter your password");
        txt.setStyle("-fx-font-size: 30px; -fx-text-fill: White");
        
        //PasswordField
        oldpass.setPromptText("oldpassword");
        oldpass.setStyle("-fx-text-inner-color: white; -fx-background-color: cdaf2a; -fx-font-size: 13px; -fx-padding: 5 20 5 20");
        oldpass.setTextFormatter(new TextFormatter<String>((Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 16) {
                return null;
            } else {
                return change;
            }
        }));
        newpass.setPromptText("newpassword");
        newpass.setStyle("-fx-text-inner-color: white; -fx-background-color: cdaf2a; -fx-font-size: 13px; -fx-padding: 5 20 5 20");
        newpass.setTextFormatter(new TextFormatter<String>((Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 16) {
                return null;
            } else {
                return change;
            }
        }));
        confirmpass.setPromptText("confirmpassword");
        confirmpass.setStyle("-fx-text-inner-color: white; -fx-background-color: cdaf2a; -fx-font-size: 13px; -fx-padding: 5 20 5 20");
        confirmpass.setTextFormatter(new TextFormatter<String>((Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 16) {
                return null;
            } else {
                return change;
            }
        }));
        
        //Buttons
        confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: #cdaf2a; -fx-text-fill: white; -fx-font-size: 15px");
        confirmButton.setOnAction(e ->{
            try {
                result = verify(adminName,oldpass,newpass,confirmpass);
            } catch (IOException ex) {
                Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(result);
            if(result){
                window.close();
            }
        });
        
        //Layout
        ly1 = new HBox(10);
        ly1.getChildren().addAll(txt);
        ly1.setAlignment(Pos.CENTER_LEFT);
        ly1.setSpacing(5);
        ly1.setMaxWidth(300);
        ly1.setTranslateY(-20);
        ly2 = new HBox(10);
        ly2.getChildren().addAll(old,oldpass);
        ly2.setAlignment(Pos.CENTER_LEFT);
        ly2.setSpacing(30);
        ly2.setMaxWidth(300);
        ly2.setTranslateY(-20);
        ly3 = new HBox(10);
        ly3.getChildren().addAll(neww,newpass);
        ly3.setAlignment(Pos.CENTER_LEFT);
        ly3.setSpacing(25);
        ly3.setMaxWidth(300);
        ly3.setTranslateY(-20);
        ly4 = new HBox(10);
        ly4.getChildren().addAll(confirm,confirmpass);
        ly4.setAlignment(Pos.CENTER_LEFT);
        ly4.setSpacing(5);
        ly4.setMaxWidth(300);
        ly4.setTranslateY(-20);
       
        layout1 = new VBox(10);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(ly1,ly2,ly3,ly4,confirmButton);
        
        layout2 = new VBox(10);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(error);
        
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setCenter(layout1);
        pane.getStyleClass().add("fontpassword2");
        pane.setStyle("-fx-background-color : linear-gradient(to bottom right ,#1b4872 60%,#1f5282 40%);");
        
        pane2.setPadding(new Insets(20, 20, 20, 20));
        pane2.setCenter(layout2);
        pane2.getStyleClass().add("fontpassword");
        pane2.setStyle("-fx-background-color : linear-gradient(to bottom right ,#1b4872 60%,#1f5282 40%);");
        
        //Scenes
        scene1.setRoot(pane);
        //scene1.getStylesheets().add(getClass().getResource("/fonts/styleAddCard.css").toExternalForm());
        scene2.setRoot(pane2);
        
        //Keyboard
        scene1.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER: {
                    try {
                        result = verify(adminName,oldpass,newpass,confirmpass);
                    } catch (IOException ex) {
                        Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(result);
                    if(result){
                        window.close();
                    }
                }
            }
        });
        
        //Stage
        window.setScene(scene1);     
        window.showAndWait();

    }
    
     static private boolean verify(String userString, PasswordField oldpass,PasswordField newpass,PasswordField confirmpass) throws IOException{
        File files = new File("UserDatabase.txt");  
        Scanner scan = new Scanner(files);
        window2 = new Stage();       
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setScene(scene2);
        boolean verified;
        boolean grantAccess = false;
        int allLine = 0;
        ArrayList<String> users = new ArrayList<>();
        while(scan.hasNextLine()){
            users.add(scan.nextLine());
            allLine++;
        }
        for(int i=0; i<allLine; i++){
            String[] data = users.get(i).split(",");
            if (data[1].equals(userString)) {
                if(data[2].equals(oldpass.getText())){ 
                grantAccess=true;
                data[2]=newpass.getText();
                users.set(i, data[0]+","+data[1]+","+data[2]);
                break;
            }}
        }   
        
        //scan.close();
        scan.close();
        //Verify oldpass
            if(grantAccess == true){
            //Verify newpass
                if(checkString(newpass.getText())==true){
                    if(newpass.getText().equals(confirmpass.getText())){
                        FileWriter writer = new FileWriter("UserDatabase.txt");
                        for(int i=0; i<allLine; i++){
                            writer.write(users.get(i) + "\n");
                        }
                        writer.close();
                        confirmpass.clear();
                        oldpass.clear();
                        newpass.clear();
                        error.setText("Success!!");
                        window2.showAndWait();
                        verified = true;
                    }
                    else{
                        error.setText("Please confirm your password.");
                        window2.showAndWait();     
                        verified = false;
                    }
                }
                else{
                    error.setText("You can use only letters,numbers for your password.");
                    window2.showAndWait();     
                    verified = false;
                }
            }
            else{       
                error.setText("Your old password is incorrect. Please try again.");
                window2.showAndWait();     
                verified = false;
            }
 
        return verified;
    }
    
    public static boolean checkString(String str){
            
        if(str != null && (!str.equals(""))&& (str.matches("^[a-zA-Z0-9]*$")))
        {
            return true;
        }
        else{
            return false;
        }
    }
}
