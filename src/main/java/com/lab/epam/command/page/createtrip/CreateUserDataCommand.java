package com.lab.epam.command.page.createtrip;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.CategoryService;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceService;
import com.lab.epam.workWithMap.Distance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        int daysBegin = -1;
        Boolean correctDate = false;
        Boolean correctBegin = false;
        Boolean correctEnd = false;
        String page = "portal?command=showMap";

        List<Category> listCategory = null;
        Boolean haveCategory = false;
        PlaceDescriptionService placeSesc = new PlaceDescriptionService();
        String beginTrip = request.getParameter("startDate");
        String endTrip = request.getParameter("endDate");
        String dontKnowDate = request.getParameter("dontKnowDate");
        String automatic = request.getParameter("automatic");
        String architecture = request.getParameter("architecture");
        String churches = request.getParameter("churches");
        String theatres = request.getParameter("theatres");
        String placeArrive = request.getParameter("txtSearch");
        String timePerDay = request.getParameter("dayTime");
        //String name = request.getParameter("name");
        userDataTrip.getSortFlag().put(1,true);
        placeArrive = Decoder.decodeStringUtf8(placeArrive);
        System.out.println("placeArrive " + placeArrive);


        if (dontKnowDate == null) {
            if (beginTrip != null && !beginTrip.equalsIgnoreCase("")) {
                try {
                    begin = format.parse(beginTrip);
                    DateTime start = new DateTime(new Date());
                    DateTime ending = new DateTime(begin);
                    Days d = Days.daysBetween(start, ending);
                    daysBegin = d.getDays();
                    if (daysBegin >= 0) {
                        java.sql.Date sqltDate = new java.sql.Date(begin.getTime());
                        userDataTrip.setBeginTrip(sqltDate);
                        correctBegin = true;

                    } else {
                        loger.info("Begindata is less than now" + beginTrip);
                    }
                } catch (ParseException e) {
                    loger.error("Error with parse begindata " + beginTrip);
                    e.printStackTrace();

                }
            }

            if (endTrip != null && !endTrip.equalsIgnoreCase("")) {
                try {
                    end = format.parse(endTrip);
                    DateTime start = new DateTime(begin);
                    DateTime ending = new DateTime(end);
                    Days d = Days.daysBetween(start, ending);
                    int days = d.getDays();
                    if (days >= 0 && daysBegin >= 0) {
                        java.sql.Date sqltDate = new java.sql.Date(end.getTime());
                        userDataTrip.setEndTrip(sqltDate);
                        correctEnd = true;
                    } else {
                        loger.info("Enddata is less than now" + endTrip);
                    }
                } catch (ParseException e) {
                    loger.info("Error with parse endTrip " + endTrip);
                    e.printStackTrace();

                }
            }

            if (correctBegin && correctEnd) {
                correctDate = true;
            }

            if (userDataTrip.getBeginTrip() != null && userDataTrip.getEndTrip() != null) {

                DateTime start = new DateTime(begin);
                DateTime ending = new DateTime(end);
                Days d = Days.daysBetween(start, ending);
                int days = d.getDays() + 1;
                userDataTrip.setDayCount(days);
                Map<Integer, List<Place>> places = new HashMap<>();
                for (int i = 1; i <= days; i++) {
                    places.put(i, new ArrayList<Place>());
                }
                userDataTrip.setPlaceDay(places);
            }
        } else {
            userDataTrip.setDontKnowDate(true);
        }

        if (placeArrive != null) {
            if (placeArrive.equalsIgnoreCase("withoutPlaceArrive")) {
                userDataTrip.setWithOutBegin(true);
            } else {
                placeArrive = placeArrive.trim();
                placeArrive = placeArrive.toLowerCase();
                if (!placeArrive.equals("")) {
                    List<PlaceDescription> plDesc = placeSesc.getAllPlaceBySearch(placeArrive);
                    PlaceService placeService = new PlaceService();
                    if (plDesc != null && !plDesc.isEmpty()) {
                        PlaceDescription place = plDesc.iterator().next();
                        Integer place_id = place.getPlace_id();
                        Place firstPlace = placeService.getByPK(place_id);
                        if (place_id != null && place_id > 0) {
                            userDataTrip.setBeginPlace(place_id);
                            Map<Integer, List<Place>> map = new HashMap<>();
                            List<Place> list = new ArrayList<>();
                            list.add(firstPlace);
                            map.put(1, list);
                            userDataTrip.setPlaceDay(map);
                            // userDataTrip.getPlaceDay().get(1).set(0,firstPlace);
                        }
                    }
                }
            }
        } else {
            userDataTrip.setWithOutBegin(true);
        }

        if (automatic != null) {
            userDataTrip.setIsAutomatic(true);
            userDataTrip.setTimePerDay(Double.parseDouble(timePerDay));

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
                Map<Integer, List<Place>> placesByTimeAndCategory = getPlacesByTimeAndCategory(listCategory, Double.valueOf(timePerDay));
                userDataTrip.setPlaceDay(placesByTimeAndCategory);
                userDataTrip.setIsSaved(false);
                userDataTrip.setIsFull(true);
            }

        } else {
            userDataTrip.setIsAutomatic(false);
        }

        HttpSession session = request.getSession();
        session.setAttribute("userDataTrip", userDataTrip);
        System.out.println("userDataTrip " + userDataTrip);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(correctDate));

        response.sendRedirect("/portal?command=showMap");
    }

    public Map<Integer, List<Place>> getPlacesByTimeAndCategory(List<Category> listCategory, Double time) {
        List<Place> places = new ArrayList<>();
        List<Place> result = new ArrayList<>();
        PlaceService placeService = new PlaceService();
        time = time * 3600; // перевести час в секунди
        for (int i = 0; i < listCategory.size(); i++) {
            places.addAll(placeService.getPlaceByCategory(listCategory.get(i).getId()));
        }
        places.sort(new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return o2.getRating() - o1.getRating();
            }
        });
        result.add(places.get(0));
        places.remove(0);
        Integer tempTime = places.get(0).getRecom_time()*60;
        int j = 0;
        Distance distance = new Distance();
        while (true) {
            if (places.size()-1 == j) {
                break;
            }
            String o1 = "" + result.get(j).getLatitude() + " " + result.get(j).getLongitude() + "";
            String o2 = "" + places.get(0).getLatitude() + " " + places.get(0).getLongitude() + "";
            Double t = 0.0;
            try {
                t = distance.getDistanceAndTime(o1, o2).get("time");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            if (((time - tempTime) > 900) && (time - (tempTime + (places.get(j).getRecom_time()*60) + t) < 0)) {
                places.remove(0);
                continue;
            }
            if ((time - tempTime) < 900) {
                break;
            }
            result.add(places.get(j));
            places.remove(0);
            tempTime = tempTime + (places.get(j).getRecom_time()*60) + t.intValue();
            j++;
        }

        Map<Integer, List<Place>> map = new HashMap<>();
        map.put(1, result);
        return map;
    }


}
