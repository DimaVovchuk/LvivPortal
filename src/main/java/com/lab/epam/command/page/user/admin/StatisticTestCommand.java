package com.lab.epam.command.page.user.admin;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.FavoritePlacesByRating;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceService;
import com.lab.epam.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Created by Vasyl on 04.07.2015.
 */
public class StatisticTestCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command StatisticTestCommand");
        /*Statistic page*/
        HttpSession session = request.getSession();
        ResourceBundle resourceBandle = (ResourceBundle) session.getAttribute("bundle");
        Locale locale = resourceBandle.getLocale();
        String language = locale.getLanguage();

        /*Top 10 favorite places*/
        PlaceService placeService = new PlaceService();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        List<FavoritePlacesByRating> topPlaceRatingList = placeService.getPlacesByRating();
        Map<String,Integer> topPlacesMap = new LinkedHashMap<>();
        for(int index = 0; index < topPlaceRatingList.size();index++){
            if(index < 10) {
                topPlacesMap.put(placeDescriptionService.getPlaceDescriptionByIdPlace(topPlaceRatingList.get(index).getPlace_id(),language).getName().replaceAll("\"", "'"),topPlaceRatingList.get(index).getCount());
            }
        }
        request.setAttribute("topPlacesMap", topPlacesMap);

        /*Quantity users statistic*/
        UserService userService = new UserService();
        Integer allUserQuantity = userService.getQuantityOfAllUsers();
        Integer adminQuantity = userService.getQuantityUsersByRoleId(1);
        Integer userQuantity = userService.getQuantityUsersByRoleId(2);
        Integer guideQuantity = userService.getQuantityUsersByRoleId(3);
        Integer agencyQuantity = userService.getQuantityUsersByRoleId(4);

        DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(locale));
        List<String> usersQuantityList = new ArrayList<>();
        usersQuantityList.clear();
        usersQuantityList.add(df.format((Double.valueOf(adminQuantity) / allUserQuantity) * 100));
        usersQuantityList.add(df.format((Double.valueOf(userQuantity) / allUserQuantity) * 100));
        usersQuantityList.add(df.format((Double.valueOf(guideQuantity) / allUserQuantity) * 100));
        usersQuantityList.add(df.format((Double.valueOf(agencyQuantity) / allUserQuantity) * 100));
        request.setAttribute("usersMap", usersQuantityList);

        /*Statistic by users status*/
        Integer activedUsers = userService.getQuantityUsersByStatusId(1);
        Integer notActivatedUsers = userService.getQuantityUsersByStatusId(2);
        Integer bannedUsers = userService.getQuantityUsersByStatusId(3);

        List<String> usersQuantityStatusList = new ArrayList<>();
        usersQuantityStatusList.clear();
        usersQuantityStatusList.add(df.format((Double.valueOf(activedUsers) / allUserQuantity) * 100));
        usersQuantityStatusList.add(df.format((Double.valueOf(notActivatedUsers) / allUserQuantity) * 100));
        usersQuantityStatusList.add(df.format((Double.valueOf(bannedUsers) / allUserQuantity) * 100));
        request.setAttribute("statusList", usersQuantityStatusList);

        loger.info("Command StatisticTestCommand ended");
        request.getRequestDispatcher("/views/pages/statisticTest.jsp").forward(request, response);
    }
}
