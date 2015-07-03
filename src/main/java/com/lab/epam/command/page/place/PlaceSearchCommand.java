package com.lab.epam.command.page.place;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Decoder;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.service.PlaceDescriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 02.07.2015.
 */
public class PlaceSearchCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();

        HttpSession session = request.getSession();

        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();
        String searchResult = "";

        String searchString = request.getParameter("search");
        searchString = Decoder.decodeStringUtf8(searchString);
     //   System.out.println("searchString " + searchString);
        List<PlaceDescription> placeDescriptions = new ArrayList<>();
        Map<Integer, String> results = new HashMap<>();
        Integer countPlace = 0;

        if (searchString != null) {
            searchString = searchString.toLowerCase();
            String[] searchParth = searchString.split(" ");
            placeDescriptions = placeDescriptionService.getPlaceByLanguege(language);//getAllPlaceBySearch(searchString);
            if (placeDescriptions != null && !placeDescriptions.isEmpty()) {
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
                            searchResult += place.getName() + "\n";
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
                        //System.out.println("Ololol");
                        name = name.toLowerCase();
                        fail = distanse(name, searchString);
                        if( fail <= 3){
                            if (results.get(fail) != null && !results.get(fail).equals("")){
                                String str = results.get(fail);
                                String[] strArray = str.split("\n");
                                if (strArray.length < 10){
                                    str += place.getName() + "\n";
                                }
                                results.put(fail, str);
                            }else{
                                String str = place.getName() + "\n";
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

//        Integer dis = s2.length() - s1.length();
//        int[] D1;
//        int[] D2 = new int[n + 1];
//
//        for(int i = 0; i <= n; i ++)
//            D2[i] = i;
//
//        for(int i = 1; i <= m; i ++) {
//            D1 = D2;
//            D2 = new int[n + 1];
//            for(int j = 0; j <= n; j ++) {
//                if(j == 0) D2[j] = i;
//                else {
//                    int cost = (s1.charAt(i - 1) != s2.charAt(j - 1)) ? 1 : 0;
//                    if(D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
//                        D2[j] = D2[j - 1] + 1;
//                    else if(D1[j] < D1[j - 1] + cost)
//                        D2[j] = D1[j] + 1;
//                    else
//                        D2[j] = D1[j - 1] + cost;
//                }
//            }
//        }
        return distance;
    }
}
