package components;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class GameObject {
    
    private Polygon shape;
    private Point2D movement;
    
    public GameObject(Polygon shape, double x, double y) {
        this.shape = shape;
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.movement = new Point2D(0, 0);
    }
    
    public Polygon getShape() {
        return shape;
    }
    
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.shape.getRotate())) * 0.07;
        double changeY = Math.sin(Math.toRadians(this.shape.getRotate())) * 0.07;
        
        this.movement = this.movement.add(changeX, changeY);
    }
    
    public void decelerate() {
        double changeX = Math.cos(Math.toRadians(this.shape.getRotate())) * 0.05;
        double changeY = Math.sin(Math.toRadians(this.shape.getRotate())) * 0.05;
        
        this.movement = this.movement.subtract(changeX, changeY);
    }
    
    public void addGravity() {
        double changeX = Math.cos(Math.toRadians(90)) * 0.02;
        double changeY = Math.sin(Math.toRadians(90)) * 0.02;
        
        this.movement = this.movement.add(changeX, changeY);
    }
    
    public void move() {
        this.shape.setTranslateX(this.shape.getTranslateX() + this.movement.getX());
        this.shape.setTranslateY(this.shape.getTranslateY() + this.movement.getY());
    }
    
    public void rotate(double amount) {
        this.shape.setRotate(this.shape.getRotate() + amount);
    }
    
    public boolean collides(GameObject otherGameObject) {
       Shape collisionArea = Shape.intersect(this.shape, otherGameObject.getShape());
       return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
    
}
