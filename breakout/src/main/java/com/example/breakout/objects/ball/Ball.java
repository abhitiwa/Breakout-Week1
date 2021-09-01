package com.example.breakout.objects.ball;

import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.objects.BreakOutObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Ball extends BreakOutObject
{

    public Ball(Position position, Velocity velocity, Color color, Shape shape) {
        super(position, velocity, color, shape);
    }

    public void draw(GraphicsContext gc)
    {
        gc.setFill(getColor());
        gc.fillOval(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}


