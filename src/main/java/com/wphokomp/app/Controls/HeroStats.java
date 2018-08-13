package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.FightOrFlight;

public class HeroStats {
    public Hero hero;
    public Enemy enemy;
    public boolean _levelUp = false;
    public boolean _flightSuccessful = false;

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
            flight();
        }
    }

    private void fight() {
        while (this.hero.getHitPoints() > 0 && this.enemy.getHitPoints() > 0) {
            this.hero.setHitPoints(this.hero.getHitPoints() - damageHero());
            this.enemy.setHitPoints(this.enemy.getHitPoints() - damageEnemy());
        }
        if (this.hero.getHitPoints() > 0) {
            this.hero.setExperience(((int) (this.hero.getLevel() * 1000 + Math.pow(this.hero.getLevel() - 1, 2) * 450)));
            if (levelUp() == 1)
                this._levelUp = true;
        }
    }

    private void flight() {
        if (this.hero.getDefense() > this.enemy.getAttack()) {
            this._flightSuccessful = true;
        } else
            fight();
    }

    private int damageHero() {
        int defense = this.hero.getDefense();
        int ret = 0;
        while (defense > 0) {
            if (this.enemy.getAttack() > defense) {
                ret = (defense > 0) ? (this.enemy.getAttack() - defense) : this.enemy.getAttack();
                break;
            }
            defense -= this.enemy.getAttack();
        }
        return ret;
    }

    private int damageEnemy() {
        int defense = this.enemy.getDefense();
        int ret = 0;
        while (defense > 0) {
            if (this.hero.getAttack() > defense) {
                ret = (defense > 0) ? (this.hero.getAttack() - defense) : this.hero.getAttack();
                break;
            }
            defense -= this.hero.getAttack();
        }
        return ret;
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
