package com.wphokomp.app.View;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Hero;

import java.util.Scanner;

public class FightOrFlight {
    private Hero hero;
    private static Scanner scanner = new Scanner(System.in);

    public FightOrFlight() {
    }

    public int fightOrFlight() throws InvalidInput {
        int choice;
        System.out.println("You have come across an enemy, what would you like to do?");
        choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > 2)
            throw new InvalidInput("You can either fight or flee.");
        return (choice);
    }
}
