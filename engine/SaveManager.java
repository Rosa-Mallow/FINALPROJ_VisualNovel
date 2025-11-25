package engine;

import java.io.*;

public class SaveManager {

    public static void save(String sceneId) {
        try {
            FileWriter fw = new FileWriter("save.txt");
            fw.write(sceneId);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("save.txt"));
            String sceneId = br.readLine();
            br.close();
            return sceneId;
        } catch (Exception e) {
            return null;
        }
    }
}
