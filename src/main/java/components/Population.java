package components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.Node;

public class Population implements Updatable {
    
    private List<Player> players;
    private int generation;
    
    public Population() {
        this.players = new ArrayList();
        this.generation = 0;
    }
    
    public void generate() {
        this.generation++;
        this.players.clear();
        for (int i = 0; i < 256; i++) {
            this.players.add(new Player(5, 200));
        }
    }
    
    public void repopulate() {
        if (players.isEmpty()) {
            return;
        }
        this.generation++;
        
        List<Player> parents = new ArrayList();
        
        List<Player> tempPlayers = new ArrayList();
        tempPlayers.addAll(this.players);
        Collections.sort(tempPlayers);
        tempPlayers = tempPlayers.subList(0, tempPlayers.size() / 2);
        // Collections.shuffle(tempPlayers);
        this.players.clear();
        for (int i = 1; i <= tempPlayers.size() + 1; i++) {
            if (i % 2 == 0) {
                Player p1 = tempPlayers.get(i - 2);
                Player p2 = tempPlayers.get(i - 1);
                DNA[] children = p1.copyDNA().merge(p2.copyDNA());
                this.players.add(new Player(p1.copyDNA()));
                this.players.add(new Player(p2.copyDNA()));
                this.players.add(new Player(children[0]));
                this.players.add(new Player(children[1]));
            }
        }
    }
    
    public boolean allDead() {
        return players.stream().noneMatch((player) -> !player.isDead());
    }
    
    public List<Player> getPlayers() {
        return this.players;
    }
    
    public List<Node> getShapes() {
        List<Node> shapes = new ArrayList();
        for (Player player : this.players) {
            shapes.add(player.getShape());
        }
        return shapes;
    }
    
    public int getGeneration() {
        return this.generation;
    }
        
    
    @Override
    public void update() {
        this.players.stream().forEach(player -> {
            player.update();
        });
    }
    
}
