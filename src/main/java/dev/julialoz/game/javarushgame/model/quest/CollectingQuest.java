package dev.julialoz.game.javarushgame.model.quest;

import dev.julialoz.game.javarushgame.model.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CollectingQuest extends Quest {

    private Long requiredItem;

    @Override
    public boolean checkCompletion(User user) {
        return user.getInventory().contains(requiredItem);
    }
}
