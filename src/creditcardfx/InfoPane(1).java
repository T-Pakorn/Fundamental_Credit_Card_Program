/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creditcardfx;

import creditcardfx.MoreCardInfoPane;
import creditcardfx.TransactionPane;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoPane extends Pane {

    private CreditCard creditCard;

    private Rectangle stateRect = new Rectangle(20, 30);
    private Label nameLabel = new Label(), serialLabel = new Label();
    private Image cardImage;
    private Button moreInfoButton = new Button("More Info"), transInfoButton = new Button("Transaction"), deleteButton = new Button("Delete"),
            changeButton = new Button("Change Status");

    public InfoPane() {
    }

    public InfoPane(CreditCard creditCard) {
        this.creditCard = creditCard;
        nameLabel.setText(creditCard.getName());
        serialLabel.setText(creditCard.getSerial());//Belived to cause bug in paintInfo

        if (creditCard.getTier().equalsIgnoreCase("platinum")) {
            cardImage = new Image("https://www.americanexpress.com/content/dam/amex/th/home-page/cards/Card_Art_full_size/THAI_Co-brand_2008.png");
        } else if (creditCard.getTier().equalsIgnoreCase("gold")) {
            cardImage = new Image("https://www.pngitem.com/pimgs/m/223-2234640_credit-card-png-green-dot-debit-card-transparent.png");
        } else if (creditCard.getTier().equalsIgnoreCase("silver")) {
            cardImage = new Image("https://www.dusitgold.com/assets/layouts/frontend/images/flyerbonus-card.png");
        } else {
            cardImage = new Image("https://www.kindpng.com/picc/m/116-1162427_visa-international-credit-card-hd-png-download.png");
        }
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        paintInfo();
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void paintInfo() {

        moreInfoButton.setOnAction(e -> {
            Stage moreStage = new Stage();
            Scene moreScene = new Scene(new MoreCardInfoPane(creditCard), 315, 440);
            moreStage.setTitle("Full Card Info");
            moreStage.setScene(moreScene);
            moreStage.initModality(Modality.APPLICATION_MODAL);
            moreStage.show();
            System.out.println("More Info...");
        });

        transInfoButton.setOnAction(e -> {
            Stage moreStage = new Stage();
            Scene moreScene = new Scene(new TransactionPane(creditCard));
            moreStage.setTitle("Transaction Scene");
            moreStage.setScene(moreScene);
            moreStage.initModality(Modality.APPLICATION_MODAL);
            moreStage.show();
            System.out.println("More Info...");
        });

        changeButton.setOnAction(e -> {
            
            System.out.println("Changing Info...");
            System.out.println(creditCard.getState());
            try {
                ToggleState.switchCardState(CreditCard.findIndex(creditCard.getSerial()) + 1, creditCard.getState());
            } catch (IOException ex) {
                Logger.getLogger(InfoPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            File fileTargetDel = new File(Utils.locationOfOriginalFile);

            if (fileTargetDel.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }
            File newFileJA = new File(Utils.locationOfTemporaryFile);
             newFileJA.renameTo(fileTargetDel);

            System.out.println("Finish Delete");
        });
        
        deleteButton.setOnAction(e -> {
            Stage moreStage = new Stage();
            Scene moreScene = new Scene(new TransactionPane(creditCard));
            try {
                DeleteRow.deleteAndWriteBackToCSV(CreditCard.findIndex(creditCard.getSerial()) + 1);
            } catch (IOException ex) {
                Logger.getLogger(InfoPane.class.getName()).log(Level.SEVERE, null, ex);
            }

            File fileTargetDel = new File(Utils.locationOfOriginalFile);

            if (fileTargetDel.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("Failed to delete the file");
            }
            File newFileJA = new File(Utils.locationOfTemporaryFile);
             newFileJA.renameTo(fileTargetDel);

            System.out.println("Finish Delete");
        });

        ImageView cardImageView = new ImageView(cardImage);
        cardImageView.setPreserveRatio(true);
        cardImageView.setFitHeight(50);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(nameLabel, serialLabel);

        stateRect.setStroke(Color.BLACK);
        if ("Normal".equalsIgnoreCase(creditCard.getState())) {
            stateRect.setFill(Color.GREEN);
        } else if("Forfeit".equalsIgnoreCase(creditCard.getState())){
            stateRect.setFill(Color.RED);
        }

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(cardImageView, vBox, stateRect, moreInfoButton, transInfoButton, changeButton, deleteButton);

        setPadding(new Insets(10));
        getChildren().addAll(hBox);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintInfo();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintInfo();
    }
}
