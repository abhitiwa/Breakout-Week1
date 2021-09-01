package com.example.breakout.objects.wall;

import com.example.breakout.Position;
import com.example.breakout.Velocity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class StandardWall extends Wall {


    public StandardWall(Position position, Velocity velocity, Color color, Shape shape, int width, int height) {
        super(position, velocity, color, shape, width, height);
    }
}