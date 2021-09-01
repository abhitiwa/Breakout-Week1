package com.example.breakout.behaviors.collision;

import com.example.breakout.CollisionType;
import com.example.breakout.GameWindow;
import com.example.breakout.controllers.GameManager;
import com.example.breakout.objects.BreakOutObject;
import com.example.breakout.objects.clock.BasicClock;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

import java.util.Optional;

public class EndGame implements CollisionBehavior
{
    @Override
    public void doCollisionBehavior(BreakOutObject colliderOrImpactee, CollisionType collisionType)
    {
        Stage stage = GameWindow.getStage();

        /*********************************************************************************************************************
         * Since the thread that would run this part is different from the main
         * Application thread, the instantiation of an Alert would throw an
         * IllegalStateException. To tackle this, the runLater would update the GUI with
         * some other thread than the application thread.
         *
         * <b>Source:</b> https://stackoverflow.com/questions/49343256/threads-in-javafx-not-on-fx-application-thread
         **********************************************************************************************************************/

        Platform.runLater(() -> {
            String contentText = "You survived for %d second(s)";
            Alert alert = new Alert(AlertType.ERROR, String.format(contentText, BasicClock.getSecondsElapsed()),
                    new ButtonType("New Game", ButtonBar.ButtonData.CANCEL_CLOSE));
            alert.setTitle("Game Over");
            alert.setHeaderText("Looks like you hit the floor");
            alert.initOwner(stage);
            alert.showAndWait().ifPresent(result -> {
                alert.close();
                try {
                    new GameWindow().start(new Stage()); //Need to get the restart working
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            alert.close();
            });

        GameManager.stopGameLoop();
    }
}
