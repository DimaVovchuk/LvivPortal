package com.lab.epam.command.save;

import com.lab.epam.command.controller.Command;
import com.lab.epam.entity.Decoder;
import com.lab.epam.entity.Place;
import com.lab.epam.entity.PlaceDescription;
import com.lab.epam.entity.PlaceImage;
import com.lab.epam.helper.ClassName;
import com.lab.epam.service.PlaceDescriptionService;
import com.lab.epam.service.PlaceImageService;
import com.lab.epam.service.PlaceService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vasyl on 22.06.2015.
 */
public class SaveEditPlaceCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String CHECK_DATA = "^[^<>/{}]+$";
//    private static final String CHECK_PHONE = "([0-9]{6,15})";
    private static final String CHECK_PLACE_TIME = "([0-9]*)";
    private static final String CHECK_COORDINATE = "([0-9]+([.][0-9]+))";
    private static Integer savePlaceID = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command SaveNewPlace.");
        HttpSession session = request.getSession();
        savePlaceID = (Integer) session.getAttribute("editPlaceID");

        List files = new ArrayList();
        Map<String, String> params = new HashMap<String, String>();
        PlaceService placeService = new PlaceService();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        Boolean errorFlag = false;

        try {
            init(request, params, files);
        } catch (FileUploadException e) {
            loger.warn(e.getMessage());
        }


        String newPlaceRating = params.get("newPlaceRating");
        String newPlaceNameUA = params.get("placeNameUA");
        String newPlaceNameEN = params.get("placeNameEN");
        String newPlaceDescriptionUA = params.get("placeDescriptionUA");
        String newPlaceDescriptionEN = params.get("placeDescriptionEN");
        String newPlacePriceUA = params.get("placePriceUA");
        String newPlacePriceEN = params.get("placePriceEN");
        String newPlacePhone = params.get("placePhone");
        String newPlaceAddressUA = params.get("editPlaceAddressUA");
        String newPlaceAddressEN = params.get("editPlaceAddressEN");
        String newPlaceLatitude = params.get("placeLatitude");
        String newPlaceLongitude = params.get("placeLongitude");
        String newPlaceTime = params.get("place_time");
        String newCategory = params.get("newCategory");
        String newVisible = params.get("newVisible");
        String newState = params.get("newState");
        String customIDSring = (String) session.getAttribute("customIDSring");
        String recomendetIDSring = (String) session.getAttribute("recomendetIDSring");
        Integer checkCustomEditID = (Integer) session.getAttribute("checkCustomEditID");

        loger.info("All data waas succesful getting");

//check input data
        if (checkData(newPlaceNameUA, CHECK_DATA) && newPlaceNameUA == "") {
            session.setAttribute("nameUAError", 1);
            errorFlag = true;
            loger.warn("NameUA is pattern error");
        }

        if (checkData(newPlaceNameEN, CHECK_DATA) && newPlaceNameEN == "") {
            session.setAttribute("nameENError", 1);
            errorFlag = true;
            loger.warn("NameEN is pattern error");
        }

        if (checkData(newPlaceDescriptionUA, CHECK_DATA) && newPlaceDescriptionUA == "") {
            session.setAttribute("descriptionUArror", 1);
            errorFlag = true;
            loger.warn("DescriptionUA is pattern error");
        }


        if (checkData(newPlaceDescriptionEN, CHECK_DATA) && newPlaceDescriptionEN == "") {
            session.setAttribute("descriptionENError", 1);
            errorFlag = true;
            loger.warn("DescriptionEN is pattern error");
        }

        if (checkData(newPlaceAddressUA, CHECK_DATA) && newPlaceAddressUA == "") {
            session.setAttribute("placeAddressUAError", 1);
            errorFlag = true;
            loger.warn("PlaceAddressUA is pattern error");
        }

        if (checkData(newPlaceAddressEN, CHECK_DATA) && newPlaceAddressEN == "") {
            session.setAttribute("placeAddressENError", 1);
            errorFlag = true;
            loger.warn("PlaceAddressEN is pattern error");
        }

        if (checkData(newPlacePriceUA, CHECK_DATA) && newPlacePriceUA == "") {
            session.setAttribute("PlacePriceUAError", 1);
            errorFlag = true;
            loger.warn("PlacePriceUA is pattern error");
        }

        if (checkData(newPlacePriceEN, CHECK_DATA) && newPlacePriceEN == "") {
            session.setAttribute("PlacePriceENError", 1);
            errorFlag = true;
            loger.warn("PlacePriceEN is pattern error");
        }

