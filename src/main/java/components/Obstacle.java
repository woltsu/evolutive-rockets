package components;

import javafx.scene.shape.Polygon;

public class Obstacle extends GameObject {
    
    public Obstacle(double x, double y, int height) {
        super(new Polygon(0, 0, 0, -100, 50, -100, 50, 0), x, y);
    }
    
    
}
