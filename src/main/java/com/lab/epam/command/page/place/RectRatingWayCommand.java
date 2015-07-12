package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.User;
import com.lab.epam.entity.Way;
import com.lab.epam.entity.WayRating;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserService;
import com.lab.epam.service.WayRatingService;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 01.07.2015.
 */
public class RectRatingWayCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        WayService wayService = new WayService();
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        WayRatingService wayRatingService = new WayRatingService();
        String ratingString = request.getParameter("rating");
        String way_idString = request.getParameter("way_id");
        //String category = request.getParameter("category");
        Integer ratingNew = null;
        Integer ratingOld = null;
        Integer way_id = null;
        WayRating way_rating = null;

        if (ratingString != null){
            ratingNew = Integer.parseInt(ratingString);
        }

        if (way_idString != null){
            way_id = Integer.parseInt(way_idString);
        }

        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }

        if (user != null && way_id != null){
            way_rating = wayRatingService.getWayRatingByWayAndUser(way_id, user.getId());
            if (way_rating != null){
                ratingOld = way_rating.getRating();
                if (ratingOld != ratingNew){
                    way_rating.setRating(ratingNew);
                    wayRatingService.update(way_rating);
                    Way way = wayService.getByPK(way_id);
                    Integer placeRating = way.getRating();
                    way.setRating(placeRating - ratingOld + ratingNew);
                    wayService.updateWayRating(way_id, way.getRating());
//                    System.out.println(place);
                   // wayService.update(way);
                }
            }else {
                wayRatingService.create(new WayRating(user.getId(),way_id,ratingNew));
                Way way = wayService.getByPK(way_id);
                Integer wayRating = way.getRating();
                way.setRating(wayRating + ratingNew);
                wayService.updateWayRating(way_id, wayRating + ratingNew);
                //wayService.update(way);

            }

        }
        loger.info("Command Rect rating way.");
       // request.setAttribute("category", category);
        // response.sendRedirect("portal?command=place&category=" + category);


    }
}
