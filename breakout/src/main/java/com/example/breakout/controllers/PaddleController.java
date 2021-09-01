package com.example.breakout.controllers;

import com.example.breakout.Constants;
import com.example.breakout.objects.paddle.StandardPaddle;
import com.example.breakout.GameWindow;
import com.example.breakout.objects.paddle.Paddle;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.scene.input.KeyCode.*;

public class PaddleController extends Controller
{
    public static List<Paddle> paddleList = new LinkedList<>();

    private static final HashMap<KeyCode, AtomicBoolean> keyCodeAtomicBooleanHashMap = new HashMap();

    private static AtomicBoolean goUp = new AtomicBoolean();
    private static AtomicBoolean goDown = new AtomicBoolean();
    private static AtomicBoolean goLeft = new AtomicBoolean();
    private static AtomicBoolean goRight = new AtomicBoolean();
    private boolean canMoveLeftRight = true;
    private boolean canMoveUpDown = false;

    public PaddleController()
    {
        paddleList = new LinkedList<>();
        setKeyConstants();
        GameWindow.getScene().setOnKeyPressed(event -> {
            keyCodeAtomicBooleanHashMap.get(event.getCode()).set(true);
        });

        GameWindow.getScene().setOnKeyReleased(event -> {
            keyCodeAtomicBooleanHashMap.get(event.getCode()).set(false);
        });

        addPaddle();
        for (Paddle paddle : paddleList) {
            GameManager.attachBreakoutObject(paddle);
        }
    }

    private void setKeyConstants() {
        //Add arrow key support
        keyCodeAtomicBooleanHashMap.put(UP, goUp);
        keyCodeAtomicBooleanHashMap.put(DOWN, goDown);
        keyCodeAtomicBooleanHashMap.put(LEFT, goLeft);
        keyCodeAtomicBooleanHashMap.put(RIGHT, goRight);
        //Add WASD support
        keyCodeAtomicBooleanHashMap.put(W, goUp);
        keyCodeAtomicBooleanHashMap.put(S, goDown);
        keyCodeAtomicBooleanHashMap.put(A, goLeft);
        keyCodeAtomicBooleanHashMap.put(D, goRight);
    }

    private void addPaddle()
    {
        //Construct paddle
        Paddle paddle = new StandardPaddle(new Position(Constants.INITIAL_SCREEN_WIDTH/2, Constants.INITIAL_SCREEN_HEIGHT - Constants.PADDLE_HEIGHT*2));
        paddle.setWidth(GameWindow.getWindowWidth()/10); //10% of window size
        paddle.setHeight(Constants.PADDLE_HEIGHT);
        //Add to paddle list
        paddleList.add(paddle);
    }

    @Override
    public void doFrameLogic()
    {
        for (Paddle paddle : paddleList)
        {
            //Adjust paddle position
            Velocity v = paddle.getVelocity();
            if (canMoveLeftRight) {
                if (isMovableToLeft(paddle))
                    v.setVelX(-Constants.PADDLE_BASE_VELOCITY);
                else if (isMovableToRight(paddle))
                    v.setVelX(Constants.PADDLE_BASE_VELOCITY);
                else
                    v.setVelX(0);
            }
            if (canMoveUpDown) {
                if (goUp.get())
                    v.setVelY(-Constants.PADDLE_BASE_VELOCITY);
                else if (goDown.get())
                    v.setVelY(Constants.PADDLE_BASE_VELOCITY);
                else
                    v.setVelY(0);
            }

            //Adjust paddle position based on its velocity
            paddle.onTick();

            //Draw the paddle
            if (!paddle.isDestroyed())
                paddle.draw(GameWindow.getGraphicsContext());
        }
    }

    private boolean isMovableToRight(Paddle paddle) {
        return goRight.get()
                && paddle.getPosition().getX() < (GameWindow.getWindowWidth() - paddle.getWidth());
    }

    private boolean isMovableToLeft(Paddle paddle) {
        return goLeft.get()
                && paddle.getPosition().getX() > 0;
    }
}
