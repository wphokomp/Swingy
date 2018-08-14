package com.wphokomp.app.View;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

@Getter
public class SwingTextView {
    private String choice;
    private static Scanner scanner = new Scanner(System.in);

    public SwingTextView() {
        clearScreen();
        System.out.println("1) Create hero.");
        System.out.println("2) Select a hero.");
        this.choice = scanner.nextLine();
    }

    public String getHeroName() {
        clearScreen();
        System.out.println("Enter hero name:");
        return (scanner.nextLine().trim());
    }

    public String getHeroClass() {
        clearScreen();
        System.out.println("Enter hero's class: ");
        return (scanner.nextLine().trim());
    }

    public int getAttack(String weapon) {
        int i = 0;
        String[] weapons = {"Short sword", "Dagger", "Falchion", "Katana", "Long Bow", "Long sword"};
        int[] attack = {12, 25, 32, 45, 50, 68};
        for (String w : weapons) {
            if (w.toLowerCase().equals(weapon.toLowerCase())) {
                return (attack[i]);
            }
            i++;
        }
        return (0);
    }

    public int getDefense(String _armor) {
        int i = 0;
        String[] armor = {"Mining armor", "Steel armor", "Rich Mahogany armor", "Ebonwood armor"
                , "Shadewood armor", "Leather armor"};
        int[] defense = {63, 57, 42, 35, 22, 10};

        for (String a : armor) {
            if (a.toLowerCase().equals(_armor.toLowerCase())) {
                return (defense[i]);
            }
            i++;
        }
        return (0);
    }

    public void displayDetails(Hero hero) {
        System.out.println("Name: ".concat(hero.getName()));
        System.out.println("Class: ".concat(hero.getHeroClass()));
        System.out.println("Level: ".concat(Integer.toString(hero.getLevel())));
        System.out.println("Experience: ".concat(Integer.toString(hero.getExperience()).concat(" XP")));
        System.out.println("Attack: ".concat(Integer.toString(hero.getAttack())));
        System.out.println("Defence: ".concat(Integer.toString(hero.getDefense())));
        System.out.println("Weapon: ".concat(hero.getWeapon()));
        System.out.println("Armor: ".concat(hero.getArmor()));
        System.out.println("Press any key to continue...");
        while (scanner.nextLine() == null) ;
    }

    public ArrayList<String> battleWon(int level) {
        ArrayList<String> artifacts = new ArrayList<>();
        String[] armor = {"Leather armor", "Ebonwood armor", "Rich Mahogany armor"
                , "Shadewood armor", "Mining armor", "Steel armor"};
        String[] weapons = {"Dagger", "Short sword", "Falchion", "Katana"
                , "Long Bow", "Long sword"};

        System.out.println("\nYOU'VE WON THE BATTLE...\nYou're rewarded with a new weapon: "
                .concat(weapons[level]));
        artifacts.add(weapons[level]);
        System.out.println("And armor: "
                .concat(armor[level]));
        System.out.println("Press any key to continue to the next level...");
        artifacts.add(armor[level]);
        scanner.nextLine();
        return (artifacts);
    }

    public void gameOver(Enemy enemy, Hero hero) {
        clearScreen();
        System.out.println("GAME OVER!!!");
        System.out.println("Hero: ".concat(hero.getName().concat(". Killed by: ").concat(enemy.getName())));
        System.out.println("XP: ".concat(Integer.toString(hero.getExperience())
                .concat(". Level: ").concat(Integer.toString(hero.getLevel()))));
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Enemy getEnemy(int x, int y, ArrayList<Enemy> enemies) {
        for (Enemy e :
                enemies) {
            if (e.getX() == x && e.getY() == y)
                return (e);
        }
        return (null);
    }

    public void drawMap(Hero hero, ArrayList<Enemy> enemies, int mapSize) {
        Enemy e;
        System.out.print(" Life: ".concat(Integer.toString(hero.getHitPoints()).concat("\t")));
        System.out.println(" Level: ".concat(Integer.toString(hero.getLevel())));
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                e = getEnemy(x, y, enemies);
                if (x == hero.getX() && y == hero.getY())
                    System.out.print(" X");
                else if (e != null) {
                    System.out.print(" O");
                } else
                    System.out.print(" .");
            }
            System.out.println();
        }
    }

    public int movePlayer() throws InvalidInput {
        int move = 0;
        System.out.println("1) North");
        System.out.println("2) East");
        System.out.println("3) West");
        System.out.println("4) South");
        try {
            move = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
        }
        if (move < 1 || move > 4)
            throw new InvalidInput("You can either move, North, East, West and South.");
        return (move);
    }
}
