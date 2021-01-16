/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BankScene extends Application {
    
    VBox vbox = new VBox();
    HBox hbox = new HBox();
    List<CreditCard> cards = CSVReaderInJava.readCardsFromCSV(Utils.locationOfOriginalFile);

    @Override
    public void start(Stage stage) throws Exception {

        int newCard = 0;
        stage.setTitle("InfoLists");
        stage.setResizable(false);
        
        Button addButton = new Button("Add");
        Button refresh = new Button("Refresh");
        TextField textField = new TextField ();
        Button searchButton = new Button("Search");
        
        
        
        addButton.setOnAction(e -> {
            AddCard add = new AddCard();
            Stage moreStage = new Stage();
            moreStage.setTitle("Registration Form");
            moreStage.setScene(add.getAddCardScene());
            moreStage.initModality(Modality.APPLICATION_MODAL);
            moreStage.show();
        });

        BufferedReader bufferedReader = new BufferedReader(new FileReader(Utils.locationOfOriginalFile));
        
        String input;
        int count = 0;

        while ((input = bufferedReader.readLine()) != null) {
            count++;
        }
        int recount = count;
        
        bufferedReader.close();

        System.out.println("Count : " + count);

        ScrollBar sb = new ScrollBar();
        
        /*sp.setContent(vbox);
        sp.setPrefSize(500, 580);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setFitToHeight(true);*/
        
        hbox.getChildren().addAll(addButton, refresh, textField, searchButton);
        hbox.setSpacing(10);
        vbox.getChildren().add(hbox);

        
        for (int i = 0; i < count; i++) {
            vbox.getChildren().addAll(new InfoPane(cards.get(i)));
        }

        Group g = new Group();
        g.getChildren().addAll(vbox, sb);
        Scene scene = new Scene(g, 560, 580);
        sb.setMin(0);
        sb.setMax((cards.size() * cards.size() * 2) + 30);
        System.out.println((cards.size() * cards.size() * 2) + 30);
        //sb.setLayoutX(20);
        sb.setLayoutX(scene.getWidth()-sb.getWidth());
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(580);
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
                RefreshScene();
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
            for (int j = 0; j < cardsRe.size(); j++) {
                System.out.println(j);
                vbox.getChildren().add(new InfoPane(cardsRe.get(j)));
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
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
     
    

