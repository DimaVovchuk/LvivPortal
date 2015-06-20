package com.lab.epam.command.page.createtrip;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Category;
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

/**
 * Created by Admin on 19.06.2015.
 */
public class CreateUserDataCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    public static final Integer TIME_DEFAULT = 8;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dayCountString = request.getParameter("dayCount");
        String dayTimeString = request.getParameter("dayTime");
        String dontNowString = request.getParameter("dontNowTime");
        String dontBegin = request.getParameter("dontBegin");
        String begin = request.getParameter("begin");
        String dontEnd = request.getParameter("dontEnd");
        String end = request.getParameter("end");
        String isCaffees = request.getParameter("isCaffees");
        String timeBetweenCaffeesString = request.getParameter("timeBetweenCaffees");
        String architectural = request.getParameter("architectural");
        String churches = request.getParameter("churches");
        String theatres = request.getParameter("theatres");
        String hotels = request.getParameter("hotels");
        String isHotel = request.getParameter("isHotel");
        String restaurants = request.getParameter("restaurants");
        Integer dayCount = 0;
        Integer dayTime = 0;
        List<Category> categry = new ArrayList<>();

        if (dayCountString != null){
            dayCount = Integer.parseInt(dayCountString);
        }
        if (dontNowString != null && !dontNowString.equalsIgnoreCase("dontNow")){
            dayTime = Integer.parseInt(dayTimeString);
        }else {
            dayTime = TIME_DEFAULT;
        }
        if (churches != null && churches.equalsIgnoreCase("churches")){
            categry.add(new Category("categry"));
        }
        if (theatres != null && theatres.equalsIgnoreCase("theatres")){
            categry.add(new Category("theatres"));
        }
        if (hotels != null && hotels.equalsIgnoreCase("hotels")){
            categry.add(new Category("hotels"));
        }
        if (architectural != null && architectural.equalsIgnoreCase("architectural")){
            categry.add(new Category("architectural"));
        }
        if (restaurants != null && restaurants.equalsIgnoreCase("restaurants")){
            categry.add(new Category("restaurants"));
        }
        UserDataAboutTrip userDataTrip = new UserDataAboutTrip.Builder(dayCount,dayTime,categry).build();

        if (dontBegin != null && !dontBegin.equalsIgnoreCase("dontBegin") && begin != null){
            userDataTrip.setBeginTrip(begin);
        }
        if (dontEnd != null && !dontEnd.equalsIgnoreCase("dontEnd") && end != null){
            userDataTrip.setEndTrip(end);
        }
        if (isCaffees != null && !isCaffees.equalsIgnoreCase("isCaffees")){
            userDataTrip.setIsCaffees(false);
        }
        if (isHotel != null && !isHotel.equalsIgnoreCase("isHotel")){
            userDataTrip.setIsHotel(false);
        }
        if (isCaffees != null && !isCaffees.equalsIgnoreCase("isCaffees") && timeBetweenCaffeesString != null){
            userDataTrip.setTimeBetweenCaffees(Integer.parseInt(timeBetweenCaffeesString));
        }
        HttpSession session = request.getSession();
        session.setAttribute("userDataTrip", userDataTrip);
        loger.info("Command Create data for user trip.");
        response.sendRedirect("portal?command=place");

    }
}
