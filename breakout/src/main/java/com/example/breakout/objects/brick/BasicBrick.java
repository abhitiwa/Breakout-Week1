package com.example.breakout.objects.brick;

import com.example.breakout.Position;
import com.example.breakout.Velocity;
import javafx.scene.shape.Rectangle;

public class BasicBrick extends Brick {
    public BasicBrick(Position position) {
        super(position, new Velocity(0, 0), new Rectangle(), 1);
    }
}