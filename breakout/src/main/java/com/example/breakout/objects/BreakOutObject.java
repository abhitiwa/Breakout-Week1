package com.example.breakout.objects;

import com.example.breakout.CollisionType;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.behaviors.collision.CollisionBehavior;
import com.example.breakout.behaviors.collision.DoNothingCollision;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public abstract class BreakOutObject implements Drawable {

    Position position;
    Velocity velocity;
    Color color;
    Shape shape;
    int width;
    int height;
    double mass;
    boolean isDestroyed = false;

    private CollisionBehavior onHitSomethingBehavior;
    private CollisionBehavior onGetHitBehavior;


    public BreakOutObject(Position position, Velocity velocity, Color color, Shape shape) {
        this.position = position;
        this.velocity = velocity;
        this.color = color;
        this.shape = shape;
        onHitSomethingBehavior = new DoNothingCollision();
        onGetHitBehavior = new DoNothingCollision();
    }


    /**BreakOutObject
     * Every tick, position is updated based on object velocity
     */
   public void onTick(){
     double newX = velocity.getVelX() + position.getX();
     double newY = velocity.getVelY() + position.getY();

     position = new Position(newX, newY);
   }




    public void onHitSomething(BreakOutObject impactee, CollisionType collisionType)
    {
        onHitSomethingBehavior.doCollisionBehavior(this, collisionType);
    }

    public void onGetHit(BreakOutObject collider, CollisionType collisionType)
    {
        onGetHitBehavior.doCollisionBehavior(this, collisionType);
    }



    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public CollisionBehavior getOnHitSomethingBehavior() {
        return onHitSomethingBehavior;
    }

    public void setOnHitSomethingBehavior(CollisionBehavior onHitSomethingBehavior) {
        this.onHitSomethingBehavior = onHitSomethingBehavior;
    }

    public CollisionBehavior getOnGetHitBehavior() {
        return onGetHitBehavior;
    }

    public void setOnGetHitBehavior(CollisionBehavior onGetHitBehavior) {
        this.onGetHitBehavior = onGetHitBehavior;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}

