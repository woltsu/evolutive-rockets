package services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;


public class KeyListener {
    
    private Scene scene;
    private Map<KeyCode, Boolean> pressedKeys;
    
    public KeyListener(Scene scene) {
        this.scene = scene;
        this.pressedKeys = new HashMap();
        this.init();
    }
    
    public Map<KeyCode, Boolean> getPressedKeys() {
        return this.pressedKeys;
    }
    
    private void init() {
        this.scene.setOnKeyPressed(e -> {
            this.pressedKeys.put(e.getCode(), true);
        });
        
        this.scene.setOnKeyReleased(e -> {
            this.pressedKeys.put(e.getCode(), false);
        });
    }
    
}
