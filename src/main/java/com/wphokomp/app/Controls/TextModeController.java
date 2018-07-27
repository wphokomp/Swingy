package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Interface.IModes;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextMode;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class  TextModeController implements IModes {
    Hero hero;
    ArrayList<Hero> heroes = new ArrayList<>();
    File inFile = new File("heroes.txt");
    SwingTextMode swingTextMode;
    GamePlay gamePlay;
    private boolean heroCreated = false;
    private static Scanner scanner = new Scanner( System.in );

    public TextModeController(SwingTextMode swingTextMode, Hero hero) {
        this.hero = hero;
        this.swingTextMode = swingTextMode;
    }

    public void playGame() throws InvalidInput {
        if (swingTextMode.getChoice().equals("1")) {
            createHero();
            this.heroCreated = true;
        }
        else if (swingTextMode.getChoice().equals("2")){
            int i = 0;
            getHeroes();
            for (Hero _hero: this.heroes) {
                System.out.println(Integer.toString(++i).concat(") ".concat(_hero.getHeroName())));
            }
            this.hero = this.heroes.get(Integer.parseInt(scanner.nextLine()) - 1);
            this.hero.setX(((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2)) / 2);
            this.hero.setY(((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2)) / 2);
        }
        else
            throw new InvalidInput("Invalid Selection. Select 1 or 2");
        //Do game stuff
        swingTextMode.displayDetails(this.hero);
        //CreateGamePlay
        gamePlay = new GamePlay();
        gamePlay.DrawMap(this.hero);
        while (this.hero.getX() >= 0 && this.hero.getX() <= gamePlay.getDimentions()
                && this.hero.getY() >= 0 && this.hero.getY() <= gamePlay.getDimentions())
        {
            gamePlay.DrawMap(this.hero);
            if (swingTextMode.movePlayer() == 1)
                this.hero.setY(this.hero.getY() - 1);
            else if (swingTextMode.movePlayer() == 4)
                this.hero.setY(this.hero.getY() + 1);
            else if (swingTextMode.movePlayer() == 2)
                this.hero.setX(this.hero.getX() + 1);
            else if (swingTextMode.movePlayer() == 3)
                this.hero.setX(this.hero.getX() - 1);
            else
                throw new InvalidInput("You can either move, North, East, West and South.");
        }
//        log(this.hero);
    }

    @Override
    public void createHero() {
        hero.setHeroName(swingTextMode.getHeroName());
        //Check if hero exists
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
        System.out.println(this.heroCreated);
        if (this.heroCreated) {
            try {
                File tmpFile = new File("myTmpFile.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));

                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    String trimmedLine = currentLine.trim().toLowerCase();
                    if (trimmedLine.contains(this.hero.getHeroName().toLowerCase())) continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.close();
                reader.close();
                boolean successful = tmpFile.renameTo(inFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        try {
            FileWriter writer = new FileWriter(inFile, true);
            String line = hero.getHeroName().concat(hero.getHeroClass()
                    .concat((Integer.toString(hero.getLevel())).concat(",")
                            .concat((Integer.toString(hero.getExperience())).concat(",")
                                    .concat(hero.getWeapon().concat(hero.getArmor())))));
            writer.write(line);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
