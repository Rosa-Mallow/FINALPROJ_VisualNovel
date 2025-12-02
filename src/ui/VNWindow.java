package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import engine.GameEngine;
import scenes.Choice;

public class VNWindow extends JFrame {

    private JLabel backgroundLabel;
    private JLabel characterLabel;
    private JLabel dialogueArea;
    private JPanel choicePanel;
    private JButton nextButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLayeredPane layeredPane;

    private GameEngine engine;

    public VNWindow() {
        engine = new GameEngine(this);

        setTitle("Monochromatic");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 900, 600);
        layeredPane.setLayout(null);
        add(layeredPane);

        // Background (bottom layer)
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 900, 600);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Character (middle layer)
        characterLabel = new JLabel();
        characterLabel.setBounds(300, 20, 400, 550);
        layeredPane.add(characterLabel, Integer.valueOf(1));

        // Dialogue Box (top layer)
        JPanel dialoguePanel = new JPanel();
        dialoguePanel.setBounds(50, 360, 800, 150);
        dialoguePanel.setBackground(new Color(165, 135, 255, 100));  // semi-transparent
        dialoguePanel.setOpaque(true);
        dialoguePanel.setLayout(new BorderLayout());
        dialogueArea = new JLabel();
        dialogueArea.setVerticalAlignment(SwingConstants.TOP);
        dialoguePanel.add(dialogueArea);
        layeredPane.add(dialoguePanel, Integer.valueOf(2));


        // Choices (same front layer)
        choicePanel = new JPanel();
        choicePanel.setBounds(50, 500, 800, 60);
        choicePanel.setOpaque(false); // transparent background
        choicePanel.setLayout(new FlowLayout());
        layeredPane.add(choicePanel, Integer.valueOf(3));

        // Next Button (top layer)
        nextButton = new JButton("Next");
        nextButton.setBounds(760, 450, 80, 40);
        nextButton.addActionListener(e -> engine.next());
        layeredPane.add(nextButton, Integer.valueOf(3));

        saveButton = new JButton("Save");
        saveButton.setBounds(760, 10, 80, 30);
        saveButton.addActionListener(e -> engine.saveGame());
        layeredPane.add(saveButton, Integer.valueOf(3));
        //add(saveButton);

        loadButton = new JButton("Load");
        loadButton.setBounds(670, 10, 80, 30);
        loadButton.addActionListener(e -> engine.loadGame());
        layeredPane.add(loadButton, Integer.valueOf(3));
        //add(loadButton);

        //add(backgroundLabel);
        //add(characterLabel);
        //add(dialogueArea);
        //add(nextButton);
        //add(choicePanel);

        engine.start();
    }

    public void setBackgroundImage(String path) {
        backgroundLabel.setIcon(new ImageIcon(path));
    }

    public void setCharacterImage(String path) {
        ImageIcon scaled = resizeImagePreserveRatio(path, 400, 550);
        characterLabel.setIcon(scaled);
        characterLabel.setSize(scaled.getIconWidth(), scaled.getIconHeight());
    }

    public void setDialogueText(String text) {
        dialogueArea.setText("<html><div style='width:650px; font-size:12px; color:black; text-align:center; padding:50px;'>" + text + "</div></html>");
    }

    public void showChoices(List<Choice> choices) {
        choicePanel.removeAll();

        for (Choice choice : choices) {
            JButton btn = new JButton(choice.getText());
            btn.addActionListener(e -> engine.choose(choice));
            choicePanel.add(btn);
        }

        choicePanel.revalidate();
        choicePanel.repaint();

        nextButton.setVisible(false);
    }

    public void hideChoices() {
        choicePanel.removeAll();
        choicePanel.revalidate();
        choicePanel.repaint();

        nextButton.setVisible(true);
    }

    private ImageIcon resizeImagePreserveRatio(String path, int maxW, int maxH) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        int origW = icon.getIconWidth();
        int origH = icon.getIconHeight();

        double wRatio = (double) maxW / origW;
        double hRatio = (double) maxH / origH;
        double scale = Math.min(wRatio, hRatio);

        int newW = (int) (origW * scale);
        int newH = (int) (origH * scale);

        Image scaled = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

}
