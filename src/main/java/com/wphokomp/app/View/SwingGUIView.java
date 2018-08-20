package com.wphokomp.app.View;

import com.wphokomp.app.Models.HeroUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGUIView extends JFrame implements ActionListener {
    private JButton newGame = new JButton("New");
    private JButton loadGame = new JButton("Load");
    private JButton exit = new JButton("Exit");
    private JPanel menuPanel= new JPanel();
    private JPanel createPanel= new JPanel();
    private JPanel loadPanel= new JPanel();
    private Border border;
    private HeroUI heroUI;

    public SwingGUIView() {
        heroUI = new HeroUI();
        border = BorderFactory.createTitledBorder("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        menuPanel.setBorder(border);
        menuPanel.setBackground(Color.LIGHT_GRAY);

        initGame();
        this.add(menuPanel);
    }

    private void initGame() {
        newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.add(newGame);
        menuPanel.add(loadGame);
        menuPanel.add(exit);

        newGame.addActionListener(this);
        loadGame.addActionListener(this);
        exit.addActionListener(this);
    }

    private void createPlayer() {
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
        heroUI.setCreateButton(new JButton("Create"));

        heroUI.getNameLabel().setBounds(80, 70, 200, 30);
        heroUI.getClassLabel().setBounds(80, 110, 200, 30);
        heroUI.getHeroName().setBounds(280, 70, 200, 30);
        heroUI.getHeroClass().setBounds(280, 110, 200, 30);
        heroUI.getCreateButton().setBounds(230, 150, 100, 30);

        createPanel.add(heroUI.getNameLabel());
        createPanel.add(heroUI.getHeroName());
        createPanel.add(heroUI.getClassLabel());
        createPanel.add(heroUI.getHeroClass());
        createPanel.add(heroUI.getCreateButton());

        heroUI.getCreateButton().addActionListener(this);
    }

    public String getHeroName() {
        return heroUI.getHeroName().getText();
    }

    public String getHeroClass() {
        return heroUI.getHeroClass().getText();
    }

    public boolean create() {
        return true;
    }

    private void loadPlayers() {
        menuPanel.setVisible(false);
        this.setSize(600, 400);
        this.add(loadPanel);
        border = BorderFactory.createTitledBorder("Create player");
        loadPanel.setBorder(border);
        loadPanel.setLayout(null);
        loadPanel.setBackground(Color.LIGHT_GRAY);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame)
            createPlayer();
        else if (e.getSource() == loadGame)
            loadPlayers();
        else if (e.getSource() == exit)
            System.exit(0);

        if (e.getSource() == heroUI.getCreateButton())
            create();
    }
}
