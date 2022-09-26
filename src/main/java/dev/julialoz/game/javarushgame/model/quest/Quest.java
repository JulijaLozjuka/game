package dev.julialoz.game.javarushgame.model.quest;

import dev.julialoz.game.javarushgame.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
public abstract class Quest {
    Long id;
    Long questHolderId;
    String description;
    public abstract boolean isQuestDone(User user);
}
