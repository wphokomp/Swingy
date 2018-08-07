package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.FightOrFlight;

import java.util.Random;

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
            fight();
            System.out.println(this.enemy.getHitPoints());
        } else if (decision == 2) {
            throw new InvalidInput("Threw 2");
        }
    }

    private void fight() throws InvalidInput {
        Random rand = new Random();
        int hits = rand.nextInt(4);
        if (hits == 0) hits = 1;
        while (this.hero.getHitPoints() > 0 && this.enemy.getHitPoints() > 0) {
            this.hero.setHitPoints((this.hero.getHitPoints() - (this.enemy.getAttack() * hits)) - this.hero.getDefense());
            this.enemy.setHitPoints((this.enemy.getHitPoints() - (this.hero.getAttack() * hits)) - this.enemy.getDefense());
        }
    }
}
