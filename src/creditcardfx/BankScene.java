/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BankScene {


    
    ImageView info_logo = new ImageView(new Image("/images/Info.png", 120, 120, false, false));
    ImageView add_logo = new ImageView(new Image("/images/Add.png", 105, 90, false, false));
    ImageView change_logo = new ImageView(new Image("/images/Change.png", 40, 30, false, false));
    ImageView refresh_logo = new ImageView(new Image("/images/Refresh.png", 100, 100, false, false));
    ImageView search_logo = new ImageView(new Image("/images/Search.png", 30, 30, false, false));
    double mouse_x;
    double mouse_y;
    VBox vbox = new VBox();
    HBox hbox = new HBox();
//    List<CreditCard> cards = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);
    static Security sc = new Security();
    static boolean result = false;

    public Scene getBankScene(Stage stage, String adminName) throws Exception {

        List<CreditCard> cards = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);

        int newCard = 0;
        stage.setTitle("InfoLists");
        stage.setResizable(false);

        Button addButton = new Button();
        addButton.setGraphic(add_logo);
        addButton.getStyleClass().add("add-button");
        Button refresh = new Button();
        refresh.setGraphic(refresh_logo);
        refresh.getStyleClass().add("add-button");
        TextField textField = new TextField();
        textField.setPromptText("Search..");
        textField.getStyleClass().add("search-field");
        Button searchButton = new Button();
        searchButton.setGraphic(search_logo);
        searchButton.getStyleClass().add("add-button");
        Button changePassButton = new Button();
        changePassButton.setGraphic(change_logo);
        changePassButton.getStyleClass().add("add-button");

        addButton.setOnAction(e -> {
            AddCard add = new AddCard();
            Stage moreStage = new Stage();
            moreStage.setTitle("Registration Form");
            moreStage.setScene(add.getAddCardScene());
            moreStage.initModality(Modality.APPLICATION_MODAL);
            moreStage.show();
        });

        changePassButton.setOnAction(e -> {
            result = sc.display(adminName);
        });

        ScrollBar sb = new ScrollBar();
//        GridPane grid = new GridPane();
//        GridPane.setConstraints(new Label("Tier"), 0, 0);
//        GridPane.setConstraints(new Label("Name"), 0, 0);
//        GridPane.setConstraints(new Label("Serial"), 0, 0);
//        GridPane.setConstraints(new Label("Tier"), 0, 0);
//        GridPane.setConstraints(new Label("Tier"), 0, 0);
//        GridPane.setConstraints(new Label("Tier"), 0, 0);
//        GridPane.setConstraints(new Label("Tier"), 0, 0);
        hbox.getChildren().addAll(textField, searchButton);
        hbox.setTranslateX(50);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setAlignment(Pos.CENTER_RIGHT);
        vbox.getChildren().add(hbox);
       vbox.setPadding(new Insets(10, 10, 10, 10));

        //    List<CreditCard> Y = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);
        File test = new File(Utils.locationOfOriginalFile);
        System.out.println("SIZE WHEN GET READY TO GET CHILDREN : " + test.length());
        if (test.length() == 5 || test.length() == 2) {

        } else {
            List<CreditCard> Y = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);
            System.out.println("Y SIZE FOR PRINT : " + Y.size());
            for (int i = 0; i < Y.size(); i++) {
                vbox.getChildren().addAll(new InfoPane(Y.get(i)));
            }
        }

        //Title Bar
        AnchorPane title = new AnchorPane();
        Button close_button = new Button();
        Button minimize_button = new Button();
        ImageView close_image = new ImageView(new Image("/images/Close_Button.png", 30, 30, false, false));
        ImageView minimize_image = new ImageView(new Image("/images/Minimize_Button.png", 30, 30, false, false));
        close_button.setGraphic(close_image);
        minimize_button.setGraphic(minimize_image);
        close_button.getStyleClass().add("add-button");
        minimize_button.getStyleClass().add("add-button");
        close_button.setLayoutX(750);
        minimize_button.setLayoutX(710);
        close_button.setOnAction(e -> {
            stage.close();
        });
        minimize_button.setOnAction(e -> {
            stage.setIconified(true);
        });
        title.getChildren().addAll(close_button, minimize_button);
        title.getStyleClass().add("title-bar");

        //layout
        BorderPane pane = new BorderPane();
        Group g = new Group();
        VBox vBox = new VBox(10);
        vBox.setMinWidth(150);
        vBox.getStyleClass().add("info-bar");
        vBox.setAlignment(Pos.TOP_CENTER);
        g.getChildren().addAll(vbox, sb);
        vBox.getChildren().addAll(info_logo, changePassButton, addButton, refresh);
        info_logo.setTranslateY(20);
        changePassButton.setTranslateY(20);
        changePassButton.setTranslateX(5);
        addButton.setTranslateY(100);
        refresh.setTranslateY(100);

        pane.setTop(title);
        pane.setCenter(g);
        pane.setLeft(vBox);

        //Transition
        vBox.setTranslateY(500);
        TranslateTransition t1 = new TranslateTransition(Duration.seconds(1));
        t1.setNode(vBox);
        t1.setToY(0);
        t1.play();
        FadeTransition f1 = new FadeTransition(Duration.seconds(2));
        f1.setNode(vbox);
        f1.setFromValue(0);
        f1.setToValue(10);
        f1.play();

        Scene scene = new Scene(pane, 800, 600);
        title.setOnMousePressed(e -> {
            mouse_x = e.getSceneX();
            mouse_y = e.getSceneY();

        });
        title.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - mouse_x);
            stage.setY(e.getScreenY() - mouse_y);
        });
        scene.getStylesheets().add(getClass().getResource("/fonts/style.css").toExternalForm());

        sb.setMin(0);
        sb.setMax((cards.size() * cards.size() * 2) + 30);
        System.out.println((cards.size() * cards.size() * 2) + 30);
        //sb.setLayoutX(20);
        sb.setLayoutX(scene.getWidth() - sb.getWidth());
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(scene.getHeight());
        sb.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                vbox.setLayoutY(-new_val.doubleValue());
            }
        });



        //scene.getStylesheets().add(getClass().getResource("/creditcard/style.css").toExternalForm());
        stage.setScene(scene);

        stage.show();

        
        
        refresh.setOnAction(e -> {
            List<CreditCard> cardsRe = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);

            try {
               

                String input;
                int recount = 0;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.locationOfOriginalFile));
                while ((input = bufferedReader.readLine()) != null) {
                    recount++;
                }
                bufferedReader.close();

                //System.out.println(count);
                vbox.getChildren().clear();
                vbox.getChildren().add(hbox);
                System.out.println(cardsRe.size());

                File test2 = new File(Utils.locationOfOriginalFile);
                System.out.println("SIZE WHEN REFRESH : " + test2.length());
                if (test2.length() == 2 || test2.length() == 5) {
                    for (int j = 0; j < cardsRe.size() - 1; j++) {
                        System.out.println(j);
                        vbox.getChildren().add(new InfoPane(cardsRe.get(j)));
                    }
                } else {
                    for (int j = 0; j < cardsRe.size(); j++) {
                        System.out.println(j);
                        vbox.getChildren().add(new InfoPane(cardsRe.get(j)));
                    }
                }
                
                //System.out.println(cardsRe.size());
                System.out.println((cardsRe.size() * cardsRe.size() * 2) + 30);
                sb.setMax((cardsRe.size() * cardsRe.size() * 2) + 30);
            } catch (IOException ex) {
                Logger.getLogger(BankScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(scene);
            stage.show();
        });

        searchButton.setOnAction(e -> {

            List<CreditCard> cardsRe = CreditCard.searchSpecificFor(textField.getText());
            try {
                RefreshSearch(textField);
                //System.out.println(cardsRe.size());
                System.out.println((cardsRe.size() * cardsRe.size() * 2) + 30);
                sb.setMax((cardsRe.size() * cardsRe.size() * 2) + 30);
            } catch (IOException ex) {
                Logger.getLogger(BankScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.setScene(scene);
            stage.show();

        });

        return scene;
    }

    public int RefreshScene() throws FileNotFoundException, IOException {

        List<CreditCard> cardsRe = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);

        String input;
        int recount = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.locationOfOriginalFile));
        while ((input = bufferedReader.readLine()) != null) {
            recount++;
        }
        bufferedReader.close();

        //System.out.println(count);
        vbox.getChildren().clear();
        vbox.getChildren().add(hbox);
        System.out.println(cardsRe.size());

        File test2 = new File(Utils.locationOfOriginalFile);
        System.out.println("SIZE WHEN REFRESH : " + test2.length());
        if (test2.length() == 2 || test2.length() == 5) {
            for (int j = 0; j < cardsRe.size() - 1; j++) {
                System.out.println(j);
                vbox.getChildren().add(new InfoPane(cardsRe.get(j)));
            }
        } else {
            for (int j = 0; j < cardsRe.size(); j++) {
                System.out.println(j);
                vbox.getChildren().add(new InfoPane(cardsRe.get(j)));
            }
        }
        return cardsRe.size();
    }

    public int RefreshSearch(TextField textField) throws FileNotFoundException, IOException {
        List<CreditCard> cardsRe = CreditCard.searchSpecificFor(textField.getText());

        //System.out.println(count);
        vbox.getChildren().clear();
        vbox.getChildren().add(hbox);
        System.out.println(cardsRe.size());
        for (int j = 0; j < cardsRe.size(); j++) {
            System.out.println(j);
            vbox.getChildren().add(new InfoPane(cardsRe.get(j)));
        }
        return cardsRe.size();
    }

    /*public static void main(String[] args) {
        launch(args);
    }*/
}
