package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.*;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 02.07.2015.
 */
public class PlaceSearchCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        PlaceImageService placeImageService = new PlaceImageService();
        UserService userService = new UserService();
        PlaceService servicePlace = new PlaceService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();
        String searchResult = "";

        String login = (String) session.getAttribute("login");
        User user = null;
        if (login != null) {
            user = userService.getUserByLogin(login);
        }

        String searchString = request.getParameter("search");
        searchString = Decoder.decodeStringUtf8(searchString);
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        Map<Integer, String> results = new HashMap<>();
        Integer countPlace = 0;

        if (searchString != null && !searchString.equals("")) {
            searchString = searchString.toLowerCase();
            String[] searchParth = searchString.split(" ");
            placeDescriptions = placeDescriptionService.getPlaceByLanguege(language);//getAllPlaceBySearch(searchString);

            if (placeDescriptions != null && !placeDescriptions.isEmpty()) {

                if(user !=null){
                    List<Place> custom = servicePlace.getAllVisbleUserCustomPlace(user.getId());
                    if(custom != null && !custom.isEmpty()){
                        for (Place plCustom: custom){
                            PlaceDescription plDCustom = placeDescriptionService.getPlaceDescriptionByIdPlace(plCustom.getId(),language);
                            if (plDCustom != null){
                                placeDescriptions.add(plDCustom);
                            }
                        }
                    }
                }

                for (PlaceDescription place : placeDescriptions) {
                    String name = place.getName().replaceAll("\"", "");
                    name = name.toLowerCase();
                    String[] nameParth = name.split(" ");
                    Integer count = 0;
                    for (String parth: nameParth) {
                        for (String res: searchParth) {
                            if (parth.contains(res)) {
                              count++;
                            }
                        }
                    }
                    if (count >= searchParth.length){
                        if (countPlace < 10) {
                            PlaceImage plIm = placeImageService.getPlaceImageByPlaceId(place.getPlace_id());
                            if (plIm == null || !isInFolder(plIm.getReference(), request)){
                                plIm = new PlaceImage(place.getId(), "default_building.jpg");
                            }
                            searchResult += place.getName() + "*" + plIm.getReference() + "\n";
                            countPlace++;
                        }
                    }

                }
                if (searchResult.isEmpty()) {
                    Integer fail = 0;
                    for (int i = 1; i <= 3; i++) {
                        results.put(i, new String(""));
                    }
                    for (PlaceDescription place : placeDescriptions) {
                        String name = place.getName().replaceAll("\"", "");
                        name = name.toLowerCase();
                        fail = distanse(name, searchString);
                        if( fail <= 3){
                            if (results.get(fail) != null && !results.get(fail).equals("")){
                                String str = results.get(fail);
                                String[] strArray = str.split("\n");
                                if (strArray.length < 10){
                                    PlaceImage plIm = placeImageService.getPlaceImageByPlaceId(place.getPlace_id());
                                    if (plIm == null || !isInFolder(plIm.getReference(), request)){
                                        plIm = new PlaceImage(place.getId(), "default_building.jpg");
                                    }
                                    str += place.getName() + "*" + plIm.getReference() + "\n";
                                    //str += place.getName() + "\n";
                                }
                                results.put(fail, str);
                            }else{
                                PlaceImage plIm = placeImageService.getPlaceImageByPlaceId(place.getPlace_id());
                                if (plIm == null || !isInFolder(plIm.getReference(), request)){
                                    plIm = new PlaceImage(place.getId(), "default_building.jpg");
                                }
                                //str += place.getName() + " " + plIm.getReference() + "\n";
                                String str = place.getName() + "*" + plIm.getReference() + "\n";//place.getName() + "\n";
                                results.put(fail, str);
                            }
                        }
                    }
                    if (!results.isEmpty()){
                        for (int i = 1; i <= 15; i++){
                            String str = results.get(i);
                            if (str != null && !str.equals("")){
                                searchResult = str;
                                break;
                            }
                        }
                    }

                }

            }

        }
        response.setContentType("application/txt");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(searchResult);
    }

    private Integer distanse(String s1, String s2){
        Integer m = s1.length();
        Integer n = s2.length();
        char[] charS2 = s2.toCharArray();
        Integer count = 0;
        if (charS2.length >= 2){
            for (int i = 0; i < charS2.length - 1; i++){
                String s = new StringBuilder().append(charS2[i]).append(charS2[i + 1]).toString();;
                if (s1.contains(s)){
                    count++;
                }
            }
        }
        Integer distance = n - count;
        return distance;
    }

    private Boolean isInFolder(String fileName, HttpServletRequest request) {
        ClassLoader classLoader = getClass().getClassLoader();
        String realPath = request.getRealPath("/upload/photo/");
        File f = new File(realPath);
        String[] list = f.list();
        for (String file : list) {
            if (fileName.equals(file)) {
                return true;
            }
        }
        return false;
    }

}
