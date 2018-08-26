package com.wphokomp.app.Controls;

import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingGUIView;
import com.wphokomp.app.View.SwingTextView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener {
    private SwingGUIView swingGUIView;
    private GamePlay gamePlay;
    private SwingTextView swingTextView;
    private Hero hero;

    public GUIController(Hero hero, SwingGUIView swingGUIView) {
        this.hero = hero;
        this.swingGUIView = swingGUIView;
        swingTextView = new SwingTextView();
        this.gamePlay = new GamePlay(swingTextView, this.hero);
    }

    public void startGame() {
        swingGUIView.setVisible(true);
        swingGUIView.getComponent().getNewGame().addActionListener(this);
        swingGUIView.getComponent().getLoadGame().addActionListener(this);
        swingGUIView.getComponent().getExit().addActionListener(this);
        swingGUIView.getDetails().addActionListener(this);
        swingGUIView.getCreateButton().addActionListener(this);

        swingGUIView.getComponent().getSelect().addActionListener(this);
    }

    public void playGame() {
        this.hero = this.gamePlay.getHeroes_().get(swingGUIView.getHeroList().getSelectedIndex());
        this.hero.setDefense(this.swingTextView.getDefense(this.hero.getArmor()));
        this.hero.setAttack(this.swingTextView.getAttack(this.hero.getWeapon()));
        this.hero.setHitPoints(100);
        this.swingGUIView.gameMode(this.hero);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == swingGUIView.getComponent().getNewGame())
            swingGUIView.createPlayer();
        else if (e.getSource() == swingGUIView.getComponent().getLoadGame()) {
            this.gamePlay.getHeroes();
            swingGUIView.loadPlayers(this.gamePlay.getHeroes_());
        }
        else if (e.getSource() == swingGUIView.getComponent().getExit())
            System.exit(0);

        if (e.getSource() == swingGUIView.getCreateButton())
            swingGUIView.create();
        if (e.getSource() == swingGUIView.getDetails())
            swingGUIView.displayDetails(this.gamePlay.getHeroes_());
        if (e.getSource() == swingGUIView.getComponent().getSelect())
            playGame();
    }
}
