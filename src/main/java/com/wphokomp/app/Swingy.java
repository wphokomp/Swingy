package com.wphokomp.app;

import com.wphokomp.app.Controls.TextModeController;
import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingTextMode;

public class Swingy {
    public static void main(String[] args) {
        Hero hero;
        SwingTextMode swingTextMode;

        if ("console".equals(args[0])) {
            swingTextMode = new SwingTextMode();
            hero = new Hero();
            TextModeController textModeController = new TextModeController(swingTextMode, hero);
            try {
                textModeController.playGame();
            } catch (InvalidInput ii) {
                System.out.println("Invalid selection. Select 1 or 2.");
            }
        }
        else if ("gui".equals(args[0]))
            System.out.println("GUI");
    }
}
