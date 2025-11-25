package scenes;

public abstract class Scene {
    protected String id;
    protected String background;
    protected String character;

    public Scene(String id, String background, String character) {
        this.id = id;
        this.background = background;
        this.character = character;
    }

    public String getId() { return id; }
    public String getBackground() { return background; }
    public String getCharacter() { return character; }

    public abstract boolean isChoiceScene();
}
