package Practice;

import java.io.File;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Video_test extends StackPane {
    String path = "./resources/vid.mp4";

    Media media = new Media(new File(path).toURI().toString());

    MediaPlayer mediaPlayer = new MediaPlayer(media);

    MediaView mediaView = new MediaView(mediaPlayer);

    public Video_test() {
        start();
    }

    public void start() {

        // // by setting this property to true, the Video will be played
        mediaPlayer.setAutoPlay(true);
        getChildren().add(mediaView);
    }

}