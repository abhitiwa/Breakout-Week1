package com.example.breakout.objects.wall;

import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.objects.BreakOutObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Wall extends BreakOutObject {

    public Wall(Position position, Velocity velocity, Color color, Shape shape, int width, int height) {
        super(position, velocity, color, shape);
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}