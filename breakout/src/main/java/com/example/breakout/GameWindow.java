package com.example.breakout;

import com.example.breakout.controllers.BallController;
import com.example.breakout.controllers.GameManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameWindow extends Application
{
    //All Game elements
    private static Group root;

    private static Scene scene = null;
    private static Stage stage = null;
    private static GraphicsContext graphicsContext = null;

    private static int windowWidth = Constants.INITIAL_SCREEN_WIDTH;
    private static int windowHeight = Constants.INITIAL_SCREEN_HEIGHT;


    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        GameWindow.scene = scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public static void setGraphicsContext(GraphicsContext graphicsContext) {
        GameWindow.graphicsContext = graphicsContext;
    }


    /**
     * Configures the window and begins the game loop
     */
    @Override
    public void start(Stage stage) throws InterruptedException {
        //Creating a Group object
        root = new Group();
        this.stage = stage;

        //Add a new canvas
        Canvas canvas = new Canvas(windowWidth, windowHeight);
        graphicsContext = canvas.getGraphicsContext2D();
        fillBackground();
        root.getChildren().add(canvas);

        //Creating a scene object
        scene = new Scene(root, windowWidth, windowHeight);
        stage.setTitle("Breakout Game");
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

        showInitialDialog(stage);
    }

    private void showInitialDialog(Stage stage) {
        String contentText = "Welcome to the Breakout game";
        Button customiseButton = new Button("Customise");
        customiseButton.setStyle("-fx-background-color: #DEB887; -fx-font-size: 2em; -fx-text-fill: #00008B");

        Button startGameButton = new Button("New Game");
        startGameButton.setStyle("-fx-background-color: #5F9EA0; -fx-font-size: 2em; -fx-text-fill: #00008B");

        TilePane tilePane = new TilePane();
        TextInputDialog customisedInputDialog = new TextInputDialog();
        customisedInputDialog.setTitle("Customise your game");
        customisedInputDialog.getEditor().setText("1");

        tilePane.getChildren().add(startGameButton);
        tilePane.getChildren().add(customiseButton);
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null)));

        Scene sc = new Scene(tilePane, 500, 300);
        sc.setFill(Constants.BACKGROUND_COLOR);
        stage.setScene(sc);
        stage.setTitle(contentText);
        stage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        EventHandler<ActionEvent> newGameEvent = e -> {
            restoreDefaultsAndStart(stage);
        };
        EventHandler<ActionEvent> customiseEvent = e -> {
            customisedInputDialog.setHeaderText("");
            customisedInputDialog.setContentText("Number of balls?");

            customisedInputDialog.showAndWait().ifPresent(result -> {
                if (isInvalid(result)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong input");
                    alert.setHeaderText("Please enter valid input between 1 and 5");
                    alert.initOwner(stage);
                    alert.showAndWait();
                } else {
                    BallController.addMultipleBalls(Integer.parseInt(result));
                    restoreDefaultsAndStart(stage);
                }
            });
        };

        // set on action of event
        startGameButton.setOnAction(newGameEvent);
        customiseButton.setOnAction(customiseEvent);
    }

    private boolean isInvalid(String text) {
        try {
            int number = Integer.parseInt(text);
            if (number < 1 || number > 5)
                return true;
        } catch (NumberFormatException exception) {
            return true;
        }
        return false;
    }

    private void restoreDefaultsAndStart(Stage stage) {
        stage.setX(0);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
        startGameLoop();
    }

    private void startGameLoop() {
        //Set up the mediator and begin game loop
        GameManager eventMediator = new GameManager();
        eventMediator.initiateGameLoop();
    }

    public static void fillBackground(Color color)
    {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, windowWidth, windowHeight);
    }

    public static void fillBackground()
    {
        fillBackground(Constants.BACKGROUND_COLOR);
    }

    /**
     * End the game when the window closes
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        GameManager.stopGameLoop();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
