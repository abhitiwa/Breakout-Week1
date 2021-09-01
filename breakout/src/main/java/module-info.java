module com.example.breakout {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.breakout to javafx.fxml;
    exports com.example.breakout;
    exports com.example.breakout.objects;
    opens com.example.breakout.objects to javafx.fxml;
    exports com.example.breakout.controllers;
    opens com.example.breakout.controllers to javafx.fxml;
    exports com.example.breakout.objects.brick;
    opens com.example.breakout.objects.brick to javafx.fxml;
    exports com.example.breakout.objects.ball;
    opens com.example.breakout.objects.ball to javafx.fxml;
    exports com.example.breakout.objects.wall;
    opens com.example.breakout.objects.wall to javafx.fxml;
    exports com.example.breakout.objects.paddle;
    opens com.example.breakout.objects.paddle to javafx.fxml;
}