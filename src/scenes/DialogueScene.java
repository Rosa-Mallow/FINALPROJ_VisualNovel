package scenes;

public class DialogueScene extends Scene {
    private String dialogue;
    private String nextSceneId;

    public DialogueScene(String id, String background, String character, String dialogue, String nextSceneId) {
        super(id, background, character);
        this.dialogue = dialogue;
        this.nextSceneId = nextSceneId;
    }

    public String getDialogue() { return dialogue; }
    public String getNextSceneId() { return nextSceneId; }

    @Override
    public boolean isChoiceScene() { return false; }
}
