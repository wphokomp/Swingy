package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Character {
    @NotNull(message = "Name cannot be null.")
    private String name;
    @Min(value = 0, message = "HP cannot be lower than 0.")
    @Max(value  = 100, message = "HP cannot be higher than 100.")
    private int hitPoints;
    @Min(value = 0, message = "Attack cannot be lower than 0")
    private int attack;
    @Min(value = 0, message = "Defense cannot be lower than 0")
    private int defense;
    @NotNull(message = "Weapon cannot be null.")
    private String weapon;
    @NotNull(message = "Armor cannot be null.")
    private String armor;
    @Min(value = 0, message = "Position cannot be lower than 0")
    private int x, y;
}
