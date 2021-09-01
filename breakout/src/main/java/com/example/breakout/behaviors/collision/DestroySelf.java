package com.example.breakout.behaviors.collision;

import com.example.breakout.CollisionType;
import com.example.breakout.controllers.GameManager;
import com.example.breakout.objects.BreakOutObject;
import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class DestroySelf implements CollisionBehavior
{
    private static final ClassLoader CLASS_LOADER = DestroySelf.class.getClassLoader();
    private static final File MUSIC_FILE = new File(CLASS_LOADER.getResource("explosion.mp3").getFile());

    @Override
    public void doCollisionBehavior(BreakOutObject colliderOrImpactee, CollisionType collisionType)
    {
        Platform.runLater(() -> { collisionAudio(); });
        GameManager.removeBreakoutObject(colliderOrImpactee);

    }

    private void collisionAudio() {
        /*********************************************************************************************************************
         * When the ball hits the brick, the audio should be played multiple times.
         * However, it should be on a separate thread hence, the run later.
         * AudioClip library was used for this effect.
         *
         * <b>Source:</b> https://openjfx.io/javadoc/12/javafx.media/javafx/scene/media/AudioClip.html
         **********************************************************************************************************************/

        AudioClip plonkSound = new AudioClip(MUSIC_FILE.toURI().toString());
        plonkSound.play();
    }
}
