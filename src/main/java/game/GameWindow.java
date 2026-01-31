package game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameWindow {
    private Stage primaryStage;
    private Canvas canvas;
    private double canvasWidth;
    private double canvasHeight;

    public GameWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(640, 640);
        this.canvasWidth = canvas.getWidth();
        this.canvasHeight = canvas.getHeight();
    }
    public void setUpWindow() {
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);

        primaryStage.setTitle("NINJA ISLAND");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public double getCanvasWidth() {
        return canvasWidth;
    }
    public double getCanvasHeight() {
        return canvasHeight;
    }

    public Scene getScene() {
        return primaryStage.getScene();
    }
}
