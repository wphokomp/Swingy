package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Hero extends Character {
    private int level = 1;
    private int experience;
    @Min(0)
    private int attack;
    @Min(0)
    private int defense;
    @NotNull
    private String weapon;
    @NotNull
    private String armor;
}
