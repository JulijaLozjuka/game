package dev.julialoz.game.javarushgame;

import dev.julialoz.game.javarushgame.model.*;
import dev.julialoz.game.javarushgame.model.item.*;

import java.util.ArrayList;
import java.util.List;

// items
public class GameBuilder {
    private List<Item> items = new ArrayList<>();
    private List<Npc> npcs = new ArrayList<>();;
    private List<Enemy> enemys = new ArrayList<>();;
    private List<Location> locations = new ArrayList<>();;

    public GameBuilder() {
        items = generateItems();
        locations = generateLocations(items);
    }

    private List<Location> generateLocations(List<Item> items) {
        List<Location> result = new ArrayList<>();

        // start zone
        result.add(Location.builder()
                .id(1L)
                .level(1)
                .name(LocationName.SMALL_GRASS)
                .description("Starting peaceful zone")
                .npcs(null)
                .enemyList(null)
                .key(null)
                .items(List.of())
                .neighbouringLocations(List.of(2L, 3L))
                .build());

        // start zone neighbours
        result.add(Location.builder()
                .id(2L)
                .level(1)
                .name(LocationName.STUMP)
                .description("Zone with sticks")
                .npcs(null)
                .enemyList(null)
                .key(null)
                .items(List.of())
                .neighbouringLocations(List.of(1L))
                .build());

        result.add(Location.builder()
                .id(3L)
                .level(1)
                .name(LocationName.SWAMP)
                .description("Zone with swaps stuff")
                .npcs(null)
                .enemyList(null)
                .key(null)
                .items(List.of())
                .neighbouringLocations(List.of(1L))
                .build());

        return result;
    }

    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();

        items.add(Potion.builder()
                .id(1L)
                .name(ItemName.HEALTH_POTION)
                .build());

        items.add(Weapon.builder()
                .id(2L)
                .name(ItemName.STICK)
                .build());

        items.add(Armor.builder()
                .id(3L)
                .name(ItemName.BOTTLE_CAP)
                .build());

        return items;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public List<Enemy> getEnemys() {
        return enemys;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<User> getUsers() {
        return new ArrayList<>();
    }
}
