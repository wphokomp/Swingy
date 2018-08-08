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
        } else if (decision == 2) {
            throw new InvalidInput("Threw 2");
        }
    }

    private void fight() {
        Random rand = new Random();
        int hits;
        while (this.hero.getHitPoints() > 0 && this.enemy.getHitPoints() > 0) {
            hits = rand.nextInt(4);
            if (hits == 0) hits = 1;
            this.enemy.setHitPoints((this.enemy.getHitPoints() - ((this.hero.getAttack() * hits) - this.enemy.getDefense())));
            this.hero.setHitPoints((this.hero.getHitPoints() - ((this.enemy.getAttack() * hits) - this.hero.getDefense())));
        }
        if (this.hero.getHitPoints() > 0) {
            this.hero.setExperience(((int) (this.hero.getLevel() * 1000 + Math.pow(this.hero.getLevel() - 1, 2) * 450)));
            levelUp();
        }
    }

    public int levelUp() {
        if (this.hero.getExperience() >= 1000) {
            this.hero.setLevel(2);
            return (1);
        } else if (this.hero.getExperience() >= 2450) {
            this.hero.setLevel(3);
            return (1);
        } else if (this.hero.getExperience() >= 4800) {
            this.hero.setLevel(4);
            return (1);
        } else if (this.hero.getExperience() >= 8050) {
            this.hero.setLevel(5);
            return (1);
        } else if (this.hero.getExperience() >= 8050) {
            this.hero.setLevel(6);
            return (1);
        } else return (0);
    }
}
