package com.example.breakout.controllers;

//
//Game loop task scheduler credit to https://stackoverflow.com/a/24104427/16519580 user Boris the Spider
//


import com.example.breakout.CollisionDetectionUtility;
import com.example.breakout.CollisionType;
import com.example.breakout.Constants;
import com.example.breakout.behaviors.collision.DoNothingCollision;
import com.example.breakout.objects.BreakOutObject;
import com.example.breakout.GameWindow;
import com.example.breakout.objects.clock.BasicClock;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameManager
{
    //All controllers
    private static PaddleController paddleController;
    private static BallController ballController;
    private static WallController wallController;
    private static BrickController brickController;
    private static ClockController clockController;
    private static List<Controller> allControllers = new LinkedList<>();

    //All game objects
    public static final List<BreakOutObject> allBreakoutObjects = new LinkedList<>();

    //Game loop scheduler
    private static ScheduledExecutorService executorService = null;


    public GameManager()
    {
        //Set up the controllers (could also be a list of controllers)
        paddleController = new PaddleController();
        ballController = new BallController();
        wallController = new WallController();
        brickController = new BrickController();
        clockController = new ClockController();
        //Add each controller to the list
        allControllers.add(paddleController);
        allControllers.add(ballController);
        allControllers.add(wallController);
        allControllers.add(brickController);
        allControllers.add(clockController);


    }

    public static void initiateGameLoop()
    {
        //Schedule the game logic task to occur every frame
        executorService = Executors.newSingleThreadScheduledExecutor();
        final long INITIAL_DELAY = 500;
        executorService.scheduleAtFixedRate(GameManager::doFrameLogic, INITIAL_DELAY, Constants.FRAME_DURATION, TimeUnit.MILLISECONDS);
    }

    private static void doFrameLogic()
    {
        //Reset background
        GameWindow.fillBackground();

        //Draw elements, check for collisions, etc. (could also be a list of controllers)

        for(Controller controller: allControllers) {
           controller.doFrameLogic();
        }

        /*
        Check for collisions...
        Define two iterators to iterate through allBreakoutObjects.
        colliderIterator checks if its object is hitting something else.
        impacteeIterator checks if its object is being hit.
         */
        Iterator<BreakOutObject> colliderIterator = allBreakoutObjects.listIterator();
        int i = 0; //index of collider
        int j = 0; //index of impactee
        while (colliderIterator.hasNext())
        {
            //Pull one object from the
            BreakOutObject collider = colliderIterator.next();

            Iterator<BreakOutObject> impacteeIterator = allBreakoutObjects.listIterator();
            while (impacteeIterator.hasNext())
            {
                BreakOutObject impactee = impacteeIterator.next();
                //Make sure collider != impactee
                if (i != j) {
                    //Check for the collision
                    CollisionType collisionType = CollisionDetectionUtility.checkForCollision(collider, impactee);
                    if (collisionType != CollisionType.NoCollision) {
                        //Fire the collision events
                        collider.onHitSomething(impactee, collisionType);
                        impactee.onGetHit(collider, collisionType);
                    }
                }
                j++; //Update impactee index
            }
            i++; //Update collider index
        }
    }

    public static void stopGameLoop()
    {
        if (executorService != null)
            executorService.shutdown();
    }

    /**
     * When this method is called, gameObject will become a child of the GameWindow.
     */
    public static void attachBreakoutObject(BreakOutObject gameObject)
    {
        allBreakoutObjects.add(gameObject);
    }

    /**
     * Stops the rendering of gameObject.
     * Also, destroys the object from this manager and its respective controller.
     */
    public static void removeBreakoutObject(BreakOutObject gameObject)
    {
        //Mark the object as destroyed and disable its collision events
        gameObject.setDestroyed(true);
        gameObject.setOnHitSomethingBehavior(new DoNothingCollision());
        gameObject.setOnGetHitBehavior(new DoNothingCollision());
    }
}
