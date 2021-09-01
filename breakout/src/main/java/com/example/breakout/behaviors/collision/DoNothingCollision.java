package com.example.breakout.behaviors.collision;

import com.example.breakout.CollisionType;
import com.example.breakout.objects.BreakOutObject;

public class DoNothingCollision implements CollisionBehavior
{
    @Override
    public void doCollisionBehavior(BreakOutObject collider, CollisionType collisionType) {
        //Do nothing
    }
}
