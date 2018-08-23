package com.wphokomp.app.Controls;

import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.Models.HeroUI;
import com.wphokomp.app.View.SwingGUIView;
import com.wphokomp.app.View.SwingTextView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController implements ActionListener {
    private SwingGUIView swingGUIView;
    private GamePlay gamePlay;
    private SwingTextView swingTextView;
    private Hero hero;
//    private HeroUI heroUI;

    public GUIController(Hero hero, SwingGUIView swingGUIView) {
        this.hero = hero;
        this.swingGUIView = swingGUIView;
        swingTextView = new SwingTextView();
        this.gamePlay = new GamePlay(swingTextView, this.hero);
//        this.heroUI = new HeroUI();
    }

    public void startGame() {
        swingGUIView.setVisible(true);
        swingGUIView.getNewGame().addActionListener(this);
        swingGUIView.getLoadGame().addActionListener(this);
        swingGUIView.getExit().addActionListener(this);
        swingGUIView.getDetails().addActionListener(this);
        swingGUIView.getCreateButton().addActionListener(this);

        swingGUIView.getHeroUI().getSelect().addActionListener(this);
    }

    public void playGame() {
        this.hero = this.gamePlay.getHeroes_().get(swingGUIView.getHeroList().getSelectedIndex());
        this.hero.setDefense(this.swingTextView.getDefense(this.hero.getArmor()));
        this.hero.setAttack(this.swingTextView.getAttack(this.hero.getWeapon()));
        this.swingGUIView.gameMode();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == swingGUIView.getNewGame())
            swingGUIView.createPlayer();
        else if (e.getSource() == swingGUIView.getLoadGame()) {
            this.gamePlay.getHeroes();
            swingGUIView.loadPlayers(this.gamePlay.getHeroes_());
        }
        else if (e.getSource() == swingGUIView.getExit())
            System.exit(0);

        if (e.getSource() == swingGUIView.getCreateButton())
            swingGUIView.create();
        if (e.getSource() == swingGUIView.getDetails())
            swingGUIView.displayDetails(this.gamePlay.getHeroes_());
        if (e.getSource() == swingGUIView.getHeroUI().getSelect())
            playGame();
    }
}
