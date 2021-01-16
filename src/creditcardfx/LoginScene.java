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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginScene extends Application {

    String adminName;
    double mouse_x;
    double mouse_y;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Login page");

        //Transition
        ImageView logo = new ImageView(new Image("/images/LogoMain.png", 270, 270, false, false));
        logo.setTranslateY(-500);
        TranslateTransition logo_t = new TranslateTransition(Duration.seconds(1));
        TranslateTransition login_t = new TranslateTransition(Duration.seconds(1));
        TranslateTransition signin_t = new TranslateTransition(Duration.seconds(1));
        logo_t.setNode(logo);
        logo_t.setToY(75);
        logo_t.play();
        logo_t.setOnFinished(e -> {
            logo_t.setDuration(Duration.seconds(1));
            logo_t.setToX(-200);
            logo_t.play();
        });

        Button loginButton = new Button("Login");
        Button clearButton = new Button("Clear");
        loginButton.getStyleClass().add("login-button");
        loginButton.setPrefWidth(100);
        clearButton.getStyleClass().add("clear-button");

        Label statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill : red; -fx-font-size : 14px;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        usernameField.getStyleClass().add("login-field");
        passwordField.getStyleClass().add("login-field");

        usernameField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                {
                    String userString = usernameField.getCharacters().toString();
                    String passwordString = passwordField.getCharacters().toString();
                    if (!userString.equals("") && !passwordString.equals("")) { //both datafield can't be null

                        try {
                            BufferedReader reader = new BufferedReader(new FileReader("UserDatabase.txt"));
                            String line = "";
                            boolean foundUsername = false;
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(",");//split at ','
                                if (data[1].equals(userString)) {
                                    foundUsername = true;
                                    if (data[2].equals(passwordString)) {
                                        statusLabel.setText("Login success");
                                        adminName = userString;
                                        //AlertBox.display("Login success", "Success");

                                        stage.setScene(new BankScene().getBankScene(stage, adminName));

                                    } else {
                                        statusLabel.setText("Incorrect username or password");
                                    }
                                    break;
                                }
                            }
                            if (!foundUsername) {
                                statusLabel.setText("Incorrect username or password");
                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            Logger.getLogger(LoginScene.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        statusLabel.setText("Missing username or password field");
                    }
                    usernameField.clear();
                    passwordField.clear();
                }
            }
        });
        passwordField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                {
                    String userString = usernameField.getCharacters().toString();
                    String passwordString = passwordField.getCharacters().toString();
                    if (!userString.equals("") && !passwordString.equals("")) { //both datafield can't be null

                        try {
                            BufferedReader reader = new BufferedReader(new FileReader("UserDatabase.txt"));
                            String line = "";
                            boolean foundUsername = false;
                            while ((line = reader.readLine()) != null) {
                                String[] data = line.split(",");//split at ','
                                if (data[1].equals(userString)) {
                                    foundUsername = true;
                                    if (data[2].equals(passwordString)) {
                                        statusLabel.setText("Login success");
                                        adminName = userString;
                                        //AlertBox.display("Login success", "Success");

                                        stage.setScene(new BankScene().getBankScene(stage, adminName));

                                    } else {
                                        statusLabel.setText("Incorrect username or password");
                                    }
                                    break;
                                }
                            }
                            if (!foundUsername) {
                                statusLabel.setText("Incorrect username or password");
                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (Exception ex) {
                            Logger.getLogger(LoginScene.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        statusLabel.setText("Missing username or password field");
                    }
                    usernameField.clear();
                    passwordField.clear();
                }
            }
        });

        //Layout
        GridPane grid = new GridPane();
        VBox vBox = new VBox(20);
        VBox vBox1 = new VBox(10);
        HBox hBox = new HBox();
        BorderPane pane = new BorderPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(logo, 1, 0);
        grid.add(vBox, 1, 1);

        Label username = new Label("Username");
        Label password = new Label("Password");
        Label sign_in = new Label("Sign In");
        username.getStyleClass().add("login-label");
        password.getStyleClass().add("login-label");
        vBox1.getChildren().addAll(username, usernameField, password, passwordField, statusLabel);
        grid.add(sign_in, 1, 1);
        sign_in.setTranslateX(150);
        sign_in.setTranslateY(-600);
        sign_in.getStyleClass().add("login-label");
        sign_in.setStyle("-fx-font-size : 80 px");

        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(clearButton, loginButton);

        vBox.getChildren().addAll(vBox1, hBox);
        vBox.setTranslateY(300);
        vBox.setTranslateX(150);

        login_t.setNode(vBox);
        login_t.setDelay(Duration.seconds(1));
        login_t.setToY(-130);
        login_t.play();
        signin_t.setNode(sign_in);
        signin_t.setDelay(Duration.seconds(1));
        signin_t.setToY(-300);
        signin_t.setToX(120);
        signin_t.play();

        loginButton.setOnAction(e -> {
            String userString = usernameField.getCharacters().toString();
            String passwordString = passwordField.getCharacters().toString();
            if (!userString.equals("") && !passwordString.equals("")) { //both datafield can't be null

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("UserDatabase.txt"));
                    String line = "";
                    boolean foundUsername = false;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");//split at ','
                        if (data[1].equals(userString)) {
                            foundUsername = true;
                            if (data[2].equals(passwordString)) {
                                statusLabel.setText("Login success");
                                adminName = userString;
                                //AlertBox.display("Login success", "Success");

                                stage.setScene(new BankScene().getBankScene(stage, adminName));

                            } else {
                                statusLabel.setText("Incorrect username or password");
                            }
                            break;
                        }
                    }
                    if (!foundUsername) {
                        statusLabel.setText("Incorrect username or password");
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    Logger.getLogger(LoginScene.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                statusLabel.setText("Missing username or password field");
            }
            usernameField.clear();
            passwordField.clear();
        });
        clearButton.setOnAction(e -> {
            usernameField.clear();
            passwordField.clear();
            statusLabel.setText("");
        });

        //New Title Bar
        AnchorPane title = new AnchorPane();
        title.getStyleClass().add("title-bar");
        Button close_button = new Button();
        Button minimize_button = new Button();
        ImageView close_image = new ImageView(new Image("/images/Close_Button.png", 30, 30, false, false));
        ImageView minimize_image = new ImageView(new Image("/images/Minimize_Button.png", 30, 30, false, false));
        close_button.setGraphic(close_image);
        minimize_button.setGraphic(minimize_image);
        close_button.setBackground(Background.EMPTY);
        minimize_button.setBackground(Background.EMPTY);
        close_button.setLayoutX(750);
        minimize_button.setLayoutX(710);

        close_button.setOnAction(e -> {
            stage.close();
        });
        minimize_button.setOnAction(e -> {
            stage.setIconified(true);
        });
        title.getChildren().addAll(close_button, minimize_button);

        pane.setCenter(grid);
        pane.setTop(title);
        Scene loginScene = new Scene(pane, 800, 600);
        title.setOnMousePressed(e -> {
            mouse_x = e.getSceneX();
            mouse_y = e.getSceneY();

        });
        title.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - mouse_x);
            stage.setY(e.getScreenY() - mouse_y);
        });
        loginScene.getStylesheets().add(getClass().getResource("/fonts/style.css").toExternalForm());

        stage.setScene(loginScene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    public static void createMockDatabase() {//creat mock database
        File file = new File("UserDatabase.txt");
        System.out.println(file.exists());
        char splitter = ',';
        try {
            PrintWriter output = new PrintWriter(file);//init PrintWriter (File writer)
            output.print("John Cena");//Name
            output.print(splitter);//splitter
            output.print("q");//user
            output.print(splitter);//splitter
            output.println("q");//pass
            output.close();//Close file to "save" and for "safety"
        } catch (FileNotFoundException ex) {
            System.out.println("Username Database can't be found/create");
        }

    }

    public static void main(String[] args) {
        //    createMockDatabase();
        launch(args);

    }
}
