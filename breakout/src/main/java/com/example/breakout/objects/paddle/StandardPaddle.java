package com.example.breakout.objects.paddle;

import com.example.breakout.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class StandardPaddle extends Paddle{

    public StandardPaddle(Position position) {
        super(position, Color.BLUEVIOLET, new Rectangle(), 3);
        setMass(20);
    }

    public void draw(GraphicsContext gc)
    {
        final int PADDLE_CURVATURE = 10;
        gc.setFill(getColor());
        gc.fillRoundRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight(), PADDLE_CURVATURE, PADDLE_CURVATURE);
    }
}
