package components;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class Player extends GameObject  implements Updatable, Comparable<Player> {
    
    private boolean isDead;
    private DNA dna;
    private double highestPoint;
    private double smallestDistanceToGoal;
    private double timeOfBirth;
    private double timeOfDeath;
    
    public Player(double x, double y, DNA dna) {
        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
        this.rotate(-90);
        this.isDead = false;
        this.dna = dna;
        this.highestPoint = this.getShape().getTranslateY();
        this.smallestDistanceToGoal = Double.MAX_VALUE;
        this.timeOfBirth = System.currentTimeMillis();
    }
    
    public Player(double x, double y) {
        this(x, y, new DNA());
    }
    
    public Player(DNA dna) {
        this(300, 375, dna);
    }
    
    public double getHighestPoint() {
        return this.highestPoint;
    }

    public double getScore() {
        return this.smallestDistanceToGoal;
        // return this.timeOfDeath - this.timeOfBirth;
        //return this.highestPoint / (this.timeOfDeath - this.timeOfBirth);
    }
    
    private double calculateDistanceToGoal() {
        return Math.sqrt(
            Math.pow(this.getShape().getTranslateX() - 300, 2) + 
            Math.pow(this.getShape().getTranslateY() - 0, 2)
        );
    }
    
    public void die() {
        this.timeOfDeath = System.currentTimeMillis();
        this.isDead = true;
    }
    
    public boolean isDead() {
        return this.isDead;
    }
    
    public DNA copyDNA() {
        return new DNA(this.dna.getMoves());
    }
    
    @Override
    public void update() {
        if (this.isDead) {
            return;
        }
        int nextMove = dna.getNextMove();
        
        if (nextMove == -1) {
            this.die();
        }
    
        if (nextMove == 0) {
            return;
            
        } else if (nextMove == 1) {
            this.rotate(-5);
            
        } else if (nextMove == 2) {
            this.rotate(5);
            
        } else if (nextMove == 3) {
            this.accelerate();
            
        }
        
        this.move();
        
        if (this.smallestDistanceToGoal > this.calculateDistanceToGoal()) {
            this.smallestDistanceToGoal = this.calculateDistanceToGoal();
        }
    }

    @Override
    public int compareTo(Player o) {
        double score1 = this.getScore();
        double score2 = o.getScore();
        
        if (score1 < score2) {
            return -1;
        } else if (score1 > score2) {
            return 1;
        }
        return 0;
    }
    
    
}
