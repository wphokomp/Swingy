package com.wphokomp.app.Models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Hero extends Character {
    @NotNull
    private String heroClass;
    private int level = 1;
    private int experience;
    public int prevDirection;
}
