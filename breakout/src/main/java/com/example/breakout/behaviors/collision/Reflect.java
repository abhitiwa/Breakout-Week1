package com.example.breakout.behaviors.collision;

import com.example.breakout.CollisionType;
import com.example.breakout.objects.BreakOutObject;

import java.util.Random;

public class Reflect implements  CollisionBehavior
{
    private final static int MIN_REFLECT_ANGLE_RANGE = 3;
    private final static Random random = new Random();

    @Override
    public void doCollisionBehavior(BreakOutObject colliderOrImpactee, CollisionType collisionType)
    {
        //Define some abbreviations
        double colliderVelX = colliderOrImpactee.getVelocity().getVelX();
        double colliderVelY = colliderOrImpactee.getVelocity().getVelY();

        //Prepare to generate an angle of reflection
        int positiveOrNegative = random.nextInt(3) - 1;

        //Check where the collider hit the impactee
        if (collisionType == CollisionType.TopImpact || collisionType == CollisionType.BottomImpact)
        {
            //Reflect the collider in the opposite direction
            colliderOrImpactee.getVelocity().setVelY(-colliderVelY);
            /*
             Angle adjustment:
             The x-velocity can be modified (added/subtracted) by as much as its current x-velocity.
             */
            int maxReflectAngle = (int)(colliderVelX) / 2;
            if (Math.abs(maxReflectAngle) < MIN_REFLECT_ANGLE_RANGE)
                maxReflectAngle = MIN_REFLECT_ANGLE_RANGE;
            int reflectAngle = positiveOrNegative * (int)(Math.random() * maxReflectAngle);
            colliderOrImpactee.getVelocity().setVelX(colliderVelX + reflectAngle);
        }
        else if (collisionType == CollisionType.LeftImpact || collisionType == CollisionType.RightImpact)
        {
            //Reflect the collider in the opposite direction
            colliderOrImpactee.getVelocity().setVelX(-colliderVelX);
            /*
             Angle adjustment:
             The y-velocity can be modified (added/subtracted) by as much as its current y-velocity.
             */
            int maxReflectAngle = (int)(colliderVelY) / 2;
            if (Math.abs(maxReflectAngle) < MIN_REFLECT_ANGLE_RANGE)
                maxReflectAngle = MIN_REFLECT_ANGLE_RANGE;
            int reflectAngle = positiveOrNegative * (int)(Math.random() * maxReflectAngle);
            colliderOrImpactee.getVelocity().setVelY(colliderVelY + reflectAngle);
        }
        else
            throw new IllegalArgumentException("CollisionType unknown or invalid.");
    }
}
