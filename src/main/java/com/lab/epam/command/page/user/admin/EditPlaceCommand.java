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
        HttpSession session = request.getSession();
        loger.info("start EditPlaceCommand");

        if(editPlaceIDSring !=null){
            Integer editPlaceID = Integer.valueOf(editPlaceIDSring);
            PlaceService placeService = new PlaceService();
            loger.info("edit place id is " + editPlaceID);
            //get place
            Place place = placeService.getByPK(editPlaceID);
            session.setAttribute("editPlace", place);
            session.setAttribute("editPlaceID",editPlaceID);

            if(place.getVisible() == null){
                place.setVisible(true);
            }
            loger.info("edit place is: " + place.toString());

            //get all place image
            PlaceImageService placeImageService = new PlaceImageService();
            List<PlaceImage> placeImageList = placeImageService.getAllPlaceImageByPlaceId(editPlaceID);
            List<PlaceImage> referenceList =new ArrayList<>();
            if(placeImageList != null) {
                for (int index = 0; index < placeImageList.size(); index++) {
                    String reference = placeImageList.get(index).getReference();
                    if (isInFolder(reference, request) && placeImageList.get(index).getDeleted()==false)
                        referenceList.add(placeImageList.get(index));
                }
            } else{
                PlaceImage placeImage = new PlaceImage(editPlaceID,"default_building.jpg");
                referenceList.add(placeImage);
            }
            session.setAttribute("placeImageList", referenceList);

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

        }
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
