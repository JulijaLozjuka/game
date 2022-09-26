package dev.julialoz.game.javarushgame.model.npc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Npc {
    private Long id;
    private String name;
    private Long questId;
    private Long dialogStartingMessage;
    private String questCompleteText;
    private String questInProgressText;
}
