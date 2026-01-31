module ninjaisland {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;

    opens game to javafx.fxml;

    exports game;
}

