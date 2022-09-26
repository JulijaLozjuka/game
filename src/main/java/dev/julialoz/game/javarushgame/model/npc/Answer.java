package dev.julialoz.game.javarushgame.model.npc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {
    private Long id;
    private String text;
    private Long nextMessage;
    private Long questId;
}
