package duel;


import duel.controller.Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class DuelDemo extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Game game = new Game();
        game.begin();
    }
}
