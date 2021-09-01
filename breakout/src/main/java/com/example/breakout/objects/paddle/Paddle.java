package com.example.breakout.objects.paddle;

import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.controllers.Controller;
import com.example.breakout.objects.BreakOutObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Paddle extends BreakOutObject {

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    double movementSpeed;

    /**
     *
     * @param position position of object
     * @param color color of the paddle
     * @param shape shape of the paddle
     * @param onKeyPressSpeed how fast paddle will move upon user input
     */
    public Paddle(Position position, Color color, Shape shape, double onKeyPressSpeed) {
        super(position, new Velocity(0,0), color, shape);
        this.movementSpeed = onKeyPressSpeed;
    }

    public void draw(GraphicsContext gc)
    {
        gc.setFill(getColor());
        gc.fillOval(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}
