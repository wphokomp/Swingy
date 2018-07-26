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
    private String heroName;
    @NotNull
    private String heroClass;
    @Min(0) @Max(100)
    private int hitPoints;
    private int x, y;
}
