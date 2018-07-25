package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class Hero {
    @NotNull
    private String heroName;
    @NotNull
    private String heroClass;
    @Min(0) @Max(100)
    private int hitPoints;
    private int level = 0;
    private int experience;
    @NotNull
    private String attack;
    @NotNull
    private String defense;
}
