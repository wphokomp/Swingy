package com.wphokomp.app.View;

import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.Models.HeroUI;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class SwingGUIView extends JFrame {
    private JButton newGame = new JButton("New");
    private JButton loadGame = new JButton("Load");
    private JButton exit = new JButton("Exit");
    private JPanel menuPanel = new JPanel();
    private JPanel createPanel = new JPanel();
    private JPanel loadPanel = new JPanel();
    private JPanel gamePanel = new JPanel();
    private JList<String> heroList;
    private JList<String> detailList;
    private DefaultListModel<String> detailModel;
    private JButton createButton = new JButton("Create");
    private JButton details = new JButton("Details");
    private Border border;
    private HeroUI heroUI;
    @Setter
    private boolean cre = false;

    public SwingGUIView() {
        heroUI = new HeroUI();
        heroUI.setSelect(new JButton("Select"));
        border = BorderFactory.createTitledBorder("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        menuPanel.setBorder(border);
        menuPanel.setBackground(Color.LIGHT_GRAY);

        initGame();
        this.add(menuPanel);
    }

    public void initGame() {
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.add(newGame);
        menuPanel.add(loadGame);
        menuPanel.add(exit);
    }

    public void createPlayer() {
        menuPanel.setVisible(false);
        this.setSize(600, 400);
        this.add(createPanel);
        border = BorderFactory.createTitledBorder("Create player");
        createPanel.setBorder(border);
        createPanel.setLayout(null);
        createPanel.setBackground(Color.LIGHT_GRAY);

        //player attributes
        heroUI.setHeroName(new JTextField(10));
        heroUI.setHeroClass(new JTextField(10));
        heroUI.setNameLabel(new JLabel("Enter hero name: "));
        heroUI.setClassLabel(new JLabel("Enter hero's class: "));

        heroUI.getNameLabel().setBounds(80, 70, 200, 30);
        heroUI.getClassLabel().setBounds(80, 110, 200, 30);
        heroUI.getHeroName().setBounds(280, 70, 200, 30);
        heroUI.getHeroClass().setBounds(280, 110, 200, 30);
        createButton.setBounds(230, 150, 100, 30);

        createPanel.add(heroUI.getNameLabel());
        createPanel.add(heroUI.getHeroName());
        createPanel.add(heroUI.getClassLabel());
        createPanel.add(heroUI.getHeroClass());
        createPanel.add(createButton);
    }

    public boolean create() {
        createPanel.setVisible(false);
        return true;
    }

    public void loadPlayers(ArrayList<Hero> heroes) {
        menuPanel.setVisible(false);
        this.setSize(600, 400);
        this.add(loadPanel);
        border = BorderFactory.createTitledBorder("Select player");
        loadPanel.setBorder(border);
        loadPanel.setLayout(null);
        loadPanel.setBackground(Color.LIGHT_GRAY);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        detailModel = new DefaultListModel<>();
        for (Hero h :
                heroes) {
            listModel.addElement(h.getName());
        }

        heroList = new JList<>(listModel);
        detailList = new JList<>(detailModel);
        detailList.setBounds(300, 30, 200, 200);
        heroList.setBounds(70, 30, 200, 200);
        loadPanel.add(heroList);
        loadPanel.add(detailList);
        //==================================================================
        details.setBounds(240, 270, 100, 30);
        heroUI.getSelect().setBounds(240, 300, 100, 30);
        loadPanel.add(details);
        loadPanel.add(heroUI.getSelect());
    }

    public void displayDetails(ArrayList<Hero> heroes) {
        Hero hero = heroes.get(heroList.getSelectedIndex());

        detailModel.removeAllElements();
        detailModel.addElement(("Class: ").concat(hero.getHeroClass()));
        detailModel.addElement("Level: ".concat(Integer.toString(hero.getLevel())));
        detailModel.addElement("Experience: ".concat(Integer.toString(hero.getExperience()).concat(" XP")));
        detailModel.addElement("Weapon: ".concat(hero.getWeapon()));
        detailModel.addElement("Armor: ".concat(hero.getArmor()));
    }

    public void gameMode() {
        if (loadPanel.isVisible()) {
            loadPanel.setVisible(false);
        } else if (createPanel.isVisible()) {
            createPanel.setVisible(false);
        }

    }
}
