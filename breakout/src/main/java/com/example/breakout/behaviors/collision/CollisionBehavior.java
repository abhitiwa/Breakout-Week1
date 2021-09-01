package com.example.breakout.behaviors.collision;

import com.example.breakout.CollisionType;
import com.example.breakout.objects.BreakOutObject;

public interface CollisionBehavior
{
    void doCollisionBehavior(BreakOutObject colliderOrImpactee, CollisionType collisionType);
}
