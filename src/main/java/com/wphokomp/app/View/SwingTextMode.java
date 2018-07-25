package com.wphokomp.app.View;

import lombok.Getter;

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
        return (scanner.nextLine().trim().concat(","));
    }

    public String getHeroClass() {
        System.out.println("Enter hero's class: ");
        return (scanner.nextLine().trim().concat(","));
    }

    public String getHeroAttack() {
        System.out.println("Enter hero's weapon: ");
        return (scanner.nextLine().trim().concat(","));
    }

    public String getHeroDefense() {
        System.out.println("Enter hero's defense: ");
        return (scanner.nextLine().trim());
    }
}
