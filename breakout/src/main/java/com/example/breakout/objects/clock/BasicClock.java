package com.example.breakout.objects.clock;

import com.example.breakout.Constants;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BasicClock extends Clock {

    private static int msElapsed = 0;

    public BasicClock(Position position) {
        super(position, new Velocity(0, 0), Color.WHITE, new Text());
    }

    @Override
    public void onTick() {
        msElapsed += Constants.FRAME_DURATION;
    }

    public static int getSecondsElapsed() {
        return msElapsed / 1000;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(30));
        gc.fillText("Time Elapsed: " + String.valueOf(msElapsed / 1000.0), this.getPosition().getX(), this.getPosition().getY());

    }
}
