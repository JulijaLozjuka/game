package dev.julialoz.game.javarushgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private int currentHealth;
    private int maxHealth;
    private Long currentLocationId;
    private int level = 1;
    @Builder.Default
    private List<Long> activeQuests = new ArrayList<>();
    @Builder.Default
    private List<Long> inventory = new ArrayList<>();

    public void pickUpItem(long itemId) {
        inventory.add(itemId);
    }

    public boolean hasItem(Long itemId) {
        return inventory.contains(itemId);
    }
}
