package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceRating;
import com.lab.epam.entity.User;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceRatingService;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 23.06.2015.
 */
public class RectRatingCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        PlaceService servicePlace = new PlaceService();
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        PlaceRatingService placeRatingService = new PlaceRatingService();
        String ratingString = request.getParameter("rating");
        String place_idString = request.getParameter("place_id");
        String category = request.getParameter("category");
        Integer ratingNew = null;
        Integer ratingOld = null;
        Integer place_id = null;
        PlaceRating place_rating = null;

        if (ratingString != null){
            ratingNew = Integer.parseInt(ratingString);
        }

        if (place_idString != null){
            place_id = Integer.parseInt(place_idString);
        }

        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }

        if (user != null && place_id != null){
            place_rating = placeRatingService.getPlaceRatingByPlaceAndUser(place_id,user.getId());
            if (place_rating != null){
                ratingOld = place_rating.getRating();
                if (ratingOld != ratingNew){
                    place_rating.setRating(ratingNew);
                    placeRatingService.update(place_rating);
                    Place place = servicePlace.getByPK(place_id);
                    Integer placeRating = place.getRating();
                    place.setRating(placeRating - ratingOld + ratingNew);
                    servicePlace.update(place);
                }
            }else {
                placeRatingService.create(new PlaceRating(user.getId(),place_id,ratingNew));
                Place place = servicePlace.getByPK(place_id);
                Integer placeRating = place.getRating();
             //   System.out.println("place " + place);
               // System.out.println("placeRating " + placeRating);
                //System.out.println("ratingNew " + ratingNew);
                place.setRating(placeRating + ratingNew);
                servicePlace.update(place);

            }

        }
        loger.info("Command Rect rating.");
        request.setAttribute("category", category);
        request.getRequestDispatcher("portal?command=place").forward(request, response);


    }
}
