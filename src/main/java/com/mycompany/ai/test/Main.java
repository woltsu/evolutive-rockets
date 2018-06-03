package com.mycompany.ai.test;

import javafx.application.Application;
import javafx.stage.Stage;
import services.GameLogic;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GameLogic f1 = new GameLogic("EVOLUTION", 600, 400);
        f1.start(stage);
    }
    
    public static void main(String[] args) {
        launch(Main.class);
    }
    
}
