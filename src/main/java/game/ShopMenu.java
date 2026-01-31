package game;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import maps.Shop;

public class ShopMenu {
    private final Shop shop;

    public ShopMenu(Shop shop) {
        this.shop = shop;
    }

    public void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Shop");
        window.setMinWidth(500);
        window.setHeight(500);

        Label label = new Label("Benvenuto nel negozio! Seleziona un potenziamento:");
        label.setFont(new Font("Brush Script", 20));
        label.setStyle("-fx-text-fill: white;");

        Button swordUpgradeButton = new Button("Potenziamento Spada (" + shop.getSwordUpgradeCost() + " monete)");
        swordUpgradeButton.setOnAction(e -> {
            if (shop.buySwordUpgrade()) {
                swordUpgradeButton.setText("Spada potenziata! (" + shop.getSwordUpgradeCost() + " monete)");
            } else {
                if(shop.getSwordUpgradeCount() > 2){
                    showError2();
                } else{
                    showError1();
                }
            }
        });

        Button kunaiUpgradeButton = new Button("Potenziamento Kunai (" + shop.getKunaiUpgradeCost() + " monete)");
        kunaiUpgradeButton.setOnAction(e -> {
            if (shop.buyKunaiUpgrade()) {
                kunaiUpgradeButton.setText("Kunai potenziati! (" + shop.getKunaiUpgradeCost() + " monete)");
            } else{
                if(shop.getKunaiUpgradeCount() > 2){
                    showError2();
                } else{
                    showError1();
                }
            }
        });

        Button healthUpgradeButton = new Button("Aumento Salute (" + shop.getHealthUpgradeCost() + " monete)");
        healthUpgradeButton.setOnAction(e -> {
            if (shop.buyHealthUpgrade()) {
                healthUpgradeButton.setText("Salute aumentata! (" + shop.getHealthUpgradeCost() + " monete)");
            } else {
                if(shop.getHealthUpgradeCount() > 2){
                    showError2();
                } else{
                    showError1();
                }
            }
        });

        // Layout della finestra e aggiunta dello sfondo
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-alignment: center;");
        menuLayout.getChildren().addAll(label, swordUpgradeButton, kunaiUpgradeButton, healthUpgradeButton);

        // Imposta lo sfondo
        Image backgroundImage = utility.ResourceLoader.loadImage("/background/shopBackGround.jpg");
        BackgroundImage bg = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        menuLayout.setBackground(new Background(bg));

        Scene scene = new Scene(menuLayout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }


    public void showError1() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Monete insufficienti");
        alert.setContentText("Non hai abbastanza monete per acquistare questo potenziamento");

        alert.showAndWait();
    }
    public void showError2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Potenziamenti Esauriti");
        alert.setContentText("Hai raggiunto il limite dei potenziameni consentiti ");

        alert.showAndWait();
    }
}