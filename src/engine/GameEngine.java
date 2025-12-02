package engine;

import java.util.Map;
import scenes.*;
import ui.VNWindow;

public class GameEngine {

    private VNWindow ui;
    private Map<String, Scene> scenes;
    private String currentSceneId;

    public GameEngine(VNWindow ui) {
        this.ui = ui;
        this.scenes = SceneLoader.loadScenes("data/scenes.txt");
    }

    public void start() {
        currentSceneId = "scene1";
        loadScene(currentSceneId);
    }

    public void loadScene(String id) {
        currentSceneId = id;

        Scene scene = scenes.get(id);
        if (scene == null) return;

        ui.setBackgroundImage(scene.getBackground());
        ui.setCharacterImage(scene.getCharacter());

        if (scene.isChoiceScene()) {
            ChoiceScene cs = (ChoiceScene) scene;
            ui.setDialogueText("");
            ui.showChoices(cs.getChoices());
        } else {
            DialogueScene ds = (DialogueScene) scene;
            ui.hideChoices();
            ui.setDialogueText(ds.getDialogue());
            
        }
    }

    public void next() {
        Scene scene = scenes.get(currentSceneId);

        if (scene instanceof DialogueScene) {
            DialogueScene ds = (DialogueScene) scene;
            currentSceneId = ds.getNextSceneId();
            if (currentSceneId != null) {
                loadScene(currentSceneId);
            }
        }
    }

    public void choose(Choice choice) {
        currentSceneId = choice.getNextSceneId();
        loadScene(currentSceneId);
    }

    public void saveGame() {
        SaveManager.save(currentSceneId);
    }

    public void loadGame() {
        String saved = SaveManager.load();
        if (saved != null){
            loadScene(saved);
        }
    }
}
