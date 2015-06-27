package com.lab.epam.command.page.delete;

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
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 26.06.2015.
 */
public class DeleteDayCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip)session.getAttribute("userDataTrip");
        Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
        String dayString = request.getParameter("day");
        Integer day = null;
        Integer size;

        if (dayString != null){
            day = Integer.parseInt(dayString);
        }
        //System.out.println(day + " day");
        //System.out.println(placeDay + " 1 placeDay");
        if (day != null && placeDay != null && !placeDay.isEmpty()){
            size = placeDay.size();
            placeDay.remove(day);
          //System.out.println(placeDay  + " 2 placeDay");
            if (day < size){
                for (int i = day + 1; i <= size; i++){
                    List <Place> places = placeDay.get(i);
                    placeDay.put(i - 1, places);
                }
                placeDay.remove(size);
                System.out.println(placeDay  + " 3 placeDay");
            }
        }
        if (!placeDay.isEmpty()){
            userDataTrip.setPlaceDay(placeDay);
        } else{
            userDataTrip = null;
        }

        session.setAttribute("userDataTrip", userDataTrip);
        response.sendRedirect("/portal?command=userWays");



    }
}
