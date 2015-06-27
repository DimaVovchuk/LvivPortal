//package com.lab.epam.command.page.map;
//
//import com.google.gson.Gson;
//import com.lab.epam.command.controller.Command;
//import com.lab.epam.entity.*;
//import com.lab.epam.service.PlaceDescriptionService;
//import com.lab.epam.service.PlaceImageService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.*;
//
//public class RoutesJSONCommand implements Command {
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
//        Locale locale = resourceBandle.getLocale();
//        String language = locale.getLanguage();
//
//        UserDataAboutTrip userDataTrip = (UserDataAboutTrip) session.getAttribute("userDataTrip");
//        List<RouteOneDayPlacesInfo> routeDayPlacesInfo = new ArrayList<>();
//        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
//        PlaceImageService placeImageService = new PlaceImageService();
//
//        if (userDataTrip != null) {
//            List<List<Place>> routeDayPlaces = new ArrayList<>(userDataTrip.getPlaceDay().values());
//            int i = 0;
//            for (List<Place> places: routeDayPlaces) {
//                i++;
//                RouteOneDayPlacesInfo routeOneDayPlacesInfo = new RouteOneDayPlacesInfo(i);
//                for (Place place: places) {
//                    PlaceDescriptionAndPhoto routeOneDayOnePlaceInfo = new PlaceDescriptionAndPhoto();
//                    routeOneDayOnePlaceInfo.setId(place.getId());
//                    routeOneDayOnePlaceInfo.setImageReference(placeImageService.getPlaceImageByPlaceId(place.getId()).getReference());
//                    routeOneDayOnePlaceInfo.setName(placeDescriptionService.getPlaceDescriptionByIdPlace(place.getId(), language).getName());
//                    routeOneDayOnePlaceInfo.setAdress(place.getAdress());
//                    routeOneDayPlacesInfo.getPlaces().add(routeOneDayOnePlaceInfo);
//                    routeOneDayPlacesInfo.setTotalMinutes(routeOneDayPlacesInfo.getTotalMinutes() + place.getPlace_time());
//                }
//                routeDayPlacesInfo.add(routeOneDayPlacesInfo);
//            }
//        }
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(new Gson().toJson(routeDayPlacesInfo));
//    }
//
//    private class RouteOneDayPlacesInfo {
//        private Integer dayNumber;
//        private Integer totalMinutes;
//        private Integer hours;
//        private Integer minutes;
//        private List<PlaceDescriptionAndPhoto> places;
//
//        public RouteOneDayPlacesInfo(Integer dayNumber) {
//            this.dayNumber = dayNumber;
//            this.totalMinutes = 0;
//            places = new ArrayList<>();
//        }
//
//        public List<PlaceDescriptionAndPhoto> getPlaces() {
//            return places;
//        }
//
//        public void setPlaces(List<PlaceDescriptionAndPhoto> places) {
//            this.places = places;
//        }
//
//        public Integer getDayNumber() {
//            return dayNumber;
//        }
//
//        public void setDayNumber(Integer dayNumber) {
//            this.dayNumber = dayNumber;
//        }
//
//        public Integer getTotalMinutes() {
//            return totalMinutes;
//        }
//
//        public void setTotalMinutes(Integer totalMinutes) {
//            this.totalMinutes = totalMinutes;
//            this.minutes = this.totalMinutes % 60;
//            this.hours = (this.totalMinutes - this.minutes) / 60;
//        }
//
//        public Integer getHours() {
//            return hours;
//        }
//
//        public Integer getMinutes() {
//            return minutes;
//        }
//
//        public void setHours(Integer hours) {
//            this.hours = hours;
//        }
//
//        public void setMinutes(Integer minutes) {
//            this.minutes = minutes;
//        }
//    }
//
//}
