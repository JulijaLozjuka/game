package dev.julialoz.game.javarushgame.domain;

import dev.julialoz.game.javarushgame.model.item.*;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    ItemBuilder() {
    }

    public List<Item> generateItems() {
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

        // Item for ladybug quest, water at swamp
        items.add(QuestItem.builder()
                .id(4L)
                .name(ItemName.WATER)
                .build());

        // Item for firefly quest, hat
        items.add(QuestItem.builder()
                .id(5L)
                .name(ItemName.SMALL_BUG_SIZE_HAT)
                .build());

        return items;
    }
}
