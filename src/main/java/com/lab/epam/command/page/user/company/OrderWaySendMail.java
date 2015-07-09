package com.lab.epam.command.page.user.company;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.User;
import com.lab.epam.entity.Way;
import com.lab.epam.service.*;
import com.lab.epam.smtp.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dima on 08-Jul-15.
 */
public class OrderWaySendMail implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wayIdString = request.getParameter("wayId");
        Map<Integer, List<Place>> placesMap = new HashMap<>();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();
        String language = (String) request.getSession().getAttribute("language");
        if (wayIdString != null) {
            Integer wayId = Integer.parseInt(wayIdString);
            WayService wayService = new WayService();
            Way way = wayService.getByPK(wayId);
            PlaceService placeService = new PlaceService();
            for (int i = 1; i <= way.getWay_days(); i++) {
                List<Place> place = placeService.getPlaceByWayIdDayNumber(wayId, i);
                if (place != null && !place.isEmpty()) {
                    placesMap.put(i, place);
                }
            }
        }
        UserService userService = new UserService();
        Integer userId = (Integer) request.getSession().getAttribute("userID");
        if (userId != null) {
            User user = userService.getByPK(userId);
            StringBuilder mailInfo = new StringBuilder();

            mailInfo.append("<h1>Order way</h1>\n" + "user: ").append(user.getName()).append(" <br>\n").append("email: ").append(user.getMail()).append(" <br>\n").append("phone: ").append(user.getPhone()).append(" <br>\n");
            for (int i = 1; i <= placesMap.size(); i++) {
                List<Place> places = placesMap.get(i);
                mailInfo.append("<h2>Day ").append(i).append("</h2> ").append("<ul>");
                for (Place place : places) {
                    PlaceDescription placeDescriptionByIdPlace = placeDescriptionService.getPlaceDescriptionByIdPlace(place.getId(), language);
                    String reference = placeImageService.getPlaceImageByPlaceId(place.getId()).getReference();
                    mailInfo.append("<li>").append(placeDescriptionByIdPlace.getName()).append("</li>");
                }
                mailInfo.append("</ul>");
            }
            mailInfo.append("</body>\n" +
                    "        </html>");

            Integer gidId = Integer.parseInt(request.getParameter("gidId"));
            User gid = userService.getByPK(gidId);
            SendEmail.sender("Order ways", mailInfo.toString(), gid.getMail());
        }
    }
}
