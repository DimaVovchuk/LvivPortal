package com.lab.epam.command.page.user.admin;

import com.lab.epam.command.controller.Command;
import com.lab.epam.dao.PersistException;
import com.lab.epam.entity.*;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasyl on 21.06.2015.
 */
public class EditPlaceCommand implements Command{
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String editPlaceIDSring = request.getParameter("editPlaceID");
//        String editPlaceIDSring = "3";
        HttpSession session = request.getSession();
        loger.info("start EditPlaceCommand");

        if(editPlaceIDSring !=null){
            Integer editPlaceID = Integer.valueOf(editPlaceIDSring);
            PlaceService placeService = new PlaceService();
            loger.info("edit place id is " + editPlaceID);
            //get place
            Place place = placeService.getByPK(editPlaceID);
            session.setAttribute("editPlace", place);

            if(place.getVisible() == null){
                place.setVisible(true);
            }
            loger.info("edit place is: " + place.toString());

            //get all place image
            PlaceImageService placeImageService = new PlaceImageService();
            List<PlaceImage> placeImageList = placeImageService.getAllPlaceImageByPlaceId(editPlaceID);
            List<PlaceImage> referenceList =new ArrayList<>();
            for(int index = 0;index < placeImageList.size();index++){
                String reference = placeImageList.get(index).getReference();
                if(isInFolder(reference,request))
                referenceList.add(placeImageList.get(index));
            }
            session.setAttribute("placeImageList", referenceList);
            loger.info("edit place fotos are " + placeImageList.toString());

            //get place description
            PlaceDescriptionService  placeDescriptionService = new PlaceDescriptionService();
            List<PlaceDescription> placeDescriptionList = placeDescriptionService.getPlaceDescriptionByIdPlace(editPlaceID);
            session.setAttribute("placeDescriptionList", placeDescriptionList);
            session.setAttribute("editPlacePhone",placeDescriptionList.get(0).getPhone());
            loger.info("edit place description are " + placeDescriptionList);

            //get category name
            try {
                CategoryService categoryService = new CategoryService();
                Category category = categoryService.getByPK(place.getCategory_id());
                session.setAttribute("categoryName", category.getCategory());
                loger.info("edit place description are " + placeDescriptionList);
            } catch (PersistException e) {
                loger.warn(e.getMessage());
            }

            //get place rating
            PlaceRatingService placeRatingService = new PlaceRatingService();
            Integer placeRating = 0;
            PlaceRating placeRatingObj = placeRatingService.getByPK(editPlaceID);
            loger.info("placeRatingObj " + placeRatingObj);
            try {
                placeRating = placeRatingObj.getRating();
            }catch (Exception e){
              loger.warn(e.getMessage());
            }
            session.setAttribute("placeRating", placeRating);
            loger.info("edit place rating is " + placeRating);
        }
        response.setContentType("text/html; charset=windows-1251");
        request.getRequestDispatcher("/views/pages/edit_place.jsp").forward(request, response);
    }

    private Boolean isInFolder(String fileName, HttpServletRequest request) {
        ClassLoader classLoader = getClass().getClassLoader();
        String realPath = request.getRealPath("/upload/photo/");
        File f = new File(realPath);
        String[] list = f.list();
        System.out.println(list);
        for (String file : list) {
            if (fileName.equals(file)) {
                return true;
            }
        }
        return false;
    }
}
