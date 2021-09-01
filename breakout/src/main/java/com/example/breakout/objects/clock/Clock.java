package com.example.breakout.objects.clock;

import com.example.breakout.Constants;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.objects.BreakOutObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Clock extends BreakOutObject {


    public Clock(Position position, Velocity velocity, Color color, Shape shape) {
        super(position, velocity, color, shape);
    }
}

