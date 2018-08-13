package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Character {
    @NotNull
    private String name;
    @Min(0)
    @Max(100)
    private int hitPoints;
    @Min(0)
    private int attack;
    @Min(0)
    private int defense;
    @NotNull
    private String weapon;
    @NotNull
    private String armor;
    private int x, y;
}
