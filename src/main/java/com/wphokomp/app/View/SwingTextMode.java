package com.wphokomp.app.View;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Enemy;
import com.wphokomp.app.Models.Hero;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Scanner;

@Getter
public class SwingTextMode {
    private String choice;
    public Enemy enemy;
    private static Scanner scanner = new Scanner(System.in);

    public SwingTextMode() {
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

    public String getHeroWeapon() {
        int i = 1;
        String[] weapons = {"Short sword", "Dagger", "Falchion", "Katana", "Long Bow", "Long sword"};

        System.out.println("Choose your weapon: ");
        for (String w : weapons) {
            System.out.println(Integer.toString(i++).concat(") ".concat(w)));
        }
        return (weapons[Integer.parseInt(scanner.nextLine()) - 1]);
    }

    public int getAttack(String weapon) {
        int i = 0;
        String[] weapons = {"Short sword", "Dagger", "Falchion", "Katana", "Long Bow", "Long sword"};
        int[] attack = {6, 5, 12, 15, 10, 30};
        for (String w : weapons) {
            if (w.toLowerCase().equals(weapon.toLowerCase())) {
                return (attack[i]);
            }
            i++;
        }
        return (0);
    }

    public String getArmor() {
        int i = 1;
        String[] armor = {"Mining armor", "Steel armor", "Rich Mahogany armor", "Ebonwood armor"
                , "Shadewood armor", "Leather armor"};

        System.out.println("Choose your armor: ");
        for (String a : armor) {
            System.out.println(Integer.toString(i++).concat(") ".concat(a)));
        }
        return (armor[Integer.parseInt(scanner.nextLine()) - 1]);
    }

    public int getDefense(String _armor) {
        int i = 0;
        String[] armor = {"Mining armor", "Steel armor", "Rich Mahogany armor", "Ebonwood armor"
                , "Shadewood armor", "Leather armor"};
        int[] defense = {20, 22, 5, 2, 7, 3};

        for (String a : armor) {
            if (a.toLowerCase().equals(_armor.toLowerCase())) {
                return (defense[i]);
            }
            i++;
        }
        return (0);
    }

    public void displayDetails(Hero hero) {
        clearScreen();
        System.out.println("Name: ".concat(hero.getHeroName()));
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
        clearScreen();
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
        } catch (NumberFormatException ex) { }
        if (move < 1 || move > 4)
            throw new InvalidInput("You can either move, North, East, West and South.");
        return (move);
    }
}
