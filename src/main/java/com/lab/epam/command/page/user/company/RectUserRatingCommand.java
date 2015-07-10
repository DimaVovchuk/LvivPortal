package com.lab.epam.command.page.user.company;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.User;
import com.lab.epam.entity.UserRating;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.UserRatingService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Admin on 10.07.2015.
 */
public class RectUserRatingCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        UserRatingService userRatingService = new UserRatingService();
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        String ratingString = request.getParameter("rating");
        String company_idString = request.getParameter("company_id");
        Integer ratingNew = null;
        Integer ratingOld = null;
        Integer company_id = null;
        UserRating userRating = null;

        if (ratingString != null){
            ratingNew = Integer.parseInt(ratingString);
        }

        if (company_idString != null){
            company_id = Integer.parseInt(company_idString);
        }

        String login = (String)session.getAttribute("login");
        User user = null;
        if (login != null){
            user = userService.getUserByLogin(login);
        }

        if (user != null && company_id != null){
            userRating = userRatingService.getUseRatingByCompanyAndUser(company_id, user.getId());
            if (userRating != null){
                ratingOld = userRating.getRating();
                if (ratingOld != ratingNew){
                    userRating.setRating(ratingNew);
                    userRatingService.update(userRating);
                    User company = userService.getByPK(company_id);
                    Integer user_Rating = company.getRating();
                    company.setRating(user_Rating - ratingOld + ratingNew);
//                    System.out.println(place);
                    try {
                        userService.update(company);
                    } catch (PersistException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                userRatingService.create(new UserRating(user.getId(),company_id,ratingNew));
                User company = userService.getByPK(company_id);
                Integer user_Rating = company.getRating();
                //   System.out.println("place " + place);
                // System.out.println("placeRating " + placeRating);
                //System.out.println("ratingNew " + ratingNew);
                company.setRating(user_Rating + ratingNew);
                try {
                    userService.update(company);
                } catch (PersistException e) {
                    e.printStackTrace();
                }

            }

        }
        loger.info("Command Rect rating user.");
        //request.setAttribute("category", category);
        // response.sendRedirect("portal?command=place&category=" + category);


    }
}
