package com.wphokomp.app.Controls;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Interface.IModes;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextMode;

import java.io.*;
import java.util.ArrayList;

public class TextModeController implements IModes {
    Hero hero;
    ArrayList<Hero> heroes = new ArrayList<>();
    File inFile = new File("data.txt");
    private boolean heroCreated = false;
    SwingTextMode swingTextMode;

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
            getHeroes();
            for (Hero _hero: this.heroes) {
                System.out.println(_hero.getHeroName());
            }
        }
        else
            throw new InvalidInput("Invalid Selection. Select 1 or 2");
        //Do game stuff

//        log(this.hero);
    }

    @Override
    public void createHero() {
        hero.setHeroName(swingTextMode.getHeroName());
        //Check if hero exists
        hero.setHeroClass(swingTextMode.getHeroClass());
        hero.setLevel(0);
        hero.setExperience(0);
        hero.setAttack(swingTextMode.getHeroAttack());
        hero.setDefense(swingTextMode.getHeroDefense());
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
                hero.setDefense(stats[4]);
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
                                    .concat(hero.getAttack().concat(hero.getDefense())))));
            writer.write(line);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
