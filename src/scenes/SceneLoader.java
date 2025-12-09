package scenes;

import java.io.*;
import java.util.*;

public class SceneLoader {

    public static Map<String, Scene> loadScenes(String path) {
        Map<String, Scene> map = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            String id = "";
            String type = "";
            String bg = "images/background.jpg";
            String chara = "";
            String text = "";
            String next = "";
            List<Choice> choices = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                if (line.startsWith("[") && line.endsWith("]")) {
                    if (!id.equals("")) {
                        // Store old scene
                        if (type.equals("dialogue")) {
                            map.put(id, new DialogueScene(id, bg, chara, text, next));
                        } else if (type.equals("choice")) {
                            map.put(id, new ChoiceScene(id, bg, chara, new ArrayList<>(choices)));
                        }
                    }

                    // Reset for new scene
                    id = line.substring(1, line.length() - 1);
                    type = "";
                    chara = "";
                    text = "";
                    next = "";
                    choices.clear();
                }

                else if (line.startsWith("type=")) {
                    type = line.substring(5);
                }
                else if (line.startsWith("background=")) {
                    bg = line.substring(11);
                }
                else if (line.startsWith("character=")) {
                    chara = line.substring(10);
                }
                else if (line.startsWith("text=")) {
                    text = line.substring(5);
                }
                else if (line.startsWith("next=")) {
                    next = line.substring(5);
                }
                else if (line.startsWith("choice=")) {
                    String[] parts = line.substring(7).split("->");
                    choices.add(new Choice(parts[0], parts[1]));
                }
            }

            // Add final scene
            if (!id.equals("")) {
                if (type.equals("dialogue")) {
                    map.put(id, new DialogueScene(id, bg, chara, text, next));
                } else if (type.equals("choice")) {
                    map.put(id, new ChoiceScene(id, bg, chara, choices));
                }
            }

            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
