package com.example.breakout;

public class Velocity {
    private double velX;

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    private double velY;

    public Velocity(double velocityX, double velocityY) {
        this.velX = velocityX;
        this.velY = velocityY;
    }


    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }
}
