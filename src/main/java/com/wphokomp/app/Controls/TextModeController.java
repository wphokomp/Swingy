package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextView;

import java.util.ArrayList;

public class TextModeController {
    Hero hero;
    GamePlay gamePlay;
    HeroStats heroStats;
    SwingTextView swingTextView;

    public TextModeController(SwingTextView swingTextView, GamePlay gamePlay) {
        this.swingTextView = swingTextView;
        this.gamePlay = gamePlay;
    }

    private void updateHero() throws InvalidInput {
        this.hero.prevDirection = swingTextView.movePlayer();
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
        Enemy enemy = null;
        START:
        while (true) {
            this.hero = gamePlay.initGame();
            ArrayList<Enemy> enemies = gamePlay.getEnemies();
            while (this.hero.getX() < gamePlay.getMapSize() && this.hero.getX() >= 0
                    && this.hero.getY() >= 0 && this.hero.getY() < gamePlay.getMapSize()) {
                swingTextView.clearScreen();
                swingTextView.drawMap(this.hero, enemies, gamePlay.getMapSize());
                updateHero();
                for (Enemy e :
                        enemies) {
                    if (e.getY() == this.hero.getY() && e.getX() == this.hero.getX()) {
                        heroStats = new HeroStats(this.hero, e);
                        enemy = e;
                        heroStats.makeDecision();
                        if (this.hero.getHitPoints() > 0) {
                            if (enemy != null)
                                enemies.remove(enemy);
                            if (heroStats._levelUp) {
                                gamePlay.artifacts();
                                continue START;
                            }
                        }
                        break;
                    }
                }
                if (this.hero.getHitPoints() <= 0) {
                    swingTextView.gameOver(enemy, this.hero);
                    gamePlay.log(this.hero);
                    break;
                }
            }
            if (this.hero.getHitPoints() <= 0)
                break;
        }
    }
}
