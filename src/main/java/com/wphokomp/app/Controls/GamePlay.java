package com.wphokomp.app.Controls;

import com.wphokomp.app.Models.Hero;

public class GamePlay {
    private Hero hero;
    public GamePlay(Hero hero) {
        this.hero = hero;
    }

    public void DrawMap() {
        int dimentions = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
        System.out.println();
        System.out.println();
        for (int y = 0; y < dimentions; y++) {
            for (int x = 0; x < dimentions; x++) {
                if (x == hero.getX() && y == hero.getY())
                    System.out.print(" X");
                else
                    System.out.print(" .");
            }
            System.out.println();
        }
    }
}
