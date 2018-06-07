package services;

import components.GameObject;
import components.Obstacle;
import components.Player;
import components.Population;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameLogic extends Application {

    private String name;
    private int width;
    private int height;
    private List<GameObject> obstacles;
    
    public GameLogic(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.obstacles = new ArrayList();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane field = new Pane();
        field.setPrefSize(this.width, this.height);
        
        Population population = new Population();
        population.generate();
        field.getChildren().addAll(population.getShapes());
        
        Label label = new Label("Generation: " + population.getGeneration());
        field.getChildren().add(label);
        
        this.addObstacle(field, 225, 200);
//        this.addObstacle(field, 450, 250);
//        this.addObstacle(field, 450, 400);
//        
//        this.addObstacle(field, 300, 100);
//        this.addObstacle(field, 300, 175);
//        this.addObstacle(field, 300, 325);
//        this.addObstacle(field, 300, 400);
//        
//        this.addObstacle(field, 150, 250);
//        this.addObstacle(field, 150, 325);
//        this.addObstacle(field, 150, 400);
  
        Scene scene = new Scene(field);
        KeyListener keyListener = new KeyListener(scene);
        new AnimationTimer() {
            @Override
            public void handle(long timeNow) {
                for (Player player : population.getPlayers()) {
                    player.addGravity();
                }
                population.update();
                population.getPlayers().stream().forEach(player -> {
                        if (player.isDead()) {
                            return;
                        }
                        if (player.getShape().getTranslateX() > width ||
                            player.getShape().getTranslateX() < 0 || 
                            player.getShape().getTranslateY() > height ||
                            player.getShape().getTranslateY() < 0) {
                            player.die();
                        }
                    });
                
                obstacles.stream().forEach(obstacle -> {
                    population.getPlayers().stream().forEach(player -> {
                        if (player.isDead()) {
                            return;
                        }
                        if (player.collides(obstacle)) {
                            player.die();
                        }
                    });
                });
                if (population.allDead()) {
                    field.getChildren().removeAll(population.getShapes());
                    population.repopulate();
                    field.getChildren().addAll(population.getShapes());
                    label.setText("Generation: " + population.getGeneration());
                }
            }
        }.start();
        
        stage.setTitle(this.name);
        stage.setScene(scene);
        stage.show();
    }
    
    private void addObstacle(Pane field, double x, double y) {
        Obstacle o = new Obstacle(x, y, 0);
        field.getChildren().add(o.getShape());
        this.obstacles.add(o);
    }
    
}
