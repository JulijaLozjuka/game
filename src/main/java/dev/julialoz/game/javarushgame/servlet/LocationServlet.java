package dev.julialoz.game.javarushgame.servlet;

import dev.julialoz.game.javarushgame.business.repository.*;
import dev.julialoz.game.javarushgame.model.Location;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.model.item.Item;
import dev.julialoz.game.javarushgame.model.npc.Npc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "locationServlet", value = "/location")
public class LocationServlet extends BaseServlet {

    protected static final Logger log = LogManager.getLogger();

    private LocationRepository locationRepository = null;
    private ItemRepository itemRepository = null;

    private NpcRepository npcRepository = null;

    private QuestRepository questRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        locationRepository = (LocationRepository) servletContext.getAttribute("locationRepository");
        itemRepository = (ItemRepository) servletContext.getAttribute("itemRepository");
        npcRepository = (NpcRepository) servletContext.getAttribute("npcRepository");
        questRepository = (QuestRepository) servletContext.getAttribute("questRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Location> allLocations = locationRepository.findAll();
        Location location = locationRepository.findById(user.getCurrentLocationId()).orElseGet(() -> {
            log.error("Location with id {} not found. Setting the first location", user.getCurrentLocationId());
            return allLocations.get(0);
        });

        req.setAttribute("userName", user.getName());
        req.setAttribute("locationName", location.getName());
        req.setAttribute("locationDescription", location.getDescription());
        req.setAttribute("items", getItems(user, location));
        req.setAttribute("locations", getLocations(user, location));
        req.setAttribute("inventory", getInventory(user));
        req.setAttribute("npcs", getNpc(location));

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/location.jsp")
                .forward(req, resp);

    }

    private List<Npc> getNpc(Location location) {
        if(location.getNpcs() == null){
            return null;
        }
        List<Npc> npcs = new ArrayList<>();
        for (Long npcId : location.getNpcs()) {
            npcs.add(npcRepository.findById(npcId).get());
        }
        return npcs;
    }

    private List<Item> getInventory(User user) {
        List<Item> inventory = new ArrayList<>();
        for (Long itemId : user.getInventory()) {
            inventory.add(itemRepository.findById(itemId).get());
        }
        return inventory;
    }

    private List<Location> getLocations(User user, Location location) {
        List<Location> neighbours = new ArrayList<>();
        for (Long neighbouringLocation : location.getNeighbouringLocations()) {
            neighbours.add(locationRepository.findById(neighbouringLocation).get());
        }

        List<Location> accessibleLocations = neighbours.stream()
                .filter(location1 -> location1.getLevel() <= user.getLevel())
                .collect(Collectors.toList());
        return accessibleLocations;
    }

    private List<Item> getItems(User user, Location location) {
        List<Item> items = new ArrayList<>();
        for (Long itemId : location.getItems()) {
            if (user.hasItem(itemId)) {
                continue;
            }
            items.add(itemRepository.findById(itemId).get());
        }
        return items;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");


        String nextLocationId = req.getParameter("locationId");
        if (nextLocationId != null) {
            user.setCurrentLocationId(Long.parseLong(nextLocationId));
        }

        String itemId = req.getParameter("itemId");
        if (itemId != null) {
            user.pickUpItem(Long.parseLong(itemId));
        }

        redirectToLocation(resp);
    }
}
