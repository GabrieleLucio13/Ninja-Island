package utility;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import java.io.*;
import java.net.URL;

public class ResourceLoader {

    // IMMAGINI
    public static Image loadImage(String path) {
        try {
            return new Image(ResourceLoader.class.getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dell'immagine: " + path);
            e.printStackTrace();
            return null;
        }
    }

    // AUDIO
    public static Media loadMedia(String path) {
        try {
            URL resource = ResourceLoader.class.getResource(path);

            if (resource == null) {
                throw new RuntimeException("Risorsa audio non trovata: " + path);
            }

            return new Media(resource.toExternalForm());

        } catch (Exception e) {
            System.err.println("Errore nel caricamento dell'audio: " + path);
            e.printStackTrace();
            return null;
        }
    }

    // MAPPE
    public static BufferedReader loadText(String path) {
        try {
            InputStream is = ResourceLoader.class.getResourceAsStream(path);

            if (is == null) {
                throw new RuntimeException("Mappa non trovata: " + path);
            }

            return new BufferedReader(new InputStreamReader(is));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

