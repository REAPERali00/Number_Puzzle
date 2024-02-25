package client;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Game_Timer {
    @FXML
    private Label timerLabel;

    private Timer timeObj;
    private int seconds;

    public void initialize() {
        startTimer();
    }

    public void startTimer() {

        timeObj = new Timer();
        timeObj.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    seconds++;
                    timerLabel.setText(getTime());
                });
            }

        }, 1000, 1000);
    }

    public String getTime() {

        int hour = seconds / 3600;
        int min = (seconds % 3600) / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    public void stopTimer() {
        if (timeObj != null) {
            timeObj.cancel();
            timeObj.purge();
        }
        seconds = 0;
    }

}
