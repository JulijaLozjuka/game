package dev.julialoz.game.javarushgame.servlet;

import dev.julialoz.game.javarushgame.business.repository.ItemRepository;
import dev.julialoz.game.javarushgame.business.repository.LocationRepository;
import dev.julialoz.game.javarushgame.business.repository.NpcRepository;
import dev.julialoz.game.javarushgame.business.repository.QuestRepository;
import dev.julialoz.game.javarushgame.model.Location;
import dev.julialoz.game.javarushgame.model.User;
import dev.julialoz.game.javarushgame.service.LocationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "locationServlet", value = "/location")
public class LocationServlet extends BaseServlet {

    protected static final Logger log = LogManager.getLogger();
    private LocationService locationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        locationService = new LocationService((LocationRepository) servletContext.getAttribute("locationRepository")
                ,(ItemRepository) servletContext.getAttribute("itemRepository")
                ,(NpcRepository) servletContext.getAttribute("npcRepository")
                ,(QuestRepository) servletContext.getAttribute("questRepository"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Location location = locationService.getCurrentLocation(user);
        req.setAttribute("avatar", user.getAvatar());
        req.setAttribute("userName", user.getName());
        req.setAttribute("locationName", location.getName());
        req.setAttribute("locationDescription", location.getDescription());
        req.setAttribute("locationImage", location.getImageName());
        req.setAttribute("items", locationService.getItems(user, location));
        req.setAttribute("locations", locationService.getAccessibleLocations(user, location));
        req.setAttribute("inventory", locationService.getInventory(user));
        req.setAttribute("activeQuests", locationService.getActiveQuests(user));
        req.setAttribute("finishedQuests", locationService.getFinishedQuests(user));
        req.setAttribute("npcs", locationService.getNpc(location));

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/location.jsp")
                .forward(req, resp);

    }

      @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        String nextLocationId = req.getParameter("locationId");
        if (nextLocationId != null) {
            user.setCurrentLocationId(Long.parseLong(nextLocationId));
            log.debug("User with name: {} moved to location id: {}",user.getName(), nextLocationId);
        }

        String itemId = req.getParameter("itemId");
        if (itemId != null) {
            user.pickUpItem(Long.parseLong(itemId));
            log.debug("User with name: {} picked up item id: {}",user.getName(), itemId);
        }

        redirectToLocation(resp);
    }
}
