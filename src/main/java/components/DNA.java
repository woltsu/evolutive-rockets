package components;

import java.util.Random;

public class DNA {
    
    private int[] moves;
    private int pointer;
    private Random r;
    
    public DNA() {
        this(new int[2000]);
        this.init();
    }
    
    public DNA(int[] moves) {
        this.moves = moves;
        this.r = new Random();
        this.pointer = 0;
    }
    
    public int getNextMove() {
        int move = this.moves[pointer];
        this.pointer++;
        if (this.pointer >= moves.length) {
            this.pointer = 0;
        }
        return move;
    }
    
    public int[] getMoves() {
        return this.moves;
    }
    
    public DNA[] merge(DNA otherDna) {
        DNA[] results = new DNA[2];
        
        int[] moves1 = new int[2000];
        int[] moves2 = new int[2000];
        
        for (int i = 0; i < this.moves.length; i++) {
            if (i < this.moves.length / 2) {
                moves1[i] = otherDna.getMoves()[i];
                moves2[i] = this.moves[i];
            } else {
                moves1[i] = this.moves[i];
                moves2[i] = otherDna.getMoves()[i];
            }
        }
        
        for (int i = 0; i < moves1.length; i++) {
            if (this.r.nextDouble() >= 0.9) {
                while(true) {
                    int mutation = this.r.nextInt(4);
                    if (mutation == moves1[i]) {
                        continue;
                    }
                    moves1[i] = mutation;
                    break;
                }
            }
            
            if (this.r.nextDouble() >= 0.9) {
                while(true) {
                    int mutation = this.r.nextInt(4);
                    if (mutation == moves2[i]) {
                        continue;
                    }
                    moves2[i] = mutation;
                    break;
                }
            }
        }
        
        results[0] = new DNA(moves1);
        results[1] = new DNA(moves2);
        return results;
    }
    
    private void init() {
        for (int i = 0; i < this.moves.length; i++) {
            double x = this.r.nextDouble();
            if (x < 0.25) {
                this.moves[i] = 0;
                
            } else if (x < 0.5) {
                this.moves[i] = 1;
                
            } else if (x < 0.75) {
                this.moves[i] = 2;
                
            } else {
                this.moves[i] = 3;
                
            }
        }
    }
    
}
