package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class HeroUI extends JFrame {
    private JTextField heroName;
    private JLabel nameLabel;
    private JLabel classLabel;
    private JTextField heroClass;
    private JButton select;
}
