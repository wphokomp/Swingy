package com.wphokomp.app;

import com.wphokomp.app.Controls.GamePlay;
import com.wphokomp.app.Controls.TextModeController;
import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextMode;

public class Swingy {
    public static void main(String[] args) {
        Hero hero;
        SwingTextMode swingTextMode;
        GamePlay gamePlay;

        if ("console".equals(args[0])) {
            swingTextMode = new SwingTextMode();
            hero = new Hero();
            gamePlay = new GamePlay(swingTextMode, hero);
            TextModeController textModeController = new TextModeController(swingTextMode, gamePlay);
            try {
                textModeController.playGame();
            } catch (InvalidInput ii) {
                System.out.println(ii.getMessage());
            }
        }
        else if ("gui".equals(args[0]))
            System.out.println("GUI");
    }
}