//        if (checkData(newPlacePhone, CHECK_PHONE) && newPlacePhone == "") {
//            session.setAttribute("PlacePhoneError", 1);
//            errorFlag = true;
//            loger.warn("PlacePhone is pattern error");
//        }

        if (checkData(newPlaceTime, CHECK_PLACE_TIME)) {
            session.setAttribute("PlaceTimeError", 1);
            errorFlag = true;
            loger.warn("PlaceTime is pattern error");
        }

        if (checkData(newPlaceLatitude, CHECK_COORDINATE)) {
            session.setAttribute("PlaceLatitudeError", 1);
            errorFlag = true;
            loger.warn("PlaceLatitude is pattern error");
        }

        if (checkData(newPlaceLongitude, CHECK_COORDINATE)) {
            session.setAttribute("PlacelongitudeError", 1);
            errorFlag = true;
            loger.warn("Placelongitude is pattern error");
        }

        if (errorFlag) {
            response.sendRedirect("/portal?command=editPlace&editPlaceID=" + savePlaceID);
        } else {
            Place place = placeService.getByPK(savePlaceID);
            place.setLatitude(newPlaceLatitude);
            place.setLongitude(newPlaceLongitude);
            place.setCategory_id(Integer.valueOf(newCategory));
            try{
                place.setRecom_time(Integer.valueOf(newPlaceTime));
            } catch (Exception e){
                place.setRecom_time(0);
                loger.warn(e.getMessage());
            }
            place.setRating(Integer.valueOf(newPlaceRating));
            if (newVisible == null) {
                newVisible = "false";
            } else {
                newVisible = "true";
            }
            place.setVisible(new Boolean(newVisible));
            place.setPlace_time(Integer.valueOf(newPlaceTime));

            if (newState == null) {
                newState = "true";
            } else {
                newState = "false";
            }

            if(recomendetIDSring !=null && recomendetIDSring!=""){
                place.setRecomended(true);
            }

            if(customIDSring !=null && customIDSring!=""){
                place.setRecomended(true);
            }

            place.setDeleted(new Boolean(newState));
            loger.info("Object place is created " + place);

            PlaceDescription placeDescriptionUA = placeDescriptionService.getPlaceDescriptionByIdPlace(savePlaceID, "UA");
            placeDescriptionUA.setName(Decoder.decodeStringUtf8(newPlaceNameUA));
            placeDescriptionUA.setdDescription(Decoder.decodeStringUtf8(newPlaceDescriptionUA));
            placeDescriptionUA.setPhone(newPlacePhone);
            placeDescriptionUA.setPrice(Decoder.decodeStringUtf8(newPlacePriceUA));
            placeDescriptionUA.setAdress(Decoder.decodeStringUtf8(newPlaceAddressUA));
            loger.info("placeDescriptionUA is  " + placeDescriptionUA);

            PlaceDescription placeDescriptionEN = placeDescriptionService.getPlaceDescriptionByIdPlace(savePlaceID, "EN");
            placeDescriptionEN.setName(Decoder.decodeStringUtf8(newPlaceNameEN));
            placeDescriptionEN.setdDescription(Decoder.decodeStringUtf8(newPlaceDescriptionEN));
            placeDescriptionEN.setPhone(newPlacePhone);
            placeDescriptionEN.setPrice(Decoder.decodeStringUtf8(newPlacePriceEN));
            placeDescriptionEN.setAdress(Decoder.decodeStringUtf8(newPlaceAddressEN));
            loger.info("placeDescriptionEN is  " + placeDescriptionEN);

            placeService.update(place);
            placeDescriptionService.update(placeDescriptionUA);
            placeDescriptionService.update(placeDescriptionEN);


            save(request, files, params);
            loger.info("Method SaveNewPlaceCommand.execute() ended.");

            List<PlaceDescription> placeDescriptionList = new ArrayList<>();
            placeDescriptionList.add(placeDescriptionUA);
            placeDescriptionList.add(placeDescriptionEN);

            //get all place image
            PlaceImageService placeImageService = new PlaceImageService();
            List<PlaceImage> placeImageList = placeImageService.getAllPlaceImageByPlaceId(savePlaceID);
            List<PlaceImage> referenceList = new ArrayList<>();
            if (placeImageList != null) {
                for (int index = 0; index < placeImageList.size(); index++) {
                    String reference = placeImageList.get(index).getReference();
                    if (isInFolder(reference, request))
                        referenceList.add(placeImageList.get(index));
                }
            } else {
                PlaceImage placeImage = new PlaceImage(savePlaceID, "default_building.jpg");
                referenceList.add(placeImage);
            }


            session.setAttribute("editPlace", place);
            session.setAttribute("placeImageList", referenceList);
            session.setAttribute("placeDescriptionList", placeDescriptionList);
            session.setAttribute("editPlacePhone", placeDescriptionEN.getPhone());

            if(checkCustomEditID!=null && checkCustomEditID !=0){
                session.setAttribute("checkCustomEditID",0);
                response.sendRedirect("/portal?command=placeInformation&place_id=" + savePlaceID);
            } else if(customIDSring !=null && customIDSring!=""){
                session.setAttribute("customIDSring","");
                response.sendRedirect("/portal?command=adminConfirmCustomPlace");
            }else if(recomendetIDSring !=null && recomendetIDSring!=""){
                session.setAttribute("recomendetIDSring","");
                response.sendRedirect("/portal?command=adminConfirmRecommendedPlace");
            } else {
                response.sendRedirect("/portal?command=editPlacesAdminPage");
            }
        }
    }

    private static void init(HttpServletRequest request, Map params, List files) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        File folder = new File("c:\\tmp");
        if (folder.exists()) {
        } else {
            folder.mkdir();
            loger.info("File tmp is created");
        }
        factory.setRepository(folder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        long imageSize = 2097152;
        upload.setSizeMax(imageSize);

        List items = upload.parseRequest(request);
        for (Iterator i = items.iterator(); i.hasNext(); ) {
            FileItem item = (FileItem) i.next();
            if (item.isFormField()) {
                params.put(item.getFieldName(), item.getString());
            } else {
                if (item.getSize() <= 0) continue;
                files.add(item);
            }
        }
    }

    private static void save(HttpServletRequest request, List files, Map params) throws IOException {
        try {
            for (Iterator i = files.iterator(); i.hasNext(); ) {
                FileItem item = (FileItem) i.next();
                String imageName = item.getName();
                HttpSession session = request.getSession();

                Pattern p = Pattern.compile("([^\\s]+(?=\\.(jpg|JPG|gif|png))\\.\\2)");
                Matcher m = p.matcher(imageName);
                boolean matches = !m.matches();

                if (!matches && savePlaceID != null) {
                    loger.info("Start uplad to place gallery");
                    PlaceImageService placeImageService = new PlaceImageService();
                    PlaceImage placeImage = new PlaceImage(savePlaceID, imageName);
                    placeImageService.create(placeImage);
                    loger.info("File is successfully uploaded in database to place image");

                    String realPath = request.getRealPath("/upload/photo/" + File.separator + imageName);

                    final File file = new File(realPath);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(item.get());
                    fos.close();
                } else {
                    session.setAttribute("imageError", 1);
                    loger.warn("Name is pattern error " + imageName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            loger.error(e.getMessage());
            throw e;
        }
        loger.info("save method ImageUploader ended");
    }

    private static boolean checkData(String testString, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        boolean matches = !m.matches();
        loger.info("Method checkData return " + matches);
        return matches;
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
