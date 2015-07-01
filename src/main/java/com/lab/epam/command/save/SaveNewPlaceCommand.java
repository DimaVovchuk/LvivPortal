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
 * Created by Vasyl on 25.06.2015.
 */
public class SaveNewPlaceCommand implements Command {
    private static final Logger loger = LogManager.getLogger(ClassName.getCurrentClassName());
    private static final String CHECK_DATA = "^[^<>/{}]+$";
    private static final String CHECK_PHONE = "([0-9]{6,15})";
    private static final String CHECK_PLACE_TIME = "([0-9])";
    private static final String CHECK_COORDINATE = "([0-9]+([.][0-9]+))";
    private static Integer lastAddedPlace = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loger.info("Command SaveNewPlace.");
        HttpSession session = request.getSession();
        List files = new ArrayList();
        Map<String, String> params = new HashMap<String, String>();
        PlaceService placeService = new PlaceService();
        PlaceDescriptionService placeDescriptionService = new PlaceDescriptionService();
        Boolean errorFlag = false;

        try {
            init(request, params, files);
            response.setContentType("text/html; charset=windows-1251");
        } catch (FileUploadException e) {
            loger.warn(e.getMessage());
        }

        String addPlaceNameUA = params.get("addPlaceNameUA");
        String addPlaceNameEN = params.get("addPlaceNameEN");
        String addPlaceDescriptionUA = params.get("addPlaceDescriptionUA");
        String addPlaceDescriptionEN = params.get("addPlaceDescriptionEN");
        String addPlaceAddressUA = params.get("addPlaceAddressUA");
        String addPlaceAddressEN = params.get("addPlaceAddressEN");
        Integer addCategoryID = Integer.valueOf(params.get("newCategory"));
        String addPlaceTime = params.get("addPlaceTime");
        String addPlacePriceUA = params.get("addPlacePriceUA");
        String addPlacePriceEN = params.get("addPlacePriceEN");
        String addPlacePhone = params.get("addPlacePhone");
        String addPlaceLatitude = params.get("addPlaceLatitude");
        String addPlacelongitude = params.get("addPlacelongitude");
        loger.info("All data waas succesful getting");


        //check input data
        if (checkData(addPlaceNameUA, CHECK_DATA) && addPlaceNameUA == "") {
            session.setAttribute("nameUAError", 1);
            errorFlag = true;
            loger.warn("NameUA is pattern error");
        }

        if (checkData(addPlaceNameEN, CHECK_DATA) && addPlaceNameEN == "") {
            session.setAttribute("nameENError", 1);
            errorFlag = true;
            loger.warn("NameEN is pattern error");
        }

        if (checkData(addPlaceDescriptionUA, CHECK_DATA) && addPlaceDescriptionUA == "") {
            session.setAttribute("descriptionUArror", 1);
            errorFlag = true;
            loger.warn("DescriptionUA is pattern error");
        }


        if (checkData(addPlaceDescriptionEN, CHECK_DATA) && addPlaceDescriptionEN == "") {
            session.setAttribute("descriptionENError", 1);
            errorFlag = true;
            loger.warn("DescriptionEN is pattern error");
        }

        if (checkData(addPlacePriceEN, CHECK_DATA) && addPlacePriceEN == "") {
            session.setAttribute("placePriceENError", 1);
            errorFlag = true;
            loger.warn("PlacePriceEN is pattern error");
        }

        if (checkData(addPlacePriceUA, CHECK_DATA) && addPlacePriceUA == "") {
            session.setAttribute("PlacePriceUAError", 1);
            errorFlag = true;
            loger.warn("PlacePriceUA is pattern error");
        }

        if (checkData(addPlacePriceEN, CHECK_DATA) && addPlacePriceEN == "") {
            session.setAttribute("PlacePriceENError", 1);
            errorFlag = true;
            loger.warn("PlacePriceEN is pattern error");
        }

        if (checkData(addPlacePhone, CHECK_PHONE) && addPlacePhone == "") {
            session.setAttribute("PlacePhoneError", 1);
            errorFlag = true;
            loger.warn("PlacePhone is pattern error");
        }

        if (checkData(addPlaceTime, CHECK_PLACE_TIME) && addPlaceTime == "") {
            session.setAttribute("PlaceTimeError", 1);
            errorFlag = true;
            loger.warn("PlaceTime is pattern error");
        }

        if (checkData(addPlaceLatitude, CHECK_COORDINATE)) {
            session.setAttribute("PlaceLatitudeError", 1);
            errorFlag = true;
            loger.warn("PlaceLatitude is pattern error");
        }

        if (checkData(addPlacelongitude, CHECK_COORDINATE)) {
            session.setAttribute("PlacelongitudeError", 1);
            errorFlag = true;
            loger.warn("Placelongitude is pattern error");
        }

        if (errorFlag) {
        } else {
            Place place = new Place();
            place.setLatitude(addPlaceLatitude);
            place.setLongitude(addPlacelongitude);
            place.setCategory_id(addCategoryID);
            place.setRating(0);
            place.setVisible(true);
            place.setPlace_time(Integer.valueOf(addPlaceTime));
            place.setDeleted(false);
            place.setRecomended(false);
            place.setCustom(false);
            loger.info("Object place is created " + place);

            lastAddedPlace = placeService.createAndReturnIndex(place);
            loger.info("lastAddedPlace is  " + lastAddedPlace);
            PlaceDescription placeDescriptionUA = new PlaceDescription(new PlaceDescription.Builder(lastAddedPlace, "UA",
                    Decoder.decodeStringUtf8(addPlaceNameUA), Decoder.decodeStringUtf8(addPlaceDescriptionUA), Decoder.decodeStringUtf8(addPlaceAddressUA)));
            placeDescriptionUA.setPhone(Decoder.decodeStringUtf8(addPlacePhone));
            placeDescriptionUA.setPrice(Decoder.decodeStringUtf8(addPlacePriceUA));
            loger.info("placeDescriptionUA is  " + placeDescriptionUA);
            PlaceDescription placeDescriptionEN = new PlaceDescription(new PlaceDescription.Builder(lastAddedPlace, "EN",
                    Decoder.decodeStringUtf8(addPlaceNameEN), Decoder.decodeStringUtf8(addPlaceDescriptionEN),Decoder.decodeStringUtf8(addPlaceAddressEN)));
            placeDescriptionEN.setPhone(Decoder.decodeStringUtf8(addPlacePhone));
            placeDescriptionEN.setPrice(Decoder.decodeStringUtf8(addPlacePriceEN));
            loger.info("placeDescriptionEN is  " + placeDescriptionEN);

            placeDescriptionService.create(placeDescriptionUA);
            loger.info("placeDescriptionUA is succesful added");
            placeDescriptionService.create(placeDescriptionEN);
            loger.info("placeDescriptionEN is succesful added");

            save(request, files, params);
            loger.info("Method SaveNewPlaceCommand.execute() ended.");
            response.sendRedirect("/portal?command=placeInformation&place_id=" + lastAddedPlace);
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

                if (!matches && lastAddedPlace != null) {
                    loger.info("Start uplad to place gallery");
                    PlaceImageService placeImageService = new PlaceImageService();
                    PlaceImage placeImage = new PlaceImage(lastAddedPlace, imageName);
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
}
