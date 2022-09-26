package dev.julialoz.game.javarushgame.service;

import dev.julialoz.game.javarushgame.business.repository.ItemRepository;
import dev.julialoz.game.javarushgame.business.repository.LocationRepository;
import dev.julialoz.game.javarushgame.business.repository.NpcRepository;
import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.Location;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.item.Item;
import dev.julialoz.game.javarushgame.model.npc.Npc;
import dev.julialoz.game.javarushgame.model.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LocationService {
    protected static final Logger log = LogManager.getLogger();
    private final LocationRepository locationRepository;
    private final ItemRepository itemRepository;

    private final NpcRepository npcRepository;

    private final QuestRepository questRepository;

    public Location getCurrentLocation(User user) {
        List<Location> allLocations = locationRepository.findAll();
        return locationRepository.findById(user.getCurrentLocationId())
                .orElseGet(() -> {
                    log.error("Location with id {} not found. Setting the first location", user.getCurrentLocationId());
                    return allLocations.get(0);
                });
    }

    public List<Quest> getFinishedQuests(User user) {
        return getQuests(user.getFinishedQuests());
    }

    public List<Quest> getActiveQuests(User user) {
        return getQuests(user.getActiveQuests());
    }

    public List<Npc> getNpc(Location location) {
        List<Npc> npcs = new ArrayList<>();
        if (location.getNpcs() == null) {
            return npcs;
        }
        for (Long npcId : location.getNpcs()) {
            Optional<Npc> optionalNpc = npcRepository.findById(npcId);
            optionalNpc.ifPresent(npcs::add);
        }
        return npcs;
    }

    public List<Item> getInventory(User user) {
        List<Item> inventory = new ArrayList<>();
        for (Long itemId : user.getInventory()) {
            Optional<Item> optionalItem = itemRepository.findById(itemId);
            optionalItem.ifPresent(inventory::add);
        }
        return inventory;
    }

    public List<Location> getAccessibleLocations(User user, Location location) {
        List<Location> neighbours = new ArrayList<>();
        for (Long neighbouringLocation : location.getNeighbouringLocations()) {
            locationRepository.findById(neighbouringLocation).ifPresent(neighbours::add);
        }
        return neighbours.stream()
                .filter(location1 -> location1.getLevel() <= user.getLevel())
                .toList();
    }

    public List<Item> getItems(User user, Location location) {
        List<Item> items = new ArrayList<>();
        if (location.getItems()==null){
            return items;
        }
        for (Long itemId : location.getItems()) {
            if (user.hasItem(itemId)) {
                continue;
            }
            itemRepository.findById(itemId).ifPresent(items::add);
        }
        return items;
    }

    private List<Quest> getQuests(List<Long> questIds) {
        List<Quest> quests = new ArrayList<>();
        for (Long questId : questIds) {
            questRepository.findById(questId).ifPresent(quests::add);
        }
        return quests;
    }
}
