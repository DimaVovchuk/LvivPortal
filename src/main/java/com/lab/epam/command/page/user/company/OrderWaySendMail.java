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
        String text = request.getParameter("text");
        String type = request.getParameter("type");
        Map<Integer, List<Place>> placesMap = new HashMap<>();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();
        String language = (String) request.getSession().getAttribute("language");
        if (wayIdString != null) {
            Integer wayId = Integer.parseInt(wayIdString);
            PlaceService placeService = new PlaceService();
            Integer userId = (Integer) request.getSession().getAttribute("userID");
            Integer gidId = Integer.parseInt(request.getParameter("gidId"));
            UserService userService = new UserService();
            User gid = userService.getByPK(gidId);
            if (type.equals("way")) {
                WayService wayService = new WayService();
                Way way = wayService.getByPK(wayId);
                for (int i = 1; i <= way.getWay_days(); i++) {
                    List<Place> place = placeService.getPlaceByWayIdDayNumber(wayId, i);
                    if (place != null && !place.isEmpty()) {
                        placesMap.put(i, place);
                    }
                }
                if (userId != null) {
                    User user = userService.getByPK(userId);
                    StringBuilder mailInfo = new StringBuilder();
                    mailInfo.append("<h1>ORDER WAY EXCURSION</h1>\n" + "user: ").append(user.getName()).append(" <br>\n").append("email: ").append(user.getMail()).append(" <br>\n").append("phone: ").append(user.getPhone()).append(" <br>\n");
                    mailInfo.append("<br><p>").append(text).append("</p>");
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

                    SendEmail.sender("ORDER WAY EXCURSION", mailInfo.toString(), gid.getMail());
                }
            }

            if (type.equals("place")) {
                PlaceDescription placeDescriptionByIdPlace = placeDescriptionService.getPlaceDescriptionByIdPlace(wayId, language);
                if (userId != null) {
                    User user = userService.getByPK(userId);
                    StringBuilder mailInfo = new StringBuilder();
                    mailInfo.append("<h1>ORDER PLACE EXCURSION</h1>\n" + "user: ").append(user.getName()).append(" <br>\n").append("email: ").append(user.getMail()).append(" <br>\n").append("phone: ").append(user.getPhone()).append(" <br>\n");
                    mailInfo.append("<br><p>").append(text).append("</p>");
                    mailInfo.append("<p>").append(placeDescriptionByIdPlace.getName()).append("</p>");
                    mailInfo.append("</body>\n" +
                            "        </html>");

                    SendEmail.sender("ORDER PLACE EXCURSION", mailInfo.toString(), gid.getMail());
                }
            }
            if (type.equals("message")) {
                if (userId != null) {
                    User user = userService.getByPK(userId);
                    StringBuilder mailInfo = new StringBuilder();
                    mailInfo.append("<h1>MESSAGE</h1>\n" + "user: ").append(user.getName()).append(" <br>\n").append("email: ").append(user.getMail()).append(" <br>\n").append("phone: ").append(user.getPhone()).append(" <br>\n");
                    mailInfo.append("<br><p>").append(text).append("</p>");
                    mailInfo.append("</body>\n" +
                            "        </html>");
                    SendEmail.sender("MESSAGE", mailInfo.toString(), gid.getMail());

                }
            }
        }
    }
}
