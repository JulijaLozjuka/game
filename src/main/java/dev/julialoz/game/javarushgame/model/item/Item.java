package dev.julialoz.game.javarushgame.model.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Long id;
    private ItemName name;
}
