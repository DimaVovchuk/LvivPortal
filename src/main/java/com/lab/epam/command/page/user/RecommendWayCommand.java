package com.lab.epam.command.page.user;

import com.google.gson.Gson;
import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Way;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.WayService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 05.07.2015.
 */
public class RecommendWayCommand   implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());

    public void execute(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        WayService wayService = new WayService();
        String way_idString = request.getParameter("way_id");
        Integer way_id = null;
        Integer recommend_result = 0;
        if (way_idString != null){
            way_id = Integer.parseInt(way_idString);
        }

        if (way_id != null && way_id > 0){
            Way way = wayService.getByPK(way_id);
            if (!way.getRecomended()){
                wayService.setWayIsRecommended(way_id);
                recommend_result = 1;
            }
        }

        loger.info("Command RecommendWayCommand.");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(recommend_result));
    }
}

