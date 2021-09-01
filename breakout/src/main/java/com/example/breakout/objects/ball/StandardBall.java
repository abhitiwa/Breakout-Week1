package com.example.breakout.objects.ball;

import com.example.breakout.Constants;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.behaviors.collision.Reflect;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StandardBall extends Ball
{
    public StandardBall(Position position) {
        super(position, new Velocity(0, 10), Color.DODGERBLUE, new Circle(Constants.BALL_SIZE));
        setWidth(Constants.BALL_SIZE);
        setHeight(Constants.BALL_SIZE);
        setMass(10);
        setOnHitSomethingBehavior(new Reflect());
    }
}
