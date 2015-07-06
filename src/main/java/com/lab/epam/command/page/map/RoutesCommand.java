package com.lab.epam.command.page.map;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.workWithMap.Distance;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dima on 19-Jun-15.
 */
public class RoutesCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDataAboutTrip userDataTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
        ResourceBundle bundle = (ResourceBundle) session.getAttribute("bundle");
        if (userDataTrip != null) {
            Map<Integer, List<Place>> placeDay = userDataTrip.getPlaceDay();
            Distance distance = new Distance();
            List<RouteOneDayPlacesInfo> routeDayPlacesInfo = new ArrayList<>();
            for (int j = 1; j <= placeDay.size(); j++) {
                if (placeDay.get(j).isEmpty()) {
                    continue;
                }
                List<Place> places = new ArrayList<>();
                List<Map<String, Double>> distAndTime = new ArrayList<>();
                RouteOneDayPlacesInfo routeOneDayInfo = new RouteOneDayPlacesInfo(j);
                places = placeDay.get(j);
                if (userDataTrip.getSortFlag().get(j)) {
                    String obj1 = "" + places.get(0).getLatitude() + " " + places.get(0).getLongitude() + "";
                    for (int i = 1; i < places.size(); i++) {
                        String obj2 = "" + places.get(i).getLatitude() + " " + places.get(i).getLongitude() + "";
                        try {
                            Map<String, Double> distanceMap = distance.getDistanceAndTime(obj1, obj2);
                            if (distAndTime.contains(distanceMap)) {
                                distanceMap.put("distance", distanceMap.get("distance") + 0.123456789);
                            }
                            distAndTime.add(distanceMap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    userDataTrip.getPlaceDay().put(j, sortPlaces(places, distAndTime, bundle.getLocale()));
                    userDataTrip.getSortFlag().put(j, false);
                }
                PlaceImageService placeImageService = new PlaceImageService();
                List<PlaceMarkerWithPhoto> placeMarkerWithPhotos = new ArrayList<>();
                PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();

                for (Place place : userDataTrip.getPlaceDay().get(j)) {
                    PlaceDescription placeDescription = placeDescriptionService.getPlaceDescriptionByIdPlace(place.getId(), bundle.getLocale().toString());
                    PlaceImage placeImage = placeImageService.getPlaceImageByPlaceId(place.getId());
                    placeMarkerWithPhotos.add(new PlaceMarkerWithPhoto(place.getId(), placeDescription.getName(), place.getLatitude(), place.getLongitude(), placeImage.getReference(), placeDescription.getDescription()));
                }
                session.setAttribute("userDataTrip", userDataTrip);
                routeOneDayInfo.setPlaces(placeMarkerWithPhotos);
                if (places.size() > 1) {
                    for (int i = 0; i < places.size() - 1; i++) {
                        String o1 = "" + places.get(i).getLatitude() + " " + places.get(i).getLongitude() + "";
                        String o2 = "" + places.get(i + 1).getLatitude() + " " + places.get(i + 1).getLongitude() + "";
                        Double time = 0.0;
                        try {
                            time = distance.getDistanceAndTime(o1, o2).get("time");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (places.get(i).getPlace_time() == 0) {
                            routeOneDayInfo.setTotalMinutes(routeOneDayInfo.getTotalMinutes() + time.intValue() / 60 + places.get(i).getRecom_time());
                        } else {
                            routeOneDayInfo.setTotalMinutes(routeOneDayInfo.getTotalMinutes() + time.intValue() / 60 + places.get(i).getPlace_time());
                        }
                    }
                }
                if (places.get(places.size()-1).getPlace_time() == 0) {
                    routeOneDayInfo.setTotalMinutes(routeOneDayInfo.getTotalMinutes() + places.get(places.size()-1).getRecom_time());
                } else {
                    routeOneDayInfo.setTotalMinutes(routeOneDayInfo.getTotalMinutes() + places.get(places.size()-1).getPlace_time());
                }
                routeDayPlacesInfo.add(routeOneDayInfo);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(routeDayPlacesInfo));
            loger.info("Command RoutesCommand.");
        }
    }

    public List<Place> sortPlaces(List<Place> places, List<Map<String, Double>> dist, Locale language) {

        TreeMap<Double, Place> sorted = new TreeMap<>();
        sorted.put(0.0, places.get(0));
        int j = 1;
        for (Map<String, Double> stringDoubleMap : dist) {
            sorted.put(stringDoubleMap.get("distance"), places.get(j));
            j++;
        }
        Collection<Place> values = sorted.values();
        List<Place> list = new ArrayList<>(values);
        return list;
    }

    private class RouteOneDayPlacesInfo {
        private Integer dayNumber;
        private Integer totalMinutes;
        private Integer hours;
        private Integer minutes;
        private List<PlaceMarkerWithPhoto> places;

        public RouteOneDayPlacesInfo(Integer dayNumber) {
            this.dayNumber = dayNumber;
            this.totalMinutes = 0;
            places = new ArrayList<>();
        }

        public List<PlaceMarkerWithPhoto> getPlaces() {
            return places;
        }

        public void setPlaces(List<PlaceMarkerWithPhoto> places) {
            this.places = places;
        }

        public Integer getDayNumber() {
            return dayNumber;
        }

        public void setDayNumber(Integer dayNumber) {
            this.dayNumber = dayNumber;
        }

        public Integer getTotalMinutes() {
            return totalMinutes;
        }

        public void setTotalMinutes(Integer totalMinutes) {
            this.totalMinutes = totalMinutes;
            this.minutes = this.totalMinutes % 60;
            this.hours = (this.totalMinutes - this.minutes) / 60;
        }

        public Integer getHours() {
            return hours;
        }

        public Integer getMinutes() {
            return minutes;
        }

        public void setHours(Integer hours) {
            this.hours = hours;
        }

        public void setMinutes(Integer minutes) {
            this.minutes = minutes;
        }

        @Override
        public String toString() {
            return "RouteOneDayPlacesInfo{" +
                    "dayNumber=" + dayNumber +
                    ", totalMinutes=" + totalMinutes +
                    ", hours=" + hours +
                    ", minutes=" + minutes +
                    ", places=" + places +
                    '}';
        }
    }
}

