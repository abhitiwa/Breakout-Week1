package com.example.breakout.controllers;

import com.example.breakout.Constants;
import com.example.breakout.GameWindow;
import com.example.breakout.Position;
import com.example.breakout.Velocity;
import com.example.breakout.objects.wall.Floor;
import com.example.breakout.objects.wall.StandardWall;
import com.example.breakout.objects.wall.Wall;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class WallController extends Controller
{
    public LinkedList<Wall> wallList = new LinkedList<>();

    public WallController()
    {
        createFourWalls();
    }

    private void createFourWalls()
    {
        int width, height;

        //Top wall
        width = GameWindow.getWindowWidth();
        height = Constants.WALL_THICKNESS;
        Wall topWall = new StandardWall(
                new Position(0, 0),
                new Velocity(0, 0),
                Constants.WALL_COLOR,
                new Rectangle(width, height),
                width, height);
        wallList.add(topWall);
        //Floor
        Floor floor = new Floor(
                new Position(0, GameWindow.getWindowHeight()-Constants.WALL_THICKNESS),
                new Velocity(0, 0),
                Constants.FLOOR_COLOR,
                new Rectangle(width, height),
                width, height);
        wallList.add(floor);

        //Left wall
        width = Constants.WALL_THICKNESS;
        height = GameWindow.getWindowHeight();
        Wall leftWall = new StandardWall(
                new Position(0, 0),
                new Velocity(0, 0),
                Constants.WALL_COLOR,
                new Rectangle(width, height),
                width, height);
        wallList.add(leftWall);
        //Right wall
        Wall rightWall = new StandardWall(
                new Position(GameWindow.getWindowWidth()-Constants.WALL_THICKNESS, 0),
                new Velocity(0, 0),
                Constants.WALL_COLOR,
                new Rectangle(width, height),
                width, height);
        wallList.add(rightWall);

        //Attach each wall to the parent controller
        for (Wall wall : wallList)
        {
            GameManager.attachBreakoutObject(wall);
        }
    }

    public void doFrameLogic()
    {
        for (Wall wall : wallList)
        {
            //Draw the wall
            if (!wall.isDestroyed())
                wall.draw(GameWindow.getGraphicsContext());
        }
    }
}
