package dev.julialoz.game.javarushgame.servlet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestDto {
    private Long questId;
    private Boolean done;
    private String description;
}
