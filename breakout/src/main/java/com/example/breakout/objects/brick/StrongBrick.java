package com.example.breakout.objects.brick;

import com.example.breakout.Position;
import com.example.breakout.Velocity;
import javafx.scene.shape.Rectangle;

public class StrongBrick extends Brick {
    public StrongBrick(Position position) {
        super(position, new Velocity(0, 0), new Rectangle(),4);
    }
}