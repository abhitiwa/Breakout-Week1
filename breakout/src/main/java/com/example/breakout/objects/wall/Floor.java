package com.example.breakout.objects.wall;

import com.example.breakout.CollisionType;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.behaviors.collision.EndGame;
import com.example.breakout.objects.BreakOutObject;
import com.example.breakout.objects.ball.Ball;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Floor extends Wall{

    public Floor(Position position, Velocity velocity, Color color, Shape shape, int width, int height) {
        super(position, velocity, color, shape, width, height);
        setOnGetHitBehavior(new EndGame()); //The game ends if the floor is touched
    }

    @Override
    public void onHitSomething(BreakOutObject impactee, CollisionType collisionType) {
        //Do nothing
    }

    @Override
    public void onGetHit(BreakOutObject collider, CollisionType collisionType) {
        //If the ball hits the floor, fire an event such as ending the game
        if (collider instanceof Ball) {
            getOnGetHitBehavior().doCollisionBehavior(collider, collisionType);
        }
    }
}