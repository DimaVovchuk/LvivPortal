package com.lab.epam.command.delete;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceImageService;
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
 * Created by Vasyl on 29.06.2015.
 */
public class DeleteImageCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command DeleteImage.");
        HttpSession session = request.getSession();
        String deleteImageID = request.getParameter("deleteImageID");
        Integer editPlaceIDSring = (Integer) session.getAttribute("editPlaceID");

        if (deleteImageID != null) {
            Integer ID = Integer.valueOf(deleteImageID);
            PlaceImageService placeImageService = new PlaceImageService();
            PlaceImage placeImage = placeImageService.getByPK(ID);
            placeImage.setDeleted(true);
            placeImageService.update(placeImage);
            loger.info("Photo it deleted");
        }

        //get all place image
        if(editPlaceIDSring !=null) {
            Integer editPlaceID = Integer.valueOf(editPlaceIDSring);
            PlaceImageService placeImageService = new PlaceImageService();
            List<PlaceImage> placeImageList = placeImageService.getAllPlaceImageByPlaceId(editPlaceID);
            List<PlaceImage> referenceList = new ArrayList<>();
            if (placeImageList != null) {
                for (int index = 0; index < placeImageList.size(); index++) {
                    String reference = placeImageList.get(index).getReference();
                    if (isInFolder(reference, request) && placeImageList.get(index).getDeleted() == false)
                        referenceList.add(placeImageList.get(index));
                }
                loger.info("All not deleted photo is successful getting.");
            } else {
                PlaceImage placeImage = new PlaceImage(editPlaceID, "default_building.jpg");
                referenceList.add(placeImage);
            }
            session.setAttribute("placeImageList", referenceList);
        }
        loger.info("Method execute in DeleteImageCommand is ended");
        response.sendRedirect("/portal?command=editPlace");
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
