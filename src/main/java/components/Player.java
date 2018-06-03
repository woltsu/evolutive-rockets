package components;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class Player extends GameObject  implements Updatable, Comparable<Player> {
    
    private boolean isDead;
    private DNA dna;
    
    public Player(double x, double y, DNA dna) {
        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
        this.isDead = false;
        this.dna = dna;
    }
    
    public Player(double x, double y) {
        this(x, y, new DNA());
    }
    
    public Player(DNA dna) {
        this(5, 200, dna);
    }
    
    public void die() {
        this.isDead = true;
    }
    
    public boolean isDead() {
        return this.isDead;
    }
    
    public DNA copyDNA() {
        return new DNA(this.dna.getMoves());
    }
    
    public double getScore() {
        return this.getShape().getTranslateX();
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
            
        } else {
            this.decelerate();
            
        }
        
        this.move();
    }

    @Override
    public int compareTo(Player o) {
        double x1 = this.getShape().getTranslateX();
        double x2 = o.getShape().getTranslateX();
        if (x1 < x2) {
            return 1;
        } else if (x1 > x2) {
            return -1;
        }
        return 0;
    }
    
    
}
