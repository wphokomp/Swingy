package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Interface.IModes;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextMode;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class  TextModeController {
    Hero hero;
    GamePlay gamePlay;
    SwingTextMode swingTextMode;

    public TextModeController(SwingTextMode swingTextMode, GamePlay gamePlay) {
        this.swingTextMode = swingTextMode;
        this.gamePlay = gamePlay;
    }

    private void updateHero() throws InvalidInput {
        int direction = swingTextMode.movePlayer();
        if (direction == 1)
            this.hero.setY(this.hero.getY() - 1);
        else if (direction ==  2)
            this.hero.setX(this.hero.getX() + 1);
        else if (direction == 3)
            this.hero.setX(this.hero.getX() - 1);
        else if (direction == 4)
            this.hero.setY(this.hero.getY() + 1);
//        for (Enemy e:
//             gamePlay.getEnemies()) {
//            if (e.getY() == this.hero.getY() && e.getX() == this.hero.getX())
//                System.out.println("FOUND AN ENEMY");
//        }
    }

    public void playGame() throws InvalidInput {
        this.hero = gamePlay.initGame();
        swingTextMode.displayDetails(this.hero);
        while (this.hero.getX() < gamePlay.getMapSize() && this.hero.getX() >= 0
                && this.hero.getY() >= 0 && this.hero.getY() < gamePlay.getMapSize()) {

            swingTextMode.drawMap(this.hero, gamePlay.getEnemies(), gamePlay.getMapSize());
            updateHero();
        }
    }
}
