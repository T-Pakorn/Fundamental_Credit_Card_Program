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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class InfoPane extends Pane {

    private CreditCard creditCard;

    private ImageView state = new ImageView();
    private Label nameLabel = new Label(), serialLabel = new Label();
    private ImageView cardImageView;
    public  Button moreInfoButton = new Button();
    public  Button transInfoButton = new Button();
    public  Button deleteButton = new Button();
    public  Button changeButton = new Button("Change Status");
    public ImageView delete_logo = new ImageView(new Image("/images/Delete.png", 25, 30, false, false));;
    public ImageView trans_logo = new ImageView(new Image("/images/Bill.png", 25, 30, false, false));;
    public ImageView search_logo = new ImageView(new Image("/images/Search.png", 25, 30, false, false));;
    
    public InfoPane() {
    }

    public InfoPane(CreditCard creditCard) {
        this.creditCard = creditCard;
        nameLabel.setText(creditCard.getName());
        serialLabel.setText(creditCard.getSerial());//Belived to cause bug in paintInfo
        

        if (creditCard.getTier().equalsIgnoreCase("platinum")) {
            cardImageView = new ImageView(new Image("/images/Platinum.png", 144, 108, false, false));
        } else if (creditCard.getTier().equalsIgnoreCase("gold")) {
            cardImageView = new ImageView(new Image("/images/Gold.png", 144, 108, false, false));
        } else if (creditCard.getTier().equalsIgnoreCase("silver")) {
            cardImageView = new ImageView(new Image("/images/Silver.png", 144, 108, false, false));
        } else {
            cardImageView = new ImageView(new Image("/images/Bronze.png", 144, 108, false, false));
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
        
        
        
       moreInfoButton .setOnAction((ActionEvent e) -> {
           Stage moreStage = new Stage();
           System.out.println("More INFO CLICK");
           Scene moreScene = new Scene(new MoreCardInfoPane(creditCard), 500,500);
           moreScene.getStylesheets().add(getClass().getResource("/fonts/style.css").toExternalForm());
           moreStage.setTitle("Full Card Info");
           moreStage.setScene(moreScene);
           moreStage.initModality(Modality.APPLICATION_MODAL);
           moreStage.show();
           System.out.println("More Info...");
        });




        transInfoButton.setOnAction(e -> {
            Stage moreStage = new Stage();
            System.out.println("TRAN CLICK");
         
            Scene moreScene = new Scene(new TransactionPane(creditCard));
            moreStage.setTitle("Transaction Scene");
            moreStage.setScene(moreScene);
            moreStage.initModality(Modality.APPLICATION_MODAL);
            moreStage.show();
            System.out.println("More Info...");
        });

        changeButton.setOnAction(e -> {
            System.out.println("CHANGE CLICK");
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
            System.out.println("DELETE CLICK");
         //   Stage moreStage = new Stage();
          //  Scene moreScene = new Scene(new TransactionPane(creditCard));
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


        //Image
        transInfoButton.getStyleClass().add("add-button");
        deleteButton.getStyleClass().add("add-button");
        moreInfoButton.getStyleClass().add("add-button");
        deleteButton.setGraphic(delete_logo);
        transInfoButton.setGraphic(trans_logo);
        moreInfoButton.setGraphic(search_logo);
        changeButton.getStyleClass().add("change-button");

        cardImageView.setPreserveRatio(true);
        cardImageView.setFitHeight(50);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(nameLabel, serialLabel);
        nameLabel.getStyleClass().add("name-label");
        serialLabel.getStyleClass().add("name-label");
                

        if ("Normal".equalsIgnoreCase(creditCard.getState())) {
            state = new ImageView(new Image("/images/Approve.png", 20, 20, false, false));
        } else if("Forfeit".equalsIgnoreCase(creditCard.getState())){
            state = new ImageView(new Image("/images/Reject.png", 20, 20, false, false));
        }

        HBox hBox = new HBox();
//        GridPane grid = new GridPane();
//        grid.setHgap(20);
//        grid.setVgap(10);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(cardImageView, vBox, state, moreInfoButton, transInfoButton, changeButton, deleteButton);
        setPadding(new Insets(10));
//        GridPane.setConstraints(cardImageView,0,0);
//        GridPane.setConstraints(vBox,1,0);
//        GridPane.setConstraints(state,2,0);
//        GridPane.setConstraints(moreInfoButton,3,0);
//        GridPane.setConstraints(transInfoButton,4,0);
//        GridPane.setConstraints(changeButton,5,0);
//        GridPane.setConstraints(deleteButton,6,0);
//        grid.getChildren().addAll(cardImageView, vBox, state, moreInfoButton, transInfoButton, changeButton, deleteButton);
        //getChildren().addAll(grid);

      


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
