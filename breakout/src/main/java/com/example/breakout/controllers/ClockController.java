package com.example.breakout.controllers;

import com.example.breakout.GameWindow;
import com.example.breakout.Position;
import com.example.breakout.objects.brick.BasicBrick;
import com.example.breakout.objects.brick.Brick;
import com.example.breakout.objects.brick.StrongBrick;
import com.example.breakout.objects.clock.BasicClock;
import com.example.breakout.objects.clock.Clock;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import static com.example.breakout.Constants.*;

public class ClockController extends Controller {

    Clock clock;
    public ClockController() {
             double width = GameWindow.getScene().getWidth();
            double height = GameWindow.getScene().getHeight();
        clock = new BasicClock(new Position(15, height-20));
    }

    @Override
    void doFrameLogic() {
        clock.onTick();
        clock.draw(GameWindow.getGraphicsContext());
    }
}







