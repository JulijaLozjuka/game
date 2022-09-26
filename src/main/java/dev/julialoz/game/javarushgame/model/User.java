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
    private String avatar;
    private String name;
    private int currentHealth;
    private int maxHealth;
    private Long currentLocationId;
    private int level = 1;
    @Builder.Default
    private List<Long> activeQuests = new ArrayList<>();

    @Builder.Default
    private List<Long> finishedQuests = new ArrayList<>();
    @Builder.Default
    private List<Long> inventory = new ArrayList<>();

    public void pickUpItem(long itemId) {
        inventory.add(itemId);
    }

    public boolean hasItem(Long itemId) {
        return inventory.contains(itemId);
    }

    public void takeQuest(Long questId) {
        activeQuests.add(questId);
    }

    public boolean isQuestTaken(Long questId) {
        return activeQuests.contains(questId);
    }

    public boolean isQuestComplete(Long questId) {
        return finishedQuests.contains(questId);
    }

    public void completeQuest(Long questId) {
        if(activeQuests.contains(questId)){
            finishedQuests.add(questId);
            activeQuests.remove(questId);
        }
    }
}
