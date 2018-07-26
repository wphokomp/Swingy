package com.wphokomp.app.View;

import com.wphokomp.app.Models.Hero;
import lombok.Getter;
import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.Scanner;

@Getter
public class SwingTextMode {
    private String choice;
    private static Scanner scanner = new Scanner( System.in );

    public SwingTextMode() {
        System.out.println("1) Create hero.");
        System.out.println("2) Select a hero.");
        this.choice = scanner.nextLine();
    }

    public String getHeroName() {
        System.out.println("Enter hero name:");
        return (scanner.nextLine().trim());
    }

    public String getHeroClass() {
        System.out.println("Enter hero's class: ");
        return (scanner.nextLine().trim());
    }

    public String getHeroWeapon() {
        int     i = 1;
        String[] weapons = {"Arming sword", "Dagger", "Falchion", "Katana", "Knife", "Longsword"};

        System.out.println("Choose your weapon: ");
        for (String w: weapons) {
            System.out.println(Integer.toString(i++).concat(") ".concat(w)));
        }
        return (weapons[Integer.parseInt(scanner.nextLine()) - 1]);
    }

    public int getAttack(String weapon) {
        int      i = 0;
        String[] weapons = {"Arming sword", "Dagger", "Falchion", "Katana", "Knife", "Longsword"};
        int[] attack = {4, 2, 6, 5, 3, 5};
        for (String w: weapons) {
            if (w.equals(weapon)) {
                return (attack[i]);
            }
            i++;
        }
        return (0);
    }

    public String getArmor() {
        int     i = 1;
        String[] armor = {"Mining armor", "Wood armor", "Rich Mahogany armor", "Ebonwood armor"
                , "Shadewood armor", "Rain armor"};

        System.out.println("Choose your armor: ");
        for (String a: armor) {
            System.out.println(Integer.toString(i++).concat(") ".concat(a)));
        }
        return (armor[Integer.parseInt(scanner.nextLine()) - 1]);
    }

    public int getDefense(String _armor) {
        int     i = 0;
        String[] armor = {"Mining armor", "Wood armor", "Rich Mahogany armor", "Ebonwood armor"
                , "Shadewood armor", "Rain armor"};
        int[]    defense = {3, 3, 4, 5, 5, 3};

        for (String a: armor) {
            if (a.equals(_armor)) {
                return (defense[i]);
            }
            i++;
        }
        return (0);
    }

    public void displayDetails(Hero hero) {
        System.out.println("Name: ".concat(hero.getHeroName()));
        System.out.println("Class: ".concat(hero.getHeroClass()));
        System.out.println("Level: ".concat(Integer.toString(hero.getLevel())));
        System.out.println("Experience: ".concat(Integer.toString(hero.getExperience()).concat(" XP")));
        System.out.println("Attack: ".concat(Integer.toString(hero.getAttack())));
        System.out.println("Defence: ".concat(Integer.toString(hero.getDefense())));
        System.out.println("Weapon: ".concat(hero.getWeapon()));
        System.out.println("Armor: ".concat(hero.getArmor()));
    }
}
