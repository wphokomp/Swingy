package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Interface.IModes;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextMode;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Getter
public class GamePlay implements IModes {
    private int mapSize;
    private Hero hero;
    private SwingTextMode swingTextMode;
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Hero> heroes = new ArrayList<>();
    File inFile = new File("heroes.txt");
    private static Scanner scanner = new Scanner(System.in);

    public GamePlay(SwingTextMode swingTextMode, Hero hero) {
        this.hero = hero;
        this.swingTextMode = swingTextMode;
    }

    public Hero initGame() throws InvalidInput {
        if (swingTextMode.getChoice().equals("1")) {
            createHero();
        } else if (swingTextMode.getChoice().equals("2")) {
            int i = 0;
            getHeroes();
            for (Hero _hero : this.heroes)
                System.out.println(Integer.toString(++i).concat(") ".concat(_hero.getHeroName())));
            hero = this.heroes.get(Integer.parseInt(scanner.nextLine()) - 1);
            hero.setAttack(swingTextMode.getAttack(hero.getWeapon()));
            hero.setDefense(swingTextMode.getDefense(hero.getArmor()));
            hero.setHitPoints(100);
        } else
            throw new InvalidInput("Invalid Selection. Select 1 or 2");
        this.mapSize = ((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
        hero.setX(this.mapSize / 2);
        hero.setY(this.mapSize / 2);
        getEnemyList(hero);
        return (hero);
    }

    public void getEnemyList(Hero hero) {
        Random rand = new Random();
        int size = (this.mapSize / 2) - ((this.mapSize / 2) % 2);
        int[] attack = {2, 4, 5, 8, 11, 15};
        int[] defense = {1, 2, 4, 7, 10, 20};
        while (this.enemies.size() < size) {
            Enemy enemy = new Enemy();
            enemy.setDefense(defense[hero.getLevel() - 1]);
            enemy.setAttack(attack[hero.getLevel() - 1]);
            enemy.setHitPoints(100);
            enemy.setX(rand.nextInt(this.mapSize));
            enemy.setY(rand.nextInt(this.mapSize));
            this.enemies.add(enemy);
        }
    }

    @Override
    public void createHero() {
        hero.setHeroName(swingTextMode.getHeroName());
        hero.setHeroClass(swingTextMode.getHeroClass());
        hero.setExperience(0);
        hero.setWeapon(swingTextMode.getHeroWeapon());
        hero.setArmor(swingTextMode.getArmor());
        hero.setAttack(swingTextMode.getAttack(hero.getWeapon()));
        hero.setDefense(swingTextMode.getDefense(hero.getArmor()));
        hero.setHitPoints(100);
    }

    @Override
    public void getHeroes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] stats = trimmedLine.split(",");
                hero = new Hero();
                hero.setHeroName(stats[0]);
                hero.setHeroClass(stats[1]);
                hero.setLevel(Integer.parseInt(stats[2]));
                hero.setExperience(Integer.parseInt(stats[3]));
                hero.setWeapon(stats[4]);
                hero.setArmor(stats[5]);
                this.heroes.add(hero);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void log(Hero hero) {
    }
}
