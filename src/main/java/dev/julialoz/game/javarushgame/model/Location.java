package dev.julialoz.game.javarushgame.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Location {

    private Long id;

    private int level;

    private LocationName name;

    private String description;

    private Long key; // item id

    private String imageName;

    @Builder.Default
    private List<Long> items = new ArrayList<>();

    @Builder.Default
    private List<Long> npcs = new ArrayList<>();

    @Builder.Default
    private List<Long> enemyList = new ArrayList<>();

    @Builder.Default
    private List<Long> neighbouringLocations = new ArrayList<>();

}
