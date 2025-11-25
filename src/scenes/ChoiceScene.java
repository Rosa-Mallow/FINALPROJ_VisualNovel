package scenes;

import java.util.List;

public class ChoiceScene extends Scene {
    private List<Choice> choices;

    public ChoiceScene(String id, String background, String character,
                       List<Choice> choices) {
        super(id, background, character);
        this.choices = choices;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    @Override
    public boolean isChoiceScene() { return true; }
}
