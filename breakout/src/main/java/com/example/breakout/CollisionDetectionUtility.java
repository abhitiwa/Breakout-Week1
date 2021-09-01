package com.example.breakout;

import com.example.breakout.objects.BreakOutObject;

public class CollisionDetectionUtility
{
    /**
     * Determines the collision type by checking where the collision happened.
     * No collision occurs if either object is marked as destroyed.
     * @param impactee the object that collider might be hitting/touching since collider object is moving
     * @return any one of the possible CollisionTypes.
     * Ex: Return LeftImpact if the collider hits the impactee's left side.
     */
    public static CollisionType checkForCollision(BreakOutObject collider, BreakOutObject impactee)
    {
        //If either object is destroyed, there is no collision
        if (collider.isDestroyed() || impactee.isDestroyed())
            return CollisionType.NoCollision;

        //Define some abbreviations
        double colliderX = collider.getPosition().getX();
        double colliderY = collider.getPosition().getY();
        double impacteeX = impactee.getPosition().getX();
        double impacteeY = impactee.getPosition().getY();
        double colliderVelX = collider.getVelocity().getVelX();
        double colliderVelY = collider.getVelocity().getVelY();
        int colliderWidth = collider.getWidth();
        int colliderHeight = collider.getHeight();
        int impacteeWidth = impactee.getWidth();
        int impacteeHeight = impactee.getHeight();

        //If the collider's y-velocity is so fast that it will speed right through the impactee...
        if (Math.abs(colliderVelY) > impacteeHeight)
        {
            if (colliderX >= impacteeX && colliderX <= impacteeX + impacteeWidth) {
                if (colliderY < impacteeY && colliderY + colliderVelY >= impacteeY)
                    return CollisionType.TopImpact;
                else if (colliderY > impacteeY && colliderY + colliderVelY <= impacteeY)
                    return CollisionType.BottomImpact;
            }
        }
        //If the collider's x-velocity is so fast that it will speed right through the impactee...
        else if (Math.abs(colliderVelX) > impacteeWidth)
        {
            if (colliderY >= impacteeY && colliderY <= impacteeY + impacteeHeight) {
                if (colliderX < impacteeX && colliderX + colliderVelX >= impacteeX)
                    return CollisionType.RightImpact;
                else if (colliderX > impacteeX && colliderX + colliderVelX <= impacteeX)
                    return CollisionType.LeftImpact;
            }
        }

        //Check if the collider is moving more vertically than horizontally. If so, it may be a vertical impact.
        if (Math.abs(colliderVelY) - Math.abs(colliderVelX) > 0)
        {
            //Return a left or right collision if it is occurring.
            CollisionType output = checkForVerticalCollision(collider, impactee);
            if (output != CollisionType.NoCollision)
                return output;
                //Collision could instead be horizontal contrary to the prediction
            else
                return checkForHorizontalCollision(collider, impactee);
        }
        //Else if the collider has more x-velocity than y-velocity, it may be a horizontal impact
        else
        {
            //Return a top or bottom collision if it is occurring.
            CollisionType output = checkForHorizontalCollision(collider, impactee);
            if (output != CollisionType.NoCollision)
                return output;
                //Collision could instead be vertical contrary to the prediction
            else
                return checkForVerticalCollision(collider, impactee);
        }
    }


    private static CollisionType checkForVerticalCollision(BreakOutObject collider, BreakOutObject impactee)
    {
        //Define some abbreviations
        double colliderX = collider.getPosition().getX();
        double colliderY = collider.getPosition().getY();
        double impacteeX = impactee.getPosition().getX();
        double impacteeY = impactee.getPosition().getY();
        int colliderWidth = collider.getWidth();
        int colliderHeight = collider.getHeight();
        int impacteeWidth = impactee.getWidth();
        int impacteeHeight = impactee.getHeight();

        //Check if the x-coordinate of the collider is inside the impactee
        if (colliderX >= impacteeX && colliderX <= impacteeX + impacteeWidth)
        {
            //Check if collider touches the TOP half of the impactee (top collision)
            if (colliderY + colliderHeight >= impacteeY && colliderY + colliderHeight <= impacteeY + (impacteeHeight / 2))
                return CollisionType.TopImpact;
                //Check if collider touches the BOTTOM half of the impactee (bottom collision)
            else if (colliderY >= impacteeY + (impacteeHeight / 2) && colliderY <= impacteeY + impacteeHeight)
                return CollisionType.BottomImpact;
        }

        //Default: The objects aren't touching
        return CollisionType.NoCollision;
    }


    private static CollisionType checkForHorizontalCollision(BreakOutObject collider, BreakOutObject impactee)
    {
        //Define some abbreviations
        double colliderX = collider.getPosition().getX();
        double colliderY = collider.getPosition().getY();
        double impacteeX = impactee.getPosition().getX();
        double impacteeY = impactee.getPosition().getY();
        int colliderWidth = collider.getWidth();
        int colliderHeight = collider.getHeight();
        int impacteeWidth = impactee.getWidth();
        int impacteeHeight = impactee.getHeight();

        //Check if the y-coordinate of the collider is inside the impactee
        if (colliderY >= impacteeY - colliderHeight && colliderY <= impacteeY + impacteeHeight)
        {
            //Check if collider touches the LEFT half of the impactee (left collision)
            if (colliderX + colliderWidth >= impacteeX && colliderX + colliderWidth <= impacteeX + (impacteeWidth / 2))
                return CollisionType.LeftImpact;
                //Check if collider touches the RIGHT half of the impactee (right collision)
            else if (colliderX >= impacteeX + (impacteeWidth / 2) && colliderX <= impacteeX + impacteeWidth)
                return CollisionType.RightImpact;
        }

        //Default: The objects aren't touching
        return CollisionType.NoCollision;
    }


}