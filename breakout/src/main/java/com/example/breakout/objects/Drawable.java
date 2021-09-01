package com.example.breakout.objects;

import javafx.scene.canvas.GraphicsContext;

public interface Drawable
{
    /**
     * Paints the BreakoutObject onto a canvas based on its height and width.
     * @param gc the GraphicsContext of the canvas to be painted onto
     */
    public void draw(GraphicsContext gc);
}
