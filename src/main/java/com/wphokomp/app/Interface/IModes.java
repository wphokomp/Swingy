package com.wphokomp.app.Interface;

import com.wphokomp.app.Exceptions.InvalidInput;
import com.wphokomp.app.Models.Hero;

public interface IModes {
    void createHero();
    void getHeroes() throws InvalidInput;
    void log(Hero hero);
}
