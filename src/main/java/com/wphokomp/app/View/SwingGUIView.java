package com.wphokomp.app.View;

import com.wphokomp.app.Models.Hero;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

@Getter
public class SwingGUIView extends JFrame {
    private JList<String> heroList;
    private JList<String> detailList;
    private DefaultListModel<String> detailModel;
    private JButton createButton = new JButton("Create");
    private JButton details = new JButton("Details");
    private Border border;
    private com.wphokomp.app.Models.Component component;
    @Setter
    private boolean cre = false;

    public SwingGUIView() {
        component = new com.wphokomp.app.Models.Component();
        component.setSelect(new JButton("Select"));
        border = BorderFactory.createTitledBorder("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocationRelativeTo(null);

        component.setNewGame(new JButton("New"));
        component.setLoadGame(new JButton("Load"));
        component.setExit(new JButton("Exit"));

        component.setMenuPanel(new JPanel());
        component.setCreatePanel(new JPanel());
        component.setLoadPanel(new JPanel());
        component.setGamePanel(new JPanel());
        component.setMenuPanel(new JPanel());

        component.getMenuPanel().setBorder(border);

        initGame();
        this.add(component.getMenuPanel());
    }

    public void initGame() {
        component.getNewGame().setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        component.getGamePanel().setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        component.getExit().setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        component.getMenuPanel().add(component.getNewGame());
        component.getMenuPanel().add(component.getLoadGame());
        component.getMenuPanel().add(component.getExit());
    }

    public void createPlayer() {
        component.getMenuPanel().setVisible(false);
        this.setSize(600, 400);
        this.add(component.getCreatePanel());
        border = BorderFactory.createTitledBorder("Create player");
        component.getCreatePanel().setBorder(border);
        component.getCreatePanel().setLayout(null);

        //player attributes
        component.setHeroName(new JTextField(10));
        component.setHeroClass(new JTextField(10));
        component.setNameLabel(new JLabel("Enter hero name: "));
        component.setClassLabel(new JLabel("Enter hero's class: "));

        component.getNameLabel().setBounds(80, 70, 200, 30);
        component.getClassLabel().setBounds(80, 110, 200, 30);
        component.getHeroName().setBounds(280, 70, 200, 30);
        component.getHeroClass().setBounds(280, 110, 200, 30);
        createButton.setBounds(230, 150, 100, 30);

        component.getCreatePanel().add(component.getNameLabel());
        component.getCreatePanel().add(component.getHeroName());
        component.getCreatePanel().add(component.getClassLabel());
        component.getCreatePanel().add(component.getHeroClass());
        component.getCreatePanel().add(createButton);
    }

    public boolean create() {
        component.getCreatePanel().setVisible(false);
        return true;
    }

    public void loadPlayers(ArrayList<Hero> heroes) {
        component.getMenuPanel().setVisible(false);
        this.setSize(600, 400);
        this.add(component.getLoadPanel());
        border = BorderFactory.createTitledBorder("Select player");
        component.getLoadPanel().setBorder(border);
        component.getLoadPanel().setLayout(null);
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
        component.getLoadPanel().add(heroList);
        component.getLoadPanel().add(detailList);
        //==================================================================
        details.setBounds(240, 270, 100, 30);
        component.getSelect().setBounds(240, 300, 100, 30);
        component.getLoadPanel().add(details);
        component.getLoadPanel().add(component.getSelect());
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

    public void gameMode(Hero hero) {
        String[] dir = new String[] {"North", "East", "West", "South"};

        if (component.getLoadPanel().isVisible()) {
            component.getLoadPanel().setVisible(false);
        } else if (component.getCreatePanel().isVisible()) {
            component.getCreatePanel().setVisible(false);
        }

        component.setGamePanel(new JPanel());
        component.getGamePanel().setLayout(null);
        border = BorderFactory.createTitledBorder("Swingy");
        component.getGamePanel().setBorder(border);

        component.setNameLabel(new JLabel("Name: ".concat(hero.getName())));
        component.getNameLabel().setBounds(20, 20, 150, 30);
        component.setLifeLabel(new JLabel("Life: ".concat(Integer.toString(hero.getHitPoints()).concat(" HP"))));
        component.getLifeLabel().setBounds(20, 50, 150, 30);
        component.setLevelLabel(new JLabel("Level: ".concat(Integer.toString(hero.getLevel()))));
        component.getLevelLabel().setBounds(20, 80, 150, 30);
        component.setXpLabel(new JLabel("Experience: ".concat(Integer.toString(hero.getExperience()).concat(" XP"))));
        component.getXpLabel().setBounds(20, 110, 150, 30);
        component.setLocationLabel(new JLabel("Location: ".concat(Integer.toString(hero.getX()))
                .concat(",".concat(Integer.toString(hero.getY())))));
        component.getLocationLabel().setBounds(20, 140, 150, 30);

        JLabel actions = new JLabel("Actions");
        actions.setFont(new Font("Monaco", Font.BOLD, 22));
        actions.setBounds(90, 180, 150, 30);

        component.setDirections(new JComboBox<>(dir));
        component.getDirections().setBounds(90, 220, 100, 30);

        component.setFight(new JButton("Fight!"));
        component.getFight().setBounds(40, 260, 100, 30);
        component.setRun(new JButton("Run!"));
        component.getRun().setBounds(130, 260, 100, 30);

        component.setMap(new JScrollPane());

        component.getGamePanel().add(component.getNameLabel());
        component.getGamePanel().add(component.getLifeLabel());
        component.getGamePanel().add(component.getLevelLabel());
        component.getGamePanel().add(component.getXpLabel());
        component.getGamePanel().add(component.getLocationLabel());
        component.getGamePanel().add(actions);
        component.getGamePanel().add(component.getDirections());
        component.getGamePanel().add(component.getFight());
        component.getGamePanel().add(component.getRun());

        this.add(component.getGamePanel());
    }
}
