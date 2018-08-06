package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Interface.IModes;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.FightOrFlight;
import com.wphokomp.app.View.SwingTextMode;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TextModeController {
    Hero hero;
    GamePlay gamePlay;
    HeroStats heroStats;
    SwingTextMode swingTextMode;

    public TextModeController(SwingTextMode swingTextMode, GamePlay gamePlay) {
        this.swingTextMode = swingTextMode;
        this.gamePlay = gamePlay;
    }

    private void updateHero() throws InvalidInput {
        this.hero.prevDirection = swingTextMode.movePlayer();
        if (this.hero.prevDirection == 1)
            this.hero.setY(this.hero.getY() - 1);
        else if (this.hero.prevDirection == 2)
            this.hero.setX(this.hero.getX() + 1);
        else if (this.hero.prevDirection == 3)
            this.hero.setX(this.hero.getX() - 1);
        else if (this.hero.prevDirection == 4)
            this.hero.setY(this.hero.getY() + 1);
    }

    public void playGame() throws InvalidInput {
        this.hero = gamePlay.initGame();
        swingTextMode.displayDetails(this.hero);
        ArrayList<Enemy> enemies = gamePlay.getEnemies();
        swingTextMode.displayDetails(this.hero);
        while (this.hero.getX() < gamePlay.getMapSize() && this.hero.getX() >= 0
                && this.hero.getY() >= 0 && this.hero.getY() < gamePlay.getMapSize()) {
            swingTextMode.drawMap(this.hero, enemies, gamePlay.getMapSize());
            updateHero();
            for (Enemy e :
                    enemies) {
                if (e.getY() == this.hero.getY() && e.getX() == this.hero.getX()) {
                    heroStats = new HeroStats(this.hero, e);
                    heroStats.makeDecision();
//                    enemies.remove(e);
                    break ;
//                    FightOrFlight fightOrFlight = new FightOrFlight();
//                    System.out.println(e.getHitPoints());
//                    System.out.println(e.getAttack());
//                    System.out.println(e.getDefense());
//                    throw new InvalidInput("END");
                }
            }
        }
    }
}
