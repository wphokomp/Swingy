package com.wphokomp.app.Controls;

import com.wphokomp.app.Models.Hero;
import lombok.Getter;

@Getter
public class GamePlay {
    private int dimentions;

    public GamePlay() {
    }

    public void DrawMap(Hero hero) {
        this.dimentions = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
//        System.out.println();
        System.out.println();
        for (int y = 0; y < this.dimentions; y++) {
            for (int x = 0; x < this.dimentions; x++) {
                if (x == hero.getX() && y == hero.getY())
                    System.out.print(" X");
                else
                    System.out.print(" .");
            }
            System.out.println();
        }
    }
}
