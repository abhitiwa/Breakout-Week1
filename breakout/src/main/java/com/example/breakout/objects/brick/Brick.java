package com.example.breakout.objects.brick;

import com.example.breakout.CollisionType;
import com.example.breakout.Constants;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.behaviors.collision.CollisionBehavior;
import com.example.breakout.behaviors.collision.DestroySelf;
import com.example.breakout.controllers.Controller;
import com.example.breakout.objects.BreakOutObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Brick extends BreakOutObject {

    private int integrity;

    private CollisionBehavior onBreakBehavior; //what happens when integrity reaches 0

    public Brick(Position position, Velocity velocity, Shape shape, int integrity) {
        //integrity (# of hits to destroy brick) cannot be less than 1
        super(position,
                velocity,
                integrity > 1 ? Constants.BRICK_DEFAULT_COLOR : Constants.BRICK_DAMAGED_COLOR,
                shape
        );
        this.integrity = integrity;
        setMass(15);
        this.onBreakBehavior = new DestroySelf();
    }


    @Override
    public void onGetHit(BreakOutObject collider, CollisionType collisionType)
    {
        //Damage the brick
        integrity = integrity - 1;
        if (integrity <= 0)
        {
            onBreakBehavior.doCollisionBehavior(this, collisionType);
        }else {
            //color changes based on integrity remaining
            this.setColor(integrity > 1 ? Constants.BRICK_INTEGRITY_TO_COLOR_MAP.get(integrity) : Constants.BRICK_DAMAGED_COLOR);
        }
        //Break the brick (or do some other action) if integrity reaches 0

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(  Constants.BRICK_INTEGRITY_TO_COLOR_MAP.get(integrity));
        gc.fillRect(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }

    public int getIntegrity() {
        return integrity;
    }

    public void setIntegrity(int integrity) {
        this.integrity = integrity;
    }

    public CollisionBehavior getOnBreakBehavior() {
        return onBreakBehavior;
    }

    public void setOnBreakBehavior(CollisionBehavior onBreakBehavior) {
        this.onBreakBehavior = onBreakBehavior;
    }
}