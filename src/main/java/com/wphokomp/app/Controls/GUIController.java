package com.wphokomp.app.Controls;

import com.wphokomp.app.Models.Hero;
import com.wphokomp.app.View.SwingGUIView;

public class GUIController {
    private SwingGUIView swingGUIView;
    private Hero hero;

    public GUIController(Hero hero, SwingGUIView swingGUIView) {
        this.hero = hero;
        this.swingGUIView = swingGUIView;

//        this.swingGUIView.addListener(new SwingListener());
    }

//    class SwingListener implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//
//        }
//    }
}
