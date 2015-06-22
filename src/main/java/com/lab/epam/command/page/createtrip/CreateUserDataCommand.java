package com.lab.epam.command.page.createtrip;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Category;
import com.lab.epam.entity.UserDataAboutTrip;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.CategoryService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Days;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 19.06.2015.
 */
public class CreateUserDataCommand implements Command {

    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    public static final String CHURCHES = "Churches";
    public static final String ARCHITECTURE = "Architectural sights";
    public static final String THEATRES = "Theatres";
    public static String SQL_FORMAT_DATE = "yyyy-MM-dd";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDataAboutTrip userDataTrip = new UserDataAboutTrip();
        String stringDateFormat = "dd MMMM, yyyy";
        SimpleDateFormat format = new SimpleDateFormat(stringDateFormat, Locale.US);
        DateFormat formatter_sql = new SimpleDateFormat(SQL_FORMAT_DATE);
        CategoryService cateroryService = new CategoryService();
        Date begin = null;
        Date end = null;

        List<Category> listCategory = null;
        Boolean haveCategory = false;
        String beginTrip = request.getParameter("startDate");
        String endTrip = request.getParameter("endDate");
        String dontKnowDate = request.getParameter("dontKnowDate");
        String automatic = request.getParameter("automatic");
        String architecture = request.getParameter("architecture");
        String churches = request.getParameter("churches");
        String theatres = request.getParameter("theatres");
        String lunch = request.getParameter("lunch");
        String placeArrive = request.getParameter("placeArrive");

        System.out.println("beginTrip " + beginTrip);
        System.out.println("endTrip " + endTrip);
        System.out.println("dontKnowDate " + dontKnowDate);
        System.out.println("automatic " + automatic);
        System.out.println("architecture " + architecture);
        System.out.println("churches " + churches);
        System.out.println("theatres " + theatres);
        System.out.println("lunch " + lunch);
        System.out.println("placeArrive " + placeArrive);

        if (dontKnowDate == null){
            if (beginTrip != null){
                try {
                    begin = format.parse(beginTrip);
                    DateTime start = new DateTime(begin);
                    DateTime ending = new DateTime(new Date());
                    Days d = Days.daysBetween(start, ending);
                    int days = d.getDays();
                    if (days >= 0){
                        java.sql.Date sqltDate= new java.sql.Date(begin.getTime());
                        userDataTrip.setBeginTrip(sqltDate);
                    }else {
                        loger.info("Begindata is less than now" + beginTrip);
                    }
                } catch (ParseException e) {
                    loger.error("Error with parse begindata " + beginTrip);
                    e.printStackTrace();

                }
            }

            if (endTrip != null){
                try {
                    end = format.parse(endTrip);
                    DateTime start = new DateTime(begin);
                    DateTime ending = new DateTime(end);
                    Days d = Days.daysBetween(start, ending);
                    int days = d.getDays();
                    if (days >= 0){
                        java.sql.Date sqltDate= new java.sql.Date(end.getTime());
                        userDataTrip.setEndTrip(sqltDate);
                    }else {
                        loger.info("Enddata is less than now" + endTrip);
                    }
                } catch (ParseException e) {
                    loger.info("Error with parse endTrip " + endTrip);
                    e.printStackTrace();

                }
            }

            if ( userDataTrip.getBeginTrip() != null && userDataTrip.getEndTrip() != null){

                    DateTime start = new DateTime(begin);
                    DateTime ending = new DateTime(end);
                    Days d = Days.daysBetween(start, ending);
                    int days = d.getDays() + 1;
                    System.out.println("days " + days);
                    userDataTrip.setDayCount(days);
            }
        } else{
            userDataTrip.setDontKnowDate(true);
        }

        if (automatic != null) {
            userDataTrip.setIsAutomatic(false);
            if (architecture != null || churches != null || theatres != null) {
                listCategory = new ArrayList<>();
                haveCategory = true;
            }

            if (architecture != null) {
                Category cat = cateroryService.getCategoryByName(ARCHITECTURE);
                listCategory.add(cat);
            }

            if (churches != null) {
                Category cat = cateroryService.getCategoryByName(CHURCHES);
                listCategory.add(cat);
            }

            if (theatres != null) {
                Category cat = cateroryService.getCategoryByName(THEATRES);
                listCategory.add(cat);
            }

            if (haveCategory) {
                userDataTrip.setCategory(listCategory);
            }

            if (lunch != null) {
                userDataTrip.setIsCaffees(true);
            }

            if (placeArrive != null){
                if (placeArrive.equalsIgnoreCase("withoutPlaceArrive")) {
                    userDataTrip.setWithOutBegin(true);
                } else {
                    userDataTrip.setBeginPlace(placeArrive);
                }
            }

        }else{
            userDataTrip.setIsAutomatic(false);
        }

        HttpSession session = request.getSession();
        System.out.println("userDataTrip " + userDataTrip);
        session.setAttribute("userDataTrip", userDataTrip);
        response.sendRedirect("portal?command=showMap");
    }
}
