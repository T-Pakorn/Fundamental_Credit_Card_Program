/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class MoreCardInfoPane extends Pane {

    private CreditCard creditCard = new CreditCard();
    private ListView nameList = new ListView(), valueList = new ListView();
    private Label listName = new Label("INFORMATION OF CARD HOLDER");

    public MoreCardInfoPane(CreditCard creditCard) {

        nameList.getItems().addAll("Serial", "CCV", "Name", "Surname", "Issue Date", "Expiry Date", "Type", "Tier", "Money Boundary", "State");
        nameList.setPrefSize(250, 500);
        nameList.getStyleClass().add("info-list-cell");
        valueList.setPrefSize(250, 500);
        valueList.getStyleClass().add("info-list-cell");
        listName.setStyle("-fx-font-size : 22px; -fx-text-fill : white;");
        this.creditCard = creditCard;
        valueList.getItems().addAll(//must invoke once
                creditCard.getSerial(),
                creditCard.getCcv(),
                creditCard.getName(),
                creditCard.getSurname(),
                creditCard.getIssueDate(),
                creditCard.getExpiryDate(),
                creditCard.getType(),
                creditCard.getTier(),
                creditCard.getMoneyBoundary(),
                creditCard.getState()
        );
        //valueList.setEditable(true);

    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        paintMoreCardInfo();
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void paintMoreCardInfo() {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(nameList, valueList);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(listName, hBox);
        vBox.setAlignment(Pos.CENTER);


        getChildren().clear();
        getChildren().add(vBox);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintMoreCardInfo();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintMoreCardInfo();
    }

}
