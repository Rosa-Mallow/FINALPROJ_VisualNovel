package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import engine.GameEngine;
import scenes.Choice;

public class VNWindow extends JFrame {

    private JLabel backgroundLabel;
    private JLabel characterLabel;
    private JTextArea dialogueArea;
    private JPanel choicePanel;
    private JButton nextButton;
    private JButton saveButton;
    private JButton loadButton;

    private GameEngine engine;

    public VNWindow() {
        engine = new GameEngine(this);

        setTitle("Monochromatic");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 900, 350);

        characterLabel = new JLabel();
        characterLabel.setBounds(300, 50, 300, 300);

        dialogueArea = new JTextArea();
        dialogueArea.setBounds(50, 370, 700, 120);
        dialogueArea.setLineWrap(true);
        dialogueArea.setWrapStyleWord(true);
        dialogueArea.setEditable(false);

        nextButton = new JButton("Next");
        nextButton.setBounds(760, 450, 80, 40);
        nextButton.addActionListener(e -> engine.next());

        choicePanel = new JPanel();
        choicePanel.setBounds(50, 500, 800, 60);
        choicePanel.setLayout(new FlowLayout());

        saveButton = new JButton("Save");
        saveButton.setBounds(760, 10, 80, 30);
        saveButton.addActionListener(e -> engine.saveGame());
        add(saveButton);

        loadButton = new JButton("Load");
        loadButton.setBounds(670, 10, 80, 30);
        loadButton.addActionListener(e -> engine.loadGame());
        add(loadButton);

        add(backgroundLabel);
        add(characterLabel);
        add(dialogueArea);
        add(nextButton);
        add(choicePanel);

        engine.start();
    }

    public void setBackgroundImage(String path) {
        backgroundLabel.setIcon(new ImageIcon(path));
    }

    public void setCharacterImage(String path) {
        characterLabel.setIcon(new ImageIcon(path));
    }

    public void setDialogueText(String text) {
        dialogueArea.setText(text);
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
}
