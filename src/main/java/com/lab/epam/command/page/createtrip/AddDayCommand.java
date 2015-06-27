package com.lab.epam.command.page.createtrip;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.helper.ClassName;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 27.06.2015.
 */
public class AddDayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
        Integer size;
        Integer dayCount = userDataTrip.getDayCount();
        if (placeDay != null) {
            size = placeDay.size();
            placeDay.put(size + 1, new ArrayList<Place>());
            dayCount = size + 1;
        }

        userDataTrip.setPlaceDay(placeDay);
        userDataTrip.setDayCount(dayCount);
        session.setAttribute("userDataTrip", userDataTrip);
        response.sendRedirect("/portal?command=userWays");
    }
}
