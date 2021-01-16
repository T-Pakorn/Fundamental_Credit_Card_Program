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
import com.sun.javafx.FXPermissions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Admin
 */
public class Security {

    static boolean result = false;
    static Stage window;
    static Button confirm_button = new Button("Confirm");
    static BorderPane pane = new BorderPane();
    static HBox layout1;
    static VBox layout2;
    static VBox logo_layout;
    static Label numbers, password, error;
    static int num1, num2;
    static TextField answer = new TextField();
    static Scene scene = new Scene(pane, 500, 400);
    static ImageView logo = new ImageView(new Image("/images/SLogo.png", 256, 144, false, false));
    static TranslateTransition error_move = new TranslateTransition(Duration.seconds(0.1));
    static TranslateTransition logo_move = new TranslateTransition(Duration.seconds(1));
    static ChangePass change = new ChangePass();

    public boolean display(String adminName) {
        answer.clear();

        //Transaction 
        logo.setTranslateY(-10);
        logo_move.setNode(logo);
        logo_move.setToY(10);
        logo_move.play();
        error_move.setNode(logo);
        error_move.setAutoReverse(true);
        error_move.setCycleCount(5);
        error_move.setFromX(logo.getTranslateX()-5);
        error_move.setOnFinished(e->{
            logo.setTranslateX(logo.getTranslateX()-5);
        });
        
        

        result = false;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Security");

        //Generate numbers
        num1 = new Random().nextInt(98) + 1;
        num2 = new Random().nextInt(98) + 1;

        //Labels 
        numbers = new Label(String.valueOf(num1) + " + " + String.valueOf(num2) + " = ");
        numbers.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
        password = new Label("Enter password");
        password.getStyleClass().add("fontpassword");
        error = new Label();
        error.setStyle("-fx-text-fill: red");

        //Text Fields
        answer.setPrefSize(85, 90);
        answer.getStyleClass().add("fontpassword");
        answer.getStyleClass().add("s-field");
        answer.setTextFormatter(new TextFormatter<String>((Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 3) {
                return null;
            } else {
                return change;
            }
        }));
        answer.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    answer.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //Buttons
        confirm_button.setOnAction(e -> {
                result = verify_password(answer);
                    if (result) {
                      
                        answer.clear();
                        change.display(adminName);
                        window.close();
                    
                    }

        });
        confirm_button.getStyleClass().add("s-button");

        //Layout 
        logo_layout = new VBox(10);
        logo_layout.getChildren().add(logo);
        logo_layout.setAlignment(Pos.CENTER);

        layout1 = new HBox(10);
        layout1.getChildren().addAll(numbers, answer);
        layout1.setAlignment(Pos.CENTER);
        layout1.setSpacing(5);
        layout1.setMaxWidth(300);
        layout1.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: cdaf2a; -fx-padding: 10;");
        layout1.setTranslateY(-20);

        layout2 = new VBox(10);
        layout2.getChildren().addAll(layout1, confirm_button, error);
        layout2.setAlignment(Pos.BOTTOM_CENTER);

        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setTop(logo_layout);
        pane.setCenter(layout2);
        pane.getStyleClass().add("fontpassword");
        pane.getStylesheets().add(getClass().getResource("/fonts/style.css").toExternalForm());

        //Scene
        scene.setRoot(pane);
        scene.getStylesheets().add(getClass().getResource("/fonts/styleAddCard.css").toExternalForm());

        //Keyboard
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER: {
                    result = verify_password(answer);
                    if (result) {
                      
                        answer.clear();
                        change.display(adminName);
                        window.close();
                    
                    }

                }
            }
        });

        //Stage 
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();

        return result;

    }

    static private boolean verify_password(TextField sum) {
        boolean verified = false;
        //Verify sum
        if (sum.getText().compareTo(String.valueOf(num1 + num2)) == 0) {
            //Verify password
            verified = true;
            
        } else {
            error_move.setToX(logo.getTranslateX()+5);
            error_move.play();
            sum.clear();
            num1 = new Random().nextInt(98) + 1;
            num2 = new Random().nextInt(98) + 1;
            numbers.setText(String.valueOf(num1) + " + " + String.valueOf(num2) + " = ");
            error.setText("Incorrect. Please try again.");
            verified = false;
        }

        return verified;
    }

}
