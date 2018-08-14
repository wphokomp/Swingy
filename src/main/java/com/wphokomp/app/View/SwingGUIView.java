package com.wphokomp.app.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingGUIView extends JFrame implements ActionListener {
    private JButton newGame = new JButton("New");
    private JButton loadGame = new JButton("Load");
    private JButton exit = new JButton("Exit");
    private JPanel menuPanel = new JPanel();
    private Border border;

    public SwingGUIView() {
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
//        menuPanel.setVisible(false);
//        this.setSize(600, 400);
//        border = BorderFactory.createTitledBorder("Create player");
    }
    private void loadPlayers() {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame)
            createPlayer();
        else if (e.getSource() == loadGame)
            loadPlayers();
        else if (e.getSource() == exit)
            System.exit(0);
    }
}
