package com.example.breakout.controllers;

import com.example.breakout.GameWindow;
import com.example.breakout.Position;
import com.example.breakout.objects.brick.BasicBrick;
import com.example.breakout.objects.brick.Brick;
import com.example.breakout.objects.brick.StrongBrick;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import static com.example.breakout.Constants.*;

public class BrickController extends Controller
{
    public static LinkedList<Brick> brickList = new LinkedList<>();
    private static final HashSet<Pair> specialPairs = new HashSet<>(Arrays.asList(new Pair(2, 1), new Pair(2, 2), new Pair(2, 3),
            new Pair(6, 1), new Pair(6, 2), new Pair(6, 3), new Pair(6, 2), new Pair(6, 3), new Pair(10, 1), new Pair(11, 1),
            new Pair(9, 3), new Pair(10, 3), new Pair(14, 1), new Pair(14, 2), new Pair(14, 3)));


    public BrickController()
    {
        addRowsOfBricks(5, 17);
        for (Brick brick : brickList) {
            GameManager.attachBreakoutObject(brick);
        }
    }

    private void addRowsOfBricks(int numberOfRows, int bricksPerRow)
    {
        //Determine the brick height/width.
        //Brick width is computed by dividing the amount of non-margin space by the number of bricksPerRow
        final int WINDOW_WIDTH = GameWindow.getWindowWidth();
        final int BRICK_WIDTH = (WINDOW_WIDTH - ((bricksPerRow+3) * BRICK_MARGIN)) / bricksPerRow;
        final int BRICK_HEIGHT = BRICK_WIDTH * 2/3;

        //Define x/y to indicate where to place the next brick.
        //Initial position is in top-left corner.
        final int INITIAL_X = BRICK_MARGIN + WALL_THICKNESS;
        int x = INITIAL_X;
        int y = BRICK_MARGIN + WALL_THICKNESS;

        int xAxis = 0;
        int yAxis = 0;

        Brick newBrick;
        for (int i = 0; i < numberOfRows; i++)
        {
            //Place the bricks that fit
            while (x < GameWindow.getWindowWidth() - BRICK_WIDTH - BRICK_MARGIN)
            {
                //Generate a brick using some predetermined settings
                double randomizer= Math.random();
                newBrick = randomizer < .5?createBasicBrick(x, y, BRICK_WIDTH, BRICK_HEIGHT): createStrongBrick(x,y,BRICK_WIDTH,BRICK_HEIGHT);
                if (!specialPairs.contains(new Pair(xAxis, yAxis)) && xAxis !=0 &&
                        xAxis !=4 && xAxis != 8  && xAxis != 12 && xAxis != 16)
                    brickList.add(newBrick);

                x += BRICK_WIDTH + BRICK_MARGIN;
                xAxis++;
            }
            x = INITIAL_X; //reset x
            xAxis = 0;

            //Adjust y to start a new row
            y += BRICK_MARGIN + BRICK_HEIGHT;
            yAxis++;
        }
    }

    private Brick createBasicBrick(int x, int y, int width, int height)
    {
        BasicBrick basicBrick = new BasicBrick(new Position(x, y));
        basicBrick.setWidth(width);
        basicBrick.setHeight(height);

        basicBrick.setIntegrity(BASE_BRICK_INTEGRITY);
        return basicBrick;
    }
    private Brick createStrongBrick(int x, int y, int width, int height)
    {
        StrongBrick basicBrick = new StrongBrick(new Position(x, y));
        basicBrick.setWidth(width);
        basicBrick.setHeight(height);
        return basicBrick;
    }


    public void doFrameLogic()
    {
        for (Brick brick : brickList)
        {
            //Adjust brick position based on its velocity
            brick.onTick();

            //Draw the brick
            if (!brick.isDestroyed())
                brick.draw(GameWindow.getGraphicsContext());
        }
    }
}
