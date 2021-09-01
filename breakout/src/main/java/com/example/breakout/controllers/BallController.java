package com.example.breakout.controllers;

import com.example.breakout.objects.ball.Ball;
import com.example.breakout.objects.ball.StandardBall;
import com.example.breakout.GameWindow;
import com.example.breakout.Position;

import java.util.LinkedList;

public class BallController extends Controller
{
    public static LinkedList<Ball> ballList = new LinkedList<>();

    public BallController()
    {
        if (ballList.isEmpty()) {
            ballList = new LinkedList<>();
            addBall();
        }

        for (Ball ball : ballList) {
            GameManager.attachBreakoutObject(ball);
            ball.draw(GameWindow.getGraphicsContext());
        }
    }

    private void addBall()
    {
        Ball ball = new StandardBall(new Position(GameWindow.getWindowWidth()/2,GameWindow.getWindowHeight()/2));
        ballList.add(ball);
    }

    public static void addMultipleBalls(int count)
    {
        for (int i = 1; i <= count; i++) {
            Ball ball = new StandardBall(new Position(GameWindow.getWindowWidth()/2,GameWindow.getWindowHeight()/2));
            ballList.add(ball);
        }
    }

    public void doFrameLogic()
    {
        for (Ball ball : ballList)
        {
            //Adjust ball position based on its velocity
            ball.onTick();

            //Draw the ball
            if (!ball.isDestroyed())
                ball.draw(GameWindow.getGraphicsContext());
        }
    }
}
