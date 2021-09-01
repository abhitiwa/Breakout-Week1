package com.example.breakout;

import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants
{
    //Speeds
    public static final long FRAME_DURATION = 33; //30 FPS
    public static final int PADDLE_BASE_VELOCITY = 20;
    public static final int BALL_INITIAL_VELOCITY = 10;

    //Dimensions
    public static final int INITIAL_SCREEN_WIDTH = (int) Screen.getPrimary().getBounds().getWidth();
    public static final int INITIAL_SCREEN_HEIGHT = (int) (Screen.getPrimary().getBounds().getHeight()*0.9);
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int BALL_SIZE = 30;
    public static final int WALL_THICKNESS = 10;
    public static final int BRICK_MARGIN = 5; //padding/space around each brick

    //Colors
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color WALL_COLOR = Color.IVORY;
    public static final Color FLOOR_COLOR = Color.CORAL;
    public static final Color BRICK_DEFAULT_COLOR = Color.TURQUOISE;
    public static final Color BRICK_DAMAGED_COLOR = Color.LIGHTYELLOW;

    public static Map<Integer, Color> BRICK_INTEGRITY_TO_COLOR_MAP =
          Map.of(1, Color.YELLOW,
                    2, Color.ORANGE,
                    3, Color.RED,
                    4, Color.DARKRED,
                    5, Color.ROSYBROWN);




    //Other
    public static final int BASE_BRICK_INTEGRITY = 1;
    public static final int BRICKS_PER_ROW = 10;
}
