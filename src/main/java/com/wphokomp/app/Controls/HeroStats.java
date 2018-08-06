package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.FightOrFlight;

public class HeroStats {
    public Hero hero;
    public Enemy enemy;
    public HeroStats(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    public void makeDecision() throws InvalidInput {
        FightOrFlight fightOrFlight = new FightOrFlight();
        int decision = fightOrFlight.fightOrFlight();
        if (decision == 1) {

        } else if (decision == 2) {

        }
    }
}
