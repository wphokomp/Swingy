package com.wphokomp.app;

import com.wphokomp.app.Controls.GUIController;
import com.wphokomp.app.Controls.GamePlay;
import com.wphokomp.app.Controls.TextModeController;
import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingGUIView;
import com.wphokomp.app.View.SwingTextView;

public class Swingy {
    public static void guiMode() {
        Hero hero = new Hero();
        SwingGUIView swingGUIView = new SwingGUIView();
        GUIController guiController = new GUIController(hero, swingGUIView);

        guiController.startGame();
    }

    public static void textMode() {
        SwingTextView swingTextView = new SwingTextView();
        Hero hero = new Hero();
        GamePlay gamePlay = new GamePlay(swingTextView, hero);

        TextModeController textModeController = new TextModeController(swingTextView, gamePlay);
        try {
            textModeController.playGame();
        } catch (InvalidInput ii) {
            System.out.println(ii.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            if ("console".equals(args[0]))
                textMode();
            else if ("gui".equals(args[0]))
                guiMode();
            else
                System.out.println("The mode selected does not exist.");
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }
}
