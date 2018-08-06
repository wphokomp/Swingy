package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class Character {
    @Min(0)
    @Max(100)
    private int hitPoints;
    @Min(0)
    private int attack;
    @Min(0)
    private int defense;
    private int x, y;
}
